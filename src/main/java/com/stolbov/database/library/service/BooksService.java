package com.stolbov.database.library.service;

import com.stolbov.database.library.models.BookTypes;
import com.stolbov.database.library.models.Books;

import java.util.List;

public interface BooksService {

    void add(Books books);

    List<Books> getBooks();

    Books getById(Long bookId);

    boolean exsistsBookTypeInBooks(Long booksTypeId);

    void increaseCount(Long bookId, int count);

    void deleteById(Long bookId);

    Long exsistsByName(String bookName);

    BookTypes getBookTypeById(Long idBook);

    void decreaseCount(Long id, int count);
}
