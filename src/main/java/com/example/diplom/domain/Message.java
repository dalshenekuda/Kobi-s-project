package com.example.diplom.domain;

import javax.persistence.*;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String name_pr;
    private String tag;
    private String qr;
    private Integer ideal;
    private Integer real;

    private String company_name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category_id;

    private Integer sub_category_id;
    private String website;
    private String logo;
    private String founder_id;
    private String video;

    @Enumerated(EnumType.STRING)
    private StageOfTheCompany stageOfTheCompany;

    private MarketValidation marketValidation;

    private String size_of_sales___;
    private String status___;
    private String priority___;
    private String transfer_to___;
    private String investment_process___;


    public Message() {
    }

    public Message(String name_pr, String tag, String qr,Integer ideal,Integer real) {
        this.name_pr = name_pr;
        this.tag = tag;
        this.qr = qr;
        this.ideal = ideal;
        this.real = real;
    }

    public Integer getSub_category_id() {
        return sub_category_id;
    }

    public void setSub_category_id(Integer sub_category_id) {
        this.sub_category_id = sub_category_id;
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
}
