package com.app.controller;

import com.app.entity.cars.Car;
import com.app.service.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("/api/v1")
@Tag(name = "Car Save Controller", description = "This Controller is for save cars")
public class SaveCarController {
    private final CarService carService;

    @Autowired
    public SaveCarController(CarService carService) {
        this.carService = carService;
    }
 //http://localhost:8080/api/v1/save-car
    @Operation(summary = "Save cars", description = "Returns save cars")
    @PostMapping("/cars")
    public ResponseEntity<?> saveCarDetails(@RequestBody Car car) {
        log.info("Received request to save car: {}",car);
        try {
           Car savedRecord= carService.saveCarDetails(car);
           log.info("Car saved successfully with ID: {}", savedRecord.getId());
            return  new ResponseEntity<>(savedRecord, HttpStatus.CREATED);
        }catch (Exception e){
            log.error("Error saving car: {}",e.getMessage(),e);
            return new ResponseEntity<>("Operation failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
}
