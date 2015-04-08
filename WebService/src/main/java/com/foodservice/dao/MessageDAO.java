package com.foodservice.dao;

import com.foodservice.entities.Message;
import com.foodservice.entities.user.ManagerUser;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
public class MessageDAO implements CRUD<Integer, Message>{

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Integer create(Message object) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(object);
        return object.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public Message get(Integer object) {
        Session session = sessionFactory.getCurrentSession();
        Message message = (Message) session.get(Message.class, object);
        return message;
    }

    @Override
    public List<Message> getSome(Integer firstResult, Integer maxResults) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Message.class);
        if (firstResult != null) criteria.setFirstResult(firstResult);
        if (maxResults != null) criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Transactional(readOnly = true)
    public List<Message> getDialogMessages(int user1Id, int user2Id, int firstResult, int maxResults) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Message.class, "m");
        criteria.add(Restrictions.or(
                Restrictions.and(
                    Restrictions.eq("m.senderId", user1Id), Restrictions.eq("m.receiverId", user2Id)),
                Restrictions.and(
                    Restrictions.eq("m.senderId", user2Id), Restrictions.eq("m.receiverId", user1Id))));
        criteria.addOrder(Order.desc("m.time"));
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public void update(Message object) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(object);
    }

    @Override
    public void delete(Message object) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(object);
    }



}
