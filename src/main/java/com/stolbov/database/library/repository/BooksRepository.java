package com.stolbov.database.library.repository;

import com.stolbov.database.library.models.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface BooksRepository extends JpaRepository<Books, Long> {

    Optional<Books> findByName(String name);

    @Modifying
    @Transactional
    @Query("update Books e set e.cnt = e.cnt + ?2 where e.id = ?1")
    void increaseCnt(Long bookId, int count);

    @Modifying
    @Transactional
    @Query("update Books e set e.cnt = e.cnt - ?2 where e.id = ?1")
    void decreaseCnt(Long bookId, int count);
}
