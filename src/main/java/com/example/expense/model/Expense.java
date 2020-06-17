package com.example.expense.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.Instant;

//@Data is for getting and settings
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "expense")
public class Expense {
    @Id
    private long id;

    private Instant expenseDate;

    private String description;

    private String location;

    //many expense to one category
    @ManyToOne
    private Category category;

    //this prevents the server from sending this to the client
    @JsonIgnore
    //many expense to one user
    @ManyToOne
    private User user;

}
