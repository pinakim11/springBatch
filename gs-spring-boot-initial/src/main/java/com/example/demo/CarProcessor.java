package com.example.demo;

import java.sql.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.example.demo.schedule.ScheduledTasks;
import com.example.demo.vo.Car;


public class CarProcessor implements ItemProcessor<Car, Car> {

	private static final Logger log = LoggerFactory.getLogger(CarProcessor.class);


	@Override
	public Car process(Car car) throws Exception {
		if(car != null){
			final String license = car.getLicensePlate().toUpperCase();
			final String manufacturer = car.getManufacturer().toUpperCase();
			final String manufactureDate = car.getManufactureDate();
			Car tranformedCar = new Car(license,manufacturer,manufactureDate);
			log.info("Transformed car", car.toString());
			return tranformedCar;
		}
		
		return null;
		
	}
}
