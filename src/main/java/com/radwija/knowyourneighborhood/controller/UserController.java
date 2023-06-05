package com.radwija.knowyourneighborhood.controller;

import com.radwija.knowyourneighborhood.exception.ResourceNotFoundException;
import com.radwija.knowyourneighborhood.model.Store;
import com.radwija.knowyourneighborhood.model.User;
import com.radwija.knowyourneighborhood.repository.UserRepository;
import com.radwija.knowyourneighborhood.security.CurrentUser;
import com.radwija.knowyourneighborhood.security.UserPrincipal;
import com.radwija.knowyourneighborhood.service.StoreService;
import com.radwija.knowyourneighborhood.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StoreService storeService;

    @Autowired
    private UserService userService;

    @GetMapping("/me")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<Optional<User>> viewUserDetail(@PathVariable Long id) {
        if (userService.userExists(id)) {
            return ResponseEntity.ok(userService.viewUserDetail(id));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("search")
    public List<User> searchUsers(@RequestParam(value = "keyword") String keyword) {
        List<User> searchedUsers = userService.searchUserByName(keyword);
        return searchedUsers;
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateProfile(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody User user) {
        User updatedUserRequest = userService.updateOwnProfile(userPrincipal, user);
        if (updatedUserRequest != null) {
            return ResponseEntity.ok(updatedUserRequest);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/update-store/{id}")
    public ResponseEntity<Store> updateOwnStore(@PathVariable Long id, @RequestBody Store updatedStore, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        System.out.println("controller/update-store/{" + id + "}");
        System.out.println("owner: " + userPrincipal.getId());
        return storeService.updateOwnStore(id, updatedStore, userPrincipal);
    }
}
