/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.web.phmis.controller;

import com.neu.web.phmis.bean.ProductBean;
import com.neu.web.phmis.bean.RoleBean;
import com.neu.web.phmis.bean.SurgeryBean;
import com.neu.web.phmis.bean.SurgeryRequestBean;
import com.neu.web.phmis.bean.UserBean;
import com.neu.web.phmis.dao.DoctorDao;
import com.neu.web.phmis.util.CommonUtil;
import com.neu.web.phmis.util.Constants;
import com.neu.web.phmis.util.EmailUtil;
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
public class DoctorController {

    @Autowired
    DoctorDao doctorDao;

    @Autowired
    SurgeryRequestBean surgeryRequestBean;

    @Autowired
    ProductBean productBean;

    @Autowired
    SurgeryBean surgeryBean;

    @Autowired
    RoleBean roleBean;

    String viewName = "error";

    @RequestMapping(value = "/surgery/requests.htm", method = {RequestMethod.GET, RequestMethod.POST})
    public String requests(Model model, HttpServletRequest request, HttpServletResponse response) {

        try {

            HttpSession session = request.getSession();

            if (session.getAttribute("user") != null) {

                UserBean userBean = (UserBean) session.getAttribute("user");

                if (userBean.getRoleBean().getName().equals(RoleBean.DOCTOR)) {

                    List<SurgeryRequestBean> requestList = doctorDao.getRequests(userBean);
                    List<ProductBean> productList = doctorDao.getProducts();

                    model.addAttribute("requestList", requestList);
                    model.addAttribute("productList", productList);
                    model.addAttribute("surgeryRequest", surgeryRequestBean);
                    model.addAttribute("requestStatus", Constants.REQUEST_STATUS);
                    viewName = "enterprise/doctor/request";

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

    @RequestMapping(value = "/surgery/requests/create.htm", method = {RequestMethod.POST})
    public String resources(Model model, HttpServletRequest request, HttpServletResponse response, SurgeryRequestBean surgeryRequestBean) {

        try {

            HttpSession session = request.getSession();

            if (session.getAttribute("user") != null) {

                UserBean userBean = (UserBean) session.getAttribute("user");

                if (userBean.getRoleBean().getName().equals(RoleBean.DOCTOR)) {

                    int error = 0;

                    if (surgeryRequestBean.getDateFrom() == null || surgeryRequestBean.getDateTo() == null) {
                        error = 1;
                    } else if (CommonUtil.isPastDate(surgeryRequestBean.getDateFrom()) || CommonUtil.isPastDate(surgeryRequestBean.getDateTo())) {
                        error = 1;
                    } else if (surgeryRequestBean.getDateFrom().after(surgeryRequestBean.getDateTo())) {
                        error = 1;
                    } else if (surgeryRequestBean.getStaffCount() == 0) {
                        error = 1;
                    }

                    if (error == 0) {

                        surgeryRequestBean.setCreatedBy(userBean);
                        surgeryRequestBean.setCreationDate(new Date());
                        String[] products = request.getParameterValues("products");
                        List<ProductBean> productList = new ArrayList<>();
                        for (String pid : products) {
                            productBean = doctorDao.getProduct(Integer.parseInt(pid));
                            productList.add(productBean);
                        }
                        surgeryRequestBean.setRequestedProducts(productList);

                        surgeryBean.setDoctor(userBean);
                        surgeryBean.setType(request.getParameter("type"));
                        surgeryBean.setPatientName(request.getParameter("patientName"));
                        surgeryBean.setCreationDate(new Date());
                        surgeryRequestBean.setSurgeryBean(surgeryBean);

                        doctorDao.createRequest(surgeryRequestBean);
                        viewName = "forward:/surgery/requests.htm";

                        model.addAttribute("requestCreated", true);

                        for (RoleBean r : Constants.ROLES) {
                            if (r.getName().equals(RoleBean.RESOURCE_MANAGER)) {
                                roleBean = r;
                            }
                        }
                        List<UserBean> managerList = doctorDao.getResourceManagers(userBean.getEnterpriseBean(), roleBean);

                        EmailUtil emailUtil = new EmailUtil();
                        emailUtil.setEmailSubject("New Surgery Request Created");

                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append("A new Request for Scheduling a surgery was submitted by Dr. ").append(userBean.getFirstName()).append(" ").append(userBean.getLastName()).append(".");
                        emailUtil.setEmailBody(stringBuffer.toString());

                        for (UserBean u : managerList) {
                            emailUtil.setRecipientEmailID(u.getEmailId());
                            emailUtil.sendEmail();
                        }

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

    @RequestMapping(value = "/surgery/requests/details.htm", method = {RequestMethod.GET})
    public String requestDetails(Model model, HttpServletRequest request, HttpServletResponse response) {

        try {

            HttpSession session = request.getSession();

            if (session.getAttribute("user") != null) {

                UserBean userBean = (UserBean) session.getAttribute("user");

                if (userBean.getRoleBean().getName().equals(RoleBean.DOCTOR)) {

                    if (request.getParameter("rid") != null && !CommonUtil.isNAN(request.getParameter("rid"))) {
                        int requestId = Integer.parseInt(request.getParameter("rid"));
                        surgeryRequestBean = doctorDao.getRequestDetails(requestId);

                        model.addAttribute("request", surgeryRequestBean);
                        model.addAttribute("requestStatus", Constants.REQUEST_STATUS);

                        viewName = "enterprise/doctor/requestDetails";
                    } else {
                        viewName = "error";
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
