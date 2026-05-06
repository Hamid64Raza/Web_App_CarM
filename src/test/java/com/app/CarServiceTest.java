package com.app;



import com.app.entity.cars.Car;
import com.app.repository.CarRepository;
import com.app.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarService carService;

    private Car testCar;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testCar = new Car();
        testCar.setId(1L);
    }

    @Test
    void saveCarDetails_shouldReturnSavedCar() {
        when(carRepository.save(testCar)).thenReturn(testCar);

        Car result = carService.saveCarDetails(testCar);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(carRepository, times(1)).save(testCar);
    }
}
