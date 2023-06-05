package com.radwija.knowyourneighborhood.controller;

import com.radwija.knowyourneighborhood.model.Store;
import com.radwija.knowyourneighborhood.repository.UserRepository;
import com.radwija.knowyourneighborhood.security.UserPrincipal;
import com.radwija.knowyourneighborhood.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Transactional
@RestController
@RequestMapping("/store/")
public class StoreController {
    @Autowired
    private StoreService storeService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("save-store")
    public Store saveStore(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody Store store) {
        return storeService.saveStore(userPrincipal, store);

    }


    @GetMapping("stores")
    public List<Store> showAllStores() {
        return storeService.showAllStores();
    }

    @GetMapping("search")
    public List<Store> searchStores(@RequestParam(value = "keyword") String keyword) {
        List<Store> searchedStores = storeService.searchStoreByName(keyword);
        return searchedStores;
    }

    @GetMapping("/owner")
    public List<Store> showStoreByOwner(@RequestParam("uId") Long uId) {
        return storeService.showStoreByOwner(uId);
    }

    @GetMapping("viewStore")
    public ResponseEntity<Store> storeDetail(@RequestParam Long cid) {
        Optional<Store> storeInfo = storeService.viewStoreDetail(cid);

        if (storeInfo.isEmpty()) {
            return null;
        } else {
            return ResponseEntity.ok(storeInfo.get());
        }
    }
}
