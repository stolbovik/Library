package com.stolbov.database.library.models;

import javax.persistence.*;

@Entity
@Table (name = "book_types")
public class BookTypes {

    @Id
/*    @GeneratedValue(strategy = GenerationType.AUTO)*/
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "booksTypeSequence")
    @SequenceGenerator(name = "booksTypeSequence", sequenceName = "books_type_sequence", allocationSize = 1, initialValue = 1 )
    @Column(name="id", nullable = false, unique = true)
    private Long id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="cnt", nullable = false)
    private int cnt;

    @Column (name = "day_count", nullable = false)
    private int dayCount;

    @Column (name = "fine", nullable = false)
    private float fine;

    public BookTypes() {
    }

    public BookTypes(String name, int cnt, int dayCount, float fine) {
        this.name = name;
        this.cnt = cnt;
        this.dayCount = dayCount;
        this.fine = fine;
    }

    public BookTypes(Long id, String name, int cnt, int dayCount, float fine) {
        this.id = id;
        this.name = name;
        this.cnt = cnt;
        this.dayCount = dayCount;
        this.fine = fine;
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

    public int getDayCount() {
        return dayCount;
    }

    public void setDayCount(int dayCount) {
        this.dayCount = dayCount;
    }

    public float getFine() {
        return fine;
    }

    public void setFine(float fine) {
        this.fine = fine;
    }

    @Override
    public String toString() {
        return "BookTypes{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cnt=" + cnt +
                ", dayCount=" + dayCount +
                ", fine=" + fine +
                '}';
    }
}
