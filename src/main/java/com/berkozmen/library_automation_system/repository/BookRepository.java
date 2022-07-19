package com.berkozmen.library_automation_system.repository;

import com.berkozmen.library_automation_system.model.Book;
import org.springframework.stereotype.Repository;



import java.util.List;

@Repository
public class BookRepository {

    private List<Book> bookList = List.of(
            new Book(1,"kitap1","ahmet",10,12345,"Palme","2010"),
            new Book(2,"kitap2","mehmet",9,54321,"Pan","2015"));


    public List<Book> findAll(){
        return bookList;
    }


}


