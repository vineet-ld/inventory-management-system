/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.web.phmis.dao;

import com.neu.web.phmis.bean.RoleBean;
import com.neu.web.phmis.util.Constants;
import com.neu.web.phmis.util.HibernateUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Vineet
 */
@Repository
public class InitDao {

    private Session session;

    public void init() {

        try {

            List<Object[]> result = null;
            session = HibernateUtil.getSessionFactory().openSession();
            result = getEnterpriseType();
            set(result, Constants.ENTERPRISE_TYPE);
            result = getRequestType();
            set(result, Constants.REQUEST_TYPE);
            result = getRequestStatus();
            set(result, Constants.REQUEST_STATUS);
            result = getAddressType();
            set(result, Constants.ADDRESS_TYPE);
            result = getContactType();
            set(result, Constants.CONTACT_TYPE);
            result = getCounties();
            set(result, Constants.COUNTIES);
            result = getPaymentType();
            set(result, Constants.PAYMENT_TYPE);
            getRoles();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void set(List<Object[]> result, HashMap<Integer, String> map) {

        try {

            for (Object[] o : result) {
                map.put(Integer.parseInt(o[0].toString()), o[1].toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private List<Object[]> getEnterpriseType() {

        List<Object[]> resultSet = null;

        try {

            SQLQuery query = session.createSQLQuery("SELECT * FROM enterprisetype");
            resultSet = query.list();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return resultSet;
        }

    }

    private List<Object[]> getRequestType() {

        List<Object[]> resultSet = null;

        try {

            SQLQuery query = session.createSQLQuery("SELECT * FROM requesttype");
            resultSet = query.list();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return resultSet;
        }

    }

    private List<Object[]> getPaymentType() {

        List<Object[]> resultSet = null;

        try {

            SQLQuery query = session.createSQLQuery("SELECT * FROM paymenttype");
            resultSet = query.list();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return resultSet;
        }

    }

    private List<Object[]> getAddressType() {

        List<Object[]> resultSet = null;

        try {

            SQLQuery query = session.createSQLQuery("SELECT * FROM addresstype");
            resultSet = query.list();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return resultSet;
        }

    }

    private List<Object[]> getContactType() {

        List<Object[]> resultSet = null;

        try {

            SQLQuery query = session.createSQLQuery("SELECT * FROM contacttype");
            resultSet = query.list();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return resultSet;
        }

    }

    private List<Object[]> getCounties() {

        List<Object[]> resultSet = null;

        try {

            SQLQuery query = session.createSQLQuery("SELECT id, name FROM county");
            resultSet = query.list();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return resultSet;
        }

    }

    private List<Object[]> getRequestStatus() {

        List<Object[]> resultSet = null;

        try {

            SQLQuery query = session.createSQLQuery("SELECT * FROM requeststatus");
            resultSet = query.list();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return resultSet;
        }

    }

    private void getRoles() {

        try {

            Query query = session.createQuery("from RoleBean");
            List<RoleBean> list = query.list();
            Constants.ROLES = (ArrayList<RoleBean>) list;

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
