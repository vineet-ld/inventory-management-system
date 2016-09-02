/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.web.phmis.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.springframework.stereotype.Component;

/**
 *
 * @author Vineet
 */
@Entity
@Table(name = "product")
@Component
public class ProductBean implements Serializable {

    private int id;
    private String number;
    private String name;
    private String description;
    private Double cost;
    private EnterpriseBean enterpriseBean;
    private Date additionDate;
    private Date modifiedDate;
    private List<SurgeryRequestBean> surgeryRequestList;
    private List<DeviceRequestBean> deviceRequestList;
    private List<OrderItemBean> orderItemList;
    private List<InventoryItemBean> inventoryItemList;

    public ProductBean() {
        number = "P" + String.valueOf(new Date().getTime());
    }

    @OneToMany(mappedBy = "productBean")
    public List<InventoryItemBean> getInventoryItemList() {
        return inventoryItemList;
    }

    public void setInventoryItemList(List<InventoryItemBean> inventoryItemList) {
        this.inventoryItemList = inventoryItemList;
    }

    @OneToMany(mappedBy = "productBean")
    public List<OrderItemBean> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItemBean> orderItemList) {
        this.orderItemList = orderItemList;
    }

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "productrequested",
            joinColumns = {
                @JoinColumn(name = "productId")},
            inverseJoinColumns = {
                @JoinColumn(name = "requestId")})
    public List<SurgeryRequestBean> getSurgeryRequestList() {
        return surgeryRequestList;
    }

    public void setSurgeryRequestList(List<SurgeryRequestBean> surgeryRequestList) {
        this.surgeryRequestList = surgeryRequestList;
    }

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "productrequested",
            joinColumns = {
                @JoinColumn(name = "productId")},
            inverseJoinColumns = {
                @JoinColumn(name = "requestId")})
    public List<DeviceRequestBean> getDeviceRequestList() {
        return deviceRequestList;
    }

    public void setDeviceRequestList(List<DeviceRequestBean> deviceRequestList) {
        this.deviceRequestList = deviceRequestList;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    @ManyToOne
    @JoinColumn(name = "enterpriseId")
    public EnterpriseBean getEnterpriseBean() {
        return enterpriseBean;
    }

    public void setEnterpriseBean(EnterpriseBean enterpriseBean) {
        this.enterpriseBean = enterpriseBean;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Lob
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getAdditionDate() {
        return additionDate;
    }

    public void setAdditionDate(Date additionDate) {
        this.additionDate = additionDate;
    }

    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

}
