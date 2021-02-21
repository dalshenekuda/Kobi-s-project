package com.example.diplom.repos;

import com.example.diplom.domain.Category;
import com.example.diplom.domain.Company;
import com.example.diplom.domain.Founder;
import com.example.diplom.domain.SubCategory;
import com.example.diplom.domain.enums.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CompanyRepo extends CrudRepository<Company, Long> {

   @Query("SELECT c FROM Company c WHERE (:stageOfTheCompany is null "+" or c.stageOfTheCompany=:stageOfTheCompany) and" +
            " (:marketValidation is null "+" or c.marketValidation=:marketValidation) and"+
            "(:statusCompany is null "+" or c.statusCompany=:statusCompany) and "+
            "(:priorityCompany is null "+" or c.priorityCompany=:priorityCompany) and "+
            "(:transferCompany is null "+" or c.transferCompany=:transferCompany) and "+
            "(:investmentProcess is null "+" or c.investmentProcess=:investmentProcess)")

    List<Company> findByStageOfTheCompanyAndMarketValidationAndStatusCompanyAndPriorityCompanyAndTransferCompanyAndInvestmentProcess(
           @Param("stageOfTheCompany") StageOfTheCompany stageOfTheCompany,
           @Param("marketValidation")MarketValidation marketValidation,
           @Param("statusCompany")StatusCompany statusCompany,
           @Param("priorityCompany") PriorityCompany priorityCompany,
           @Param("transferCompany")TransferCompany transferCompany,
           @Param("investmentProcess")InvestmentProcess investmentProcess);

}
