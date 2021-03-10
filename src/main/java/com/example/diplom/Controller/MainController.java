package com.example.diplom.Controller;

import com.example.diplom.domain.*;
import com.example.diplom.domain.enums.*;
import com.example.diplom.repos.CategoryRepo;
import com.example.diplom.repos.CompanyRepo;
import com.example.diplom.repos.FounderRepo;
import com.example.diplom.repos.SubCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

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

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public  String greeting(){
        return  "greeting";
    }



    @GetMapping("/Main")
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
        Iterable<Category> categories = CategoryRepo.findAll();
        model.addAttribute("categories", categories);
        return "addCategory";
    }

    @PostMapping("/addCategory")
    public String addCategory(@RequestParam String category_name, Model model) {

        Category category = new Category(category_name);
        CategoryRepo.save(category);
        Iterable<Category> categories = CategoryRepo.findAll();
        model.addAttribute("categories", categories);
        return "addCategory";
    }

    @GetMapping("/sub")
    public String addSub(Model model) {
        Iterable<Category> categories = CategoryRepo.findAll();
        model.addAttribute("categories", categories);

        Iterable<SubCategory> subCategories = SubCategoryRepo.findAll();
        model.addAttribute("subCategories", subCategories);
        return "sub";
    }

    @PostMapping("/sub")
    public String addSub(@RequestParam String sub_category_name, String category_name, Model model) {


        Category category = CategoryRepo.findByName(category_name);
        SubCategory subCategory = new SubCategory(sub_category_name, category);
        SubCategoryRepo.save(subCategory);

        Iterable<SubCategory> subCategories = SubCategoryRepo.findAll();
        model.addAttribute("subCategories", subCategories);

        Iterable<Category> categories = CategoryRepo.findAll();
        model.addAttribute("categories", categories);

        return "sub";
    }


    @PostMapping
    public String add(@RequestParam (required = false) String founder_name, @RequestParam (required = false)String founder_family_name,
                      @RequestParam (required = false) String founder_email, @RequestParam (required = false) String founder_mobile,
                      @RequestParam (required = false) String founder_linkedin, @RequestParam(required = false) String founder_country,
                      @RequestParam String company_name,
                      @RequestParam String _stageOfCompany, @RequestParam String _sizeOfTheSales, @RequestParam String _marketValidation,
                      @RequestParam String _investmentProcess, @RequestParam String _priorityCompany,
                      @RequestParam String _statusCompany, @RequestParam String _transferCompany,
                      @RequestParam (required = false)String[] subCategories_s, @RequestParam (required = false) String video,
                      @RequestParam (required = false) String website,
                      @RequestParam("file") MultipartFile file,@RequestParam("infoFile") MultipartFile infoFile, Model model) throws IOException {

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

        List<String> subCategories = new ArrayList<String>() {
        };

        List<SubCategory> subCategoriesObj = new ArrayList<SubCategory>();
        if(subCategories_s!=null) {
            subCategories = Arrays.asList(subCategories_s);///ЛИСТ СТРИНГОВ

            subCategories.forEach(nameS -> subCategoriesObj.add(SubCategoryRepo.findByName(nameS)));
        }
        Founder founder = new Founder(founder_name, founder_family_name, founder_email, founder_mobile, founder_linkedin, founder_country);
        FounderRepo.save(founder);

        StageOfTheCompany stageOfTheCompany = StageOfTheCompany.valueOf(_stageOfCompany);
        SizeOfTheSales sizeOfTheSales = SizeOfTheSales.valueOf(_sizeOfTheSales);
        MarketValidation marketValidation = MarketValidation.valueOf(_marketValidation);
        InvestmentProcess investmentProcess = InvestmentProcess.valueOf(_investmentProcess);
        PriorityCompany priorityCompany = PriorityCompany.valueOf(_priorityCompany);
        StatusCompany statusCompany = StatusCompany.valueOf(_statusCompany);
        TransferCompany transferCompany = TransferCompany.valueOf(_transferCompany);

        Company company = new Company(company_name, founder, stageOfTheCompany, sizeOfTheSales, marketValidation,
                investmentProcess, priorityCompany, statusCompany, transferCompany, subCategoriesObj, video, website);

        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFilename));
            company.setFilename(resultFilename);

        }

        if (infoFile != null && !infoFile.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + infoFile.getOriginalFilename();
            infoFile.transferTo(new File(uploadPath + "/" + resultFilename));
            company.setInfoFilename(resultFilename);

        }

        CompanyRepo.save(company);

        return "Main";
    }
}


