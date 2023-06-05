package com.radwija.knowyourneighborhood.exception;

public class StoreNotFoundException extends RuntimeException {
    public StoreNotFoundException(Long id) {
        super("Could not found the store with id " + id);
    }
}
