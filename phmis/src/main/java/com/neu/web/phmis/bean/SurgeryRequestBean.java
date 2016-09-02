/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.web.phmis.bean;

import com.neu.web.phmis.util.CommonUtil;
import com.neu.web.phmis.util.Constants;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.stereotype.Component;

/**
 *
 * @author Vineet
 */
@Entity
@Table(name = "surgeryrequest")
@Component
@PrimaryKeyJoinColumn(name = "id")
public class SurgeryRequestBean extends RequestBean implements Serializable{

    private int surgeryId;
    private SurgeryBean surgeryBean;
    private Date dateFrom;
    private Date dateTo;
    private String specialInstructions;
    private int staffCount;
    private List<ProductBean> requestedProducts;

    public SurgeryRequestBean() {
        super();
        setTypeId(CommonUtil.getMapKey(Constants.REQUEST_TYPE, RequestBean.SURGERY));
        setStatusId(CommonUtil.getMapKey(Constants.REQUEST_STATUS, RequestBean.PENDING));
    }

    @GeneratedValue(generator = "gen")
    @GenericGenerator(name = "gen", strategy = "foreign", parameters = @Parameter(name = "property", value = "surgeryBean"))
    public int getSurgeryId() {
        return surgeryId;
    }

    public void setSurgeryId(int surgeryId) {
        this.surgeryId = surgeryId;
    }

    @ManyToMany(mappedBy = "surgeryRequestList")
    public List<ProductBean> getRequestedProducts() {
        return requestedProducts;
    }

    public void setRequestedProducts(List<ProductBean> requestedProducts) {
        this.requestedProducts = requestedProducts;
    }

    @OneToOne
    @PrimaryKeyJoinColumn
    public SurgeryBean getSurgeryBean() {
        return surgeryBean;
    }

    public void setSurgeryBean(SurgeryBean surgeryBean) {
        this.surgeryBean = surgeryBean;
    }

    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public String getSpecialInstructions() {
        return specialInstructions;
    }

    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }

    public int getStaffCount() {
        return staffCount;
    }

    public void setStaffCount(int staffCount) {
        this.staffCount = staffCount;
    }

}
