package com.foodservice.dao;

import com.foodservice.entities.data.Gender;
import com.foodservice.entities.data.SystemStatus;
import com.foodservice.entities.user.ManagerUser;
import com.foodservice.entities.user.ShopAdminUser;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

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

    @Transactional(readOnly = true)
    public List<ShopAdminUser> getByCriterion(Map<String, Object> criterionParameters) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(ShopAdminUser.class);

        criteria.setReadOnly(true);
        String param;
        if (!(param = (String) criterionParameters.get("firstNameLike")).equals("")) {
            criteria.add(Restrictions.ilike("firstName", "%" + param + "%"));
        }
        if (!(param = (String) criterionParameters.get("lastNameLike")).equals("")) {
            criteria.add(Restrictions.ilike("lastName", "%" + param + "%"));
        }
        int temp;
        if ((temp = (int) criterionParameters.get("ageMin")) != 0) {
            Date date = new Date();
            date.setYear(date.getYear() - temp);
            criteria.add(Restrictions.gt("dob", date));
        }
        if ((temp = (int) criterionParameters.get("ageMax")) != 100) {
            Date date = new Date();
            date.setYear(date.getYear() - temp);
            criteria.add(Restrictions.lt("dob", date));
        }
        Gender gender;
        if ((gender = (Gender) criterionParameters.get("gender")) != null) {
            criteria.add(Restrictions.eq("gender", gender));
        }
        SystemStatus systemStatus;
        if ((systemStatus = (SystemStatus) criterionParameters.get("systemStatus")) != null) {
            criteria.add(Restrictions.eq("systemStatus", systemStatus));
        }
        return criteria.list();
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
