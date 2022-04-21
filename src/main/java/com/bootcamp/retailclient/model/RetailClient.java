package com.bootcamp.retailclient.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document

public class RetailClient {
    @Id
    private String  id;
    private String  name;
    private String  lastName;
    private String  documentId;
    private String  documentType;
    private Boolean isVip;
    private String  address;
    private String  phone;
    private String  maritalStatus;
    private String  gender;
}
