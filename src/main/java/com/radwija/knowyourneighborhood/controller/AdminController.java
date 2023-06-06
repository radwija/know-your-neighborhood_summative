package com.radwija.knowyourneighborhood.controller;

import com.radwija.knowyourneighborhood.model.Contact;
import com.radwija.knowyourneighborhood.model.Store;
import com.radwija.knowyourneighborhood.model.User;
import com.radwija.knowyourneighborhood.repository.ContactRepository;
import com.radwija.knowyourneighborhood.service.ContactService;
import com.radwija.knowyourneighborhood.service.StoreService;
import com.radwija.knowyourneighborhood.service.UserService;
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
    StoreService storeService;

    @Autowired
    private ContactService contactService;

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
        return "Store with id " + id + " has been deleted successfully!";
    }

    @DeleteMapping("/delete-store/{id}")
    public String deleteStore(@PathVariable Long id) {
        storeService.deleteStore(id);
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

    @PutMapping("/update-store/{id}")
    public Store updateUserStore(@PathVariable Long id, @RequestBody Store updatedStore) {
        return storeService.updateUserStore(id, updatedStore);
    }

    @GetMapping("/contacts")
    public List<Contact> showAllContacts() {
        return contactService.showAllContacts();
    }

    @DeleteMapping("/delete-contact/{id}")
    public String deleteContactById(@PathVariable Long id) {
        contactService.deleteContact(id);
        return "Contact deleted successfully";
    }
}
