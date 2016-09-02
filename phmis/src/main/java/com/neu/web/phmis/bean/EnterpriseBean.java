/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.web.phmis.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.springframework.stereotype.Component;

/**
 *
 * @author Vineet
 */
@Entity
@Table(name = "enterprise")
@Component
public class EnterpriseBean implements Serializable {

    public static final String HOSPITAL = "Hospital";
    public static final String WAREHOUSE = "Warehouse";
    public static final String VENDOR = "Vendor";

    private int id;
    private String number;
    private String name;
    private int typeId;
    private int countyId;
    private String street;
    private String suite;
    private String city;
    private String state;
    private String zipCode;
    private String contact;
    private Date creationDate;
    private Date modifiedDate;
    private String url;
    private List<UserBean> users;
    private List<ProductBean> products;
    private List<MedicalStaffBean> medicalStaff;
    private List<OperatingRoomBean> operatingRooms;
    private List<DeviceRequestBean> deviceRequestBean;
    private List<OrderBean> orderedByList;
    private List<OrderBean> orderedFromList;
    private List<InventoryItemBean> inventoryItemList;

    public EnterpriseBean() {
        number = "E" + String.valueOf(new Date().getTime());
    }

    @OneToMany(mappedBy = "enterpriseBean")
    public List<InventoryItemBean> getInventoryItemList() {
        return inventoryItemList;
    }

    public void setInventoryItemList(List<InventoryItemBean> inventoryItemList) {
        this.inventoryItemList = inventoryItemList;
    }

    @OneToMany(mappedBy = "orderedBy")
    public List<OrderBean> getOrderedByList() {
        return orderedByList;
    }

    public void setOrderedByList(List<OrderBean> orderedByList) {
        this.orderedByList = orderedByList;
    }

    @OneToMany(mappedBy = "orderedFrom")
    public List<OrderBean> getOrderedFromList() {
        return orderedFromList;
    }

    public void setOrderedFromList(List<OrderBean> orderedFromList) {
        this.orderedFromList = orderedFromList;
    }

    @OneToMany(mappedBy = "warehouse")
    public List<DeviceRequestBean> getDeviceRequestBean() {
        return deviceRequestBean;
    }

    public void setDeviceRequestBean(List<DeviceRequestBean> deviceRequestBean) {
        this.deviceRequestBean = deviceRequestBean;
    }

    @OneToMany(mappedBy = "enterpriseBean")
    public List<OperatingRoomBean> getOperatingRooms() {
        return operatingRooms;
    }

    public void setOperatingRooms(List<OperatingRoomBean> operatingRooms) {
        this.operatingRooms = operatingRooms;
    }

    @OneToMany(mappedBy = "enterpriseBean")
    public List<MedicalStaffBean> getMedicalStaff() {
        return medicalStaff;
    }

    public void setMedicalStaff(List<MedicalStaffBean> medicalStaff) {
        this.medicalStaff = medicalStaff;
    }

    @OneToMany(mappedBy = "enterpriseBean")
    public List<ProductBean> getProducts() {
        return products;
    }

    public void setProducts(List<ProductBean> products) {
        this.products = products;
    }

    @Id
    @GeneratedValue
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getCountyId() {
        return countyId;
    }

    public void setCountyId(int countyId) {
        this.countyId = countyId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @OneToMany(mappedBy = "enterpriseBean")
    public List<UserBean> getUsers() {
        return users;
    }

    public void setUsers(List<UserBean> users) {
        this.users = users;
    }

}
