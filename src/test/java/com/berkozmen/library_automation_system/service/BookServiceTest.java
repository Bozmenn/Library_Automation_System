package com.berkozmen.library_automation_system.service;

import com.berkozmen.library_automation_system.exception.EntityNotFoundException;
import com.berkozmen.library_automation_system.model.entity.Book;
import com.berkozmen.library_automation_system.repository.BookRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

/*    @Before*/

/*    @BeforeEach
    public void setup(){

    }*/

    @Test
    void getAllBooks() {
        // init step
        // JavaFaker kullan
        List<Book> expectedBookList = getSampleTestBooks();

        //stub - when step
        Mockito.when(bookRepository.findAll()).thenReturn(expectedBookList);

        // then - validate step
        List<Book> actualBookList = bookService.getAllBooks();

        Assert.assertEquals(expectedBookList.size(),actualBookList.size());
        // sorted list by ID with a comparator
        expectedBookList = expectedBookList.stream().sorted(getBookComparator()).collect(Collectors.toList());
        actualBookList = actualBookList.stream().sorted(getBookComparator()).collect(Collectors.toList());
        for(int i = 0; i< expectedBookList.size();i++){
            Book currentExpectedBook = expectedBookList.get(i);
            Book currentActualBook = actualBookList.get(i);
            Assert.assertEquals(currentExpectedBook.getId(),currentActualBook.getId());
            Assert.assertEquals(currentExpectedBook.getAuthor(),currentActualBook.getAuthor());

        }
    }

    @Test
    void getById_successful() {

        // init step
        Book expectedBook = getSampleTestBooks().get(0);
        Optional<Book> optionalExpectedBook = Optional.of(expectedBook);

        // stub - when step
        Mockito.when(bookRepository.findById(any())).thenReturn(optionalExpectedBook);

        // then - validate step
        Book actualBook = bookService.getById(10L);
        Assert.assertEquals(expectedBook.getId(),actualBook.getId());

    }

    @Test
    void getById_NOT_FOUND() {

        // stub - when step
        Mockito.when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        // then - validate step
        assertThrows(EntityNotFoundException.class,
                () -> {Book actualBook = bookService.getById(1L);});
    }

    @Test
    void getByTitle() {

        // init step
        // stub - when step
        // then - validate step
    }

    @Test
    void create() {
        // init step
        // stub - when step
        // then - validate step
    }

    @Test
    void delete() {
        // init step
        // stub - when step
        // then - validate step
    }

    @Test
    void update() {
        // init step
        // stub - when step
        // then - validate step
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
    private Comparator<Book> getBookComparator() {
        return (o1, o2) -> {
            if (o1.getId() - o2.getId() > 0)
                return -1;
            if (o1.getId() - o2.getId() == 0)
                return 0;
            return 1;
        };
    }
}