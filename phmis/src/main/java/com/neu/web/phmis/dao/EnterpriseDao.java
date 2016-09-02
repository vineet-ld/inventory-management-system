/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.web.phmis.dao;

import com.neu.web.phmis.bean.EnterpriseBean;
import com.neu.web.phmis.bean.UserBean;
import com.neu.web.phmis.util.HibernateUtil;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
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
public class EnterpriseDao {

    @Autowired
    UserBean userBean;
    
    private Session session = null;

    public EnterpriseBean getUsers(EnterpriseBean enterpriseBean) {

        try {

            session = HibernateUtil.getSessionFactory().openSession();
            enterpriseBean = (EnterpriseBean) session.get(EnterpriseBean.class, enterpriseBean.getId());
            Hibernate.initialize(enterpriseBean.getUsers());

        } catch (HibernateException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            return enterpriseBean;
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

}
