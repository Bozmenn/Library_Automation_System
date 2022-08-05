package com.berkozmen.library_automation_system.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {


    private Long id;
    @NotBlank
    private String title;
    @NotBlank
    private String author;
    @NotNull
    private long ISBN;
    @NotBlank
    private String publisher;
    @NotBlank
    private String publishedDate;

}
