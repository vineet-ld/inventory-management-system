/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.web.phmis.dao;

import com.neu.web.phmis.bean.EnterpriseBean;
import com.neu.web.phmis.bean.UserBean;
import com.neu.web.phmis.util.HibernateUtil;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Vineet
 */
@Repository
@Transactional
public class AdminDao {

    @Autowired
    EnterpriseBean enterpriseBean;

    @Autowired
    UserBean userBean;

    private Session session;

    public EnterpriseBean addEnterpriseBean(EnterpriseBean enterpriseBean) {

        try {

            session = HibernateUtil.getSessionFactory().openSession();

            session.beginTransaction();
            SQLQuery query = session.createSQLQuery("CALL addEnterprise (:number, :name, :typeId, :countyId, :street, :suite, :city, :state, :zip, :contact, :url)");
            query.setString("number", enterpriseBean.getNumber());
            query.setString("name", enterpriseBean.getName());
            query.setInteger("typeId", enterpriseBean.getTypeId());
            query.setInteger("countyId", enterpriseBean.getCountyId());
            query.setString("street", enterpriseBean.getStreet());
            query.setString("suite", enterpriseBean.getSuite());
            query.setString("city", enterpriseBean.getCity());
            query.setString("state", enterpriseBean.getState());
            query.setString("zip", enterpriseBean.getZipCode());
            query.setString("contact", enterpriseBean.getContact());
            query.setString("url", enterpriseBean.getUrl());

            List<Integer> list = query.list();
            enterpriseBean.setId(list.get(0));
            session.getTransaction().commit();

        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            return enterpriseBean;
        }

    }

    public ArrayList<EnterpriseBean> getEnterpriseList(int typeId) {

        List<EnterpriseBean> enterpriseList = null;

        try {

            session = HibernateUtil.getSessionFactory().openSession();

            Query query = session.createQuery("from EnterpriseBean where typeId = :typeId");
            query.setInteger("typeId", typeId);
            enterpriseList = query.list();

        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            return (ArrayList<EnterpriseBean>) enterpriseList;
        }

    }

    public EnterpriseBean getEnterprise(int id) {

        try {

            session = HibernateUtil.getSessionFactory().openSession();
            enterpriseBean = (EnterpriseBean) session.get(EnterpriseBean.class, id);
            Hibernate.initialize(enterpriseBean.getUsers());
//            List<UserBean> users = enterpriseBean.getUsers();
//            enterpriseBean.setUsers(users);

        } catch (HibernateException e) {
            enterpriseBean = null;
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            return enterpriseBean;
        }

    }

    public void editEnterprise(EnterpriseBean enterpriseBean) {

        try {

            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            SQLQuery query = session.createSQLQuery("SELECT creationDate FROM enterprise where id = :id");
            query.setInteger("id", enterpriseBean.getId());
            List list = query.list();
            enterpriseBean.setCreationDate((Date) list.get(0));

            session.update(enterpriseBean);
            session.getTransaction().commit();

        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    public UserBean addUser(UserBean userBean) {

        try {

            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            SQLQuery query = session.createSQLQuery("CALL addUser (:number, :userName, :emailId, :roleId, :enterpriseId, :firstName, :lastName)");
            query.setString("number", userBean.getNumber());
            query.setString("userName", userBean.getUsername());
            query.setString("emailId", userBean.getEmailId());
            query.setInteger("roleId", userBean.getRoleBean().getId());
            query.setInteger("enterpriseId", userBean.getEnterpriseBean().getId());
            query.setString("firstName", userBean.getFirstName());
            query.setString("lastName", userBean.getLastName());
            List<Object[]> list = query.list();
            Object[] data = list.get(0);
            userBean.setId(Integer.parseInt(data[0].toString()));
            userBean.setPassword(data[1].toString());
            session.getTransaction().commit();

        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            return userBean;
        }

    }

    public UserBean getUser(int id) {

        try {

            session = HibernateUtil.getSessionFactory().openSession();
            userBean = (UserBean) session.get(UserBean.class, id);
            Hibernate.initialize(userBean.getRoleBean());
            Hibernate.initialize(userBean.getEnterpriseBean());

        } catch (HibernateException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            return userBean;
        }

    }

    public String authenticate(String username, String password) {

        String flag = "0";

        try {

            session = HibernateUtil.getSessionFactory().openSession();
            SQLQuery query = session.createSQLQuery("SELECT COUNT(*) FROM admin WHERE username = :user AND password = :pass");
            query.setString("user", username);
            query.setString("pass", password);
            List<BigInteger> list = query.list();
            int count = list.get(0).intValue();
            if (count == 1) {
                flag = "1";
            }

        } catch (HibernateException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            return flag;
        }

    }

}
