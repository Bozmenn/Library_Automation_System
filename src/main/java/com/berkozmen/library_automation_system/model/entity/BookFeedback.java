package com.berkozmen.library_automation_system.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Book_Feedbacks")
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Book Feedback Api model documentation", description = "Model")
public class BookFeedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "Unique id field of BookFeedback object")
    private Long id;
    @ApiModelProperty(value = "User field of BookFeedback object")
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;
    @ApiModelProperty(value = "Book field of BookFeedback object")
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "book_id",referencedColumnName = "id")
    private Book book;
    @ApiModelProperty(value = "Feedback field of BookFeedback object")
    private String feedback;


}
