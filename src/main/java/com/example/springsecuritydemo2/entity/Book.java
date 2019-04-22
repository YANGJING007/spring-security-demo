package com.example.springsecuritydemo2.entity;

public class Book {
    private int bookId;

    private String bookName;

    private Integer userId;

    public Book(int bookId, String bookName, Integer userId) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
