package com.stolbov.database.library.service;

import com.stolbov.database.library.models.BookTypes;
import com.stolbov.database.library.repository.BookTypesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookTypesServiceImpl implements BookTypesService{

    private BookTypesRepository bookTypesRepository;

    @Autowired
    public BookTypesServiceImpl(BookTypesRepository bookTypesRepository) {
        this.bookTypesRepository = bookTypesRepository;
    }

    @Override
    public void add(BookTypes booksType) {
        if (this.exsistsByName(booksType.getName()) != 0) {
            throw new IllegalArgumentException("Book's type with name \"" + booksType.getName()
                    + "\" is already in the table!");
        }
        bookTypesRepository.save(booksType);
    }

    @Override
    public List<BookTypes> getBookTypes() {
        return bookTypesRepository.findAll();
    }

    @Override
    public BookTypes getById(Long bookTypeId) {
        BookTypes bookTypes = bookTypesRepository.findById(bookTypeId).orElseThrow(() -> new IllegalArgumentException((
                "Book's type with id = " + bookTypeId + " doesn't exists!\n")));
        return bookTypes;
    }

    @Override
    public void updateFine(Long bookTypeId, float fine) {
        if (!bookTypesRepository.existsById(bookTypeId)) {
            throw new IllegalArgumentException(("Book's type with id = " + bookTypeId + " doesn't exists!\n"));
        }
        if (fine > 0.0) {
            bookTypesRepository.changeFine(bookTypeId, fine);
        }
        else {
            throw new IllegalArgumentException("Illegal value of fine!\n");
        }
    }

    @Override
    public void updateDayCount(Long bookTypeId, int dayCount) {
        if (!bookTypesRepository.existsById(bookTypeId)) {
            throw new IllegalArgumentException(("Book's type with id = " + bookTypeId + " doesn't exists!\n"));
        }
        if (dayCount > 0) {
            bookTypesRepository.changeDayCount(bookTypeId, dayCount);
        }
        else {
            throw new IllegalArgumentException("Illegal value of count of day!\n");
        }
    }

    @Override
    public void deleteById(Long booksTypeId) {
        if (!bookTypesRepository.existsById(booksTypeId)) {
            throw new IllegalArgumentException("Books type with id = " + booksTypeId + " doesn't exists");
        }
        bookTypesRepository.deleteById(booksTypeId);
    }

    @Override
    public Long exsistsByName(String bookType) {
        Long id = 0L;
        List<BookTypes> bookTypesList = bookTypesRepository.findAll();
        for (BookTypes bookTypes : bookTypesList) {
            if (Objects.equals(bookType, bookTypes.getName())) {
                id = bookTypes.getId();
                break;
            }
        }
        return id;
    }

    @Override
    public void decreaseCount(Long idTypeBook, int count) {
        if (!bookTypesRepository.existsById(idTypeBook)) {
            throw new IllegalArgumentException(("Book's type with id = " + idTypeBook + " not found!\n"));
        }
        if (count <= 0) {
            throw new IllegalArgumentException("Illegal value of count for decrease!");
        }
        if (bookTypesRepository.findById(idTypeBook).get().getCnt() - count < 0) {
            throw new IllegalArgumentException("Not enough books with id = " + idTypeBook + "!\n");
        }
        bookTypesRepository.decreaseCnt(idTypeBook, count);
    }

    @Override
    public void increaseCount(Long bookTypeId, int count) {
        if (!bookTypesRepository.existsById(bookTypeId)) {
            throw new IllegalArgumentException(("Book's type with id = " + bookTypeId + " not found!\n"));
        }
        if (count <= 0) {
            throw new IllegalArgumentException("Illegal value of count for increase!");
        }
        bookTypesRepository.increaseCnt(bookTypeId, count);
    }

    @Override
    public BookTypes getByName(String bookType) {
        Optional<BookTypes> bookType1 = bookTypesRepository.findByName(bookType);
        if (bookType1.isEmpty()) {
            throw new IllegalStateException(("Book's type with name = " + bookType + " not found!\n"));
        }
        return bookType1.get();
    }
}
