package com.radwija.knowyourneighborhood.service;

import com.radwija.knowyourneighborhood.model.Contact;
import org.springframework.stereotype.Service;

@Service
public interface ContactService {
    Contact saveContact(Contact contact);
}
