package com.example.diplom.Controller;

import com.example.diplom.domain.Category;
import com.example.diplom.domain.Company;
import com.example.diplom.domain.Founder;
import com.example.diplom.domain.SubCategory;
import com.example.diplom.domain.enums.*;
import com.example.diplom.methods.SaveFiles;
import com.example.diplom.repos.CategoryRepo;
import com.example.diplom.repos.CompanyRepo;
import com.example.diplom.repos.FounderRepo;
import com.example.diplom.repos.SubCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.expression.Lists;


import java.util.*;


@RestController
@RequestMapping("test")
public class TestController {
    @Autowired
    private CompanyRepo CompanyRepo;
    @Autowired
    private FounderRepo FounderRepo;
    @Autowired
    private CategoryRepo CategoryRepo;
    @Autowired
    private SubCategoryRepo SubCategoryRepo;


    private List<Map<String, String>> messages = new ArrayList<Map<String, String>>()   {{



        add(new HashMap<String, String>() {{
            put("id", "1");
            put("text", "First message");
            put("dwd","dwdw");
        }});
        add(new HashMap<String, String>() {{
            put("id", "2");
            put("text", "Second message");
        }});
        add(new HashMap<String, String>() {{
            put("id", "3");
            put("text", "Third message");
        }});
    }};


    @GetMapping
    public Iterable<Founder> list(Model model) {
//        Iterable<Company> companies = CompanyRepo.findAll();
//        List<Company> c = new ArrayList<>();
//        companies.forEach(c::add);
        Iterable<Founder> f = FounderRepo.findAll();

        //model.addAttribute("companies", companies);
        return f;
    }
}