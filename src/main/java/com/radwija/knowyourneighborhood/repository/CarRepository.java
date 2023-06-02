package com.radwija.knowyourneighborhood.repository;

import com.radwija.knowyourneighborhood.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    @Query(value = "SELECT c FROM Car c WHERE c.carName LIKE '%' || :keyword || '%'")
    public List<Car> searchCarByName(@Param("keyword") String keyword);

    @Query(value = "SELECT c FROM Car c WHERE c.makeYear = :year")
    public List<Car> searchCarByMakeYear(@Param("year") Integer year);

    @Query(value = "SELECT c FROM Car c WHERE c.model LIKE '%' || :keyword || '%'")
    public List<Car> searchCarByModel(@Param("keyword") String keyword);

    @Query("SELECT c FROM Car c WHERE c.price >= :min AND c.price <= :max")
    public List<Car> searchCarByRangePrice(@Param("min") Long min, @Param("max") Long max);

    @Query("SELECT c FROM Car c WHERE c.price >= :min")
    public List<Car> searchCarByMinPrice(@Param("min") Long min);

    @Query("SELECT c FROM Car c WHERE c.price <= :max")
    public List<Car> searchCarByMaxPrice(@Param("max") Long max);
}
