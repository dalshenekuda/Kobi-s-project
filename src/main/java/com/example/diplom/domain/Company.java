package com.example.diplom.domain;

import com.example.diplom.domain.enums.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private String company_name;
    private String website;
 //   private String logo;
    private String video;



    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "founder_id")
    private Founder founder;

    @ManyToMany
    @JoinTable (name="company_sub_category",
            joinColumns=@JoinColumn (name="company_id"),
            inverseJoinColumns=@JoinColumn(name="sub_category_id"))
    private List<SubCategory> subCategories;


    @Enumerated(EnumType.STRING)
    private StageOfTheCompany stageOfTheCompany;

    @Enumerated(EnumType.STRING)
    private SizeOfTheSales sizeOfTheSales;

    @Enumerated(EnumType.STRING)
    private MarketValidation marketValidation;

    @Enumerated(EnumType.STRING)
    private InvestmentProcess investmentProcess;

    @Enumerated(EnumType.STRING)
    private PriorityCompany priorityCompany;

    @Enumerated(EnumType.STRING)
    private StatusCompany statusCompany;

    @Enumerated(EnumType.STRING)
    private TransferCompany transferCompany;

    public Company() {

    }


    public Company(String company_name, Founder founder, StageOfTheCompany stageOfTheCompany,
                   SizeOfTheSales sizeOfTheSales, MarketValidation marketValidation,
                   InvestmentProcess investmentProcess, PriorityCompany priorityCompany,
                   StatusCompany statusCompany, TransferCompany transferCompany, List<SubCategory> subCategories,String video,String website) {
        this.company_name = company_name;
        this.founder = founder;
        this.stageOfTheCompany = stageOfTheCompany;
        this.sizeOfTheSales = sizeOfTheSales;
        this.marketValidation = marketValidation;
        this.investmentProcess = investmentProcess;
        this.priorityCompany = priorityCompany;
        this.statusCompany = statusCompany;
        this.transferCompany = transferCompany;
        this.subCategories = subCategories;
        this.website=website;
        this.video=video;

    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFounder(Founder founder) {
        this.founder = founder;
    }


    public StageOfTheCompany getStageOfTheCompany() {
        return stageOfTheCompany;
    }

    public SizeOfTheSales getSizeOfTheSales() {
        return sizeOfTheSales;
    }

    public MarketValidation getMarketValidation() {
        return marketValidation;
    }


    public void setStageOfTheCompany(StageOfTheCompany stageOfTheCompany) {
        this.stageOfTheCompany = stageOfTheCompany;
    }

    public void setSizeOfTheSales(SizeOfTheSales sizeOfTheSales) {
        this.sizeOfTheSales = sizeOfTheSales;
    }

    public void setMarketValidation(MarketValidation marketValidation) {
        this.marketValidation = marketValidation;
    }

    public InvestmentProcess getInvestmentProcess() {
        return investmentProcess;
    }

    public void setInvestmentProcess(InvestmentProcess investmentProcess) {
        this.investmentProcess = investmentProcess;
    }

    public PriorityCompany getPriorityCompany() {
        return priorityCompany;
    }

    public void setPriorityCompany(PriorityCompany priorityCompany) {
        this.priorityCompany = priorityCompany;
    }

    public StatusCompany getStatusCompany() {
        return statusCompany;
    }

    public void setStatusCompany(StatusCompany statusCompany) {
        this.statusCompany = statusCompany;
    }

    public TransferCompany getTransferCompany() {
        return transferCompany;
    }

    public void setTransferCompany(TransferCompany transferCompany) {
        this.transferCompany = transferCompany;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

//    public String getLogo() {
//        return logo;
//    }
//
//    public void setLogo(String logo) {
//        this.logo = logo;
//    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public List<SubCategory> getSubCategories() {
        return subCategories;
    }

    public List<Category> getCategories() {

        List<Category> categoriesAll = new ArrayList<>();
        subCategories.forEach(subCategory -> categoriesAll.add(subCategory.getCategory()));

        List<Category> categories = categoriesAll.stream().distinct().collect(Collectors.toList());

        return categories;
    }

    public void setSubCategories(List<SubCategory> subCategories) {
        this.subCategories = subCategories;
    }

    public Founder getFounder() {
        return founder;
    }



    @Override
    public String toString()
    {
     return "Company: " + company_name + " |           founder: "+ founder + " |     stage of company: "+
              stageOfTheCompany ;
    }
}
