package com.example.diplom.methods;

import com.example.diplom.domain.Company;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileOutputStream;
import java.util.List;

public class SaveFiles {
    public static void saveFileXL(List<Company> companies,String fileName){

        try {
            String filename = "C:/Users/arsen/OneDrive/Рабочий стол/Стажировка/"+fileName+".xls";
            @SuppressWarnings("resource")
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet(fileName);



                HSSFRow rowhead = sheet.createRow((short) 0);
                rowhead.createCell(0).setCellValue("Company name");
                rowhead.createCell(1).setCellValue("Web site");
                rowhead.createCell(2).setCellValue("Video");

                rowhead.createCell(3).setCellValue("Stage Of The Company");
                rowhead.createCell(4).setCellValue("Market Validation");
                rowhead.createCell(5).setCellValue("Size Of The Sales");
                rowhead.createCell(6).setCellValue("Investment Process");
                rowhead.createCell(7).setCellValue("Priority Company");
                rowhead.createCell(8).setCellValue("Status Company");
                rowhead.createCell(9).setCellValue("Transfer Company");

                rowhead.createCell(10).setCellValue("Founder name");
                rowhead.createCell(11).setCellValue("Founder family name");
                rowhead.createCell(12).setCellValue("Email");
                rowhead.createCell(13).setCellValue("Linkedin");
                rowhead.createCell(14).setCellValue("Country");

                rowhead.createCell(15).setCellValue("Categories");
                rowhead.createCell(16).setCellValue("Sub categories");

                rowhead.createCell(17).setCellValue("PDFfile");



        for (int i=0    ;i<companies.size();i++) {
            HSSFRow row = sheet.createRow((short) i+2);
            row.createCell(0).setCellValue(companies.get(i).getCompany_name());
            row.createCell(1).setCellValue(companies.get(i).getWebsite());
            row.createCell(2).setCellValue(companies.get(i).getVideo());

            row.createCell(3).setCellValue(companies.get(i).getStageOfTheCompany().toString());
            row.createCell(4).setCellValue(companies.get(i).getMarketValidation().toString());
            row.createCell(5).setCellValue(companies.get(i).getSizeOfTheSales().getSale());
            row.createCell(6).setCellValue(companies.get(i).getInvestmentProcess().toString());
            row.createCell(7).setCellValue(companies.get(i).getPriorityCompany().toString());
            row.createCell(8).setCellValue(companies.get(i).getStatusCompany().toString());
            row.createCell(9).setCellValue(companies.get(i).getTransferCompany().toString());

            row.createCell(10).setCellValue(companies.get(i).getFounder().getName());
            row.createCell(11).setCellValue(companies.get(i).getFounder().getFamilyName());
            row.createCell(12).setCellValue(companies.get(i).getFounder().getEmail());
            row.createCell(13).setCellValue(companies.get(i).getFounder().getLinkedin());
            row.createCell(14).setCellValue(companies.get(i).getFounder().getCountry());

            row.createCell(15).setCellValue(companies.get(i).getCategories().toString());
            row.createCell(16).setCellValue(companies.get(i).getSubCategories().toString());
            row.createCell(17).setCellValue(companies.get(i).getFilename());
        }

            FileOutputStream fileOut = new FileOutputStream(filename);
            workbook.write(fileOut);
            fileOut.close();

            System.out.println("Your excel file has been generated!");

        } catch ( Exception ex ) {
            System.out.println(ex);
        }
    }
}
