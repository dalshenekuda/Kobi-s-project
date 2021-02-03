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
    private String family_name;
//    private String email;
//    private String mobile;
//    private String linkedin;
//    private String country;

}
