package com.radwija.knowyourneighborhood.service.impl;

import com.radwija.knowyourneighborhood.model.Contact;
import com.radwija.knowyourneighborhood.repository.ContactRepository;
import com.radwija.knowyourneighborhood.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    ContactRepository contactRepository;

    @Override
    public Contact saveContact(Contact contact) {
        return contactRepository.save(contact);
    }
}
