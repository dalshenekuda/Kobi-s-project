package com.example.diplom.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer category_id;
    private String name;

//    @OneToMany(mappedBy="category",fetch = FetchType.EAGER)
//    private List<Company> companies;

    @OneToMany(mappedBy="category",fetch = FetchType.EAGER)
    private List<SubCategory> subCategories;





}
