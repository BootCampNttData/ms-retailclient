package com.bootcamp.retailclient.model;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
//@Document

public class ProductsReport {
    List<Integer> savingAccountList;
    List<Integer> currentAccountList;
    List<Integer> fixedDepositAccountList;
    List<Integer> CreditCardList;
    List<String>  CreditsList;
}
