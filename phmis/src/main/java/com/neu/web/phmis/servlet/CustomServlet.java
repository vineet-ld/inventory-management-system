/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.web.phmis.servlet;

import com.neu.web.phmis.dao.InitDao;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import org.springframework.web.servlet.DispatcherServlet;

/**
 *
 * @author Vineet
 */
public class CustomServlet extends DispatcherServlet {

    private InitDao initDao = new InitDao();

    @Override
    public void init(ServletConfig config) throws ServletException {

        try {
            super.init(config);
            initDao.init();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
