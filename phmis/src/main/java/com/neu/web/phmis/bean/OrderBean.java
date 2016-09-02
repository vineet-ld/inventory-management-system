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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.stereotype.Component;

/**
 *
 * @author Vineet
 */
@Entity
@Table(name = "orders")
@Component
public class OrderBean implements Serializable {

    private int id;
    private String number;
    private EnterpriseBean orderedBy;
    private EnterpriseBean orderedFrom;
    private Date creationDate;
    private List<OrderItemBean> orderItemList;
    private int addedToInventory;

    public OrderBean() {
        number = "O" + String.valueOf(new Date().getTime());
    }

    public int getAddedToInventory() {
        return addedToInventory;
    }

    public void setAddedToInventory(int addedToInventory) {
        this.addedToInventory = addedToInventory;
    }

    @OneToMany(mappedBy = "orderBean")
    public List<OrderItemBean> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItemBean> orderItemList) {
        this.orderItemList = orderItemList;
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

    @ManyToOne
    @JoinColumn(name = "orderedBy")
    public EnterpriseBean getOrderedBy() {
        return orderedBy;
    }

    public void setOrderedBy(EnterpriseBean orderedBy) {
        this.orderedBy = orderedBy;
    }

    @ManyToOne
    @JoinColumn(name = "orderedFrom")
    public EnterpriseBean getOrderedFrom() {
        return orderedFrom;
    }

    public void setOrderedFrom(EnterpriseBean orderedFrom) {
        this.orderedFrom = orderedFrom;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

}
