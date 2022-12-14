package com.stolbov.database.library.rest;

import com.stolbov.database.library.models.BookTypes;
import com.stolbov.database.library.service.BookTypesService;
import com.stolbov.database.library.service.BooksService;
import com.stolbov.database.library.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/book_type")
public class BookTypeController {

    private final BookTypesService bookTypesService;
    private final BooksService booksService;
    private final JournalService journalService;

    @Autowired
    public BookTypeController(BookTypesService bookTypesService,
                              BooksService booksService,
                              JournalService journalService) {
        this.bookTypesService = bookTypesService;
        this.booksService = booksService;
        this.journalService = journalService;
    }

    @PostMapping(path = "/add_new_books_type")
    public void addNewBookType(@RequestBody BookTypes bookType) {
        bookTypesService.add(bookType);
    }

    @GetMapping(path = "/get_all_book_types")
    public List<BookTypes> getBookTypes() {
        return bookTypesService.getBookTypes();
    }

    @GetMapping(path = "/get_book_type/{bookTypeId}")
    public BookTypes getBookType(@PathVariable("bookTypeId") Long bookTypeId) {
        return bookTypesService.getById(bookTypeId);
    }

    @PutMapping(path = "/update_book_type/update_fine/{bookTypeId}")
    public void updateFineInBookType(
            @PathVariable("bookTypeId") Long bookTypeId,
            @RequestParam float fine) {
        bookTypesService.updateFine(bookTypeId, fine);
    }

    @Transactional
    @PutMapping(path = "/update_book_type/update_day_count/{bookTypeId}")
    public void updateDayCountInBookType(
            @PathVariable("bookTypeId") Long bookTypeId,
            @RequestParam int dayCount) {
        bookTypesService.updateDayCount(bookTypeId, dayCount);
        journalService.updateTimeEnd(bookTypeId, dayCount);
    }

    @DeleteMapping(path = "/delete_books_type/{booksTypeId}")
    public void deleteBookType(@PathVariable("booksTypeId") Long bookTypeId) {
        if (!booksService.exsistsBookTypeInBooks(bookTypeId)) {
            bookTypesService.deleteById(bookTypeId);
        } else {
            throw new IllegalArgumentException("Books type with id = " + bookTypeId + " can't be deleted! " +
                    "There are books with this type in the database!\n");
        }
    }

}
