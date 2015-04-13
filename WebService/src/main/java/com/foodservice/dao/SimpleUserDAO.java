package com.foodservice.dao;

import com.foodservice.entities.data.Gender;
import com.foodservice.entities.data.State;
import com.foodservice.entities.data.SystemStatus;
import com.foodservice.entities.friendship.Friendship;
import com.foodservice.entities.user.SimpleUser;
import org.hibernate.*;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    private Criteria addRestrictions(Criteria criteria, Map<String, Object> criterionParameters) {
        String param;
        if (!(param = (String) criterionParameters.get("firstNameLike")).equals("")) {
            criteria.add(Restrictions.ilike("u.firstName", "%" + param + "%"));
        }
        if (!(param = (String) criterionParameters.get("lastNameLike")).equals("")) {
            criteria.add(Restrictions.ilike("u.lastName", "%" + param + "%"));
        }
        Integer temp;
        if ((temp = (Integer) criterionParameters.get("ageMin")) != null) {
            Date date = new Date();
            date.setYear(date.getYear() - temp);
            criteria.add(Restrictions.gt("u.dob", date));
        }
        if ((temp = (Integer) criterionParameters.get("ageMax")) != null) {
            Date date = new Date();
            date.setYear(date.getYear() - temp);
            criteria.add(Restrictions.lt("u.dob", date));
        }
        Gender gender;
        if ((gender = (Gender) criterionParameters.get("gender")) != null) {
            criteria.add(Restrictions.eq("u.gender", gender));
        }
        SystemStatus systemStatus;
        if ((systemStatus = (SystemStatus) criterionParameters.get("systemStatus")) != null) {
            criteria.add(Restrictions.eq("u.systemStatus", systemStatus));
        }
        return criteria;
    }

    @Transactional(readOnly = true)
    public List<SimpleUser> getByCriterion(Map<String, Object> criterionParameters) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(SimpleUser.class, "u");
        criteria.setReadOnly(true);
        criteria = addRestrictions(criteria, criterionParameters);
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

    //********************************friendship support-start**************************************//

    @Transactional(readOnly = true)
    public List<SimpleUser> getFriends(int id, Map<String, Object> criterionParameters) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria1 = session.createCriteria(Friendship.class, "f");
        criteria1.setProjection(Projections.property("f.applicant"));
        criteria1.createAlias("f.applicant", "u");
        criteria1.add(Restrictions.and(Restrictions.eq("f.acceptorId", id), Restrictions.eq("f.state", State.ACCEPTED)));
        criteria1 = addRestrictions(criteria1, criterionParameters);

        Criteria criteria2 = session.createCriteria(Friendship.class, "f");
        criteria2.setProjection(Projections.property("f.acceptor"));
        criteria2.createAlias("f.acceptor", "u");
        criteria2.add(Restrictions.and(Restrictions.eq("f.applicantId", id), Restrictions.eq("f.state", State.ACCEPTED)));
        criteria2 = addRestrictions(criteria2, criterionParameters);

        List<SimpleUser> simpleUsers1 = criteria1.list();
        List<SimpleUser> simpleUsers2 = criteria2.list();
        simpleUsers1.addAll(simpleUsers2);
        for (SimpleUser simpleUser :simpleUsers1)
            System.out.println(simpleUser);
        return simpleUsers1;
    }

    @Transactional(readOnly = true)
    public List<SimpleUser> getFollowers(int id, Map<String, Object> criterionParameters) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Friendship.class, "f");
        criteria.setProjection(Projections.property("f.applicant"));
        criteria.createAlias("f.applicant", "u");
        criteria.add(Restrictions.eq("f.acceptorId", id));
        criteria.add(Restrictions.eq("f.state", State.REFUSED));

        criteria = addRestrictions(criteria, criterionParameters);
        List<SimpleUser> simpleUsers = criteria.list();
        for (SimpleUser simpleUser :simpleUsers)
            System.out.println(simpleUser);
        return simpleUsers;
    }

    @Transactional(readOnly = true)
    public List<SimpleUser> getFollowedBy(int id, Map<String, Object> criterionParameters) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Friendship.class, "f");
        criteria.setProjection(Projections.property("f.acceptor"));
        criteria.createAlias("f.acceptor", "u");
        criteria.add(Restrictions.and(Restrictions.eq("f.applicantId", id), Restrictions.eq("f.state", State.REFUSED)));

        criteria = addRestrictions(criteria, criterionParameters);
        List<SimpleUser> simpleUsers = criteria.list();
        for (SimpleUser simpleUser :simpleUsers)
            System.out.println(simpleUser);
        return simpleUsers;
    }

    @Transactional(readOnly = true)
    public List<SimpleUser> getRequestedTo(int id, Map<String, Object> criterionParameters) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Friendship.class, "f");
        criteria.setProjection(Projections.property("f.applicant"));
        criteria.createAlias("f.applicant", "u");
        criteria.add(Restrictions.and(Restrictions.eq("f.acceptorId", id), Restrictions.eq("f.state", State.NEW)));

        criteria = addRestrictions(criteria, criterionParameters);
        List<SimpleUser> simpleUsers = criteria.list();
        for (SimpleUser simpleUser :simpleUsers)
            System.out.println(simpleUser);
        return simpleUsers;
    }

    @Transactional(readOnly = true)
    public List<SimpleUser> getRequestedBy(int id, Map<String, Object> criterionParameters) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Friendship.class, "f");
        criteria.setProjection(Projections.property("f.acceptor"));
        criteria.createAlias("f.acceptor", "u");
        System.out.println(id);
        criteria.add(Restrictions.eq("f.applicantId", id));
        criteria.add(Restrictions.eq("f.state", State.NEW));

        criteria = addRestrictions(criteria, criterionParameters);
//        criteria.setFetchMode("f.acceptor", FetchMode.JOIN);
//        criteria.setFetchMode("u.photos", FetchMode.JOIN);
        List<SimpleUser> simpleUsers = criteria.list();
        for (SimpleUser simpleUser :simpleUsers)
            System.out.println(simpleUser);
        return simpleUsers;
    }

    //********************************friendship support-end**************************************//



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
