package com.radwija.knowyourneighborhood.service.impl;

import com.radwija.knowyourneighborhood.model.Car;
import com.radwija.knowyourneighborhood.model.User;
import com.radwija.knowyourneighborhood.repository.CarRepository;
import com.radwija.knowyourneighborhood.repository.UserRepository;
import com.radwija.knowyourneighborhood.security.UserPrincipal;
import com.radwija.knowyourneighborhood.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Car> searchCar(String by, String keyword, Integer year, Long min, Long max) {
        if (by.equalsIgnoreCase("carName")) {
            return carRepository.searchCarByName(keyword);
        } else if (by.equalsIgnoreCase("makeYear")) {
            return carRepository.searchCarByMakeYear(year);
        } else if (by.equalsIgnoreCase("model")) {
            return carRepository.searchCarByModel(keyword);
        } else if (by.equalsIgnoreCase("price")) {
            if (min != null && max == null) {
                return carRepository.searchCarByMinPrice(min);
            } else if (min == null && max != null) {
                return carRepository.searchCarByMaxPrice(max);
            } else {
                return carRepository.searchCarByRangePrice(min, max);
            }
        } else {
            return null;
        }
    }

    @Override
    public List<Car> showAllCars() {
        return carRepository.findAll();
    }

    @Override
    public Optional<Car> viewCarDetail(Long cid) {
        return carRepository.findById(cid);
    }


    @Transactional
    @Override
    public Car saveCar(UserPrincipal userPrincipal, Car car) {
        User user = userRepository.findById(userPrincipal.getId()).orElse(null);
        if (user != null) {
            car.setUser(user);
            return carRepository.save(car);
        }
        return new Car();
    }
}
