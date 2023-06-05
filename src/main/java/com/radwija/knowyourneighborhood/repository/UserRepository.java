package com.radwija.knowyourneighborhood.repository;

import com.radwija.knowyourneighborhood.model.Store;
import com.radwija.knowyourneighborhood.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT u FROM User u WHERE u.name LIKE '%' || :keyword || '%'")
    public List<User> searchUserByName(@Param("keyword") String keyword);
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);
    Boolean existsById(String username);
    Boolean existsByUsernameAndIdNot(String username, Long id);
    Boolean existsByEmailAndIdNot(String email, Long id);


}
