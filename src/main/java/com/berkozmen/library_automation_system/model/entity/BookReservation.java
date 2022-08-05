package com.berkozmen.library_automation_system.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Book_Reservations")
@ApiModel(value = "Book Reservation Api model documentation", description = "Model")
public class BookReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "Unique id field of BookReservation object")
    private Long id;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    @ApiModelProperty(value = "User field of BookReservation object")
    private User user;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "book_id",referencedColumnName = "id")
    @ApiModelProperty(value = "Book field of BookReservation object")
    private Book book;
    @Column(nullable = false)
    @CreationTimestamp
    @ApiModelProperty(value = "Start date field of BookReservation object")
    private Date startDate;
    @ApiModelProperty(value = "End date planned field of BookReservation object")
    private String endDatePlanned;
    @ApiModelProperty(value = "End date actual field of BookReservation object")
    private Date endDateActual;

}
