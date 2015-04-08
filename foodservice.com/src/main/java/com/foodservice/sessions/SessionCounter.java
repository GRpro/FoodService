package com.foodservice.sessions;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.ArrayList;

public class SessionCounter implements HttpSessionListener
{
    private List sessions = new ArrayList();

    public void sessionCreated(HttpSessionEvent event)
    {
//        System.out.println(":::::::::::::::::::::::::::::::Session Created");
        HttpSession session = event.getSession();
        sessions.add(session.getId());

        session.setAttribute("counter", this);
    }

    public void sessionDestroyed(HttpSessionEvent event)
    {
//        System.out.println(":::::::::::::::::::::::::::::::Session Destroyed");
        HttpSession session = event.getSession();
        sessions.remove(session.getId());

        session.setAttribute("counter", this);
    }

    public int getActiveSessionNumber()
    {
        return sessions.size();
    }
}