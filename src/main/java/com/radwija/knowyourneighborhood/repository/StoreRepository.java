package com.radwija.knowyourneighborhood.repository;

import com.radwija.knowyourneighborhood.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    @Query("SELECT s FROM Store s WHERE s.user.id = :uId")
    List<Store> getCarsByOwner(@Param("uId") Long uId);

    @Query(value = "SELECT s FROM Store s WHERE s.storeName LIKE '%' || :keyword || '%'")
    public List<Store> searchStoreByName(@Param("keyword") String keyword);
}
