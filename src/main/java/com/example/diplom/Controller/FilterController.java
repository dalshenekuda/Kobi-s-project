package com.example.diplom.Controller;
import com.example.diplom.domain.Category;
import com.example.diplom.domain.Company;
import com.example.diplom.domain.Founder;
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
import org.thymeleaf.expression.Lists;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        List<String> f = new ArrayList<>();
        f.add("NONE");
        for (Founder e : foundersList) {
            f.add(e + "");
        }
        model.addAttribute("foundersList", f);


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

        return "view";
    }

    //    @RequestParam String founder,@RequestParam String category,
    @PostMapping("/view")
    public String filter(@RequestParam String _founder,@RequestParam String _stage,
                         @RequestParam String _market, @RequestParam String _status,
                         @RequestParam String _priory, @RequestParam String _transfer, @RequestParam String _investment, Model model) {


        Iterable<Company> companies = CompanyRepo.findAll();
        model.addAttribute("companies", companies);

        Iterable<Founder> foundersList = FounderRepo.findAll();
        List<String> f = new ArrayList<>();
        f.add("NONE");
        for (Founder e : foundersList) {
            f.add(e + "");
        }
        model.addAttribute("foundersList", f);

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






        Founder founder = null;
        if (!_founder.equals("NONE"))
               founder= FounderRepo.findAllByName(_founder);

        StageOfTheCompany stage = null;
        if (!_stage.equals("NONE"))
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

        companies = CompanyRepo.findByFounderAndStageOfTheCompanyAndMarketValidationAndStatusCompanyAndPriorityCompanyAndTransferCompanyAndInvestmentProcess(founder,stage, market,
                status, priory, transfer, investment);

        model.addAttribute("companies", companies);


        return "view";
    }
}
