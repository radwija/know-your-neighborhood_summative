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
    public List<Store> searchStores(@RequestParam("by") String by,
                                  @RequestParam(value = "keyword", required = false) String keyword,
                                  @RequestParam(value = "year", required = false) Integer year,
                                  @RequestParam(value = "min", required = false) Long min,
                                  @RequestParam(value = "max", required = false) Long max) {
        List<Store> searchedStores = storeService.searchStore(by, keyword, year, min, max);

        System.out.println(searchedStores);
        return searchedStores;
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