package com.berkozmen.library_automation_system.service;

import com.berkozmen.library_automation_system.utils.ObjectExtensions;
import com.berkozmen.library_automation_system.exception.EntityNotFoundException;
import com.berkozmen.library_automation_system.model.dto.BookReservationDTO;
import com.berkozmen.library_automation_system.model.entity.Book;
import com.berkozmen.library_automation_system.model.entity.BookReservation;
import com.berkozmen.library_automation_system.model.entity.User;
import com.berkozmen.library_automation_system.model.mapper.BookReservationMapper;
import com.berkozmen.library_automation_system.repository.BookReservationRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookReservationServiceTest {

    @Mock
    private BookReservationRepository bookReservationRepository;

    @Mock
    private BookReservationMapper bookReservationMapper;

    @InjectMocks
    private BookReservationService bookReservationService;

    @Test
    void getAllBookReservations() {
        // init step (JavaFaker kullan)
        List<BookReservation> expectedBookReservationList = getSampleTestBookReservations();
        String expectedListJSON = ObjectExtensions.toJson(expectedBookReservationList);

        //stub - when step
        Mockito.when(bookReservationRepository.findAll()).thenReturn(expectedBookReservationList);

        // then
        List<BookReservation> actualBookReservationList = bookReservationService.getAllBookReservations();
        String actualListJSON = ObjectExtensions.toJson(actualBookReservationList);

        // validate step
        Assert.assertEquals(expectedListJSON,actualListJSON);
        verify(bookReservationRepository,times(1)).findAll();

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
        //init
        BookReservation expectedBookReservation = getSampleTestBookReservations().get(0);
        Optional<BookReservation> optExpectedBookReservation = Optional.of(expectedBookReservation);
        String expectedBookReservationJson = ObjectExtensions.toJson(expectedBookReservation);

        //stub
        Mockito.when(bookReservationRepository.findById(expectedBookReservation.getId())).thenReturn(optExpectedBookReservation);
        //then
        BookReservation actualBookReservation = bookReservationService.getById(expectedBookReservation.getId());
        String actualBookReservationJson = ObjectExtensions.toJson(actualBookReservation);
        //validate
        Assert.assertEquals(expectedBookReservationJson,actualBookReservationJson);
        Mockito.verify(bookReservationRepository,Mockito.times(1)).findById(expectedBookReservation.getId());
    }

    @Test
    void getById_NOT_FOUND() {
        //init
        // stub - when step
        Mockito.when(bookReservationRepository.findById(any())).thenReturn(Optional.empty());
        // then
        // validate
        assertThrows(EntityNotFoundException.class,
                () -> {
                    bookReservationService.getById(any());
                });
    }

    @Test
    void getByUserId_successful() {
        //init
        BookReservation expectedBookReservation = getSampleTestBookReservations().get(0);
        Optional<BookReservation> optExpectedBookReservation = Optional.of(expectedBookReservation);
        String expectedBookReservationJSON = ObjectExtensions.toJson(expectedBookReservation);
        //stub
        Mockito.when(bookReservationRepository.findBookReservationByUserId(expectedBookReservation.getUser().getId()))
                .thenReturn(optExpectedBookReservation);
        //then
        BookReservation actualBookReservation = bookReservationService.getByUserId(expectedBookReservation.getUser().getId());
        String actualBookReservationJSON = ObjectExtensions.toJson(actualBookReservation);
        //validate
        Assert.assertEquals(expectedBookReservationJSON,actualBookReservationJSON);
        Mockito.verify(bookReservationRepository,Mockito.times(1)).findBookReservationByUserId(any());
    }

    @Test
    void getByUserId_NOT_FOUND() {
        //init
        // stub - when step
        Mockito.when(bookReservationRepository.findBookReservationByUserId(any())).thenReturn(Optional.empty());
        // then
        // validate
        assertThrows(EntityNotFoundException.class,
                () -> {
                    bookReservationService.getByUserId(any());
                });
    }

    @Test
    void create() {
        // init step
        BookReservationDTO bookReservationDTO = new BookReservationDTO();
        BookReservation expectedBookReservation = bookReservationMapper.toEntity(bookReservationDTO);
        String exepctedBookReservationJSON = ObjectExtensions.toJson(expectedBookReservation);

        // stub - when step
        Mockito.when(bookReservationRepository.save(expectedBookReservation)).thenReturn(expectedBookReservation);

        // then step
        BookReservation actualBookReservation = bookReservationService.create(bookReservationDTO);
        String actualBookReservationJSON = ObjectExtensions.toJson(actualBookReservation);

        // validate step
        Assert.assertEquals(exepctedBookReservationJSON, actualBookReservationJSON);
        verify(bookReservationRepository,times(1)).save(any());

    }

    @Test
    void delete() {
        // init step
        BookReservation expectedBookReservation = getSampleTestBookReservations().get(0);
        Optional<BookReservation> optExpectedBookReservation = Optional.of(expectedBookReservation);
        // stub - when step
        Mockito.when(bookReservationRepository.findById(expectedBookReservation.getId())).thenReturn(optExpectedBookReservation);
        doNothing().when(bookReservationRepository).deleteById(expectedBookReservation.getId());
        // then - validate step
        bookReservationService.delete(expectedBookReservation.getId());
        Mockito.verify(bookReservationRepository, Mockito.times(1))
                .deleteById(expectedBookReservation.getId());
    }

    @Test
    void update() {
        // init step
        BookReservationDTO bookReservationDTO = new BookReservationDTO(1L, 1L, 1L, new Date(), "endDatePlanned", new Date());
        BookReservation expectedBookReservation = new BookReservation(1L, new User(), new Book(), new Date(), "endDatePlanned", new Date());
        String expectedBookReservationJSON = ObjectExtensions.toJson(expectedBookReservation);
        Optional<BookReservation> optExpectedBookReservation = Optional.of(expectedBookReservation);
        // stub - when step
        Mockito.when(bookReservationMapper.toEntity(bookReservationDTO)).thenReturn(expectedBookReservation);
        Mockito.when(bookReservationRepository.findById(expectedBookReservation.getId())).thenReturn(optExpectedBookReservation);
        Mockito.when(bookReservationRepository.save(expectedBookReservation)).thenReturn(expectedBookReservation);
        // then - validate step
        BookReservation actualBookReservation = bookReservationService.update(1L, bookReservationDTO);
        String actualBookReservationJSON = ObjectExtensions.toJson(actualBookReservation);

        Assert.assertEquals(expectedBookReservationJSON, actualBookReservationJSON);
        verify(bookReservationRepository, times(1)).save(any());
    }

    private List<BookReservation> getSampleTestBookReservations() {
        List<BookReservation> expectedBookReservationList = new ArrayList<>();
        BookReservation bookReservation = new BookReservation(1L, new User(), new Book(), new Date(), "endDatePlanned", new Date());
        BookReservation bookReservation2 = new BookReservation(2L, new User(), new Book(), new Date(), "endDatePlanned2", new Date());
        BookReservation bookReservation3 = new BookReservation(3L, new User(), new Book(), new Date(), "endDatePlanned3", new Date());
        expectedBookReservationList.add(bookReservation);
        expectedBookReservationList.add(bookReservation2);
        expectedBookReservationList.add(bookReservation3);
        return expectedBookReservationList;
    }
}