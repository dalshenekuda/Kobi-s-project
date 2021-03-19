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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.thymeleaf.expression.Lists;


import javax.servlet.http.HttpServletResponse;
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

    @Value("${upload.path}")
    String uploadPath;

    @GetMapping("/company")
    public String view1(String id,Model model) {

        Integer idd = Integer.parseInt(id);
        Company company = CompanyRepo.findById(idd);
        model.addAttribute("company",company);
        return"company";
    }

    @PostMapping("/company/delete")
    public String view2(@RequestParam String idd,@RequestParam String iddC,Model model) {

        if(idd.equals(iddC)) {
            int id = Integer.parseInt(idd);

            Company company = CompanyRepo.findById(id);
            if (company != null) {
                CompanyRepo.delete(company);
            }
        }
        return "redirect:/view";
    }

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

        Iterable<SubCategory> SubCategoriesList = SubCategoryRepo.findAll();
       // List<Category> list1 = new ArrayList<>();
       // CategoriesList.forEach(list1::add);

        model.addAttribute("SubCategoriesList", SubCategoriesList);


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

    @PostMapping("/view")
    public String filter(@RequestParam(required = false) List<String> _founders,@RequestParam (required = false) List<String> _categories,
                         @RequestParam (required = false) List<String> subCategories_s, @RequestParam String _stage,
                         @RequestParam String _market,@RequestParam String cName, @RequestParam String _status,
                         @RequestParam String _priory, @RequestParam String _transfer,
                         @RequestParam String _investment, @RequestParam String fileName,@RequestParam String action, Model model) {


        Iterable<Company> companies = CompanyRepo.findAll();
        model.addAttribute("companies", companies);

        Iterable<Founder> foundersList = FounderRepo.findAll();
        model.addAttribute("foundersList", foundersList);

        Iterable<Category> CategoriesList = CategoryRepo.findAll();
        List<Category> list1 = new ArrayList<>();

        Iterable<SubCategory> SubCategoriesList = SubCategoryRepo.findAll();
        List<SubCategory> list2 = new ArrayList<>();

        CategoriesList.forEach(list1::add);
        model.addAttribute("CategoriesList", list1);

        SubCategoriesList.forEach(list2::add);
        model.addAttribute("SubCategoriesList", list2);



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

        List<Company> companies1 = new ArrayList<>();

        if(cName!=null) {
//            companies.forEach(companies1::add);
            for (Company c : companies) {
//                Company company =CompanyRepo.findByCompany_name(cName);
                if (c.getCompany_name().equalsIgnoreCase(cName)) {
                    companies1.add(c);
                }
            }
        }
        if(companies1.isEmpty())
            companies.forEach(companies1::add);



//
        if ((_founders != null)) {
            Founder founder = null;

            companies1.forEach(companiesTmp::add);
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
            companies1.forEach(companiesAfterEnumsFilter::add);

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
//res
        companiesTmp.clear();

        if ((subCategories_s != null)) {
            SubCategory subCategory = null;

            for (String e : subCategories_s) {
                subCategory = SubCategoryRepo.findByName(e);
                //System.out.println(e);
                for (Company c : res) {
                    if (c.getSubCategories().contains(subCategory)) {
                        companiesTmp.add(c);
                    }
                }
            }
            res.clear();
            res=companiesTmp;//////
        }
            //companiesTmp.forEach(res::add);
        Set<Company> resS= new HashSet<>(res);
        if(action.equals("Filter and save in XLS file")&&fileName!=null)
        SaveFiles.saveFileXL(res,fileName);
        model.addAttribute("companies",resS);


        return "view";
    }


    @GetMapping(value = "/img/{imageUrl}")
    public @ResponseBody
    byte[] image(@PathVariable String imageUrl) throws IOException {

         String url = uploadPath +"/" + imageUrl;
        InputStream in = new FileInputStream(url);
        System.out.println(in);
        return IOUtils.toByteArray(in);
    }

//    @RequestMapping(value = "downloadFile", method = RequestMethod.GET)
//    public StreamingResponseBody getSteamingFile(HttpServletResponse response) throws IOException {
//        response.setContentType("application/pdf");
//        response.setHeader("Content-Disposition", "attachment; filename=\"demo.pdf\"");
//        InputStream inputStream = new FileInputStream(new File("C:\\demo-file.pdf"));
//        return outputStream -> {
//            int nRead;
//            byte[] data = new byte[1024];
//            while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
//                System.out.println("Writing some bytes..");
//                outputStream.write(data, 0, nRead);
//            }
//        };
//    }

    @GetMapping(value = "/file/{fileUrl}")
    public @ResponseBody
    byte[] file(@PathVariable String fileUrl) throws IOException {
        String url = "C:/Java/Kobi-s-project/uploads/" + fileUrl; //здесь указываете СВОЙ путь к папке с картинками
//         String url = ${upload.path} + imageUrl;
        //  System.out.println("${upload.path}");
        InputStream in = new FileInputStream(url);
        System.out.println(in);
        return IOUtils.toByteArray(in);
    }
    @GetMapping(value = "/file")
    String fileNo(){
        return "file";
    }





//    @GetMapping(value = "/file/{fileUrl}")
//    @ResponseBody
//    public File  file(@PathVariable String fileUrl) throws IOException {
//        String url = "C:/Java/Kobi-s-project/uploads/" + fileUrl; //здесь указываете СВОЙ путь к папке с картинками
////         String url = ${upload.path} + imageUrl;
//        //  System.out.println("${upload.path}");
//       // InputStream in = new FileInputStream(url);
//
//        return File(url);
//    }


//    @GetMapping(value="/file/{fileUrl}")
//    @ResponseBody
//    public FileSystemResource downloadFile(@Param(value="id") Integer id) {
//        Company company = CompanyRepo.findById(id);
//        FileSystemResource f.
////        ApplicationContext context = new AnnotationConfigApplicationContext();
////        Resource resource = context.getResource("C:/Java/Kobi-s-project/uploads/c30ab7a4-f1c1-4bc4-a187-1d5243595575.rg.xls");
////       return new FileSystemResource(new File(company.getInfoFilename()));
////        return new FileSystemResource(new File("C:/Java/Kobi-s-project/uploads/c30ab7a4-f1c1-4bc4-a187-1d5243595575.rg.xls"));
//        return ;
//    }


//"${upload.path}"


    }
