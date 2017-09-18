package com.example.demo;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.demo.JobCompletionNotificationListener;
import com.example.demo.vo.Car;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactroy;

	@Autowired
	private DataSource datasource;
	
		
	/**
	 * 
	 * @param listener
	 * @return
	 */
	@Bean
	public Job importCarJob( JobCompletionNotificationListener listener) {
		return jobBuilderFactory.get("importCarJob").incrementer(new RunIdIncrementer()).listener(listener)
				.flow(step1(stepBuilderFactroy)).end().build();
	}

	@Bean
	public Step step1(StepBuilderFactory stepBuilderFactroy) {
		return (Step) stepBuilderFactroy.get("step1").<Car, Car> chunk(10).reader(reader()).processor(processor())
				.writer(writer()).build();

	}

	@Bean
	public FlatFileItemReader<Car> reader() {
		FlatFileItemReader<Car> reader = new FlatFileItemReader<Car>();
		reader.setResource(new ClassPathResource("sample-data.csv"));

		reader.setLineMapper(new DefaultLineMapper<Car>() {
			{
				setLineTokenizer(new DelimitedLineTokenizer() {
					{
						setNames(new String[] { "licensePlate", "manufacturer", "manufactureDate" });
					}
				});
				setFieldSetMapper(new BeanWrapperFieldSetMapper<Car>() {
					{
						setTargetType(Car.class);
					}
				});
			}
		});
		return reader;
	}

	@Bean
	public CarProcessor processor() {
		return new CarProcessor();
	}

	@Bean
	public JdbcBatchItemWriter<Car> writer() {
		JdbcBatchItemWriter<Car> writer = new JdbcBatchItemWriter<Car>();
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Car>());
		writer.setSql(
				"INSERT INTO CAR (licensePlate, manufacturer,manufactureDate) VALUES (:licensePlate, :manufacturer,:manufactureDate)");
		writer.setDataSource(datasource);
		return writer;
	}

}
