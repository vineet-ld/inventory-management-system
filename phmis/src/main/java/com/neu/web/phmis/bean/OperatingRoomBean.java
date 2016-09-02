/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.web.phmis.bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.springframework.stereotype.Component;

/**
 *
 * @author Vineet
 */
@Entity
@Table(name = "operatingroom")
@Component
public class OperatingRoomBean implements Serializable {

    private int id;
    private String number;
    private String location;
    private EnterpriseBean enterpriseBean;
    private Date creationDate;
    private Date modifiedDate;

    public OperatingRoomBean() {
        number = "R" + String.valueOf(new Date().getTime());
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

}
