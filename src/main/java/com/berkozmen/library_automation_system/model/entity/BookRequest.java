package com.berkozmen.library_automation_system.model.entity;

import com.berkozmen.library_automation_system.model.Status;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Book_Requests")
@ApiModel(value = "Book Request Api model documentation", description = "Model")
public class BookRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "Unique id field of BookRequest object")
    private Long id;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    @ApiModelProperty(value = "User field of BookRequest object")
    private User user;
    @ApiModelProperty(value = "Requested book title field of BookRequest object")
    private String RequestedBookTitle;
    @ApiModelProperty(value = "Requested author title field of BookRequest object")
    private String RequestedBookAuthor;
    @Enumerated(EnumType.STRING)
    @ApiModelProperty(value = "Requested status field of BookRequest object")
    private Status status;




}
