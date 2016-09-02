/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.web.phmis.dao;

import com.neu.web.phmis.bean.EnterpriseBean;
import com.neu.web.phmis.bean.ProductBean;
import com.neu.web.phmis.bean.RoleBean;
import com.neu.web.phmis.bean.SurgeryBean;
import com.neu.web.phmis.bean.SurgeryRequestBean;
import com.neu.web.phmis.bean.UserBean;
import com.neu.web.phmis.util.HibernateUtil;
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
public class DoctorDao {

    @Autowired
    SurgeryBean surgeryBean;

    @Autowired
    SurgeryRequestBean surgeryRequestBean;

    private Session session;

    public List<SurgeryRequestBean> getRequests(UserBean userBean) {

        List<SurgeryRequestBean> requestList = null;

        try {

            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from RequestBean where createdBy = :user");
            query.setEntity("user", userBean);

            requestList = query.list();

        } catch (HibernateException e) {
            requestList = null;
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            return requestList;
        }

    }

    public List<ProductBean> getProducts() {

        List<ProductBean> productList = null;

        try {

            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from ProductBean");

            productList = query.list();

        } catch (HibernateException e) {
            productList = null;
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            return productList;
        }

    }

    public ProductBean getProduct(int productId) {

        ProductBean productBean = null;

        try {

            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from ProductBean where id = :id");
            query.setInteger("id", productId);

            productBean = (ProductBean) query.uniqueResult();

        } catch (HibernateException e) {
            productBean = null;
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            return productBean;
        }

    }

    public void createRequest(SurgeryRequestBean surgeryRequestBean) {

        try {

            surgeryBean = surgeryRequestBean.getSurgeryBean();
            List<ProductBean> requestedProducts = surgeryRequestBean.getRequestedProducts();

            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            SQLQuery query = session.createSQLQuery("CALL createSurgeryRequest(:userId, :surgeryType, :patientName, :requestNo, :requestTypeId, :requestStatusId, :dateFrom, :dateTo, :instru, :staff)");
            query.setInteger("userId", surgeryBean.getDoctor().getId());
            query.setString("surgeryType", surgeryBean.getType());
            query.setString("patientName", surgeryBean.getPatientName());
            query.setString("requestNo", surgeryRequestBean.getNumber());
            query.setInteger("requestTypeId", surgeryRequestBean.getTypeId());
            query.setInteger("requestStatusId", surgeryRequestBean.getStatusId());
            query.setDate("dateFrom", surgeryRequestBean.getDateFrom());
            query.setDate("dateTo", surgeryRequestBean.getDateTo());
            query.setString("instru", surgeryRequestBean.getSpecialInstructions());
            query.setInteger("staff", surgeryRequestBean.getStaffCount());

            List<Integer> list = query.list();
            surgeryRequestBean.setId(list.get(0));

            for (ProductBean p : requestedProducts) {
                query = session.createSQLQuery("INSERT INTO productrequested VALUES (:requestId, :productId)");
                query.setInteger("requestId", surgeryRequestBean.getId());
                query.setInteger("productId", p.getId());
                query.executeUpdate();
            }

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

    public List<UserBean> getResourceManagers(EnterpriseBean enterpriseBean, RoleBean roleBean) {

        List<UserBean> userList = null;

        try {

            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from UserBean where enterpriseBean = :enterprise and roleBean = :role");
            query.setEntity("enterprise", enterpriseBean);
            query.setEntity("role", roleBean);
            userList = query.list();

        } catch (HibernateException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            return userList;
        }

    }

    public SurgeryRequestBean getRequestDetails(int requestId) {

        try {

            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from SurgeryRequestBean where id = :id");
            query.setInteger("id", requestId);
            surgeryRequestBean = (SurgeryRequestBean) query.uniqueResult();
            Hibernate.initialize(surgeryRequestBean.getRequestedProducts());
            Hibernate.initialize(surgeryRequestBean.getProcessedBy());

        } catch (HibernateException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            return surgeryRequestBean;
        }

    }

    public List<SurgeryBean> getSurgeries(UserBean userBean) {

        List<SurgeryBean> surgeryList = null;

        try {

            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from SurgeryBean where doctor = :user and scheduleDate is NOT NULL");
            query.setEntity("user", userBean);
            surgeryList = query.list();
            for (SurgeryBean sb : surgeryList) {
                Hibernate.initialize(sb.getOperatingRoomBean());
            }

        } catch (HibernateException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            return surgeryList;
        }

    }

}
