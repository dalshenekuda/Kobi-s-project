package com.example.diplom.domain;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Founder")
public class Founder {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer founder_id;

    @OneToMany(mappedBy="founder",fetch = FetchType.EAGER)
    private List<Company> companies;

    private String name;
    private String familyName;
    private String email;
    private String mobile;
    private String linkedin;
    private String country;

    public Founder(){
    }

    public Founder(String name, String familyName, String email, String mobile, String linkedin, String country){
        this.name=name;
        this.familyName = familyName;
        this.email = email;
        this.mobile = mobile;
        this.linkedin = linkedin;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public String getCountry() {
        return country;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFamilyName(String family_name) {
        this.familyName = family_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getFounder_id() {
        return founder_id;
    }

    public void setFounder_id(Integer founder_id) {
        this.founder_id = founder_id;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }
    @Override
    public String toString()
    {
        return ""+name+" "+ familyName;
    }


    public  boolean equalsForFilter(Founder founder) {
        boolean b=true;
        if (this.name!=founder.name || this.familyName !=founder.familyName)
            b=false;
     return b;
    }
}
