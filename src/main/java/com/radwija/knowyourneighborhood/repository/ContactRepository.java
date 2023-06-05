package com.radwija.knowyourneighborhood.repository;

import com.radwija.knowyourneighborhood.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

}
