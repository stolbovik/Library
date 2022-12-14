package com.stolbov.database.library.service;

import com.stolbov.database.library.models.BookTypes;
import com.stolbov.database.library.models.Books;
import com.stolbov.database.library.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BooksServiceImpl implements BooksService{

    private final BooksRepository booksRepository;

    @Autowired
    public BooksServiceImpl(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    @Override
    public void add(Books books) {
        Optional<Books> bookOptional = booksRepository.findByName(books.getName());
        if (bookOptional.isPresent()) {
            throw new IllegalStateException("Book with name \"" + books.getName()
                    + "\" is already in the table!");
        }
        if (books.getCnt() <= 0) {
            throw new IllegalArgumentException("Illegal value of book's count!\n");
        }
        booksRepository.save(books);
    }

    @Override
    public List<Books> getBooks() {
        return booksRepository.findAll();
    }

    @Override
    public Books getById(Long bookId) {
        Books book = booksRepository.findById(bookId).orElseThrow(() -> new IllegalArgumentException((
                "Book with id = " + bookId + " doesn't exists!\n")));
        return book;
    }

    @Override
    public boolean exsistsBookTypeInBooks(Long booksTypeId) {
        boolean flag = false;
        List<Books> books = booksRepository.findAll();
        for(Books book : books) {
            if (Objects.equals(book.getType().getId(), booksTypeId)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    @Override
    public Long exsistsByName(String bookName) {
        Long id = 0L;
        List<Books> books = booksRepository.findAll();
        for (Books book : books) {
            if (Objects.equals(bookName, book.getName())) {
                id = book.getId();
                break;
            }
        }
        return id;
    }

    @Override
    public void increaseCount(Long bookId, int count) {
        if (!booksRepository.existsById(bookId)) {
            throw new IllegalArgumentException(("Book with id = " + bookId + " not found!\n"));
        }
        if (count <= 0) {
            throw new IllegalArgumentException("Illegal value of count for increase!");
        }
        booksRepository.increaseCnt(bookId, count);
    }

    @Override
    public void deleteById(Long bookId) {
        boolean exists = booksRepository.existsById(bookId);
        if (!exists) {
            throw new IllegalArgumentException("Book with id = " + bookId + " doesn't exists");
        }
        booksRepository.deleteById(bookId);
    }

    @Override
    public BookTypes getBookTypeById(Long bookId) {
        Books book = booksRepository.findById(bookId).orElseThrow(() -> new IllegalArgumentException((
                "Book with id = " + bookId + " doesn't exists!\n")));
        return book.getType();
    }

    @Override
    public void decreaseCount(Long id, int count) {
        Optional<Books> book = booksRepository.findById(id);
        if(book.isEmpty()) {
            throw new IllegalArgumentException(("Book with id = " + id + " not found!\n"));
        }
        if (count <= 0) {
            throw new IllegalArgumentException("Illegal value of count for increase!");
        }
        if (book.get().getCnt() - count < 0) {
            throw new IllegalArgumentException("Not enough books with id = " + id + "!\n");
        }
        booksRepository.decreaseCnt(id, count);
    }
}
