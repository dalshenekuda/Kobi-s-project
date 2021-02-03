package com.example.diplom.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "SubCategory")
public class SubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sub_category_id;

    private String name;


//    @OneToMany(mappedBy="sub_category",fetch = FetchType.EAGER)
//    private List<Company> companies;
//
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;


    @ManyToMany
    @JoinTable(name="company_sub_category",
            joinColumns=@JoinColumn(name="sub_category_id"),
            inverseJoinColumns=@JoinColumn(name="company_id"))
    private List<Company> companies;

}
