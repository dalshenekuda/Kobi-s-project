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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;


    @ManyToMany
    @JoinTable(name="company_sub_category",
            joinColumns=@JoinColumn(name="sub_category_id"),
            inverseJoinColumns=@JoinColumn(name="company_id"))
    private List<Company> companies;

    public SubCategory(){}

    public SubCategory(String name,Category category){
        this.name = name;
        this.category=category;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSub_category_id() {
        return sub_category_id;
    }

    public void setSub_category_id(Integer sub_category_id) {
        this.sub_category_id = sub_category_id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
