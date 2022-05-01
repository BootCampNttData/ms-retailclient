package com.bootcamp.retailclient.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@AllArgsConstructor
@Data

public class ProductsReport {
    private Integer ProductNumber;
    private String tipoProducto;
}
