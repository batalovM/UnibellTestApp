package org.example.unibelltestapp.Services;

import org.example.unibelltestapp.Entities.Client;
import org.example.unibelltestapp.Entities.Contact;
import org.example.unibelltestapp.Repositories.ClientRepository;
import org.example.unibelltestapp.Repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author batal
 * @Date 25.09.2024
 */
@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ContactRepository contactRepository;

    public void addClient(Client client) {
        clientRepository.save(client);
    }

    public void addContact(Long clientId, Contact contact) {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new RuntimeException("Client not found"));
        contact.setClient(client);
        contactRepository.save(contact);
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new RuntimeException("Client not found"));
    }

    public List<Contact> getClientContacts(Long clientId) {
        return contactRepository.findByClientId(clientId);
    }

    public List<Contact> getClientContactsByType(Long clientId, String type) {
        return contactRepository.findByClientIdAndType(clientId, type);
    }
}

