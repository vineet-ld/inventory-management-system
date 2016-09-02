/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.web.phmis.dao;

import com.neu.web.phmis.bean.DeviceRequestBean;
import com.neu.web.phmis.bean.EnterpriseBean;
import com.neu.web.phmis.bean.InventoryItemBean;
import com.neu.web.phmis.bean.MedicalStaffBean;
import com.neu.web.phmis.bean.OperatingRoomBean;
import com.neu.web.phmis.bean.ProductBean;
import com.neu.web.phmis.bean.RequestBean;
import com.neu.web.phmis.bean.RoleBean;
import com.neu.web.phmis.bean.SurgeryBean;
import com.neu.web.phmis.bean.SurgeryRequestBean;
import com.neu.web.phmis.bean.UserBean;
import com.neu.web.phmis.util.CommonUtil;
import com.neu.web.phmis.util.Constants;
import com.neu.web.phmis.util.HibernateUtil;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
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
public class ResourceDao {

    @Autowired
    SurgeryRequestBean surgeryRequestBean;

    private Session session;

    public List<OperatingRoomBean> getOperatingRooms(int enterpriseId) {

        List<OperatingRoomBean> roomList = null;

        try {

            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from OperatingRoomBean where enterpriseId = :id");
            query.setInteger("id", enterpriseId);

            roomList = query.list();

        } catch (HibernateException e) {
            roomList = null;
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            return roomList;
        }

    }

    public List<MedicalStaffBean> getMedicalStaff(int enterpriseId) {

        List<MedicalStaffBean> staffList = null;

        try {

            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from MedicalStaffBean where enterpriseId = :id");
            query.setInteger("id", enterpriseId);

            staffList = query.list();

        } catch (HibernateException e) {
            staffList = null;
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            return staffList;
        }

    }

    public void addRoom(OperatingRoomBean operatingRoomBean) {

        try {

            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(operatingRoomBean);
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

    public void addStaff(MedicalStaffBean medicalStaffBean) {

        try {

            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(medicalStaffBean);
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

    public int getRequestCounts() {

        int counts = 0;

        try {

            session = HibernateUtil.getSessionFactory().openSession();
            SQLQuery query = session.createSQLQuery("SELECT COUNT(id) FROM request WHERE typeId = (SELECT typeId FROM requestType WHERE type = :type)");
            query.setString("type", RequestBean.SURGERY);
            List<BigInteger> list = query.list();
            counts = list.get(0).intValue();

        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            return counts;
        }

    }

    public List<SurgeryRequestBean> getSurgeryRequests(EnterpriseBean enterpriseBean, RoleBean roleBean) {

        List<SurgeryRequestBean> surgeryRequestList = null;

        try {

            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from UserBean where enterpriseBean = :enterpriseBean and roleBean = :roleBean");
            query.setEntity("enterpriseBean", enterpriseBean);
            query.setEntity("roleBean", roleBean);

            List<UserBean> userList = query.list();
            query = session.createQuery("from SurgeryRequestBean where createdBy IN (:list)");
            query.setParameterList("list", userList);
            surgeryRequestList = query.list();

        } catch (HibernateException e) {
            surgeryRequestList = null;
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            return surgeryRequestList;
        }

    }

    public List<DeviceRequestBean> getDeviceRequests(UserBean createdBy) {

        List<DeviceRequestBean> deviceRequestList = null;

        try {

            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from DeviceRequestBean where createdBy = :user");
            query.setEntity("user", createdBy);
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

    public SurgeryRequestBean getSurgeryRequestDetails(int requestId) {

        try {

            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from SurgeryRequestBean where id = :id");
            query.setInteger("id", requestId);
            surgeryRequestBean = (SurgeryRequestBean) query.uniqueResult();
            Hibernate.initialize(surgeryRequestBean.getRequestedProducts());
            Hibernate.initialize(surgeryRequestBean.getCreatedBy());
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

    public void createRequest(DeviceRequestBean deviceRequestBean) {

        try {

            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            SQLQuery query = session.createSQLQuery("CALL createDeviceRequest(:1, :2, :3, :4, :5, :6)");
            query.setInteger("1", deviceRequestBean.getCreatedBy().getId());
            query.setString("2", deviceRequestBean.getNumber());
            query.setInteger("3", deviceRequestBean.getTypeId());
            query.setInteger("4", deviceRequestBean.getStatusId());
            query.setInteger("5", deviceRequestBean.getWarehouse().getId());
            query.setString("6", deviceRequestBean.getSpecialInstruction());
            List<Integer> list = query.list();
            deviceRequestBean.setId(list.get(0));

            for (ProductBean p : deviceRequestBean.getRequestedProducts()) {
                query = session.createSQLQuery("INSERT INTO productrequested VALUES (:requestId, :productId)");
                query.setInteger("requestId", deviceRequestBean.getId());
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

    public List<EnterpriseBean> getWarehouses() {

        List<EnterpriseBean> warehouseList = null;

        try {

            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from EnterpriseBean where typeId = :typeId");
            query.setInteger("typeId", CommonUtil.getMapKey(Constants.ENTERPRISE_TYPE, EnterpriseBean.WAREHOUSE));

            warehouseList = query.list();

        } catch (HibernateException e) {
            warehouseList = null;
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            return warehouseList;
        }

    }

    public List<UserBean> getInventoryManagers(EnterpriseBean enterpriseBean, RoleBean roleBean) {

        List<UserBean> userList = null;

        try {

            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from UserBean where enterpriseBean = :enterprise and roleBean = :role");
            query.setEntity("enterprise", enterpriseBean);
            query.setEntity("role", roleBean);

            userList = query.list();

        } catch (HibernateException e) {
            userList = null;
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            return userList;
        }

    }

    public SurgeryRequestBean processRequest(SurgeryRequestBean surgeryRequestBean) {

        try {

            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            SQLQuery query = session.createSQLQuery("UPDATE request SET statusId = :status, processedDate = NOW(), processComment = :comment WHERE id = :id");
            query.setInteger("status", surgeryRequestBean.getStatusId());
            query.setString("comment", surgeryRequestBean.getProcessComment());
            query.setInteger("id", surgeryRequestBean.getId());
            query.executeUpdate();
            surgeryRequestBean = (SurgeryRequestBean) session.get(SurgeryRequestBean.class, surgeryRequestBean.getId());
            session.getTransaction().commit();

        } catch (HibernateException e) {
            session.getTransaction().rollback();
            surgeryRequestBean = null;
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            return surgeryRequestBean;
        }

    }

    public int isDateAvailable(Date date, SurgeryRequestBean surgeryRequestBean) {

        int flag = 0;

        try {

            session = HibernateUtil.getSessionFactory().openSession();
            SQLQuery query = session.createSQLQuery("CALL isDateAvailable (:1, :2, :3);");
            query.setInteger("1", surgeryRequestBean.getCreatedBy().getEnterpriseBean().getId());
            query.setDate("2", date);
            query.setInteger("3", surgeryRequestBean.getStaffCount());
            List<Integer> list = query.list();
            flag = list.get(0).intValue();

        } catch (HibernateException e) {
            session.getTransaction().rollback();
            surgeryRequestBean = null;
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            return flag;
        }

    }

    public HashMap<Integer, String> getRoomsMap(EnterpriseBean enterpriseBean) {

        HashMap<Integer, String> roomsMap = new HashMap<>();

        try {

            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from OperatingRoomBean where enterpriseBean = :bean");
            query.setEntity("bean", enterpriseBean);
            List<OperatingRoomBean> roomList = query.list();
            for (OperatingRoomBean operatingRoomBean : roomList) {
                roomsMap.put(operatingRoomBean.getId(), operatingRoomBean.getLocation());
            }

        } catch (HibernateException e) {
            session.getTransaction().rollback();
            surgeryRequestBean = null;
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            return roomsMap;
        }

    }

    public List<MedicalStaffBean> getStaff(EnterpriseBean enterpriseBean) {

        List<MedicalStaffBean> staffList = null;

        try {

            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from MedicalStaffBean where enterpriseBean = :bean");
            query.setEntity("bean", enterpriseBean);
            staffList = query.list();

        } catch (HibernateException e) {
            session.getTransaction().rollback();
            surgeryRequestBean = null;
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            return staffList;
        }

    }

    public List<InventoryItemBean> getItems() {

        List<InventoryItemBean> itemsList = null;

        try {

            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from InventoryItemBean");
            itemsList = query.list();

        } catch (HibernateException e) {
            session.getTransaction().rollback();
            surgeryRequestBean = null;
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            return itemsList;
        }

    }

    public void scheduleSurgery(SurgeryBean surgeryBean) {

        try {

            session = HibernateUtil.getSessionFactory().openSession();
            SQLQuery query = session.createSQLQuery("CALL addSurgery (:1, :2, :3)");
            session.beginTransaction();
            query.setInteger("1", surgeryBean.getId());
            query.setDate("2", surgeryBean.getScheduleDate());
            query.setInteger("3", surgeryBean.getOperatingRoomBean().getId());

            query.executeUpdate();
            session.getTransaction().commit();

        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

}
