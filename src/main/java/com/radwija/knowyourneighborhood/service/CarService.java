package com.radwija.knowyourneighborhood.service;

import com.radwija.knowyourneighborhood.model.Car;
import com.radwija.knowyourneighborhood.security.UserPrincipal;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CarService {
    Car saveCar(UserPrincipal userPrincipal, Car car);

    List<Car> searchCar(String by, String keyword, Integer year, Long min, Long max);

    Optional<Car> viewCarDetail(Long cId);

    List<Car> showAllCars();
}
