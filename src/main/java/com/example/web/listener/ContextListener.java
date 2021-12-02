package com.example.web.listener;

import com.example.db.ConnectionPool;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        // lazy initializing DBHelper
        System.out.println("Context initialization");
        ConnectionPool.getInstance();
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {

        System.out.println("context destroyer was called");
    }
}
