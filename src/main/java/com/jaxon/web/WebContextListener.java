package com.jaxon.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * web上下文监听
 * 
 * @author Thinkpad 2018年2月25日
 */
public class WebContextListener implements ServletContextListener
{
    
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent)
    {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent)
    {
        // 获取web上下文
        ServletContext servletContext = servletContextEvent.getServletContext();
        // 获取context-param中配置的参数
        System.out.println("encoding is:" + servletContext.getInitParameter("encoding"));
        
    }
    
}
