package com.foodservice.dao;

import com.foodservice.entities.data.State;
import com.foodservice.entities.friendship.Friendship;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
public class FriendshipDAO implements CRUD<Integer, Friendship> {
    
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Integer create(Friendship object) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(object);
        return object.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public Friendship get(Integer object) {
        Session session = sessionFactory.getCurrentSession();
        Friendship friendship = (Friendship) session.get(Friendship.class, object);
        return friendship;
    }

    @Transactional(readOnly = true)
    public Friendship get(int user1Id, int user2Id) {
        Session session = sessionFactory.getCurrentSession();
//        Criteria criteria = session.createCriteria(Friendship.class, "f");
//        criteria.setProjection(Projections.projectionList()
//                        .add(Projections.property("f.applicantId"), "applicantId")
//                        .add(Projections.property("f.acceptorId"), "acceptorId"));
//        criteria.add(Restrictions.or(Restrictions.and(
//                Restrictions.eq("f.applicantId", user1Id), Restrictions.eq("f.acceptorId", user2Id),
//                Restrictions.eq("f.applicantId", user2Id), Restrictions.eq("f.acceptorId", user1Id))));
        Query query = session.createQuery("from Friendship f where " +
                "(f.applicantId = :user1Id and f.acceptorId = :user2Id) or " +
                "(f.applicantId = :user2Id and f.acceptorId = :user1Id)");
        query.setParameter("user1Id", user1Id);
        query.setParameter("user2Id", user2Id);
        Friendship friendship = (Friendship) query.uniqueResult();
//        Friendship res = new Friendship();
//        res.setAcceptorId(friendship.getAcceptorId());
//        res.setApplicantId(friendship.getApplicantId());
//        res.setId(friendship.getId());
        return friendship;
//        criteria.setResultTransformer(Transformers.aliasToBean(Friendship.class));
//        return (Friendship) criteria.uniqueResult();
    }


    @Override
    @Transactional(readOnly = true)
    public List<Friendship> getSome(Integer firstResult, Integer maxResults) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Friendship.class);
        if (firstResult != null) criteria.setFirstResult(firstResult);
        if (maxResults != null) criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    /**
     * Change state by couple of identifiers
     * @param applicantId
     * @param acceptorId
     * @param state
     * @return
     */
    public boolean changeState(int applicantId, int acceptorId, State state) {
        Session session = sessionFactory.getCurrentSession();
        int res = session.createQuery("update Friendship f set f.state = :state " +
                "where f.applicantId = :applicantId and " +
                "f.acceptorId = :acceptorId")
                .setParameter("state", state)
                .setParameter("applicantId", applicantId)
                .setParameter("acceptorId", acceptorId).executeUpdate();
        return res == 1;
    }

    /**
     * Change state by id
     * @param id
     * @param state
     * @return
     */
    public boolean changeState(int id, State state) {
        Session session = sessionFactory.getCurrentSession();
        int res = session.createQuery("update Friendship f set f.state = :state " +
                "where f.id = :id")
                .setParameter("id", id).executeUpdate();
        return res == 1;
    }

    @Override
    public void update(Friendship object) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(object);
    }


    public void delete(int user1Id, int user2Id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from Friendship f where (f.applicantId = :user1Id and f.acceptorId = :user2Id) or " +
        "(f.applicantId = :user2Id and f.acceptorId = :user1Id)");
        query.setParameter("user1Id", user1Id);
        query.setParameter("user2Id", user2Id);
        query.executeUpdate();
    }

    @Override
    public void delete(Friendship object) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(object);
    }
}
