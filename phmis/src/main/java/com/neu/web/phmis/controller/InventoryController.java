/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.web.phmis.controller;

import com.neu.web.phmis.bean.DeviceRequestBean;
import com.neu.web.phmis.bean.EnterpriseBean;
import com.neu.web.phmis.bean.InventoryItemBean;
import com.neu.web.phmis.bean.OrderBean;
import com.neu.web.phmis.bean.OrderItemBean;
import com.neu.web.phmis.bean.ProductBean;
import com.neu.web.phmis.bean.RequestBean;
import com.neu.web.phmis.bean.RoleBean;
import com.neu.web.phmis.bean.UserBean;
import com.neu.web.phmis.dao.InventoryDao;
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
public class InventoryController {

    @Autowired
    RoleBean roleBean;

    @Autowired
    InventoryDao inventoryDao;

    @Autowired
    DeviceRequestBean deviceRequestBean;

    @Autowired
    OrderItemBean orderItemBean;

    @Autowired
    OrderBean orderBean;

    String viewName = "error";

    @RequestMapping(value = "/inventory/requests.htm", method = {RequestMethod.GET, RequestMethod.POST})
    public String surgeryRequests(Model model, HttpServletRequest request, HttpServletResponse response) {

        try {

            HttpSession session = request.getSession();

            if (session.getAttribute("user") != null) {

                UserBean userBean = (UserBean) session.getAttribute("user");

                if (userBean.getRoleBean().getName().equals(RoleBean.INVENTORY_MANAGER)) {

                    List<DeviceRequestBean> deviceRequestList = inventoryDao.getDeviceRequests(userBean.getEnterpriseBean());

                    model.addAttribute("deviceRequestList", deviceRequestList);
                    model.addAttribute("deviceRequest", deviceRequestBean);
                    model.addAttribute("requestMap", Constants.REQUEST_STATUS);
                    model.addAttribute("productList", inventoryDao.getProducts());

                    viewName = "enterprise/inventory/home";

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

    @RequestMapping(value = "/device/requests/process.htm", method = RequestMethod.GET)
    public String viewDeviceRequest(Model model, HttpServletRequest request, HttpServletResponse response) {

        try {

            HttpSession session = request.getSession();

            if (session.getAttribute("user") != null) {

                UserBean userBean = (UserBean) session.getAttribute("user");

                if (userBean.getRoleBean().getName().equals(RoleBean.INVENTORY_MANAGER)) {

                    if (request.getParameter("rid") != null && !CommonUtil.isNAN(request.getParameter("rid"))) {
                        int requestId = Integer.parseInt(request.getParameter("rid"));
                        deviceRequestBean = inventoryDao.getDeviceRequestDetails(requestId);

                        model.addAttribute("request", deviceRequestBean);
                        model.addAttribute("requestMap", Constants.REQUEST_STATUS);

                        viewName = "enterprise/inventory/processRequest";
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

    @RequestMapping(value = "/device/requests/process.htm", method = RequestMethod.POST)
    public String processDeviceRequest(Model model, HttpServletRequest request, HttpServletResponse response, DeviceRequestBean deviceRequestBean) {

        try {

            HttpSession session = request.getSession();

            if (session.getAttribute("user") != null) {

                UserBean userBean = (UserBean) session.getAttribute("user");

                if (userBean.getRoleBean().getName().equals(RoleBean.INVENTORY_MANAGER)) {

                    if (deviceRequestBean.getStatusId() == CommonUtil.getMapKey(Constants.REQUEST_STATUS, RequestBean.APPROVED)) {
                        viewName = "enterprise/inventory/addOrder";
                        deviceRequestBean = inventoryDao.getDeviceRequestDetails(deviceRequestBean.getId());
                        model.addAttribute("request", deviceRequestBean);
                        model.addAttribute("productList", deviceRequestBean.getRequestedProducts());
                    } else {
                        inventoryDao.processRequest(deviceRequestBean);
                        viewName = "forward:/home.htm";

                        deviceRequestBean = inventoryDao.getDeviceRequestDetails(deviceRequestBean.getId());
                        EmailUtil emailUtil = new EmailUtil(deviceRequestBean.getCreatedBy().getEmailId());
                        emailUtil.setEmailSubject("Device Request Processed");

                        StringBuffer buffer = new StringBuffer();
                        buffer.append("Your Request for purchasing New Devices (Request ID: ").append(deviceRequestBean.getNumber()).append(") has been processed.\n");
                        buffer.append("Current Status: ").append(Constants.REQUEST_STATUS.get(deviceRequestBean.getStatusId()));
                        emailUtil.setEmailBody(buffer.toString());
                        emailUtil.sendEmail();
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

    @RequestMapping(value = "/order/create.htm", method = RequestMethod.POST)
    public String createOrder(Model model, HttpServletRequest request, HttpServletResponse response, DeviceRequestBean deviceRequestBean) {

        try {

            HttpSession session = request.getSession();

            if (session.getAttribute("user") != null) {

                UserBean userBean = (UserBean) session.getAttribute("user");

                if (userBean.getRoleBean().getName().equals(RoleBean.INVENTORY_MANAGER)) {

                    int requestId = Integer.parseInt(request.getParameter("requestId"));
                    deviceRequestBean = inventoryDao.getDeviceRequestDetails(requestId);

                    List<OrderItemBean> orderItemList = new ArrayList<>();
                    List<EnterpriseBean> enterpriseList = new ArrayList<>();
                    List<OrderBean> orderList = new ArrayList<>();
                    OrderItemBean orderItemBean = null;
                    for (ProductBean pb : deviceRequestBean.getRequestedProducts()) {

                        orderItemBean = new OrderItemBean();
                        orderItemBean.setProductBean(pb);
                        orderItemBean.setQuantity(Integer.parseInt(request.getParameter(String.valueOf(pb.getId()))));
                        orderItemList.add(orderItemBean);
                        if (!enterpriseList.contains(pb.getEnterpriseBean())) {
                            enterpriseList.add(pb.getEnterpriseBean());
                        }

                    }

                    for (EnterpriseBean eb : enterpriseList) {
                        orderBean.setOrderedBy(userBean.getEnterpriseBean());
                        orderBean.setOrderedFrom(eb);
                        orderBean.setCreationDate(new Date());
                        orderBean.setOrderItemList(new ArrayList<OrderItemBean>());
                        for (OrderItemBean oib : orderItemList) {
                            if (oib.getProductBean().getEnterpriseBean().getId() == eb.getId()) {
                                orderBean.getOrderItemList().add(oib);
                            }
                        }
                        orderList.add(orderBean);
                    }

                    deviceRequestBean.setStatusId(CommonUtil.getMapKey(Constants.REQUEST_STATUS, RequestBean.APPROVED));
                    deviceRequestBean = inventoryDao.processRequest(deviceRequestBean);
                    inventoryDao.addOrder(orderList);

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

    @RequestMapping(value = "/inventory/orders.htm", method = RequestMethod.GET)
    public String orders(Model model, HttpServletRequest request, HttpServletResponse response) {

        try {

            HttpSession session = request.getSession();

            if (session.getAttribute("user") != null) {

                UserBean userBean = (UserBean) session.getAttribute("user");

                if (userBean.getRoleBean().getName().equals(RoleBean.INVENTORY_MANAGER)) {

                    List<OrderBean> orderList = inventoryDao.getOrders(userBean.getEnterpriseBean());
                    model.addAttribute("orderList", orderList);

                    viewName = "/enterprise/inventory/orders";

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

    @RequestMapping(value = "/order/details.htm", method = RequestMethod.GET)
    public String viewOrder(Model model, HttpServletRequest request, HttpServletResponse response) {

        try {

            HttpSession session = request.getSession();

            if (session.getAttribute("user") != null) {

                UserBean userBean = (UserBean) session.getAttribute("user");

                if (userBean.getRoleBean().getName().equals(RoleBean.INVENTORY_MANAGER)) {

                    int orderId = Integer.parseInt(request.getParameter("oid"));
                    orderBean = inventoryDao.getOrderDetails(orderId);
                    model.addAttribute("order", orderBean);

                    viewName = "enterprise/inventory/orderDetails";

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

    @RequestMapping(value = "/inventory/items/add.htm", method = RequestMethod.GET)
    public String addToInventory(Model model, HttpServletRequest request, HttpServletResponse response) {

        try {

            HttpSession session = request.getSession();

            if (session.getAttribute("user") != null) {

                UserBean userBean = (UserBean) session.getAttribute("user");

                if (userBean.getRoleBean().getName().equals(RoleBean.INVENTORY_MANAGER)) {

                    int orderId = Integer.parseInt(request.getParameter("oid"));
                    orderBean = inventoryDao.getOrderDetails(orderId);

                    List<InventoryItemBean> inventoryItemList = new ArrayList<>();
                    InventoryItemBean inventoryItemBean = null;
                    int count = 0;
                    for (OrderItemBean orderItemBean : orderBean.getOrderItemList()) {
                        for (int i = 0; i < orderItemBean.getQuantity(); i++) {
                            inventoryItemBean = new InventoryItemBean();
                            inventoryItemBean.setItemCode(inventoryItemBean.getItemCode() + String.valueOf(count++));
                            inventoryItemBean.setEnterpriseBean(userBean.getEnterpriseBean());
                            inventoryItemBean.setProductBean(orderItemBean.getProductBean());
                            inventoryItemBean.setAdditionDate(new Date());
                            inventoryItemList.add(inventoryItemBean);
                        }
                    }

                    orderBean.setAddedToInventory(1);
                    for (InventoryItemBean bean : inventoryItemList) {
                        inventoryDao.addToInventory(bean);
                    }

                    inventoryDao.markOrderAsAdded(orderBean);
                    viewName = "forward:/inventory/orders.htm";

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

    @RequestMapping(value = "/inventory.htm", method = RequestMethod.GET)
    public String inventory(Model model, HttpServletRequest request, HttpServletResponse response) {

        try {

            HttpSession session = request.getSession();

            if (session.getAttribute("user") != null) {

                UserBean userBean = (UserBean) session.getAttribute("user");

                if (userBean.getRoleBean().getName().equals(RoleBean.INVENTORY_MANAGER)) {

                    List<InventoryItemBean> itemList = inventoryDao.getInventory(userBean.getEnterpriseBean());
                    model.addAttribute("itemList", itemList);
                    viewName = "/enterprise/inventory/inventory";

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
