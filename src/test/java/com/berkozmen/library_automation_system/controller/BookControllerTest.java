package com.berkozmen.library_automation_system.controller;

import com.berkozmen.library_automation_system.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @Test
    void getAllBooks() {
    }

    @Test
    void getBookById() {
    }

    @Test
    void getBookByTitle() {
    }

    @Test
    void createNewBook() {
    }

    @Test
    void deleteBook() {
    }

    @Test
    void updateBook() {
    }
}