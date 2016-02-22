package com.blueware.util;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * @author qinheng
 *
 * Date 2015年6月7日
 */
public class InitConfigInfo extends HttpServlet{
	protected static final Logger LOG = LoggerFactory.getLogger(InitConfigInfo.class);  
	public void init() throws ServletException {

        ServletConfig config = this.getServletConfig();
        String driver = config.getInitParameter("driver");
        String username = config.getInitParameter("username");
        String password = config.getInitParameter("password");
        String url = config.getInitParameter("url");

        DBManager.init(driver, url, username, password);

        System.out.println("数据库初始化成功!");
    }
}


