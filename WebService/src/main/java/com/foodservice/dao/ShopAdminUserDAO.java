package com.foodservice.dao;

import com.foodservice.entities.user.ShopAdminUser;
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
public class ShopAdminUserDAO implements UserDAO<Integer, ShopAdminUser> {
    
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Integer create(ShopAdminUser object) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(object);
        return object.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public ShopAdminUser get(Integer object) {
        Session session = sessionFactory.getCurrentSession();
        ShopAdminUser managerUser = (ShopAdminUser) session.get(ShopAdminUser.class, object);
        return managerUser;
    }

    @Override
    public List<ShopAdminUser> getSome(Integer firstResult, Integer maxResults) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(ShopAdminUser.class);
        if (firstResult != null) criteria.setFirstResult(firstResult);
        if (maxResults != null) criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    @Transactional(readOnly = true)
    public ShopAdminUser getByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ShopAdminUser s where s.email = :email");
        query.setParameter("email", email);
        return (ShopAdminUser) query.uniqueResult();
    }

    @Override
    public void update(ShopAdminUser object) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(object);
    }

    @Override
    public void delete(ShopAdminUser object) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(object);
    }
}
