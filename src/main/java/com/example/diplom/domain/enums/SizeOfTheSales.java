package com.example.diplom.domain.enums;

//public enum SizeOfTheSales {
//    No_sales, Stage_1, Stage_2, Stage_3, Stage_4,
//    Stage_5, Stage_6;
//}

public enum SizeOfTheSales {
    NONE("NONE"),
    ONE("40,000- 100000$"),
    TWO("100,000$-300,000$"),
    THREE("300,000$- 1,000,000$"),
    FOUR("1,000,000$-3,000,000$"),
    FIVE("3,000,000-10,000,000$");


   private final String sale;

    SizeOfTheSales(String sale) {
        this.sale = sale;
    }

    public String getSale() {
        return sale;
    }
}