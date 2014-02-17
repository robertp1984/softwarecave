package com.example.staxread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Book {
    private List<String> authors;
    private String title;
    private Category category;
    private String language;
    private int year;

    public Book(List<String> authors, String title, Category category, String language, int year) {
        this.authors = new ArrayList<>(authors);
        this.title = title;
        this.category = category;
        this.language = language;
        this.year = year;
    }

    public Book(String author, String title, Category category, String language, int year) {
        this (Collections.singletonList(author), title, category, language, year);
    }

    public List<String> getAuthors() {
        return Collections.unmodifiableList(authors);
    }

    public void setAuthors(List<String> authors) {
        this.authors.clear();
        this.authors.addAll(authors);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Book{" + "authors=" + authors + ", title=" + title + ", category=" + category + ", language=" + language + ", year=" + year + '}';
    }

}
