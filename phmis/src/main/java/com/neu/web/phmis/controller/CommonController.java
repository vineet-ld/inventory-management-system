/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.web.phmis.controller;

import com.neu.web.phmis.bean.UserBean;
import com.neu.web.phmis.dao.CommonDao;
import com.neu.web.phmis.util.CommonUtil;
import javax.servlet.http.Cookie;
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
public class CommonController {

    @Autowired
    UserBean userBean;

    @Autowired
    CommonDao commonDao;

    String viewName = "error";

    @RequestMapping(value = "/account/activate.htm", method = RequestMethod.GET)
    public String getActivationPage(Model model, HttpServletRequest request, HttpServletResponse response) {

        try {

            String u = request.getParameter("u");
            String key = request.getParameter("key");

            if (u == null || key == null) {
                response.sendRedirect("../error.jsp");
            } else if (CommonUtil.isNAN(u)) {
                response.sendRedirect("../error.jsp");
            } else {

                userBean.setId(Integer.parseInt(u));
                model.addAttribute("key", key);
                model.addAttribute("user", userBean);

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return "common/activate";
        }
    }

    @RequestMapping(value = "/account/activate.htm", method = RequestMethod.POST)
    public void activateAccount(Model model, HttpServletRequest request, HttpServletResponse response, UserBean userBean) {

        try {

            String key = request.getParameter("key");

            if (key == null) {
                response.sendRedirect("../error.jsp");
            } else {

                if (userBean.getPassword().equals("")) {
                    response.sendRedirect("../error.jsp");
                } else if (!userBean.getPassword().equals(request.getParameter("confirm"))) {
                    response.sendRedirect("../error.jsp");
                } else {

                    userBean.setLastAccessedFrom(request.getRemoteAddr());
                    userBean = commonDao.activateAccount(userBean, key);
                    if (userBean != null) {
                        HttpSession session = request.getSession();
                        session.setAttribute("user", userBean);
                        response.sendRedirect("../home.htm");
                    } else {
                        response.sendRedirect("../error.jsp");
                    }

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/login.htm", method = {RequestMethod.GET})
    public String login(Model model, HttpServletRequest request, HttpServletResponse response) {

        try {

            HttpSession session = request.getSession();
            Cookie[] cookies = request.getCookies();
            Cookie c1 = null;
            Cookie c2 = null;
            for (Cookie c : cookies) {
                switch (c.getName()) {
                    case "userCookie":
                        c1 = c;
                        break;
                    case "passCookie":
                        c2 = c;
                        break;
                }
            }

            if (c1 != null && c2 != null) {
                String username = c1.getValue();
                String password = c2.getValue();
                userBean.setUsername(username);
                userBean.setPassword(password);
                userBean = commonDao.authenticate(userBean, request.getRemoteAddr());
                if (userBean != null) {
                    session.setAttribute("user", userBean);
                    viewName = "forward:/home.htm";
                }
            } else {

                model.addAttribute("auth", request.getParameter("auth") == null);
                model.addAttribute("user", userBean);
                viewName = "common/login";
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return viewName;
        }
    }

    @RequestMapping(value = "/login.htm", method = RequestMethod.POST)
    public void authenticate(Model model, HttpServletRequest request, HttpServletResponse response, UserBean user) {

        try {

            if (request.getParameter("remember") != null) {
                Cookie cookie1 = new Cookie("userCookie", user.getUsername());
                Cookie cookie2 = new Cookie("passCookie", user.getPassword());
                cookie1.setMaxAge(60 * 60 * 24 * 7);
                cookie2.setMaxAge(60 * 60 * 24 * 7);
                response.addCookie(cookie1);
                response.addCookie(cookie2);
            }
            user = commonDao.authenticate(user, request.getRemoteAddr());
            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);

                response.sendRedirect("home.htm");

            } else {
                response.sendRedirect("login.htm?auth=0");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @RequestMapping(value = "/logout.htm", method = RequestMethod.GET)
    public String logout(Model model, HttpServletRequest request, HttpServletResponse response, UserBean user) {

        try {

            HttpSession session = request.getSession();
            session.invalidate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return "admin/login";
        }

    }

}
