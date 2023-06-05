package com.radwija.knowyourneighborhood.controller;

import com.radwija.knowyourneighborhood.model.Car;
import com.radwija.knowyourneighborhood.model.User;
import com.radwija.knowyourneighborhood.service.CarService;
import com.radwija.knowyourneighborhood.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UserService userService;

    @Autowired
    CarService carService;

    @GetMapping("/test")
    public String checkAdminRole() {
        return "Admin role works";
    }

    @GetMapping("/users")
    public List<User> showAllUsers() {
        return userService.showAllUsers();
    }

    @DeleteMapping("/delete-user/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "Car with id " + id + " has been deleted successfully!";
    }

    @DeleteMapping("/delete-car/{id}")
    public String deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return "User with id " + id + " has been deleted successfully!";
    }

    @PutMapping("/update-user/{id}")
    public ResponseEntity<User> updateUserProfile(@PathVariable Long id, @RequestBody User updatedUser) {
        User updatedUserRequest = userService.updateUserProfile(id, updatedUser);
        if (updatedUserRequest != null) {
            return ResponseEntity.ok(updatedUserRequest);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/update-car/{id}")
    public Car updateUserCar(@PathVariable Long id, @RequestBody Car updatedCar) {
        return carService.updateUserCar(id, updatedCar);
    }
}
