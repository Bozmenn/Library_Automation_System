package com.berkozmen.library_automation_system.service;

import com.berkozmen.library_automation_system.ObjectExtensions;
import com.berkozmen.library_automation_system.exception.EntityNotFoundException;
import com.berkozmen.library_automation_system.model.dto.BookDTO;
import com.berkozmen.library_automation_system.model.dto.BookFeedbackDTO;
import com.berkozmen.library_automation_system.model.entity.Book;
import com.berkozmen.library_automation_system.model.entity.BookFeedback;
import com.berkozmen.library_automation_system.model.entity.User;
import com.berkozmen.library_automation_system.model.mapper.BookFeedbackMapper;
import com.berkozmen.library_automation_system.model.mapper.BookMapper;
import com.berkozmen.library_automation_system.repository.BookFeedbackRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
class BookFeedbackServiceTest {

    @Mock
    private BookFeedbackRepository bookFeedbackRepository;

    @Mock
    private BookFeedbackMapper bookFeedbackMapper;

    @InjectMocks
    private BookFeedbackService bookFeedbackService;

    @Test
    void getAllBookFeedbacks() {
        //init
        List<BookFeedback> expectedList = getSampleTestFeedbacks();
        String expectedListJSON = ObjectExtensions.toJson(expectedList);
        //stub
        Mockito.when(bookFeedbackRepository.findAll()).thenReturn(expectedList);
        //then
        List<BookFeedback> actualList = bookFeedbackService.getAllBookFeedbacks();
        String actualListJSON = ObjectExtensions.toJson(actualList);
        //validate
        Assert.assertEquals(expectedListJSON,actualListJSON);
        Mockito.verify(bookFeedbackRepository, Mockito.times(1)).findAll();

    }


    @Test
    void getById_successful() {
        //init
        BookFeedback expectedBookFeedback = getSampleTestFeedbacks().get(0);
        Optional<BookFeedback> optExpectedBookFeedback = Optional.of(expectedBookFeedback);
        String expectedBookFeedbackJson = ObjectExtensions.toJson(expectedBookFeedback);

        //stub
        Mockito.when(bookFeedbackRepository.findById(expectedBookFeedback.getId())).thenReturn(optExpectedBookFeedback);
        //then
        BookFeedback actualBookFeedback = bookFeedbackService.getById(expectedBookFeedback.getId());
        String actualBookFeedbackJson = ObjectExtensions.toJson(actualBookFeedback);
        //validate
        Assert.assertEquals(actualBookFeedbackJson,expectedBookFeedbackJson);
        Mockito.verify(bookFeedbackRepository,Mockito.times(1)).findById(expectedBookFeedback.getId());
    }

    @Test
    void getById_NOT_FOUND() {
        //init
        // stub - when step
        Mockito.when(bookFeedbackRepository.findById(any())).thenReturn(Optional.empty());
        // then
        // validate
        assertThrows(EntityNotFoundException.class,
                () -> {
                   bookFeedbackService.getById(any());
                });
    }


    @Test
    void getByUserId_successful() {
        //init
        BookFeedback expectedBookFeedback = getSampleTestFeedbacks().get(0);
        Optional<BookFeedback> optExpectedBookFeedback = Optional.of(expectedBookFeedback);
        String expectedBookFeedbackJSON = ObjectExtensions.toJson(expectedBookFeedback);
        //stub
        Mockito.when(bookFeedbackRepository.findBookFeedbackByUserId(expectedBookFeedback.getUser().getId()))
                .thenReturn(optExpectedBookFeedback);
        //then
        BookFeedback actualBookFeedback = bookFeedbackService.getByUserId(expectedBookFeedback.getUser().getId());
        String actualBookFeedbackJSON = ObjectExtensions.toJson(actualBookFeedback);
        //validate
        Assert.assertEquals(expectedBookFeedbackJSON,actualBookFeedbackJSON);
        Mockito.verify(bookFeedbackRepository,Mockito.times(1)).findBookFeedbackByUserId(any());
    }

    @Test
    void getByUserId_NOT_FOUND() {
        //init
        // stub - when step
        Mockito.when(bookFeedbackRepository.findBookFeedbackByUserId(any())).thenReturn(Optional.empty());
        // then
        // validate
        assertThrows(EntityNotFoundException.class,
                () -> {
                    bookFeedbackService.getByUserId(any());
                });
    }

    @Test
    void getByBookId_successful() {
        //init
        BookFeedback expectedBookFeedback = getSampleTestFeedbacks().get(0);
        Optional<BookFeedback> optExpectedBookFeedback = Optional.of(expectedBookFeedback);
        String expectedBookFeedbackJSON = ObjectExtensions.toJson(expectedBookFeedback);
        //stub
        Mockito.when(bookFeedbackRepository.findBookFeedbackByBookId(expectedBookFeedback.getBook().getId()))
                .thenReturn(optExpectedBookFeedback);
        //then
        BookFeedback actualBookFeedback = bookFeedbackService.getByBookId(expectedBookFeedback.getBook().getId());
        String actualBookFeedbackJSON = ObjectExtensions.toJson(actualBookFeedback);
        //validate
        Assert.assertEquals(expectedBookFeedbackJSON,actualBookFeedbackJSON);
        Mockito.verify(bookFeedbackRepository,Mockito.times(1)).findBookFeedbackByBookId(any());
    }

    @Test
    void getByBookId_NOT_FOUND() {
        //init
        // stub - when step
        Mockito.when(bookFeedbackRepository.findBookFeedbackByBookId(any())).thenReturn(Optional.empty());
        // then
        // validate
        assertThrows(EntityNotFoundException.class,
                () -> {
                    bookFeedbackService.getByBookId(any());
                });
    }

    @Test
    void create() {
        // init step
        BookFeedbackDTO bookFeedbackDTO = new BookFeedbackDTO();
        BookFeedback expectedBookFeedback = bookFeedbackMapper.toEntity(bookFeedbackDTO);
        String exepctedBookFeedbackJSON = ObjectExtensions.toJson(expectedBookFeedback);

        // stub - when step
        Mockito.when(bookFeedbackRepository.save(expectedBookFeedback)).thenReturn(expectedBookFeedback);

        // then step
        BookFeedback actualBookFeedback = bookFeedbackService.create(bookFeedbackDTO);
        String actualBookFeedbackJSON = ObjectExtensions.toJson(actualBookFeedback);

        // validate step
        Assert.assertEquals(actualBookFeedbackJSON, exepctedBookFeedbackJSON);

    }

    @Test
    void delete() {
        // init step
        BookFeedback expectedBookFeedback = getSampleTestFeedbacks().get(0);
        Optional<BookFeedback> optExpectedBookFeedback = Optional.of(expectedBookFeedback);
        // stub - when step
        Mockito.when(bookFeedbackRepository.findById(expectedBookFeedback.getId())).thenReturn(optExpectedBookFeedback);
        doNothing().when(bookFeedbackRepository).deleteById(expectedBookFeedback.getId());
        // then - validate step
        bookFeedbackService.delete(expectedBookFeedback.getId());
        Mockito.verify(bookFeedbackRepository, Mockito.times(1))
                .deleteById(expectedBookFeedback.getId());
    }

    private List<BookFeedback> getSampleTestFeedbacks() {
        List<BookFeedback> bookFeedbackTestSampleList = new ArrayList<>();
        bookFeedbackTestSampleList.add(new BookFeedback(1l,new User(),new Book(),"feedback1"));
        bookFeedbackTestSampleList.add(new BookFeedback(1l,new User(),new Book(),"feedback2"));
        bookFeedbackTestSampleList.add(new BookFeedback(1l,new User(),new Book(),"feedback3"));
        return bookFeedbackTestSampleList;
    }

}
