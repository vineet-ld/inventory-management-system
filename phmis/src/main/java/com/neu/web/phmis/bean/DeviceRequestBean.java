/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.web.phmis.bean;

import com.neu.web.phmis.util.CommonUtil;
import com.neu.web.phmis.util.Constants;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.stereotype.Component;

/**
 *
 * @author Vineet
 */
@Entity
@Table(name = "devicerequest")
@Component
@PrimaryKeyJoinColumn(name = "id")
public class DeviceRequestBean extends RequestBean implements Serializable {

    private String specialInstruction;
    private EnterpriseBean warehouse;
    private List<ProductBean> requestedProducts;

    public DeviceRequestBean() {
        super();
        setTypeId(CommonUtil.getMapKey(Constants.REQUEST_TYPE, RequestBean.DEVICE));
        setStatusId(CommonUtil.getMapKey(Constants.REQUEST_STATUS, RequestBean.PENDING));
    }

    @ManyToOne
    @JoinColumn(name = "enterpriseId")
    public EnterpriseBean getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(EnterpriseBean warehouse) {
        this.warehouse = warehouse;
    }

    public String getSpecialInstruction() {
        return specialInstruction;
    }

    public void setSpecialInstruction(String specialInstruction) {
        this.specialInstruction = specialInstruction;
    }

    @ManyToMany(mappedBy = "deviceRequestList")
    public List<ProductBean> getRequestedProducts() {
        return requestedProducts;
    }

    public void setRequestedProducts(List<ProductBean> requestedProducts) {
        this.requestedProducts = requestedProducts;
    }

}
