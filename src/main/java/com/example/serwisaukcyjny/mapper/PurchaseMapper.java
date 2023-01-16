package com.example.serwisaukcyjny.mapper;

import com.example.serwisaukcyjny.form.CreatePurchaseForm;
import com.example.serwisaukcyjny.model.Purchase;

public class PurchaseMapper {

    public static Purchase toEntity(CreatePurchaseForm form){
        return new Purchase(form.getAuction(),form.getUser(),form.getPrice());
    }
}
