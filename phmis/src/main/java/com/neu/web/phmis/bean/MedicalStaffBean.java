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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.springframework.stereotype.Component;

/**
 *
 * @author Vineet
 */
@Entity
@Table(name = "medicalstaff")
@Component
public class MedicalStaffBean implements Serializable {

    private int id;
    private String number;
    private String name;
    private EnterpriseBean enterpriseBean;
    private Date additionDate;
    private Date modifiedDate;
    private List<SurgeryBean> surgeryList;

    public MedicalStaffBean() {
        number = "R" + String.valueOf(new Date().getTime());
    }

    @ManyToMany(cascade = (CascadeType.ALL))
    @JoinTable(name = "medicalteam", joinColumns = {
        @JoinColumn(name = "staffId")}, inverseJoinColumns = {
        @JoinColumn(name = "surgeryId")})
    public List<SurgeryBean> getSurgeryList() {
        return surgeryList;
    }

    public void setSurgeryList(List<SurgeryBean> surgeryList) {
        this.surgeryList = surgeryList;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    @JoinColumn(name = "enterpriseId")
    public EnterpriseBean getEnterpriseBean() {
        return enterpriseBean;
    }

    public void setEnterpriseBean(EnterpriseBean enterpriseBean) {
        this.enterpriseBean = enterpriseBean;
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
