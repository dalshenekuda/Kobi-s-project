package com.example.diplom;

import com.example.diplom.domain.*;
import com.example.diplom.domain.enums.*;
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
import java.util.Arrays;
import java.util.List;

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
            Iterable<SubCategory> subCategoriesList = SubCategoryRepo.findAll();
            model.addAttribute("subCategoriesList", subCategoriesList);


            Iterable<Category> CategoriesList = CategoryRepo.findAll();
            model.addAttribute("CategoriesList", CategoriesList);

            StageOfTheCompany[] stageOfTheCompanies = StageOfTheCompany.values();
            model.addAttribute("stageOfTheCompanies", stageOfTheCompanies);
           // Iterable<Company> companies = CompanyRepo.findAll();

            SizeOfTheSales[] sizeOfTheSales1 = SizeOfTheSales.values();
            model.addAttribute("sizeOfTheSales1", sizeOfTheSales1);

            MarketValidation[] marketValidations = MarketValidation.values();
            model.addAttribute("marketValidations", marketValidations);

            InvestmentProcess[] investmentProcesses = InvestmentProcess.values();
            model.addAttribute("investmentProcesses", investmentProcesses);

            PriorityCompany[] priorityCompanies = PriorityCompany.values();
            model.addAttribute("priorityCompanies", priorityCompanies);

            StatusCompany[] statusCompanies = StatusCompany.values();
            model.addAttribute("statusCompanies", statusCompanies);

            TransferCompany[] transferCompanies = TransferCompany.values();
            model.addAttribute("transferCompanies", transferCompanies);

            return "Main";
        }
        @GetMapping("/addCategory")
        public String addCategory(Model model) {
            Iterable<Category> categories =CategoryRepo.findAll();
            model.addAttribute("categories",categories);
            return "addCategory";
        }

        @PostMapping("/addCategory")
        public String addCategory(@RequestParam String category_name, Model model) {


            Category category = new Category(category_name);
            CategoryRepo.save(category);
            Iterable<Category> categories =CategoryRepo.findAll();
            model.addAttribute("categories",categories);
            return "addCategory";
        }

        @GetMapping("/sub")
        public String addSub(Model model) {
            Iterable<Category> categories =CategoryRepo.findAll();
            model.addAttribute("categories",categories);

            Iterable<SubCategory> subCategories = SubCategoryRepo.findAll();
            model.addAttribute("subCategories",subCategories);
            return "sub";
        }

        @PostMapping("/sub")
        public String addSub(@RequestParam String sub_category_name,String category_name, Model model) {



            Category category = CategoryRepo.findByName(category_name);
            SubCategory subCategory = new SubCategory(sub_category_name,category);
            SubCategoryRepo.save(subCategory);

            Iterable<SubCategory> subCategories = SubCategoryRepo.findAll();
            model.addAttribute("subCategories",subCategories);

            Iterable<Category> categories =CategoryRepo.findAll();
            model.addAttribute("categories",categories);

            return "sub";
        }

//        @GetMapping
//        public String main(Map<String, Object> model) {
////            Iterable<Company> companies = CompanyRepo.findAll();
////            model.put("companies", companies);
//            return "add";
//        }


    @PostMapping
    public String add(@RequestParam String founder_name, @RequestParam String founder_family_name,
                      @RequestParam String founder_email, @RequestParam String founder_mobile,
                      @RequestParam String founder_linkedin, @RequestParam String founder_country, @RequestParam String company_name,
                      @RequestParam String _stageOfCompany,@RequestParam String _sizeOfTheSales, @RequestParam String _marketValidation,
                      @RequestParam String _investmentProcess, @RequestParam String _priorityCompany,
                      @RequestParam String _statusCompany, @RequestParam String _transferCompany,
                      @RequestParam String[] subCategories_s, Model model)
    {

        Iterable<SubCategory> subCategoriesList = SubCategoryRepo.findAll();
        model.addAttribute("subCategoriesList", subCategoriesList);

        Iterable<Category> CategoriesList = CategoryRepo.findAll();
        model.addAttribute("CategoriesList", CategoriesList);

        StageOfTheCompany[] stageOfTheCompanies = StageOfTheCompany.values();
        model.addAttribute("stageOfTheCompanies", stageOfTheCompanies);

        SizeOfTheSales[] sizeOfTheSales1 = SizeOfTheSales.values();
        model.addAttribute("sizeOfTheSales1", sizeOfTheSales1);

        MarketValidation[] marketValidations = MarketValidation.values();
        model.addAttribute("marketValidations", marketValidations);

        InvestmentProcess[] investmentProcesses = InvestmentProcess.values();
        model.addAttribute("investmentProcesses", investmentProcesses);

        PriorityCompany[] priorityCompanies = PriorityCompany.values();
        model.addAttribute("priorityCompanies", priorityCompanies);

        StatusCompany[] statusCompanies = StatusCompany.values();
        model.addAttribute("statusCompanies", statusCompanies);

        TransferCompany[] transferCompanies = TransferCompany.values();
        model.addAttribute("transferCompanies", transferCompanies);

        List<String> subCategories= new ArrayList<String>(){};
        List<SubCategory> subCategoriesObj = new ArrayList<SubCategory>();
        subCategories=Arrays.asList(subCategories_s);///ЛИСТ СТРИНГОВ

        subCategories.forEach(nameS ->subCategoriesObj.add(SubCategoryRepo.findByName(nameS)));

        Founder founder = new Founder(founder_name, founder_family_name, founder_email, founder_mobile, founder_linkedin, founder_country);
        FounderRepo.save(founder);

        StageOfTheCompany stageOfTheCompany=StageOfTheCompany.valueOf(_stageOfCompany);
        SizeOfTheSales sizeOfTheSales = SizeOfTheSales.valueOf(_sizeOfTheSales);
        MarketValidation marketValidation = MarketValidation.valueOf(_marketValidation);
        InvestmentProcess investmentProcess = InvestmentProcess.valueOf(_investmentProcess);
        PriorityCompany priorityCompany = PriorityCompany.valueOf(_priorityCompany);
        StatusCompany statusCompany = StatusCompany.valueOf(_statusCompany);
        TransferCompany transferCompany = TransferCompany.valueOf(_transferCompany);

        Company company = new Company(company_name, founder, stageOfTheCompany, sizeOfTheSales, marketValidation,
                investmentProcess, priorityCompany, statusCompany, transferCompany, subCategoriesObj);


        return "Main";
    }

    @GetMapping("/view")
    public String view(Model model) {
        Iterable<Company> companies =CompanyRepo.findAll();
        model.addAttribute("companies",companies);
        return "view";
    }

//    @PostMapping("/view")
//    public String view(@RequestParam String category_name, Model model) {
//
//
//        Iterable<Company> companies =CompanyRepo.findAll();
//        model.addAttribute("companies",companies);
//
//        Iterable<Category> categories =CategoryRepo.findAll();
//        model.addAttribute("categories",categories);
//        return "view";
//    }
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


