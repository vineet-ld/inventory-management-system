/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.web.phmis.util;

import com.neu.web.phmis.bean.RoleBean;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Vineet
 */
public class Constants {

    public static HashMap<Integer, String> ENTERPRISE_TYPE = new HashMap<>();
    public static HashMap<Integer, String> REQUEST_TYPE = new HashMap<>();
    public static HashMap<Integer, String> PAYMENT_TYPE = new HashMap<>();
    public static HashMap<Integer, String> ADDRESS_TYPE = new HashMap<>();
    public static HashMap<Integer, String> CONTACT_TYPE = new HashMap<>();
    public static HashMap<Integer, String> COUNTIES = new HashMap<>();
    public static HashMap<Integer, String> REQUEST_STATUS = new HashMap<>();
    public static ArrayList<RoleBean> ROLES = new ArrayList<>();

    public static final String URL_ACTIVATION = "http://localhost:8084/phmis/account/activate.htm";

}
