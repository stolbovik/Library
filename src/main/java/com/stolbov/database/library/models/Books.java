package com.stolbov.database.library.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "books")
public class Books {

    @Id
/*    @GeneratedValue(strategy = GenerationType.AUTO)*/
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "booksSequence")
    @SequenceGenerator(name = "booksSequence", sequenceName = "books_sequence", allocationSize = 1, initialValue = 1 )
    @Column(name="id", nullable = false, unique = true)
    private Long id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="cnt", nullable = false)
    private int cnt;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name="type_id", referencedColumnName = "id", nullable = false)
    private BookTypes type;

    public Books() {
    }

    public Books(String name, int cnt, BookTypes type) {
        this.name = name;
        this.cnt = cnt;
        this.type = type;
    }

    public Books(Long id, String name, int cnt, BookTypes type) {
        this.id = id;
        this.name = name;
        this.cnt = cnt;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }
    public void increaseCnt(int cnt) {
        this.cnt += cnt;
    }

    public BookTypes getType() {
        return type;
    }

    public void setType(BookTypes type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Books{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cnt=" + cnt +
                ", type=" + type +
                '}';
    }

}
