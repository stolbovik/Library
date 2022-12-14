package com.stolbov.database.library.service;

import com.stolbov.database.library.models.Books;
import com.stolbov.database.library.models.Clients;
import com.stolbov.database.library.models.Journal;

import java.util.List;

public interface JournalService {

    boolean exsistsBookInJournal(Long bookId);

    boolean exsistsClientInJournal(Long clientId);

    void add(Books book, Clients client);

    List<Journal> getJournals();

    Journal getById(Long noteId);

    void deleteById(Long noteId);

    void updateTimeEnd(Long bookTypeId, int dayCount);

    void returnBookById(Long noteId);
}
