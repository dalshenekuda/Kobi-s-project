package com.example.diplom.domain;
import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import  com.example.diplom.domain.StageOfTheCompany;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String company_name;

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

    public Company(String company_name, Founder founder,StageOfTheCompany stageOfTheCompany) {
        this.company_name=company_name;
        this.founder=founder;
        this.stageOfTheCompany=stageOfTheCompany;

    }


    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFounder(Founder founder) {
        this.founder = founder;
    }

    public StageOfTheCompany getStageOfTheCompany() {
        return stageOfTheCompany;
    }

    public void setStageOfTheCompany(StageOfTheCompany stageOfTheCompany) {
        this.stageOfTheCompany = stageOfTheCompany;
    }
}
