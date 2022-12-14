package com.stolbov.database.library.rest;

import com.stolbov.database.library.models.Clients;
import com.stolbov.database.library.service.ClientsService;
import com.stolbov.database.library.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientsService clientsService;
    private final JournalService journalService;

    @Autowired
    public ClientController(ClientsService clientsService, JournalService journalService) {
        this.clientsService = clientsService;
        this.journalService = journalService;
    }

    @PostMapping(path = "/register_client")
    public void registerNewClient(@RequestBody Clients client) {
        clientsService.add(client);
    }

    @GetMapping(path = "/get_all_clients")
    public List<Clients> getClients() {
        return clientsService.getClients();
    }

    @GetMapping(path = "/get_client/{clientId}")
    public Clients getClient(@PathVariable("clientId") Long clientId) {
        return clientsService.getById(clientId);
    }

    @PutMapping(path = "/update_clients_passport/{clientId}")
    public void updateClientsPassport(
            @PathVariable("clientId") Long clientId,
            @RequestParam String seria,
            @RequestParam String num) {
        clientsService.updatePassport(clientId, seria, num);
    }

    @Transactional
    @PutMapping(path = "/update_clients_name/{clientId}")
    public void updateClientsName(
            @PathVariable("clientId") Long clientId,
            @RequestParam String name) {
        clientsService.updateName(clientId, name);
    }

    @Transactional
    @PutMapping(path = "/update_clients_surname/{clientId}")
    public void updateClientsSurname(
            @PathVariable("clientId") Long clientId,
            @RequestParam String surname) {
        clientsService.updateSurname(clientId, surname);
    }

    @Transactional
    @PutMapping(path = "/update_clients_fathername/{clientId}")
    public void updateClientsFathername(
            @PathVariable("clientId") Long clientId,
            @RequestParam String fathername) {
        clientsService.updateFathername(clientId, fathername);
    }

    @DeleteMapping(path = "/delete_client/{clientId}")
    public void deleteClient(@PathVariable("clientId") Long clientId) {
        if (!journalService.exsistsClientInJournal(clientId)) {
            clientsService.deleteById(clientId);
        } else {
            throw new IllegalArgumentException("Client with id = " + clientId + " can't be deleted! " +
                    "His name appears in the journal entries.\n");
        }
    }


}
