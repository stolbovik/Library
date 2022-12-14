package com.stolbov.database.library.repository;

import com.stolbov.database.library.models.Clients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface ClientsRepository extends JpaRepository<Clients, Long> {

    Optional<Clients> findByPassportSeriaAndPassportNum(String seria, String num);

    @Modifying
    @Transactional
    @Query("update Clients e set e.passportSeria = ?2, e.passportNum = ?3 where e.id = ?1")
    void changePassportInfo(Long clientId, String seria, String num);

    @Modifying
    @Query("update Clients e set e.firstName = ?2 where e.id = ?1")
    void changeName(Long clientId, String name);

    @Modifying
    @Query("update Clients e set e.lastName = ?2 where e.id = ?1")
    void changeSurname(Long clientId, String surname);

    @Modifying
    @Query("update Clients e set e.fatherName = ?2 where e.id = ?1")
    void changeFathername(Long clientId, String fathername);
}
