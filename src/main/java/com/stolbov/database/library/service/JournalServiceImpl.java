package com.stolbov.database.library.service;

import com.stolbov.database.library.models.Books;
import com.stolbov.database.library.models.Clients;
import com.stolbov.database.library.models.Journal;
import com.stolbov.database.library.repository.BooksRepository;
import com.stolbov.database.library.repository.ClientsRepository;
import com.stolbov.database.library.repository.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class JournalServiceImpl implements JournalService{

    private final JournalRepository journalRepository;
    private final ClientsRepository clientsRepository;
    private final BooksRepository booksRepository;

    @Autowired
    public JournalServiceImpl(JournalRepository journalRepository, ClientsRepository clientsRepository, BooksRepository booksRepository, BooksRepository booksRepository1) {
        this.journalRepository = journalRepository;
        this.clientsRepository = clientsRepository;
        this.booksRepository = booksRepository1;
    }

    @Override
    public boolean exsistsBookInJournal(Long bookId) {
        boolean flag = false;
        List<Journal> journals = journalRepository.findAll();
        for(Journal journal : journals) {
            if (Objects.equals(journal.getBook().getId(), bookId)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public boolean exsistsClientInJournal(Long clientId) {
        boolean flag = false;
        List<Journal> journals = journalRepository.findAll();
        for(Journal journal : journals) {
            if (Objects.equals(journal.getClient().getId(), clientId)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    @Override
    public void add(Books book, Clients client) {
        Timestamp timeNow = new Timestamp(new Date().getTime());
        int dayCount = book.getType().getDayCount();
        Timestamp timeEnd = Timestamp.valueOf(LocalDateTime.now().plusDays(dayCount));
        journalRepository.save(new Journal(timeNow, timeEnd, book, client));
    }

    @Override
    public List<Journal> getJournals() {
        return journalRepository.findAll();
    }

    @Override
    public Journal getById(Long noteId) {
        Journal journal = journalRepository.findById(noteId).orElseThrow(() -> new IllegalArgumentException((
                "In journal note with id = " + noteId + " not found!\n")));
        return journal;
    }

    @Override
    public void deleteById(Long noteId) {
        boolean exists = journalRepository.existsById(noteId);
        if (!exists) {
            throw new IllegalArgumentException("Note with id = " + noteId + " doesn't exists");
        }
        journalRepository.deleteById(noteId);
    }

    @Override
    public void updateTimeEnd(Long bookTypeId, int dayCount) {
        List<Journal> journals = journalRepository.findAll();
        for (Journal journal : journals) {
            if (Objects.equals(bookTypeId, journal.getBook().getType().getId())) {
                journal.setDateEnd(Timestamp.valueOf(journal.getDateBeg().toLocalDateTime().plusDays(dayCount)));
            }
        }
    }

    @Override
    public void returnBookById(Long noteId) {
        Optional<Journal> journal = journalRepository.findById(noteId);
        if (journal.isEmpty()) {
            throw new IllegalArgumentException("Note with id = " + noteId + " doesn't exists");
        }
        Timestamp timeRet = Timestamp.valueOf(LocalDateTime.now());
        journal.get().setDateRet(timeRet);
    }
}
