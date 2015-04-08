package com.foodservice.dao;

import com.foodservice.entities.friendship.Friendship;
import com.foodservice.entities.user.ManagerUser;
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
public class ManagerUserDAO implements UserDAO<Integer, ManagerUser> {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Integer create(ManagerUser object) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(object);
        return object.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public ManagerUser get(Integer object) {
        Session session = sessionFactory.getCurrentSession();
        ManagerUser managerUser = (ManagerUser) session.get(ManagerUser.class, object);
        return managerUser;
    }

    @Override
    public List<ManagerUser> getSome(Integer firstResult, Integer maxResults) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(ManagerUser.class);
        if (firstResult != null) criteria.setFirstResult(firstResult);
        if (maxResults != null) criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Transactional(readOnly = true)
    public List<ManagerUser> getByShopAdminUserID(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        List managerUsers = session.createQuery(
                "from ManagerUser m where m.shopAdminUserId = :shopAdminUserId")
                .setParameter("shopAdminUserId", id).list();
        return managerUsers;
    }

    @Override
    public void update(ManagerUser object) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(object);
    }

    @Override
    public void delete(ManagerUser object) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(object);
    }

    @Override
    public ManagerUser getByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ManagerUser m where m.email = :email");
        query.setParameter("email", email);
        return (ManagerUser) query.uniqueResult();
    }

}
