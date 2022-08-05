package com.berkozmen.library_automation_system.model.entity;

import com.berkozmen.library_automation_system.model.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Books")
@ApiModel(value = "Book Api model documentation", description = "Model")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "Unique id field of book object")
    private Long id;
    @ApiModelProperty(value = "Title field of book object")
    private String title;
    @ApiModelProperty(value = "Author field of book object")
    private String author;
    @ApiModelProperty(value = "ISBN field of book object")
    private long ISBN;
    @ApiModelProperty(value = "Publisher field of book object")
    private String publisher;
    @ApiModelProperty(value = "Published date field of book object")
    private String publishedDate;


}
