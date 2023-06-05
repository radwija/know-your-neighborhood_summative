package com.radwija.knowyourneighborhood.repository;

import com.radwija.knowyourneighborhood.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    @Query(value = "SELECT c FROM Store c WHERE c.storeName LIKE '%' || :keyword || '%'")
    public List<Store> searchStoreByName(@Param("keyword") String keyword);

    @Query(value = "SELECT c FROM Store c WHERE c.makeYear = :year")
    public List<Store> searchStoreByMakeYear(@Param("year") Integer year);

    @Query(value = "SELECT c FROM Store c WHERE c.model LIKE '%' || :keyword || '%'")
    public List<Store> searchStoreByModel(@Param("keyword") String keyword);

    @Query("SELECT c FROM Store c WHERE c.price >= :min AND c.price <= :max")
    public List<Store> searchStoreByRangePrice(@Param("min") Long min, @Param("max") Long max);

    @Query("SELECT c FROM Store c WHERE c.price >= :min")
    public List<Store> searchStoreByMinPrice(@Param("min") Long min);

    @Query("SELECT c FROM Store c WHERE c.price <= :max")
    public List<Store> searchStoreByMaxPrice(@Param("max") Long max);
}
