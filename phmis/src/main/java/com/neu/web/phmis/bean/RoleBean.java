/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.web.phmis.bean;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.stereotype.Component;

/**
 *
 * @author Vineet
 */
@Entity
@Table(name = "role")
@Component
public class RoleBean implements Serializable {

    public static final String HOSPITAL_ADMIN = "Hospital Administrator";
    public static final String DOCTOR = "Doctor";
    public static final String RESOURCE_MANAGER = "Resource Manager";
    public static final String WAREHOUSE_ADMIN = "Warehouse Administrator";
    public static final String INVENTORY_MANAGER = "Inventory Manager";
    public static final String STORE_ADMIN = "Store Administrator";
    public static final String PRODUCT_MANAGER = "Product Manager";
    public static final String SALES_MANAGER = "Sales Manager";

    private int id;
    private String name;
    private int supportedEnterpriseTypeId;
    private Set<UserBean> users;

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSupportedEnterpriseTypeId() {
        return supportedEnterpriseTypeId;
    }

    public void setSupportedEnterpriseTypeId(int supportedEnterpriseTypeId) {
        this.supportedEnterpriseTypeId = supportedEnterpriseTypeId;
    }

    @OneToMany(mappedBy = "roleBean")
    public Set<UserBean> getUsers() {
        return users;
    }

    public void setUsers(Set<UserBean> users) {
        this.users = users;
    }

}
