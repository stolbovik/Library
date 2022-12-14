package com.stolbov.database.library.rest;

import com.stolbov.database.library.models.Books;
import com.stolbov.database.library.service.BookTypesService;
import com.stolbov.database.library.service.BooksService;
import com.stolbov.database.library.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BooksService booksService;
    private final BookTypesService bookTypesService;
    private final JournalService journalService;

    @Autowired
    public BookController(BooksService booksService,
                          BookTypesService bookTypesService,
                          JournalService journalService) {
        this.booksService = booksService;
        this.bookTypesService = bookTypesService;
        this.journalService = journalService;
    }

    @Transactional
    @PostMapping(path = "/add_new_book")
    public void addNewBook(@RequestParam String name,
                           @RequestParam int count,
                           @RequestParam String bookType
    ) {
        Long bookId = bookTypesService.exsistsByName(bookType);
        if (bookId != 0) {
            booksService.add(new Books(name, count, bookTypesService.getById(bookId)));
            bookTypesService.increaseCount(bookTypesService.getByName(bookType).getId(), count);
        } else {
            throw new IllegalArgumentException("Books type with name = " + bookType + " not found!");
        }
    }

    @GetMapping(path = "/get_all_books")
    public List<Books> getBooks() {
        return booksService.getBooks();
    }

    @GetMapping(path = "/get_book/{bookId}")
    public Books getBook(@PathVariable("bookId") Long bookId) {
        return booksService.getById(bookId);
    }

    @Transactional
    @PutMapping(path = "/update_book/increase_count/{bookId}")
    public void increaseCountOFBook(
            @PathVariable("bookId") Long bookId,
            @RequestParam int count) {
        booksService.increaseCount(bookId, count);
        bookTypesService.increaseCount(booksService.getById(bookId).getType().getId(), count);
    }

    @Transactional
    @PutMapping(path = "/update_book/decrease_count/{bookId}")
    public void decreaseCountOFBook(
            @PathVariable("bookId") Long bookId,
            @RequestParam int count) {
        booksService.decreaseCount(bookId, count);
        bookTypesService.decreaseCount(booksService.getById(bookId).getType().getId(), count);
    }

    @Transactional
    @DeleteMapping(path = "/delete_book/{bookId}")
    public void deleteBook(@PathVariable("bookId") Long bookId) {
        if (!journalService.exsistsBookInJournal(bookId)) {
            bookTypesService.decreaseCount(   booksService.getById(bookId).getType().getId(),
                    booksService.getById(bookId).getCnt());
            booksService.deleteById(bookId);
        } else {
            throw new IllegalArgumentException("Book with id = " + bookId + " can't be deleted! " +
                    "The book is present in the journal entries.\n");
        }
    }

}
