package com.berkozmen.library_automation_system.controller;

import com.berkozmen.library_automation_system.exception.handler.GenericExceptionHandler;
import com.berkozmen.library_automation_system.model.dto.BookDTO;
import com.berkozmen.library_automation_system.model.entity.Book;
import com.berkozmen.library_automation_system.service.BookService;
import com.berkozmen.library_automation_system.utils.ObjectExtensions;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.ArrayList;
import java.util.List;


@ExtendWith(MockitoExtension.class)
class BookControllerTest {

    private MockMvc mvc;

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    public void setup(){
        JacksonTester.initFields(this, new ObjectMapper());
        mvc = MockMvcBuilders.standaloneSetup(bookController)
                .setControllerAdvice(new GenericExceptionHandler()).build();
    }


    @Test
    void getAllBooks() throws Exception{
        //init
        List<Book> expectedList = getSampleTestBooks();
        String expectedListJSON = ObjectExtensions.toJson(expectedList);
        //stub
        Mockito.when(bookService.getAllBooks()).thenReturn(expectedList);
        //then
        MockHttpServletResponse response = mvc.perform(get("/books")
                        .accept(MediaType.APPLICATION_JSON))
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn().getResponse();
        //validate
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        List<Book> actualList = new ObjectMapper().
                readValue(response.getContentAsString(), new TypeReference<List<Book>>() {});
        String actualListJSON = ObjectExtensions.toJson(actualList);
        assertEquals(expectedListJSON,actualListJSON);
    }

    @Test
    void getBookById() throws Exception{
        //init
        Book expectedBook = getSampleTestBooks().get(0);
        String expectedBookJSON = ObjectExtensions.toJson(expectedBook);
        //stub
        Mockito.when(bookService.getById(Mockito.any())).thenReturn(expectedBook);
        //then
        MockHttpServletResponse response = mvc.perform(get("/books/getBookById/1")
                .accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse();
        //validate
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        Book actualBook = new ObjectMapper().readValue(response.getContentAsString(), new TypeReference<Book>() {});
        String actualBookJSON = ObjectExtensions.toJson(actualBook);
        assertEquals(expectedBookJSON,actualBookJSON);
    }

    @Test
    void getBookByTitle() throws Exception{
        //init
        Book expectedBook = getSampleTestBooks().get(0);
        String expectedBookJSON = ObjectExtensions.toJson(expectedBook);
        //stub
        Mockito.when(bookService.getByTitle(Mockito.any())).thenReturn(expectedBook);
        //then
        MockHttpServletResponse response = mvc.perform(get("/books/getBookByTitle/book1")
                        .accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print())
                        .andReturn().getResponse();
        //validate
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        Book actualBook = new ObjectMapper().readValue(response.getContentAsString(), new TypeReference<Book>() {
        });
        String actualBookJSON = ObjectExtensions.toJson(actualBook);
        assertEquals(expectedBookJSON,actualBookJSON);
    }

    @Test
    void createNewBook_successful() throws Exception {
        //init
        BookDTO bookDTO = new BookDTO(1L,"book1","author1",123L,"Publisher1","04/10/1994");
        Book expectedBook = getSampleTestBooks().get(2);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String expectedBookDTO = ow.writeValueAsString(bookDTO);
        //stub
        Mockito.when(bookService.create(bookDTO)).thenReturn(expectedBook);
        //then
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.post("/books/create")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(expectedBookDTO))
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse();
        //validate
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertEquals("Book successfully created",response.getContentAsString());
    }

    @Test
    void createNewBook_INTERNAL_SERVER_ERROR() throws Exception {
        //init
        BookDTO bookDTO = new BookDTO(1L,"book1","author1",123L,"Publisher1","04/10/1994");
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String expectedBookDTO = ow.writeValueAsString(bookDTO);
        //stub
        Mockito.when(bookService.create(bookDTO)).thenReturn(null);
        //then
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.post("/books/create")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(expectedBookDTO))
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse();
        //validate
        assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertEquals("Book could not created",response.getContentAsString());
    }

    @Test
    void deleteBook() throws Exception{
        //init
        //stub
        Mockito.doNothing().when(bookService).delete(Mockito.any());
        //then
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.delete("/books/delete/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse();
        //validate
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertEquals("Related book deleted successfully",response.getContentAsString());
    }

    @Test
    void updateBook() throws Exception{
        //init
        BookDTO bookDTO = new BookDTO(1L,"book1","author1",123L,"Publisher1","04/10/1994");
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String expectedBookDTO = ow.writeValueAsString(bookDTO);
        Book expectedBook = getSampleTestBooks().get(2);
        //stub
        Mockito.when(bookService.update(1L,bookDTO)).thenReturn(expectedBook);
        //then
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.put("/books/update/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(expectedBookDTO))
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse();
        //validate
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertEquals("Book successfully updated",response.getContentAsString());
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
}