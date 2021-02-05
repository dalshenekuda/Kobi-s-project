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
//    private String family_name;
//    private String email;
//    private String mobile;
//    private String linkedin;
//    private String country;

    public Founder(){
    }

    public Founder(String name){
        this.name=name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
