package com.stolbov.database.library.service;

import com.stolbov.database.library.models.Clients;

import java.util.List;

public interface ClientsService {

    void add(Clients client);

    List<Clients> getClients();

    Clients getById(Long clientId);

    void updatePassport(Long clientId, String seria, String num);

    void deleteById(Long id);

    Long exsistsByPassport(String clientPassportSeria, String clientPassportNum);

    void updateName(Long clientId, String name);

    void updateSurname(Long clientId, String surname);

    void updateFathername(Long clientId, String fathername);
}
