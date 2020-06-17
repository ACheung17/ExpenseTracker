package com.example.expense.model;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
@Table(name = "category")
public class Category {
    @Id
    private long id;

    @NonNull
    //name of category eg. travel, grocery...
    private String name;

}


