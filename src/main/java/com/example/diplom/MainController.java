package com.example.diplom;

import com.example.diplom.domain.Category;
import com.example.diplom.domain.Company;
import com.example.diplom.domain.Founder;
import com.example.diplom.domain.StageOfTheCompany;
import com.example.diplom.repos.CategoryRepo;
import com.example.diplom.repos.CompanyRepo;
import com.example.diplom.repos.FounderRepo;
import com.example.diplom.repos.SubCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

    @Controller
    public class MainController {
        @Autowired
        private CompanyRepo CompanyRepo;
        @Autowired
        private FounderRepo FounderRepo;
        @Autowired
        private CategoryRepo CategoryRepo;
        @Autowired
        private SubCategoryRepo SubCategoryRepo;

        @GetMapping
        public String test1(Model model) {
           StageOfTheCompany[] stageOfTheCompanies = StageOfTheCompany.values();

          //  List<String> stageOfTheCompanies= new ArrayList<>();

            Iterable<Company> companies = CompanyRepo.findAll();
           model.addAttribute("stageOfTheCompanies", stageOfTheCompanies);
            return "test1";
        }
        @GetMapping("/addCategory")
        public String addCategory(Model model) {
            Iterable<Category> categories =CategoryRepo.findAll();
            model.addAttribute("categories",categories);
            return "addCategory";
        }

        @PostMapping("/addCategory")
        public String addCategory(@RequestParam String category_name, Model model) {
            Iterable<Category> categories =CategoryRepo.findAll();
            model.addAttribute("categories",categories);

            Category category = new Category(category_name);
            CategoryRepo.save(category);
            return "addCategory";
        }





//        @GetMapping
//        public String main(Map<String, Object> model) {
////            Iterable<Company> companies = CompanyRepo.findAll();
////            model.put("companies", companies);
//            return "add";
//        }

        @PostMapping
        public String add(@RequestParam String founder_name, @RequestParam String company_name,@RequestParam String _stageOfCompany,  Model model)
        {
            StageOfTheCompany[] stageOfTheCompanies = StageOfTheCompany.values();
            model.addAttribute("stageOfTheCompanies", stageOfTheCompanies);

            Founder founder = new Founder(founder_name);
            FounderRepo.save(founder);
            StageOfTheCompany stageOfTheCompany=StageOfTheCompany.valueOf(_stageOfCompany);

            Company company = new Company(company_name,founder,stageOfTheCompany);
            CompanyRepo.save(company);

            return "test1";
        }
//
//        @PostMapping
//        public String add(@RequestParam String name_pr, @RequestParam String tag,@RequestParam String qr,
//                          @RequestParam Integer ideal, @RequestParam Integer real,  Map<String, Object> model)
//        {
//            Company company = new Company(name_pr, tag, qr,ideal,real);
//            messageRepo.save(company);
//            Iterable<Company> messages = messageRepo.findAll();
//            model.put("messages", messages);
//
//            return "main";
//        }


//        @PostMapping("filter")
//        public String filter(@RequestParam Integer filter, Map<String, Object> model) {
//            Iterable<Company> messages;
//                if(filter!=null) {
//                    messages = messageRepo.findByReal(filter);
//                    model.put("messages", messages);
//                }
//                return "fltr";
//        }


//        @PostMapping("check")
//        public void check(@RequestParam String check) {
//            //check = qr
//            if (check != null && !check.isEmpty()) {
//                boolean bool = messageRepo.existsByQr(check);
//                if(bool)
//                {
//                    Company company;
//                    company = messageRepo.findByQr(check);
//                    company.setReal(1);
//                    messageRepo.save(company);
//                }
//                else {
//
//                    Company company = new Company("unknown", "unknown", check,1,1);
//                    messageRepo.save(company);
//                }
//            }
//        }

}


