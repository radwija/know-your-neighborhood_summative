package com.example.springsocial.service;

import com.example.springsocial.model.Car;
import com.example.springsocial.security.UserPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
