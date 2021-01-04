package com.example.diplom.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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




    public Message() {
    }

    public Message(String name_pr, String tag, String qr,Integer ideal,Integer real) {
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
}
