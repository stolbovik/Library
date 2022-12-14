package com.stolbov.database.library.service;

import com.stolbov.database.library.models.Clients;
import com.stolbov.database.library.repository.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ClientsServiceImpl implements ClientsService {

    private final ClientsRepository clientsRepository;

    @Autowired
    public ClientsServiceImpl(ClientsRepository clientsRepository) {
        this.clientsRepository = clientsRepository;
    }

    @Override
    public void add(Clients client) {
        if (client.getPassportSeria().length() != 4 || client.getPassportNum().length() != 6) {
            throw new IllegalArgumentException("Illegal passport's date!\n");
        }
        long pNum;
        long pSer;
        try {
            pNum = Long.parseLong(client.getPassportNum());
            pSer = Long.parseLong(client.getPassportSeria());
        }
        catch (NumberFormatException error) {
            throw new IllegalArgumentException("Illegal passport's date!\n");
        }
        if (pSer <= 0 || pSer >= 10000 || pNum <= 0 || pNum >= 1000000) {
            throw new IllegalArgumentException("Illegal passport's date!\n");
        }
        Optional<Clients> clientsOptional =
                        clientsRepository.findByPassportSeriaAndPassportNum(
                        client.getPassportSeria(), client.getPassportNum());
        if (clientsOptional.isPresent()) {
            throw new IllegalArgumentException("Client with passport's number = " + client.getPassportNum()
                                            + " and passport's seria = " + client.getPassportSeria()
                                            + " is already in the table!");
        }
        for(char c: client.getFirstName().toCharArray()) {
            if(!Character.isLetter(c)) {
                throw new IllegalArgumentException("Name of client is invalid!");
            }
        }
        for(char c: client.getLastName().toCharArray()) {
            if(!Character.isLetter(c)) {
                throw new IllegalArgumentException("Surname of client is invalid!");
            }
        }
        for(char c: client.getFatherName().toCharArray()) {
            if(!Character.isLetter(c)) {
                throw new IllegalArgumentException("Father's name of client is invalid!");
            }
        }
        clientsRepository.save(client);
    }

    @Override
    public List<Clients> getClients() {
        return clientsRepository.findAll();
    }

    @Override
    public Clients getById(Long clientId) {
        Clients client = clientsRepository.findById(clientId).orElseThrow(() -> new IllegalArgumentException((
                "Client with id = " + clientId + " doesn't exists!\n")));
        return client;
    }

    @Override
    @Transactional
    public void updatePassport(Long clientId, String seria, String num) {
        if (!clientsRepository.existsById(clientId)) {
            throw new IllegalArgumentException(("Client with id = " + clientId + " doesn't exists!\n"));
        }
        Optional<Clients> client = clientsRepository.findByPassportSeriaAndPassportNum(seria, num);
        if(client.isPresent()) {
            throw new IllegalArgumentException("Client with passport's seria = " + seria
                    + " and passport's number = " + num + " is already in the table!");
        }
        if (seria.length() != 4 || num.length() != 6) {
            throw new IllegalArgumentException("Illegal passport's date!\n");
        }
        long pNum;
        long pSer;
        try {
            pNum = Long.parseLong(num);
            pSer = Long.parseLong(seria);
        }
        catch (NumberFormatException error) {
            throw new IllegalArgumentException("Illegal passport's date!\n");
        }
        if (pSer > 0 && pSer < 10000 && pNum > 0 && pNum < 1000000) {
            clientsRepository.changePassportInfo(clientId, seria, num);
        }
        else {
            throw new IllegalArgumentException("Illegal passport's date!\n");
        }
    }

    @Override
    public void deleteById(Long id) {
        boolean exists = clientsRepository.existsById(id);
        if (!exists) {
            throw new IllegalArgumentException("Client with id = " + id + " doesn't exists");
        }
        clientsRepository.deleteById(id);
    }

    @Override
    public Long exsistsByPassport(String clientPassportSeria, String clientPassportNum) {
        Long id = 0L;
        List<Clients> clients = clientsRepository.findAll();
        for (Clients client : clients) {
            if (Objects.equals(clientPassportSeria, client.getPassportSeria())
                && Objects.equals(clientPassportNum, client.getPassportNum())) {
                id = client.getId();
                break;
            }
        }
        return id;
    }

    @Override
    public void updateName(Long clientId, String name) {
        if (!clientsRepository.existsById(clientId)) {
            throw new IllegalArgumentException("Client with id = " + clientId + " doesn't exists!\n");
        }
        for(char c: clientsRepository.getById(clientId).getFirstName().toCharArray()) {
            if(!Character.isLetter(c)) {
                throw new IllegalArgumentException("New name is invalid!");
            }
        }
        clientsRepository.changeName(clientId, name);
    }

    @Override
    public void updateSurname(Long clientId, String surname) {
        if (!clientsRepository.existsById(clientId)) {
            throw new IllegalArgumentException("Client with id = " + clientId + " doesn't exists!\n");
        }
        for(char c: clientsRepository.getById(clientId).getLastName().toCharArray()) {
            if(!Character.isLetter(c)) {
                throw new IllegalArgumentException("New surname is invalid!");
            }
        }
        clientsRepository.changeSurname(clientId, surname);
    }

    @Override
    public void updateFathername(Long clientId, String fathername) {
        if (!clientsRepository.existsById(clientId)) {
            throw new IllegalArgumentException("Client with id = " + clientId + " doesn't exists!\n");
        }
        for(char c: clientsRepository.getById(clientId).getFatherName().toCharArray()) {
            if(!Character.isLetter(c)) {
                throw new IllegalArgumentException("New father's name is invalid!");
            }
        }
        clientsRepository.changeFathername(clientId, fathername);
    }
}
