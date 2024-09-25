package org.example.unibelltestapp.Controllers;

import jakarta.validation.Valid;
import org.example.unibelltestapp.Entities.Client;
import org.example.unibelltestapp.Entities.Contact;
import org.example.unibelltestapp.Services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author batal
 * @Date 25.09.2024
 */
@Controller
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public String listClients(Model model) {
        model.addAttribute("clients", clientService.getAllClients());
        return "clients";
    }

    @GetMapping("/{id}")
    public String viewClient(@PathVariable Long id, Model model) {
        model.addAttribute("client", clientService.getClientById(id));
        model.addAttribute("contacts", clientService.getClientContacts(id));
        return "client-details";
    }

    @GetMapping("/new")
    public String showClientForm(Model model) {
        model.addAttribute("client", new Client());
        return "new-client";
    }

    @PostMapping("/add")
    public String addClient(@Valid @ModelAttribute Client client, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "new-client";
        }
        clientService.addClient(client);
        return "redirect:/clients";
    }

    @GetMapping("/{clientId}/contacts/new")
    public String showContactForm(@PathVariable Long clientId, Model model) {
        model.addAttribute("contact", new Contact());
        model.addAttribute("clientId", clientId);
        return "new-contact";
    }
    @PostMapping("/{clientId}/contacts")
    public String addContact(@PathVariable Long clientId,
                             @Valid @ModelAttribute Contact contact,
                             BindingResult result,
                             Model model) {
        if (result.hasErrors()) {
            model.addAttribute("clientId", clientId);
            return "new-contact";
        }
        clientService.addContact(clientId, contact);
        return "redirect:/clients/" + clientId;
    }
    @GetMapping("/{clientId}/contacts/filter")
    public String filterContactsByType(@PathVariable Long clientId,
                                       @RequestParam("type") String type,
                                       Model model) {
        List<Contact> filteredContacts = clientService.getClientContactsByType(clientId, type);
        model.addAttribute("client", clientService.getClientById(clientId));
        model.addAttribute("contacts", filteredContacts);
        model.addAttribute("selectedType", type);
        return "client-details";
    }
}

