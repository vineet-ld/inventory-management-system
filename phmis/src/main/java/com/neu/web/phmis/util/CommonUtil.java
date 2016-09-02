/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.web.phmis.util;

import com.neu.web.phmis.bean.RoleBean;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Vineet
 */
public class CommonUtil {

    public static int getMapKey(HashMap<Integer, String> map, String value) {

        int key = 0;

        try {

            for (Map.Entry<Integer, String> e : map.entrySet()) {
                if (e.getValue().equals(value)) {
                    key = e.getKey();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return key;
        }

    }

    public static boolean isNAN(String number) {

        try {

            Integer.parseInt(number);
            return false;

        } catch (NumberFormatException e) {
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }

    }

    public static RoleBean getRole(String role) {

        RoleBean roleBean = null;

        try {

            for (RoleBean r : Constants.ROLES) {
                if (r.getName().equals(role)) {
                    roleBean = r;
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return roleBean;
        }

    }

    public static boolean isPastDate(Date date) {

        boolean flag = false;

        try {
            Date currentDate = new Date();
            if (date.before(currentDate) || date.equals(currentDate)) {
                flag = true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return flag;
        }
    }

    public static RoleBean getRoleBean(String roleName) {

        RoleBean roleBean = null;

        try {

            for (RoleBean rb : Constants.ROLES) {
                if (rb.getName().equals(roleName)) {
                    roleBean = rb;
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return roleBean;
        }

    }
    
    public static Date getNextDate(Date originalDate) {

        Date date = null;

        try {

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(originalDate);
            calendar.add(Calendar.DATE, 1);
            date = calendar.getTime();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return date;
        }

    }
    
     public static String changeDateFormat(Date date, String format) {

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            return simpleDateFormat.format(date);
        } catch (Exception e) {
            return null;
        }

    }

}
