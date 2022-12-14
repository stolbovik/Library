package com.stolbov.database.library.service;

import com.stolbov.database.library.models.BookTypes;

import java.util.List;

public interface BookTypesService {

    void add(BookTypes booksType);

    List<BookTypes> getBookTypes();

    BookTypes getById(Long bookTypeId);

    void updateFine(Long bookTypeId, float fine);

    void updateDayCount(Long bookTypeId, int dayCount);

    void deleteById(Long booksTypeId);

    Long exsistsByName(String bookType);

    void decreaseCount(Long idTypeBook, int count);

    void increaseCount(Long bookTypeId, int count);

    BookTypes getByName(String bookType);
}
