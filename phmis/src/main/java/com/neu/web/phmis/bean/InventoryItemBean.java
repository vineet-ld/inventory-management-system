/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.web.phmis.bean;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.stereotype.Component;

/**
 *
 * @author Vineet
 */
@Entity
@Table(name = "inventoryitem")
@Component
public class InventoryItemBean {

    private int id;
    private String itemCode;
    private EnterpriseBean enterpriseBean;
    private ProductBean productBean;
    private Date additionDate;

    public InventoryItemBean() {
        itemCode = String.valueOf(new Date().getTime());
    }

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    @ManyToOne
    @JoinColumn(name = "enterpriseId")
    public EnterpriseBean getEnterpriseBean() {
        return enterpriseBean;
    }

    public void setEnterpriseBean(EnterpriseBean enterpriseBean) {
        this.enterpriseBean = enterpriseBean;
    }

    @ManyToOne
    @JoinColumn(name = "productId")
    public ProductBean getProductBean() {
        return productBean;
    }

    public void setProductBean(ProductBean productBean) {
        this.productBean = productBean;
    }

    public Date getAdditionDate() {
        return additionDate;
    }

    public void setAdditionDate(Date additionDate) {
        this.additionDate = additionDate;
    }

}
