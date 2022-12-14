package com.stolbov.database.library.models;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "journal")
public class Journal {

    @Id
/*    @GeneratedValue(strategy = GenerationType.AUTO)*/
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "journalSequence")
    @SequenceGenerator(name = "journalSequence", sequenceName = "journal_sequence", allocationSize = 1, initialValue = 1 )
    @Column(name="id", nullable = false, unique = true)
    private Long id;

    @Column(name="date_beg", nullable = false)
    private Timestamp dateBeg;

    @Column(name="date_end", nullable = false)
    private Timestamp dateEnd;

    @Column(name="date_ret")
    private Timestamp dateRet;

    @ManyToOne(optional = false)
    @JoinColumn(name="book_id", referencedColumnName = "id", nullable = false)
    private Books book;

    @ManyToOne(optional = false)
    @JoinColumn(name="client_id", referencedColumnName = "id", nullable = false)
    private Clients client;

    public Journal() {
    }

    public Journal(Timestamp dateBeg, Timestamp dateEnd, Books book, Clients client) {
        this.dateBeg = dateBeg;
        this.dateEnd = dateEnd;
        this.book = book;
        this.client = client;
    }

    public Journal(Timestamp dateBeg, Timestamp dateEnd, Timestamp dateRet, Books book, Clients client) {
        this.dateBeg = dateBeg;
        this.dateEnd = dateEnd;
        this.dateRet = dateRet;
        this.book = book;
        this.client = client;
    }

    public Journal(Long id, Timestamp dateBeg, Timestamp dateEnd, Timestamp dateRet, Books book, Clients client) {
        this.id = id;
        this.dateBeg = dateBeg;
        this.dateEnd = dateEnd;
        this.dateRet = dateRet;
        this.book = book;
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getDateBeg() {
        return dateBeg;
    }

    public void setDateBeg(Timestamp dateBeg) {
        this.dateBeg = dateBeg;
    }

    public Timestamp getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Timestamp dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Timestamp getDateRet() {
        return dateRet;
    }

    public void setDateRet(Timestamp dateRet) {
        this.dateRet = dateRet;
    }

    public Books getBook() {
        return book;
    }

    public void setBook(Books book) {
        this.book = book;
    }

    public Clients getClient() {
        return client;
    }

    public void setClient(Clients client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Journal{" +
                "id=" + id +
                ", dateBeg=" + dateBeg +
                ", dateEnd=" + dateEnd +
                ", dateRet=" + dateRet +
                ", book=" + book +
                ", client=" + client +
                '}';
    }
}
