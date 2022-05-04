package com.spring.kurswork_beautysalon_web.entity;

import javax.persistence.*;

@Entity
@Table(name = "booked_records")
public class BookedRecords {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "record_id", nullable = false)
    private FreeRecords freeRecords;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public BookedRecords() {
    }

    public BookedRecords(Long id, FreeRecords freeRecords, User user) {
        this.id = id;
        this.freeRecords = freeRecords;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FreeRecords getFreeRecords() {
        return freeRecords;
    }

    public void setFreeRecords(FreeRecords freeRecords) {
        this.freeRecords = freeRecords;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}