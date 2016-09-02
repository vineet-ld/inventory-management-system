/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.web.phmis.controller;

import com.neu.web.phmis.bean.EnterpriseBean;
import com.neu.web.phmis.bean.RoleBean;
import com.neu.web.phmis.bean.SurgeryBean;
import com.neu.web.phmis.bean.UserBean;
import com.neu.web.phmis.dao.DoctorDao;
import com.neu.web.phmis.dao.EnterpriseDao;
import com.neu.web.phmis.dao.ResourceDao;
import com.neu.web.phmis.util.CommonUtil;
import com.neu.web.phmis.util.Constants;
import com.neu.web.phmis.util.EmailUtil;
import java.util.HashMap;
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
public class EnterpriseController {

    @Autowired
    UserBean userBean;

    @Autowired
    EnterpriseBean enterpriseBean;

    @Autowired
    EnterpriseDao enterpriseDao;

    @Autowired
    DoctorDao doctorDao;

    @Autowired
    ResourceDao resourceDao;

    String viewName = "error";

    @RequestMapping(value = "/home.htm", method = {RequestMethod.GET, RequestMethod.POST})
    public String home(Model model, HttpServletRequest request, HttpServletResponse response) {

        try {

            HttpSession session = request.getSession();
            if (session.getAttribute("user") == null) {
                viewName = "forward:/login.htm";
            } else {

                userBean = (UserBean) session.getAttribute("user");

                switch (userBean.getRoleBean().getName()) {

                    case RoleBean.HOSPITAL_ADMIN:
                    case RoleBean.WAREHOUSE_ADMIN:
                    case RoleBean.STORE_ADMIN:
                        viewName = "enterprise/home";
                        break;

                    case RoleBean.DOCTOR:
                        List<SurgeryBean> surgeryList = doctorDao.getSurgeries(userBean);
                        model.addAttribute("surgeryList", surgeryList);
                        viewName = "enterprise/doctor/home";
                        break;

                    case RoleBean.RESOURCE_MANAGER:
                        int count = resourceDao.getRequestCounts();
                        model.addAttribute("totalCount", count);
                        viewName = "enterprise/resource/home";
                        break;

                    case RoleBean.INVENTORY_MANAGER:
                        viewName = "forward:/inventory/requests.htm";
                        break;

                    case RoleBean.PRODUCT_MANAGER:
                        viewName = "enterprise/product/home";
                        break;

                    case RoleBean.SALES_MANAGER:
                        viewName = "enterprise/sales/home";
                        break;

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return viewName;
        }
    }

    @RequestMapping(value = "/users.htm", method = {RequestMethod.GET, RequestMethod.POST})
    public String users(Model model, HttpServletRequest request, HttpServletResponse response) {

        try {

            HttpSession session = request.getSession();
            if (session.getAttribute("user") == null) {
                viewName = "forward:/login.htm";
            } else {

                UserBean user = (UserBean) session.getAttribute("user");

                enterpriseBean = enterpriseDao.getUsers(user.getEnterpriseBean());
                model.addAttribute("users", enterpriseBean.getUsers());
                viewName = "enterprise/users";
                model.addAttribute("user", new UserBean());

                HashMap<Integer, String> supportedRoles = new HashMap<>();
                for (RoleBean roleBean : Constants.ROLES) {
                    if (roleBean.getSupportedEnterpriseTypeId() == enterpriseBean.getTypeId()) {
                        supportedRoles.put(roleBean.getId(), roleBean.getName());
                    }
                }
                model.addAttribute("roles", supportedRoles);

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return viewName;
        }
    }

    @RequestMapping(value = {"/users/add.htm"}, method = {RequestMethod.POST})
    public String addUser(Model model, HttpServletRequest request, HttpServletResponse response, UserBean newUser) {

        try {

            HttpSession session = request.getSession();
            userBean = (UserBean) session.getAttribute("user");

            if (userBean == null) {
                response.sendRedirect("forward:/login.htm");
            } else {

                int error = 0;
                if (newUser.getUsername().equals("")) {
                    error = 1;
                } else if (newUser.getEmailId().equals("")) {
                    error = 1;
                } else if (newUser.getFirstName().equals("")) {
                    error = 1;
                } else if (newUser.getLastName().equals("")) {
                    error = 1;
                }

                if (error != 1) {

                    newUser.setEnterpriseBean(userBean.getEnterpriseBean());
                    newUser = enterpriseDao.addUser(newUser);

                    if (newUser.getId() != 0) {

                        request.setAttribute("userAdded", true);

                        EmailUtil emailUtil = new EmailUtil(newUser.getEmailId());
                        emailUtil.setEmailSubject("New User Credentials");

                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append("Hello ").append(newUser.getFirstName()).append(",\n");
                        stringBuffer.append("Welcome to Partner's Health Group - Medical Device Management System.\n");
                        stringBuffer.append("Your account for ").append(newUser.getEnterpriseBean().getName()).append(" has been created.\n");
                        stringBuffer.append("Please click on the link below to activate your account:\n");
                        stringBuffer.append(Constants.URL_ACTIVATION).append("?u=").append(newUser.getId()).append("&key=").append(newUser.getPassword());

                        emailUtil.setEmailBody(stringBuffer.toString());
                        emailUtil.sendEmail();

                    } else {
                        request.setAttribute("userAdded", false);
                    }
                    request.setAttribute("user", newUser);
                    viewName = "forward:/users.htm";

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return viewName;
        }
    }

    @RequestMapping(value = "/users/view.htm", method = RequestMethod.GET)
    public String viewUser(Model model, HttpServletRequest request, HttpServletResponse response) {
        try {

            String uid = request.getParameter("uid");
            if (uid != null && !CommonUtil.isNAN(uid)) {

                userBean = enterpriseDao.getUser(Integer.parseInt(uid));
                model.addAttribute("user", userBean);
                model.addAttribute("enterprise", userBean.getEnterpriseBean());
                viewName = "enterprise/viewUser";

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return viewName;
        }
    }

}
