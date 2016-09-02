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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
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
@Table(name = "request")
@Component
@Inheritance(strategy = InheritanceType.JOINED)
public class RequestBean implements Serializable {

    public static final String SURGERY = "Surgery Request";
    public static final String DEVICE = "Device Request";
    public static final String ORDER = "Order Request";
    
    public static final String PENDING = "Pending";
    public static final String APPROVED = "Approved";
    public static final String HOLD = "On Hold";
    public static final String CANCELLED = "Cancelled";
    public static final String DELIVERED = "Delivered";
    public static final String AVAILABLE = "Available";

    private int id;
    private String number;
    private UserBean createdBy;
    private UserBean processedBy;
    private int typeId;
    private int statusId;
    private Date creationDate;
    private Date processedDate;
    private String processComment;

    public RequestBean() {
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

    @ManyToOne
    @JoinColumn(name = "createdBy")
    public UserBean getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserBean createdBy) {
        this.createdBy = createdBy;
    }

    @ManyToOne
    @JoinColumn(name = "processedBy")
    public UserBean getProcessedBy() {
        return processedBy;
    }

    public void setProcessedBy(UserBean processedBy) {
        this.processedBy = processedBy;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getProcessedDate() {
        return processedDate;
    }

    public void setProcessedDate(Date processedDate) {
        this.processedDate = processedDate;
    }

    public String getProcessComment() {
        return processComment;
    }

    public void setProcessComment(String processComment) {
        this.processComment = processComment;
    }

}
