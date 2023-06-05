package com.radwija.knowyourneighborhood.service.impl;

import com.radwija.knowyourneighborhood.exception.StoreNotFoundException;
import com.radwija.knowyourneighborhood.model.Store;
import com.radwija.knowyourneighborhood.model.User;
import com.radwija.knowyourneighborhood.repository.StoreRepository;
import com.radwija.knowyourneighborhood.repository.UserRepository;
import com.radwija.knowyourneighborhood.security.UserPrincipal;
import com.radwija.knowyourneighborhood.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class StoreServiceImpl implements StoreService {
    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Store> searchStoreByName(String keyword) {
            return storeRepository.searchStoreByName(keyword);
    }

    private Store updateStore(Long id, Store updatedStore) {
        return storeRepository.findById(id)
                .map(store -> {
                            store.setStoreName(updatedStore.getStoreName());
                            store.setCity(updatedStore.getCity());
                            store.setCountry(updatedStore.getCountry());
                            store.setPhone(updatedStore.getPhone());
                            return storeRepository.save(store);
                        }
                ).orElseThrow(() -> new StoreNotFoundException(updatedStore.getId()));
    }

    @Override
    public ResponseEntity<Store> updateOwnStore(Long id, Store updatedStore, UserPrincipal userPrincipal) {
        Long storeOwner = storeRepository.findById(id).get().getUser().getId();

        if (storeOwner != null) {
            if (userPrincipal.getId().equals(storeOwner)) {
                return ResponseEntity.ok(updateStore(id, updatedStore));
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @Override
    public Store updateUserStore(Long id, Store updatedStore) {
        return updateStore(id, updatedStore);
    }

    @Override
    public List<Store> showAllStores() {
        return storeRepository.findAll();
    }

    @Override
    public List<Store> showStoreByOwner(Long uId) {
        return storeRepository.getCarsByOwner(uId);
    }

    @Override
    public Optional<Store> viewStoreDetail(Long cid) {
        return storeRepository.findById(cid);
    }


    @Transactional
    @Override
    public Store saveStore(UserPrincipal userPrincipal, Store store) {
        User user = userRepository.findById(userPrincipal.getId()).orElse(null);
        if (user != null) {
            store.setUser(user);
            return storeRepository.save(store);
        }
        return new Store();
    }

    @Override
    public void deleteStore(Long id) {
        storeRepository.deleteById(id);
    }
}
