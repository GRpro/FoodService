package com.foodservice.dao;

import com.foodservice.entities.data.State;
import com.foodservice.entities.user.SimpleUser;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
public class SimpleUserDAO implements UserDAO<Integer, SimpleUser> {
    
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Integer create(SimpleUser object) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(object);
        return object.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public SimpleUser get(Integer object) {
        Session session = sessionFactory.getCurrentSession();
        SimpleUser managerUser = (SimpleUser) session.get(SimpleUser.class, object);
        return managerUser;
    }

    @Override
    public List<SimpleUser> getSome(Integer firstResult, Integer maxResults) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(SimpleUser.class);
        if (firstResult != null) criteria.setFirstResult(firstResult);
        if (maxResults != null) criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    @Transactional(readOnly = true)
    public SimpleUser getByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from SimpleUser s where s.email = :email");
        query.setParameter("email", email);
        return (SimpleUser) query.uniqueResult();
    }

    @Transactional(readOnly = true)
    public List<SimpleUser> getRelativeUsersOf(int id, State state) {
        Session session = sessionFactory.getCurrentSession();
//        Criteria criteria1 = session.createCriteria(Friendship.class, "f");
//        criteria1.setProjection(Property.forName("applicant").as("apl"))
//                .add(Restrictions.eq("apl.acceptorId", id));
//
//        Criteria criteria2 = session.createCriteria(Friendship.class, "f");
//        criteria1.setProjection(Property.forName("acceptor").as("ac"))
//                .add(Restrictions.eq("ac.applicantId", id));
        Query query1 = session.createQuery("select f.applicantId from Friendship f where f.acceptorId = :id and f.state = :state");
        Query query2 = session.createQuery("select f.acceptorId from Friendship f where f.applicantId = :id and f.state = :state");
        query1.setParameter("id", id);
        query2.setParameter("id", id);
        List<SimpleUser> users = query1.list();
        users.addAll(query2.list());
        return users;
    }


    @Override
    public void update(SimpleUser object) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(object);
    }

    @Override
    public void delete(SimpleUser object) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(object);
    }
    
}
