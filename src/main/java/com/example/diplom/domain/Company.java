package com.example.diplom.domain;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private String name_pr;
    private String tag;
    private String qr;
    private Integer ideal;
    private Integer real;

    private String company_name;


//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "category_id")
//    private Category category;

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "sub_category_id")
//    private SubCategory sub_category;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "founder_id")
    private Founder founder;


    @ManyToMany
    @JoinTable (name="company_sub_category",
            joinColumns=@JoinColumn (name="company_id"),
            inverseJoinColumns=@JoinColumn(name="sub_category_id"))
    private List<SubCategory> subCategories;



//
//    private String website;
//    private String logo;
//    private String video;

    @Enumerated(EnumType.STRING)
    private StageOfTheCompany stageOfTheCompany;

//
//    private MarketValidation marketValidation;
//
//    private String size_of_sales___;
//    private String status___;
//    private String priority___;
//    private String transfer_to___;
//    private String investment_process___;


    public Company() {
    }

    public Company(String name_pr, String tag, String qr, Integer ideal, Integer real) {
        this.name_pr = name_pr;
        this.tag = tag;
        this.qr = qr;
        this.ideal = ideal;
        this.real = real;
    }

    public String getName_pr() {
        return name_pr;
    }
    public void setName_pr(String name_pr) {
        this.name_pr = name_pr;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getTag() {
        return tag;
    }
    public void setTag(String tag) {
        this.tag = tag;
    }
    public String getQr() {
        return qr;
    }
    public void setQr(String qr) {
        this.qr = qr;
    }
    public Integer getIdeal() {
        return ideal;
    }
    public void setIdeal(Integer ideal) {
        this.ideal = ideal;
    }

    public Integer getReal() {
        return real;
    }
    public void setReal(Integer real) {
        this.real = real;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }
}
