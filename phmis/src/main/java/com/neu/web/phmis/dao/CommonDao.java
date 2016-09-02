/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.web.phmis.dao;

import com.neu.web.phmis.bean.UserBean;
import com.neu.web.phmis.util.HibernateUtil;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Vineet
 */
@Repository
@Transactional
public class CommonDao {

    private Session session;

    public UserBean activateAccount(UserBean userBean, String key) {

        try {

            session = HibernateUtil.getSessionFactory().openSession();

            session.beginTransaction();
            SQLQuery query = session.createSQLQuery("CALL activateAccount (:id, :password, :key, :ipAddr)");
            query.setInteger("id", userBean.getId());
            query.setString("password", userBean.getPassword());
            query.setString("key", key);
            query.setString("ipAddr", userBean.getLastAccessedFrom());

            List<UserBean> list = query.addEntity(UserBean.class).list();
            if (!list.isEmpty()) {
                userBean = list.get(0);
                Hibernate.initialize(userBean.getEnterpriseBean());
                Hibernate.initialize(userBean.getRoleBean());
            }

            session.getTransaction().commit();

        } catch (HibernateException e) {
            session.getTransaction().rollback();
            userBean = null;
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            return userBean;
        }

    }

    public UserBean authenticate(UserBean userBean, String ipAddr) {

        try {

            session = HibernateUtil.getSessionFactory().openSession();

            session.beginTransaction();
            SQLQuery query = session.createSQLQuery("CALL authenticateUser (:username, :password, :ipAddr)");
            query.setString("username", userBean.getUsername());
            query.setString("password", userBean.getPassword());
            query.setString("ipAddr", ipAddr);

            List<UserBean> list = query.addEntity(UserBean.class).list();
            if (!list.isEmpty()) {
                userBean = list.get(0);
                Hibernate.initialize(userBean.getEnterpriseBean());
                Hibernate.initialize(userBean.getRoleBean());
            } else {
                userBean = null;
            }

            session.getTransaction().commit();

        } catch (HibernateException e) {
            session.getTransaction().rollback();
            userBean = null;
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            return userBean;
        }

    }

}
