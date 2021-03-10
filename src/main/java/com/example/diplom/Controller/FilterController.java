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
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.expression.Lists;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Controller
public class FilterController {
    @Autowired
    private CompanyRepo CompanyRepo;
    @Autowired
    private FounderRepo FounderRepo;
    @Autowired
    private CategoryRepo CategoryRepo;
    @Autowired
    private SubCategoryRepo SubCategoryRepo;

    @GetMapping("/view")
    public String view(Model model) {
        Iterable<Company> companies = CompanyRepo.findAll();
        model.addAttribute("companies", companies);

        Iterable<Founder> foundersList = FounderRepo.findAll();
        model.addAttribute("foundersList", foundersList);


        Iterable<Category> CategoriesList = CategoryRepo.findAll();
        List<Category> list1 = new ArrayList<>();
        CategoriesList.forEach(list1::add);

        model.addAttribute("CategoriesList", list1);

        Iterable<SubCategory> subCategoriesList = SubCategoryRepo.findAll();
       // List<Category> list1 = new ArrayList<>();
       // CategoriesList.forEach(list1::add);

        model.addAttribute("subCategoriesList", subCategoriesList);


        StageOfTheCompany[] stageOfTheCompanies = StageOfTheCompany.values();
        List<String> s = new ArrayList<>();
        s.add("NONE");
        for (StageOfTheCompany e : stageOfTheCompanies) {
            s.add(e + "");
        }
        model.addAttribute("stageOfTheCompanies", s);

        MarketValidation[] marketValidations = MarketValidation.values();
        List<String> m = new ArrayList<>();
        m.add("NONE");
        for (MarketValidation e : marketValidations) {
            m.add(e + "");
        }
        model.addAttribute("marketValidations", m);

        StatusCompany[] statusCompanies = StatusCompany.values();
        List<String> st = new ArrayList<>();
        st.add("NONE");
        for (StatusCompany e : statusCompanies) {
            st.add(e + "");
        }
        model.addAttribute("statusCompanies", st);

        PriorityCompany[] priorityCompanies = PriorityCompany.values();
        List<String> p = new ArrayList<>();
        p.add("NONE");
        for (PriorityCompany e : priorityCompanies) {
            p.add(e + "");
        }
        model.addAttribute("priorityCompanies", p);

        TransferCompany[] transferCompanies = TransferCompany.values();
        List<String> t = new ArrayList<>();
        t.add("NONE");
        for (TransferCompany e : transferCompanies) {
            t.add(e + "");
        }
        model.addAttribute("transferCompanies", t);

        InvestmentProcess[] investmentProcesses = InvestmentProcess.values();
        List<String> i = new ArrayList<>();
        i.add("NONE");
        for (InvestmentProcess e : investmentProcesses) {
            i.add(e + "");
        }
        model.addAttribute("investmentProcesses", i);

        return "view";
    }

    //    @RequestParam String founder,@RequestParam String category,
    @PostMapping("/view")
    public String filter(@RequestParam(required = false) List<String> _founders,@RequestParam (required = false) List<String> _categories,
                         @RequestParam String _stage,
                         @RequestParam String _market, @RequestParam String _status,
                         @RequestParam String _priory, @RequestParam String _transfer,
                         @RequestParam String _investment, @RequestParam String fileName, Model model) {


        Iterable<Company> companies = CompanyRepo.findAll();
        model.addAttribute("companies", companies);

        Iterable<Founder> foundersList = FounderRepo.findAll();
        model.addAttribute("foundersList", foundersList);

        Iterable<Category> CategoriesList = CategoryRepo.findAll();
        List<Category> list1 = new ArrayList<>();

        CategoriesList.forEach(list1::add);

        model.addAttribute("CategoriesList", list1);


        StageOfTheCompany[] stageOfTheCompanies = StageOfTheCompany.values();
        List<String> s = new ArrayList<>();
        s.add("NONE");
        for (StageOfTheCompany e : stageOfTheCompanies) {
            s.add(e + "");
        }
        model.addAttribute("stageOfTheCompanies", s);

        MarketValidation[] marketValidations = MarketValidation.values();
        List<String> m = new ArrayList<>();
        m.add("NONE");
        for (MarketValidation e : marketValidations) {
            m.add(e + "");
        }
        model.addAttribute("marketValidations", m);

        StatusCompany[] statusCompanies = StatusCompany.values();
        List<String> st = new ArrayList<>();
        st.add("NONE");
        for (StatusCompany e : statusCompanies) {
            st.add(e + "");
        }
        model.addAttribute("statusCompanies", st);

        PriorityCompany[] priorityCompanies = PriorityCompany.values();
        List<String> p = new ArrayList<>();
        p.add("NONE");
        for (PriorityCompany e : priorityCompanies) {
            p.add(e + "");
        }
        model.addAttribute("priorityCompanies", p);

        TransferCompany[] transferCompanies = TransferCompany.values();
        List<String> t = new ArrayList<>();
        t.add("NONE");
        for (TransferCompany e : transferCompanies) {
            t.add(e + "");
        }
        model.addAttribute("transferCompanies", t);

        InvestmentProcess[] investmentProcesses = InvestmentProcess.values();
        List<String> i = new ArrayList<>();
        i.add("NONE");
        for (InvestmentProcess e : investmentProcesses) {
            i.add(e + "");
        }
        model.addAttribute("investmentProcesses", i);


        StageOfTheCompany stage = null;
        if (!_stage.equalsIgnoreCase("NONE"))
            stage = StageOfTheCompany.valueOf(_stage);

        MarketValidation market = null;
        if (!_market.equalsIgnoreCase("NONE"))
            market = MarketValidation.valueOf(_market);

        StatusCompany status = null;
        if (!_status.equalsIgnoreCase("NONE"))
            status = StatusCompany.valueOf(_status);

        PriorityCompany priory = null;
        if (!_priory.equalsIgnoreCase("NONE"))
            priory = PriorityCompany.valueOf(_priory);

        TransferCompany transfer = null;
        if (!_transfer.equalsIgnoreCase("NONE"))
            transfer = TransferCompany.valueOf(_transfer);

        InvestmentProcess investment = null;
        if (!_investment.equalsIgnoreCase("NONE"))
            investment = InvestmentProcess.valueOf(_investment);

        companies = CompanyRepo.findByStageOfTheCompanyAndMarketValidationAndStatusCompanyAndPriorityCompanyAndTransferCompanyAndInvestmentProcess(
                stage, market, status, priory, transfer, investment);


        List<Company> companiesAfterEnumsFilter = new ArrayList<>();
        List<Company> companiesTmp = new ArrayList<>();

        if ((_founders != null)) {
            Founder founder = null;

            companies.forEach(companiesTmp::add);
            String s1;
            String s2;

            for (String e : _founders) {
                String[] str = e.split(" ");
                s1 = str[0];
                s2 = str[1];
                founder = FounderRepo.findByNameAndFamilyName(s1, s2);
                for (Company c : companiesTmp) {
                    if (c.getFounder().equalsForFilter(founder)) {
                        companiesAfterEnumsFilter.add(c);
                    }
                }
            }

        }
        if(companiesAfterEnumsFilter.isEmpty())
            companies.forEach(companiesAfterEnumsFilter::add);

        //  companiesAfterEnumsFilter

            List<Company> res= new ArrayList<>();

            companiesTmp.clear();
            if ((_categories != null)) {
                Category category = null;

                for (String e : _categories) {
                    category = CategoryRepo.findByName(e);

                    for (Company c : companiesAfterEnumsFilter) {
                        if (c.getCategories().contains(category)) {
                            companiesTmp.add(c);
                        }
                    }
                }
                companiesTmp.forEach(res::add);
            }else
                companiesAfterEnumsFilter.forEach(res::add);

            Set<Company> resS= new HashSet<>(res);
        SaveFiles.saveFileXL(res,fileName);


         model.addAttribute("companies",resS);

            return "view";
        }


    @GetMapping(value = "/img/{imageUrl}")
    public @ResponseBody
    byte[] image(@PathVariable String imageUrl) throws IOException {
        String url = "C:/Java/Kobi-s-project/uploads/" + imageUrl; //здесь указываете СВОЙ путь к папке с картинками
//         String url = ${upload.path} + imageUrl;
      //  System.out.println("${upload.path}");
        InputStream in = new FileInputStream(url);
        return IOUtils.toByteArray(in);
    }
    @GetMapping(value = "/file/{fileUrl}")
    public @ResponseBody
    byte[] file(@PathVariable String fileUrl) throws IOException {
        String url = "C:/Java/Kobi-s-project/uploads/" + fileUrl; //здесь указываете СВОЙ путь к папке с картинками
//         String url = ${upload.path} + imageUrl;
        //  System.out.println("${upload.path}");
        InputStream in = new FileInputStream(url);
        return IOUtils.toByteArray(in);
    }

//    @RequestMapping(value="/file/{fileUrl}", method=RequestMethod.GET)
//    @ResponseBody
//    public File downloadFile(@Param(value="id") Long id) {
//        Product product = productRepo.findOne(id);
//        return new File(new File(product.getFileUrl()));
//    }

//"${upload.path}"


    }
