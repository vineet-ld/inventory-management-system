/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.web.phmis.controller;

import com.neu.web.phmis.bean.ProductBean;
import com.neu.web.phmis.bean.RoleBean;
import com.neu.web.phmis.bean.UserBean;
import com.neu.web.phmis.dao.ProductDao;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Vineet
 */
@Controller
public class ProductController {

    @Autowired
    ProductDao productDao;

    @Autowired
    ProductBean productBean;

    @Autowired
    UserBean userBean;

    private String viewName = "error";

    @RequestMapping(value = "/products.htm", method = {RequestMethod.GET, RequestMethod.POST})
    public String products(Model model, HttpServletRequest request, HttpServletResponse response) {

        try {

            HttpSession session = request.getSession();

            if (session.getAttribute("user") != null) {

                UserBean userBean = (UserBean) session.getAttribute("user");

                if (userBean.getRoleBean().getName().equals(RoleBean.PRODUCT_MANAGER)) {
                    List<ProductBean> productList = productDao.getProducts(userBean.getEnterpriseBean().getId());

                    ArrayList<String> productNames = new ArrayList<>();
                    for (ProductBean productBean : productList) {
                        productNames.add(productBean.getName());
                    }

                    model.addAttribute("productList", productList);
                    model.addAttribute("product", productBean);
                    model.addAttribute("productNames", productNames);
                    viewName = "enterprise/product/products";
                } else {
                    viewName = "unauthorized";
                }

            } else {
                viewName = "forward:/login.htm";
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return viewName;
        }

    }

    @RequestMapping(value = "/products/add.htm", method = RequestMethod.POST)
    public String add(Model model, HttpServletRequest request, HttpServletResponse response, ProductBean productBean) {

        try {

            HttpSession session = request.getSession();

            if (session.getAttribute("user") != null) {

                userBean = (UserBean) session.getAttribute("user");
                if (userBean.getRoleBean().getName().equals(RoleBean.PRODUCT_MANAGER)) {
                    if (!productBean.getName().equals("") && !productBean.getDescription().equals("") && productBean.getCost() != 0.0) {

                        productBean.setEnterpriseBean(userBean.getEnterpriseBean());
                        productBean.setAdditionDate(new Date());
                        productDao.addProduct(productBean);
                        model.addAttribute("productAdded", true);
                        viewName = "forward:/products.htm";

                    }
                } else {
                    viewName = "unauthorized";
                }

            } else {
                viewName = "forward:/login.htm";
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return viewName;
        }

    }

}
