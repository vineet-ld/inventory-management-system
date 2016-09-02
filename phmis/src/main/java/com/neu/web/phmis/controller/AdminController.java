/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.web.phmis.controller;

import com.neu.web.phmis.bean.EnterpriseBean;
import com.neu.web.phmis.bean.RoleBean;
import com.neu.web.phmis.bean.UserBean;
import com.neu.web.phmis.dao.AdminDao;
import com.neu.web.phmis.util.CommonUtil;
import com.neu.web.phmis.util.Constants;
import com.neu.web.phmis.util.EmailUtil;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Vineet
 */
@Controller
public class AdminController {

    @Autowired
    EnterpriseBean enterpriseBean;

    @Autowired
    UserBean userBean;

    @Autowired
    AdminDao adminDao;

    private HttpSession session;
    private String viewName;

    @RequestMapping(value = "/admin.htm", method = {RequestMethod.GET, RequestMethod.POST})
    public String getAdminLoginPage(Model model) {

        return "admin/login";
    }

    @RequestMapping(value = "/admin/login.htm", method = RequestMethod.POST)
    public String login(Model model, HttpServletRequest request, HttpServletResponse response) {

        String viewName = "";

        try {

            String session = adminDao.authenticate(request.getParameter("username"), request.getParameter("password"));

            if (session.equals("1")) {
                viewName = "forward:/admin/home.htm";
                HttpSession session1 = request.getSession();
                session1.setAttribute("session", session);
            } else {
                viewName = "forward:/admin.htm";
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return viewName;
        }

    }

    @RequestMapping(value = "/admin/home.htm", method = {RequestMethod.GET, RequestMethod.POST})
    public String home(Model model, HttpServletRequest request) {
        try {
            session = request.getSession();
            if (session.getAttribute("session") != null && session.getAttribute("session").equals("1")) {
                viewName = "admin/home";
            } else {
                viewName = "unauthorized";
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return viewName;
        }
    }

    @RequestMapping(value = {"/admin/{enterprise}.htm"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String hospitals(Model model, HttpServletRequest request, HttpServletResponse response, @PathVariable String enterprise) {

        String viewName = "";

        try {

            if (session.getAttribute("session") != null && session.getAttribute("session").equals("1")) {
                viewName = "admin/home";

                ArrayList<EnterpriseBean> enterpriseList = null;

                switch (enterprise) {

                    case "hospitals":
                        enterpriseList = adminDao.getEnterpriseList(CommonUtil.getMapKey(Constants.ENTERPRISE_TYPE, EnterpriseBean.HOSPITAL));
                        viewName = "admin/hospitals";
                        break;

                    case "warehouses":
                        enterpriseList = adminDao.getEnterpriseList(CommonUtil.getMapKey(Constants.ENTERPRISE_TYPE, EnterpriseBean.WAREHOUSE));
                        viewName = "admin/warehouses";
                        break;

                    case "vendors":
                        enterpriseList = adminDao.getEnterpriseList(CommonUtil.getMapKey(Constants.ENTERPRISE_TYPE, EnterpriseBean.VENDOR));
                        viewName = "admin/vendors";
                        break;

                    default:
                        response.sendRedirect("../../error.jsp");

                }

                model.addAttribute("counties", Constants.COUNTIES);
                model.addAttribute("enterprise", enterpriseBean);
                model.addAttribute("enterpriseList", enterpriseList);
            } else {
                viewName = "unauthorized";
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return viewName;
        }
    }

    @RequestMapping(value = {"/admin/{enterprise}/add.htm"}, method = RequestMethod.POST)
    public String addHospital(Model model, EnterpriseBean enterpriseBean, HttpServletRequest request, HttpServletResponse response, @PathVariable String enterprise) {

        String viewName = "";

        try {

            if (session.getAttribute("session") != null && session.getAttribute("session").equals("1")) {
                int error = 0;
                if (enterpriseBean.getName().equals("")) {
                    error = 1;
                } else if (enterpriseBean.getStreet().equals("")) {
                    error = 1;
                } else if (enterpriseBean.getCity().equals("")) {
                    error = 1;
                } else if (enterpriseBean.getState().equals("")) {
                    error = 1;
                } else if (enterpriseBean.getZipCode().equals("")) {
                    error = 1;
                } else if (enterpriseBean.getContact().equals("")) {
                    error = 1;
                }

                if (error == 1) {
                    response.sendRedirect("../../error.jsp");
                } else {

                    switch (enterprise) {

                        case "hospitals":
                            enterpriseBean.setTypeId(CommonUtil.getMapKey(Constants.ENTERPRISE_TYPE, EnterpriseBean.HOSPITAL));
                            viewName = "forward:/admin/hospitals.htm";
                            break;

                        case "warehouses":
                            enterpriseBean.setTypeId(CommonUtil.getMapKey(Constants.ENTERPRISE_TYPE, EnterpriseBean.WAREHOUSE));
                            viewName = "forward:/admin/warehouses.htm";
                            break;

                        case "vendors":
                            enterpriseBean.setTypeId(CommonUtil.getMapKey(Constants.ENTERPRISE_TYPE, EnterpriseBean.VENDOR));
                            viewName = "forward:/admin/vendors.htm";
                            break;

                        default:
                            response.sendRedirect("../../error.jsp");

                    }

                    enterpriseBean.setState(enterpriseBean.getState().toUpperCase());
                    enterpriseBean = adminDao.addEnterpriseBean(enterpriseBean);

                    request.setAttribute("enterpriseAdded", enterpriseBean.getId() != 0);

                }
            } else {
                viewName = "unauthorized";
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return viewName;
        }
    }

    @RequestMapping(value = {"/admin/hospitals/edit.htm", "/admin/warehouses/edit.htm", "/admin/vendors/edit.htm"}, method = RequestMethod.GET)
    public String editPage(Model model, HttpServletRequest request, HttpServletResponse response) {
        try {

            if (session.getAttribute("session") != null && session.getAttribute("session").equals("1")) {
                String eid = request.getParameter("eid");
                if (eid != null && !CommonUtil.isNAN(eid)) {

                    enterpriseBean = adminDao.getEnterprise(Integer.parseInt(eid));
                    model.addAttribute("enterprise", enterpriseBean);
                    model.addAttribute("counties", Constants.COUNTIES);

                } else {
                    response.sendRedirect("../../error.jsp");
                }
            } else {
                viewName = "unauthorized";
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return "admin/editEnterprise";
        }
    }

    @RequestMapping(value = {"/admin/hospitals/edit.htm", "/admin/warehouses/edit.htm", "/admin/vendors/edit.htm"}, method = RequestMethod.POST)
    public String editEnterprise(Model model, HttpServletRequest request, HttpServletResponse response, EnterpriseBean enterpriseBean) {

        String viewName = "";

        try {

            if (session.getAttribute("session") != null && session.getAttribute("session").equals("1")) {
                enterpriseBean.setModifiedDate(new Date());
                adminDao.editEnterprise(enterpriseBean);

                switch (enterpriseBean.getTypeId()) {

                    case 1:
                        viewName = "forward:/admin/hospitals/view.htm?eid=" + enterpriseBean.getId();
                        break;

                    case 2:
                        viewName = "forward:/admin/warehouses/view.htm?eid=" + enterpriseBean.getId();
                        break;

                    case 3:
                        viewName = "forward:/admin/vendors/view.htm?eid=" + enterpriseBean.getId();
                        break;

                }
            } else {
                viewName = "unauthorized";
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return viewName;
        }
    }

    @RequestMapping(value = {"/admin/hospitals/view.htm", "/admin/warehouses/view.htm", "/admin/vendors/view.htm"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String viewEnterprise(Model model, HttpServletRequest request, HttpServletResponse response) {
        try {

            if (session.getAttribute("session") != null && session.getAttribute("session").equals("1")) {
                String eid = request.getParameter("eid");
                if (eid != null && !CommonUtil.isNAN(eid)) {

                    enterpriseBean = adminDao.getEnterprise(Integer.parseInt(eid));
                    model.addAttribute("enterprise", enterpriseBean);
                    model.addAttribute("county", Constants.COUNTIES.get(enterpriseBean.getCountyId()));
                    model.addAttribute("user", userBean);
                    viewName = "admin/viewEnterprise";

                } else {
                    response.sendRedirect("../../error.jsp");
                }
            } else {
                viewName = "unauthorized";
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return viewName;
        }
    }

    @RequestMapping(value = {"/admin/enterprise/user/add.htm"}, method = {RequestMethod.POST})
    public String addUser(Model model, HttpServletRequest request, HttpServletResponse response, UserBean userBean) {

        String viewName = "";

        try {

            if (session.getAttribute("session") != null && session.getAttribute("session").equals("1")) {
                String typeId = request.getParameter("et");
                String eid = request.getParameter("eid");

                int error = 0;
                if (userBean.getUsername().equals("")) {
                    error = 1;
                } else if (userBean.getEmailId().equals("")) {
                    error = 1;
                } else if (userBean.getFirstName().equals("")) {
                    error = 1;
                } else if (userBean.getLastName().equals("")) {
                    error = 1;
                } else if (typeId == null || eid == null) {
                    error = 1;
                } else if (CommonUtil.isNAN(typeId) || CommonUtil.isNAN(eid)) {
                    error = 1;
                }

                if (error == 1) {
                    response.sendRedirect("../../../error.jsp");
                } else {

                    switch (Integer.parseInt(typeId)) {

                        case 1:
                            userBean.setRoleBean(CommonUtil.getRole(RoleBean.HOSPITAL_ADMIN));
                            viewName = "forward:/admin/hospitals/view.htm?eid=" + eid;
                            break;

                        case 2:
                            userBean.setRoleBean(CommonUtil.getRole(RoleBean.WAREHOUSE_ADMIN));
                            viewName = "forward:/admin/warehouses/view.htm?eid=" + eid;
                            break;

                        case 3:
                            userBean.setRoleBean(CommonUtil.getRole(RoleBean.STORE_ADMIN));
                            viewName = "forward:/admin/vendors/view.htm?eid=" + eid;
                            break;

                        default:
                            response.sendRedirect("../../../error.jsp");

                    }

                    userBean.setEnterpriseBean(adminDao.getEnterprise(Integer.parseInt(eid)));
                    userBean = adminDao.addUser(userBean);

                    if (userBean.getId() != 0) {

                        request.setAttribute("userAdded", true);

                        EmailUtil emailUtil = new EmailUtil(userBean.getEmailId());
                        emailUtil.setEmailSubject("New User Credentials");

                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append("Hello ").append(userBean.getFirstName()).append(",\n");
                        stringBuffer.append("Welcome to Partner's Health Group - Medical Device Management System.\n");
                        stringBuffer.append("Your Administrative account for ").append(userBean.getEnterpriseBean().getName()).append(" has been created.\n");
                        stringBuffer.append("Please click on the link below to activate your account:\n");
                        stringBuffer.append(Constants.URL_ACTIVATION).append("?u=").append(userBean.getId()).append("&key=").append(userBean.getPassword());

                        emailUtil.setEmailBody(stringBuffer.toString());
                        emailUtil.sendEmail();

                    } else {
                        request.setAttribute("userAdded", false);
                    }
                    request.setAttribute("user", userBean);

                }
            } else {
                viewName = "unauthorized";
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return viewName;
        }
    }

    @RequestMapping(value = {"/admin/hospitals/users/view.htm", "/admin/warehouses/users/view.htm", "/admin/vendors/users/view.htm"}, method = RequestMethod.GET)
    public String viewUser(Model model, HttpServletRequest request, HttpServletResponse response) {
        try {

            if (session.getAttribute("session") != null && session.getAttribute("session").equals("1")) {
                String uid = request.getParameter("uid");
                if (uid != null && !CommonUtil.isNAN(uid)) {

                    userBean = adminDao.getUser(Integer.parseInt(uid));
                    model.addAttribute("user", userBean);
                    model.addAttribute("enterprise", userBean.getEnterpriseBean());

                } else {
                    response.sendRedirect("../../error.jsp");
                }
            } else {
                viewName = "unauthorized";
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return "admin/viewUser";
        }
    }

    @RequestMapping(value = "/admin/logout.htm", method = RequestMethod.GET)
    public String logout(Model model, HttpServletRequest request, HttpServletResponse response, UserBean user) {

        try {

            HttpSession session = request.getSession();
            session.invalidate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return "forward:/admin.htm";
        }

    }

}
