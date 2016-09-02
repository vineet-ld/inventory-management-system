/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.web.phmis.dao;

import com.neu.web.phmis.bean.DeviceRequestBean;
import com.neu.web.phmis.bean.EnterpriseBean;
import com.neu.web.phmis.bean.InventoryItemBean;
import com.neu.web.phmis.bean.OrderBean;
import com.neu.web.phmis.bean.OrderItemBean;
import com.neu.web.phmis.bean.ProductBean;
import com.neu.web.phmis.bean.RoleBean;
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
import org.springframework.stereotype.Repository;

/**
 *
 * @author Vineet
 */
@Repository
@Transactional
public class InventoryDao {

    private Session session = null;

    public List<DeviceRequestBean> getDeviceRequests(EnterpriseBean enterpriseBean) {

        List<DeviceRequestBean> deviceRequestList = null;

        try {

            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from DeviceRequestBean where warehouse = :warehouse");
            query.setEntity("warehouse", enterpriseBean);
            deviceRequestList = query.list();

        } catch (HibernateException e) {
            deviceRequestList = null;
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            return deviceRequestList;
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

    public DeviceRequestBean getDeviceRequestDetails(int requestId) {

        DeviceRequestBean deviceRequestBean = null;

        try {

            session = HibernateUtil.getSessionFactory().openSession();
            deviceRequestBean = (DeviceRequestBean) session.get(DeviceRequestBean.class, requestId);
            Hibernate.initialize(deviceRequestBean.getRequestedProducts());

        } catch (HibernateException e) {
            deviceRequestBean = null;
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            return deviceRequestBean;
        }

    }

    public DeviceRequestBean processRequest(DeviceRequestBean deviceRequestBean) {

        try {

            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            SQLQuery query = session.createSQLQuery("UPDATE request SET statusId = :status, processedDate = NOW(), processComment = :comment WHERE id = :id");
            query.setInteger("status", deviceRequestBean.getStatusId());
            query.setString("comment", deviceRequestBean.getProcessComment());
            query.setInteger("id", deviceRequestBean.getId());
            query.executeUpdate();
            deviceRequestBean = (DeviceRequestBean) session.get(DeviceRequestBean.class, deviceRequestBean.getId());
            session.getTransaction().commit();

        } catch (HibernateException e) {
            session.getTransaction().rollback();
            deviceRequestBean = null;
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            return deviceRequestBean;
        }

    }

    public void addOrder(List<OrderBean> orderList) {

        try {

            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            for (OrderBean ob : orderList) {
                SQLQuery query = session.createSQLQuery("CALL addOrder(:1,:2,:3)");
                query.setString("1", ob.getNumber());
                query.setInteger("2", ob.getOrderedBy().getId());
                query.setInteger("3", ob.getOrderedFrom().getId());
                List<OrderItemBean> temp = ob.getOrderItemList();
                ob = (OrderBean) query.addEntity(OrderBean.class).uniqueResult();
                ob.setOrderItemList(temp);
                for (OrderItemBean oib : ob.getOrderItemList()) {
                    query = session.createSQLQuery("INSERT INTO orderitem (orderId, productId, quantity) VALUES (:1, :2, :3)");
                    query.setInteger("1", ob.getId());
                    query.setInteger("2", oib.getProductBean().getId());
                    query.setInteger("3", oib.getQuantity());
                    query.executeUpdate();
                }
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

    public List<OrderBean> getOrders(EnterpriseBean enterpriseBean) {

        List<OrderBean> orderList = null;

        try {

            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from OrderBean where orderedBy = :id");
            query.setEntity("id", enterpriseBean);
            orderList = query.list();

        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            return orderList;
        }

    }

    public OrderBean getOrderDetails(int orderId) {

        OrderBean orderBean = null;

        try {

            session = HibernateUtil.getSessionFactory().openSession();
            orderBean = (OrderBean) session.get(OrderBean.class, orderId);
            Hibernate.initialize(orderBean.getOrderItemList());

        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            return orderBean;
        }

    }

    public void addToInventory(InventoryItemBean inventoryItemBean) {

        try {

            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(inventoryItemBean);
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

    public void markOrderAsAdded(OrderBean orderBean) {

        try {

            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(orderBean);
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

    public List<InventoryItemBean> getInventory(EnterpriseBean enterpriseBean) {

        List<InventoryItemBean> inventoryItemList = null;

        try {

            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from InventoryItemBean where enterpriseBean = :bean");
            query.setEntity("bean", enterpriseBean);
            inventoryItemList = query.list();

        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            return inventoryItemList;
        }

    }

}
