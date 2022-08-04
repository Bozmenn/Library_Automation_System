package com.berkozmen.library_automation_system.service;

import com.berkozmen.library_automation_system.ObjectExtensions;
import com.berkozmen.library_automation_system.exception.EntityNotFoundException;
import com.berkozmen.library_automation_system.model.dto.BookDTO;
import com.berkozmen.library_automation_system.model.entity.Book;
import com.berkozmen.library_automation_system.model.mapper.BookMapper;
import com.berkozmen.library_automation_system.repository.BookRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

// ctrl + shift + T create test class form service class
@ExtendWith(MockitoExtension.class)
class BookServiceTest {


    @Mock // mock related instance
    private BookRepository bookRepository;

    @InjectMocks // injects mocked instances into this instance
    private BookService bookService;


    @Test
    void getAllBooks() {
        // init step (JavaFaker kullan)
        List<Book> expectedBookList = getSampleTestBooks();
        String expectedListJSON = ObjectExtensions.toJson(expectedBookList);

        //stub - when step
        Mockito.when(bookRepository.findAll()).thenReturn(expectedBookList);

        // then
        List<Book> actualBookList = bookService.getAllBooks();
        String actualListJSON = ObjectExtensions.toJson(actualBookList);

        // validate step
        Assert.assertEquals(actualListJSON,expectedListJSON);
        verify(bookRepository,times(1)).findAll();
/*
        Assert.assertEquals(expectedBookList.size(),actualBookList.size());
        // sorted list by ID with a comparator
        expectedBookList = expectedBookList.stream().sorted(getBookComparator()).collect(Collectors.toList());
        actualBookList = actualBookList.stream().sorted(getBookComparator()).collect(Collectors.toList());
        for(int i = 0; i< expectedBookList.size();i++){
            Book currentExpectedBook = expectedBookList.get(i);
            Book currentActualBook = actualBookList.get(i);
            Assert.assertEquals(currentExpectedBook.getId(),currentActualBook.getId());
            Assert.assertEquals(currentExpectedBook.getAuthor(),currentActualBook.getAuthor());
        }*/
    }

    @Test
    void getById_successful() {

        // init step
        Book expectedBook = getSampleTestBooks().get(0);
        Optional<Book> optionalExpectedBook = Optional.of(expectedBook);
        String expectedBookJSON = ObjectExtensions.toJson(expectedBook);

        // stub - when step
        Mockito.when(bookRepository.findById(any())).thenReturn(optionalExpectedBook);

        // then
        Book actualBook = bookService.getById(any());
        String actualBookJSON = ObjectExtensions.toJson(actualBook);

        // validate step
        Assert.assertEquals(expectedBookJSON,actualBookJSON);

    }

    @Test
    void getById_NOT_FOUND() {

        // stub - when step
        Mockito.when(bookRepository.findById(any())).thenReturn(Optional.empty());

        // then
/*
        defined in validate step
*/

        // validate
        assertThrows(EntityNotFoundException.class,
                () -> {Book actualBook = bookService.getById(any());});

    }

    @Test
    void getByTitle_successful() {

        // init step
        Book expectedBook = getSampleTestBooks().get(0);
        Optional<Book> optionalExpectedBook = Optional.of(expectedBook);
        String expectedBookJSON = ObjectExtensions.toJson(expectedBook);

        // stub - when step

        Mockito.when(bookRepository.findBookByTitle(any())).thenReturn(optionalExpectedBook);
        // then - validate step
        Book actualBook = bookService.getByTitle(any());
        String actualBookJSON = ObjectExtensions.toJson(actualBook);

        Assert.assertEquals(expectedBookJSON,actualBookJSON);

    }

    @Test
    void getByTitle_NOT_FOUND() {

        // init step
        // stub - when step
        Mockito.when(bookRepository.findBookByTitle(any())).thenReturn(Optional.empty());

        // then
        Executable executable = () -> bookService.getByTitle(any());

        // validate step
        assertThrows(EntityNotFoundException.class,
                executable);
    }

    @Test
    void create() {
        // init step
        BookDTO bookDTO = new BookDTO();
        Book expectedBook = BookMapper.toEntity(bookDTO);
        String exepctedBookJSON = ObjectExtensions.toJson(expectedBook);

        // stub - when step
        Mockito.when(bookRepository.save(expectedBook)).thenReturn(expectedBook);

        // then step
        Book actualBook = bookService.create(bookDTO);
        String actualBookJSON = ObjectExtensions.toJson(actualBook);

        // validate step
        Assert.assertEquals(actualBookJSON, exepctedBookJSON);

    }

    @Test
    void delete() {
        // init step
        Book book = getSampleTestBooks().get(0);
        Optional<Book> optBook = Optional.of(book);
        // stub - when step
        Mockito.when(bookRepository.findById(book.getId())).thenReturn(optBook);
        doNothing().when(bookRepository).deleteById(book.getId());
        // then - validate step
        bookService.delete(book.getId());
        Mockito.verify(bookRepository, Mockito.times(1)).deleteById(book.getId());
    }

    @Test
    void update() {
        // init step
        BookDTO bookDTO = new BookDTO(1L,"title1","author4",123456L,"Publisher4","04/10/1997");
        Book expectedBook = BookMapper.toEntity(bookDTO);
        String expectedBookJSON = ObjectExtensions.toJson(expectedBook);
        Optional<Book> optExpectedBook = Optional.of(expectedBook);

        // stub - when step
        Mockito.when(bookRepository.findById(expectedBook.getId())).thenReturn(optExpectedBook);
        Mockito.when(bookRepository.save(expectedBook)).thenReturn(expectedBook);

        // then - validate step
        Book actualBook = bookService.update(1L, bookDTO);
        String actualBookJSON = ObjectExtensions.toJson(actualBook);

        Assert.assertEquals(actualBookJSON, expectedBookJSON);
        verify(bookRepository, times(1)).save(actualBook);

    }

    private List<Book> getSampleTestBooks() {
        List<Book> expectedBookList = new ArrayList<>();
        Book book   = new Book(1L,"book1","author1",123L,"Publisher1","04/10/1994");
        Book book1   = new Book(2L,"book2","author2",1234L,"Publisher2","04/10/1995");
        Book book2   = new Book(3L,"book3","author3",12345L,"Publisher3","04/10/1996");
        expectedBookList.add(book1);
        expectedBookList.add(book2);
        expectedBookList.add(book);
        return expectedBookList;
    }

    //ctrl + alt + M change duplicate code to new method.
/*    private Comparator<Book> getBookComparator() {
        return (o1, o2) -> {
            if (o1.getId() - o2.getId() > 0)
                return -1;
            if (o1.getId() - o2.getId() == 0)
                return 0;
            return 1;
        };
    }*/

}