package com.app.service;

import com.app.entity.cars.Car;
import com.app.repository.CarRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CarService {
    private CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public  Car saveCarDetails(Car car){
        log.debug("Saving car details: {}", car);
        return carRepository.save(car);
    }
}
