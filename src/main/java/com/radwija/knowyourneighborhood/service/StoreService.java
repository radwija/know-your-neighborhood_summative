package com.radwija.knowyourneighborhood.service;

import com.radwija.knowyourneighborhood.model.Store;
import com.radwija.knowyourneighborhood.security.UserPrincipal;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface StoreService {
    Store saveStore(UserPrincipal userPrincipal, Store store);

    ResponseEntity<Store> updateOwnStore(Long id, Store updatedStore, UserPrincipal userPrincipal);
    Store updateUserStore(Long id, Store updatedStore);

//    List<Store> searchStore(String by, String keyword, Integer year, Long min, Long max);

    Optional<Store> viewStoreDetail(Long cId);

    List<Store> showAllStores();

    void deleteStore(Long id);
}
