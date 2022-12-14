package com.stolbov.database.library.rest;

import com.stolbov.database.library.models.Journal;
import com.stolbov.database.library.service.BookTypesService;
import com.stolbov.database.library.service.BooksService;
import com.stolbov.database.library.service.ClientsService;
import com.stolbov.database.library.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalController {

    private final JournalService journalService;
    private final BookTypesService bookTypesService;
    private final BooksService booksService;
    private final ClientsService clientsService;

    @Autowired
    public JournalController(JournalService journalService,
                             BookTypesService bookTypesService,
                             BooksService booksService,
                             ClientsService clientsService) {
        this.journalService = journalService;
        this.bookTypesService = bookTypesService;
        this.booksService = booksService;
        this.clientsService = clientsService;
    }

    @Transactional
    @PostMapping(path = "/book_lend")
    public void addNewNote(@RequestParam String bookName,
                           @RequestParam String clientPassportSeria,
                           @RequestParam String clientPassportNum
    ) {
        Long bookId = booksService.exsistsByName(bookName);
        Long clientId = clientsService.exsistsByPassport(clientPassportSeria, clientPassportNum);
        if (bookId != 0 && clientId != 0) {
            journalService.add(booksService.getById(bookId), clientsService.getById(clientId));
            booksService.decreaseCount(bookId, 1);
            bookTypesService.decreaseCount(booksService.getBookTypeById(bookId).getId(), 1);
        } else {
            if (bookId == 0 && clientId == 0) {
                throw new IllegalArgumentException("Book with name \"" + bookName + "\" and client " +
                        "with passport = " + clientPassportSeria + clientPassportNum + " not found!\n");
            }
            if (bookId == 0) {
                throw new IllegalArgumentException("Book with name \"" + bookName + "\" not found\n");
            }
            throw new IllegalArgumentException("Client with passport = "
                    + clientPassportSeria + clientPassportNum + " not found!\n");
        }
    }

    @GetMapping(path = "/get_all_notes")
    public List<Journal> getJournals() {
        return journalService.getJournals();
    }

    @GetMapping(path = "/get_note/{noteId}")
    public Journal getNote(@PathVariable("noteId") Long noteId) {
        return journalService.getById(noteId);
    }

    @Transactional
    @DeleteMapping(path = "/delete_note/{noteId}")
    public void deleteNote(@PathVariable("noteId") Long noteId) {
        booksService.increaseCount(journalService.getById(noteId).getBook().getId(), 1);
        bookTypesService.increaseCount(journalService.getById(noteId).getBook().getType().getId(), 1);
        journalService.deleteById(noteId);
    }

    @Transactional
    @PutMapping(path = "/return_book/{noteId}")
    public void returnBook(@PathVariable("noteId") Long noteId) {
        journalService.returnBookById(noteId);
        booksService.increaseCount(journalService.getById(noteId).getBook().getId(), 1);
        bookTypesService.increaseCount(journalService.getById(noteId).getBook().getType().getId(), 1);
    }

}

