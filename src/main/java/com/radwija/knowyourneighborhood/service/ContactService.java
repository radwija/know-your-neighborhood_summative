package com.radwija.knowyourneighborhood.service;

import com.radwija.knowyourneighborhood.model.Contact;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContactService {
    Contact saveContact(Contact contact);

    List<Contact> showAllContacts();

    void deleteContact(Long id);
}
