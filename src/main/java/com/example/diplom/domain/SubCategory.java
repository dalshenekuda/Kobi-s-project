package com.example.diplom.domain;

import javax.persistence.*;

@Entity
@Table(name = "SubCategory")
public class SubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Long category_id;

}
