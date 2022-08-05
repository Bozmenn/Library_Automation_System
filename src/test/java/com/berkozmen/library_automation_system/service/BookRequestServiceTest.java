package com.berkozmen.library_automation_system.service;

import com.berkozmen.library_automation_system.utils.ObjectExtensions;
import com.berkozmen.library_automation_system.exception.EntityNotFoundException;
import com.berkozmen.library_automation_system.model.dto.BookRequestDTO;
import com.berkozmen.library_automation_system.model.entity.BookRequest;
import com.berkozmen.library_automation_system.model.Status;
import com.berkozmen.library_automation_system.model.entity.User;
import com.berkozmen.library_automation_system.model.mapper.BookRequestMapper;
import com.berkozmen.library_automation_system.repository.BookRequestRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookRequestServiceTest {

    @Mock
    private BookRequestRepository bookRequestRepository;

    @Mock
    private BookRequestMapper bookRequestMapper;

    @InjectMocks
    private BookRequestService bookRequestService;

    @Test
    void getAllBookRequests() {
        // init step (JavaFaker kullan)
        List<BookRequest> expectedBookRequestList = getSampleTestBookRequests();
        String expectedListJSON = ObjectExtensions.toJson(expectedBookRequestList);

        //stub - when step
        Mockito.when(bookRequestRepository.findAll()).thenReturn(expectedBookRequestList);

        // then
        List<BookRequest> actualBookRequestList = bookRequestService.getAllBookRequests();
        String actualListJSON = ObjectExtensions.toJson(actualBookRequestList);

        // validate step
        Assert.assertEquals(actualListJSON,expectedListJSON);
        verify(bookRequestRepository,times(1)).findAll();
    }

    @Test
    void getById_successful() {
        // init step
        BookRequest expectedBookRequest = getSampleTestBookRequests().get(0);
        Optional<BookRequest> optionalExpectedBook = Optional.of(expectedBookRequest);
        String expectedBookRequestJSON = ObjectExtensions.toJson(expectedBookRequest);

        // stub - when step
        Mockito.when(bookRequestRepository.findById(expectedBookRequest.getId())).thenReturn(optionalExpectedBook);

        // then
        BookRequest actualBookRequest = bookRequestService.getById(expectedBookRequest.getId());
        String actualBookRequestJSON = ObjectExtensions.toJson(actualBookRequest);

        // validate step
        Assert.assertEquals(expectedBookRequestJSON,actualBookRequestJSON);
        Mockito.verify(bookRequestRepository,times(1)).findById(expectedBookRequest.getId());

    }

    @Test
    void getById_NOT_FOUND() {
        // stub - when step
        Mockito.when(bookRequestRepository.findById(any())).thenReturn(Optional.empty());

        // then

        // validate
        assertThrows(EntityNotFoundException.class,
                () -> {bookRequestService.getById(any());});

    }

    @Test
    void getByUserId_successful() {
        // init step
        BookRequest expectedBookRequest = getSampleTestBookRequests().get(0);
        Optional<BookRequest> optionalExpectedBook = Optional.of(expectedBookRequest);
        String expectedBookRequestJSON = ObjectExtensions.toJson(expectedBookRequest);

        // stub - when step
        Mockito.when(bookRequestRepository.findBookRequestByUserId(expectedBookRequest.getUser().getId())).thenReturn(optionalExpectedBook);

        // then
        BookRequest actualBookRequest = bookRequestService.getByUserId(expectedBookRequest.getUser().getId());
        String actualBookRequestJSON = ObjectExtensions.toJson(actualBookRequest);

        // validate step
        Assert.assertEquals(expectedBookRequestJSON,actualBookRequestJSON);
        verify(bookRequestRepository,times(1))
                .findBookRequestByUserId(expectedBookRequest.getUser().getId());

    }

    @Test
    void getByUserId_NOT_FOUND() {
        // stub - when step
        Mockito.when(bookRequestRepository.findBookRequestByUserId(any())).thenReturn(Optional.empty());

        // then

        // validate
        assertThrows(EntityNotFoundException.class,
                () -> {bookRequestService.getByUserId(any());});

    }

    @Test
    void create() {
        // init step
        BookRequestDTO bookRequestDTO = new BookRequestDTO();
        BookRequest expectedBookRequest = bookRequestMapper.toEntity(bookRequestDTO);
        String exepctedBookRequestJSON = ObjectExtensions.toJson(expectedBookRequest);

        // stub - when step
        Mockito.when(bookRequestRepository.save(expectedBookRequest)).thenReturn(expectedBookRequest);

        // then step
        BookRequest actuaBookRequest = bookRequestService.create(bookRequestDTO);
        String actualBookRequestJSON = ObjectExtensions.toJson(actuaBookRequest);

        // validate step
        Assert.assertEquals(actualBookRequestJSON, exepctedBookRequestJSON);

    }

    @Test
    void delete() {
        // init step
        BookRequest expectedBookRequest = getSampleTestBookRequests().get(0);
        Optional<BookRequest> optExpectedBookRequest = Optional.of(expectedBookRequest);
        // stub - when step
        Mockito.when(bookRequestRepository.findById(expectedBookRequest.getId())).thenReturn(optExpectedBookRequest);
        doNothing().when(bookRequestRepository).deleteById(expectedBookRequest.getId());
        // then - validate step
        bookRequestService.delete(expectedBookRequest.getId());
        Mockito.verify(bookRequestRepository, Mockito.times(1)).deleteById(expectedBookRequest.getId());
    }

    @ParameterizedTest
    @CsvSource({
            "1,1,0",
            "2,0,1"
    })
    void updateStatus_successful(Long id, Integer status, Integer index) {
        //init
        BookRequest expectedBookRequest = getSampleTestBookRequests().get(index);
        Optional<BookRequest> optExpectedBookRequest = Optional.of(expectedBookRequest);
        String expectedBookRequestJSON = ObjectExtensions.toJson(expectedBookRequest);
        //stub
        Mockito.when(bookRequestRepository.findById(id)).thenReturn(optExpectedBookRequest);
        Mockito.when(bookRequestRepository.save(expectedBookRequest)).thenReturn(expectedBookRequest);
        //then
        BookRequest actualBookRequest = bookRequestService.updateStatus(id, status);
        String actualBookRequestJSON = ObjectExtensions.toJson(actualBookRequest);
        //validate
        Assert.assertEquals(expectedBookRequestJSON,actualBookRequestJSON);
        verify(bookRequestRepository,times(1)).findById(id);
        verify(bookRequestRepository,times(1)).save(any());

    }

    @Test
    void updateStatus_NOT_FOUND() {
        // stub - when step
        Mockito.when(bookRequestRepository.findById(any())).thenReturn(Optional.empty());

        // then

        // validate
        assertThrows(EntityNotFoundException.class,
                () -> {bookRequestService.updateStatus(1L,1);});

    }

    private List<BookRequest> getSampleTestBookRequests() {
        List<BookRequest> bookRequestList = new ArrayList<>();
        BookRequest bookRequest = new BookRequest(1L, new User(), "title1", "author1", Status.APPROVED);
        BookRequest bookRequest2 = new BookRequest(2L, new User(), "title2", "author2", Status.DENIED);
        BookRequest bookRequest3 = new BookRequest(3L, new User(), "title3", "author3", Status.APPROVED);
        bookRequestList.add(bookRequest);
        bookRequestList.add(bookRequest2);
        bookRequestList.add(bookRequest3);
        return bookRequestList;
    }
}