package com.stolbov.database.library.repository;

import com.stolbov.database.library.models.BookTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface BookTypesRepository extends JpaRepository<BookTypes, Long> {

    Optional<BookTypes> findByName(String name);

    @Modifying
    @Transactional
    @Query("update BookTypes e set e.fine = ?2 where e.id = ?1")
    void changeFine(Long bookTypeId, float fine);

    @Modifying
    @Transactional
    @Query("update BookTypes e set e.dayCount = ?2 where e.id = ?1")
    void changeDayCount(Long bookTypeId, int dayCount);

    @Modifying
    @Transactional
    @Query("update BookTypes e set e.cnt = e.cnt - ?2 where e.id = ?1")
    void decreaseCnt(Long idTypeBook, int count);

    @Modifying
    @Transactional
    @Query("update BookTypes e set e.cnt = e.cnt + ?2 where e.id = ?1")
    void increaseCnt(Long bookTypeId, int count);

}
