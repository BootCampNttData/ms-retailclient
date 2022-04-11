package com.bootcamp.retailclient.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "retailclient")

public class RetailClient {
    @Id
    private String id;
    private String name;
    private String lastName;
    private String documentId;
    private String documentType;
    private String address;
    private String phone;
    private String maritalStatus;
    private String gender;
    
	public RetailClient() {}
    
	public RetailClient(String name, String lastName, String documentId, String documentType, String address,
			String phone, String maritalStatus, String gender) {
		this.name = name;
		this.lastName = lastName;
		this.documentId = documentId;
		this.documentType = documentType;
		this.address = address;
		this.phone = phone;
		this.maritalStatus = maritalStatus;
		this.gender = gender;
	}
   
    
}
