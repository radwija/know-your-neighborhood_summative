package com.radwija.knowyourneighborhood.controller;

import com.radwija.knowyourneighborhood.model.Contact;
import com.radwija.knowyourneighborhood.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contact")
public class ContactController {
    @Autowired
    ContactService contactService;

    @PostMapping("/save-contact")
    public Contact saveContact(@RequestBody Contact contact) {
        return contactService.saveContact(contact);
    }
}
