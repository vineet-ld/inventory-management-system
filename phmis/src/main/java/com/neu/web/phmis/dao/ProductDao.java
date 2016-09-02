/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.web.phmis.dao;

import com.neu.web.phmis.bean.EnterpriseBean;
import com.neu.web.phmis.bean.ProductBean;
import com.neu.web.phmis.util.HibernateUtil;
import java.util.List;
import javax.transaction.Transactional;
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
public class ProductDao {

    private Session session;

    public List<ProductBean> getProducts(int enterpriseId) {

        List<ProductBean> productList = null;

        try {

            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from ProductBean where enterpriseId = :id");
            query.setInteger("id", enterpriseId);

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

    public void addProduct(ProductBean productBean) {

        try {

            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(productBean);
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

}
