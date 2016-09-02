/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.web.phmis.controller;

import com.neu.web.phmis.bean.DeviceRequestBean;
import com.neu.web.phmis.bean.EnterpriseBean;
import com.neu.web.phmis.bean.MedicalStaffBean;
import com.neu.web.phmis.bean.OperatingRoomBean;
import com.neu.web.phmis.bean.ProductBean;
import com.neu.web.phmis.bean.RequestBean;
import com.neu.web.phmis.bean.RoleBean;
import com.neu.web.phmis.bean.SurgeryBean;
import com.neu.web.phmis.bean.SurgeryRequestBean;
import com.neu.web.phmis.bean.UserBean;
import com.neu.web.phmis.dao.ResourceDao;
import com.neu.web.phmis.util.CommonUtil;
import com.neu.web.phmis.util.Constants;
import com.neu.web.phmis.util.EmailUtil;
import java.util.ArrayList;
import java.util.Date;
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
public class ResourceController {

    @Autowired
    ResourceDao resourceDao;

    @Autowired
    OperatingRoomBean operatingRoomBean;

    @Autowired
    MedicalStaffBean medicalStaffBean;

    @Autowired
    RoleBean roleBean;

    @Autowired
    SurgeryRequestBean surgeryRequestBean;

    @Autowired
    DeviceRequestBean deviceRequestBean;

    @Autowired
    ProductBean productBean;

    private String viewName = "error";

    @RequestMapping(value = "/resources.htm", method = {RequestMethod.GET, RequestMethod.POST})
    public String resources(Model model, HttpServletRequest request, HttpServletResponse response) {

        try {

            HttpSession session = request.getSession();

            if (session.getAttribute("user") != null) {

                UserBean userBean = (UserBean) session.getAttribute("user");

                if (userBean.getRoleBean().getName().equals(RoleBean.RESOURCE_MANAGER)) {

                    List<OperatingRoomBean> roomList = resourceDao.getOperatingRooms(userBean.getEnterpriseBean().getId());
                    List<MedicalStaffBean> staffList = resourceDao.getMedicalStaff(userBean.getEnterpriseBean().getId());
                    model.addAttribute("roomList", roomList);
                    model.addAttribute("staffList", staffList);
                    model.addAttribute("operatingRoom", operatingRoomBean);
                    model.addAttribute("medicalStaff", medicalStaffBean);
                    viewName = "enterprise/resource/resources";

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

    @RequestMapping(value = "/resources/add.htm", method = {RequestMethod.POST})
    public String addResources(Model model, HttpServletRequest request, HttpServletResponse response, OperatingRoomBean operatingRoomBean, MedicalStaffBean medicalStaffBean) {

        try {

            HttpSession session = request.getSession();

            if (session.getAttribute("user") != null) {

                UserBean userBean = (UserBean) session.getAttribute("user");

                if (userBean.getRoleBean().getName().equals(RoleBean.RESOURCE_MANAGER)) {

                    if (request.getParameter("type") != null) {

                        String type = request.getParameter("type");

                        switch (type) {

                            case "room":
                                operatingRoomBean.setCreationDate(new Date());
                                operatingRoomBean.setEnterpriseBean(userBean.getEnterpriseBean());
                                resourceDao.addRoom(operatingRoomBean);
                                model.addAttribute("roomAdded", true);
                                viewName = "forward:/resources.htm";
                                break;

                            case "staff":
                                medicalStaffBean.setAdditionDate(new Date());
                                medicalStaffBean.setEnterpriseBean(userBean.getEnterpriseBean());
                                resourceDao.addStaff(medicalStaffBean);
                                model.addAttribute("staffAdded", true);
                                viewName = "forward:/resources.htm";
                                break;

                            default:
                                viewName = "error";

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

    @RequestMapping(value = "/resource/requests.htm", method = {RequestMethod.GET, RequestMethod.POST})
    public String surgeryRequests(Model model, HttpServletRequest request, HttpServletResponse response) {

        try {

            HttpSession session = request.getSession();

            if (session.getAttribute("user") != null) {

                UserBean userBean = (UserBean) session.getAttribute("user");

                if (userBean.getRoleBean().getName().equals(RoleBean.RESOURCE_MANAGER)) {

                    roleBean = CommonUtil.getRole(RoleBean.DOCTOR);
                    List<SurgeryRequestBean> surgeryRequestList = resourceDao.getSurgeryRequests(userBean.getEnterpriseBean(), roleBean);
                    List<DeviceRequestBean> deviceRequestList = resourceDao.getDeviceRequests(userBean);
                    List<EnterpriseBean> warehouseList = resourceDao.getWarehouses();
                    HashMap<Integer, String> warehouseMap = new HashMap<>();
                    for (EnterpriseBean eb : warehouseList) {
                        warehouseMap.put(eb.getId(), eb.getName());
                    }

                    model.addAttribute("surgeryRequestList", surgeryRequestList);
                    model.addAttribute("deviceRequestList", deviceRequestList);
                    model.addAttribute("surgeryRequest", surgeryRequestBean);
                    model.addAttribute("deviceRequest", deviceRequestBean);
                    model.addAttribute("requestMap", Constants.REQUEST_STATUS);
                    model.addAttribute("productList", resourceDao.getProducts());
                    model.addAttribute("warehouseMap", warehouseMap);

                    viewName = "enterprise/resource/request";

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

    @RequestMapping(value = "/surgery/requests/process.htm", method = RequestMethod.GET)
    public String viewSurgeryRequest(Model model, HttpServletRequest request, HttpServletResponse response) {

        try {

            HttpSession session = request.getSession();

            if (session.getAttribute("user") != null) {

                UserBean userBean = (UserBean) session.getAttribute("user");

                if (userBean.getRoleBean().getName().equals(RoleBean.RESOURCE_MANAGER)) {

                    if (request.getParameter("rid") != null && !CommonUtil.isNAN(request.getParameter("rid"))) {
                        int requestId = Integer.parseInt(request.getParameter("rid"));
                        surgeryRequestBean = resourceDao.getSurgeryRequestDetails(requestId);

                        model.addAttribute("request", surgeryRequestBean);
                        model.addAttribute("requestStatus", Constants.REQUEST_STATUS);
                        model.addAttribute("requestMap", Constants.REQUEST_STATUS);

                        viewName = "enterprise/resource/processRequest";
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

    @RequestMapping(value = "/device/requests/create.htm", method = RequestMethod.POST)
    public String createRequest(Model model, HttpServletRequest request, HttpServletResponse response, DeviceRequestBean deviceRequestBean) {

        try {

            HttpSession session = request.getSession();

            if (session.getAttribute("user") != null) {

                UserBean userBean = (UserBean) session.getAttribute("user");

                if (userBean.getRoleBean().getName().equals(RoleBean.RESOURCE_MANAGER)) {

                    String[] products = request.getParameterValues("products");
                    if (products.length == 0) {
                        viewName = "error";
                    } else {

                        deviceRequestBean.setCreatedBy(userBean);
                        deviceRequestBean.setCreationDate(new Date());
                        List<ProductBean> productList = new ArrayList<>();
                        for (String pid : products) {
                            productBean = resourceDao.getProduct(Integer.parseInt(pid));
                            productList.add(productBean);
                        }
                        deviceRequestBean.setRequestedProducts(productList);

                        resourceDao.createRequest(deviceRequestBean);

                        roleBean = CommonUtil.getRoleBean(RoleBean.INVENTORY_MANAGER);
                        List<UserBean> userList = resourceDao.getInventoryManagers(deviceRequestBean.getWarehouse(), roleBean);

                        EmailUtil emailUtil = new EmailUtil();
                        emailUtil.setEmailSubject("New Device Request Created");

                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append("A new Request for purchasing new devices was submitted by ").append(userBean.getFirstName()).append(" ").append(userBean.getLastName()).append(".");
                        emailUtil.setEmailBody(stringBuffer.toString());

                        for (UserBean u : userList) {
                            emailUtil.setRecipientEmailID(u.getEmailId());
                            emailUtil.sendEmail();
                        }

                        model.addAttribute("requestCreated", true);
                        viewName = "forward:/resource/requests.htm";

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

    @RequestMapping(value = "/surgery/requests/process.htm", method = RequestMethod.POST)
    public String processRequest(Model model, HttpServletRequest request, HttpServletResponse response, SurgeryRequestBean surgeryRequestBean) {

        try {

            HttpSession session = request.getSession();

            if (session.getAttribute("user") != null) {

                UserBean userBean = (UserBean) session.getAttribute("user");

                if (userBean.getRoleBean().getName().equals(RoleBean.RESOURCE_MANAGER)) {

                    if (surgeryRequestBean.getStatusId() == CommonUtil.getMapKey(Constants.REQUEST_STATUS, RequestBean.APPROVED)) {
                        viewName = "enterprise/resource/scheduleDates";
                        surgeryRequestBean = resourceDao.getSurgeryRequestDetails(surgeryRequestBean.getId());

                        HashMap<Date, String> dateMap = new HashMap<>();
                        Date date = surgeryRequestBean.getDateFrom();
                        while (!date.after(surgeryRequestBean.getDateTo())) {
                            dateMap.put(date, CommonUtil.changeDateFormat(date, "yyyy-MM-dd"));
                            date = CommonUtil.getNextDate(date);
                        }

                        model.addAttribute("request", surgeryRequestBean);
                        model.addAttribute("schedulingDates", dateMap);
                        model.addAttribute("operatingRooms", resourceDao.getRoomsMap(userBean.getEnterpriseBean()));
                        model.addAttribute("staffList", resourceDao.getStaff(userBean.getEnterpriseBean()));
                        model.addAttribute("itemList", resourceDao.getItems());

                    } else {
                        resourceDao.processRequest(surgeryRequestBean);
                        viewName = "forward:/home.htm";
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
    
    @RequestMapping(value = "/surgery/schedule.htm", method = RequestMethod.POST)
    public String schedule(Model model, HttpServletRequest request, HttpServletResponse response, SurgeryRequestBean surgeryRequestBean) {

        try {

            HttpSession session = request.getSession();

            if (session.getAttribute("user") != null) {

                UserBean userBean = (UserBean) session.getAttribute("user");

                if (userBean.getRoleBean().getName().equals(RoleBean.RESOURCE_MANAGER)) {

                    String[] medicalStaffList = request.getParameterValues("staff");
                    String[] itemList = request.getParameterValues("items");
                    
                    SurgeryBean surgeryBean = surgeryRequestBean.getSurgeryBean();
                    surgeryRequestBean = resourceDao.getSurgeryRequestDetails(surgeryRequestBean.getId());
                    surgeryBean.setId(surgeryRequestBean.getSurgeryId());
                    resourceDao.scheduleSurgery(surgeryBean);
                    
                    viewName = "forward:/home.htm";

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
