package com.foodservice.dao;

import com.foodservice.entities.data.SystemStatus;
import com.foodservice.entities.user.User;
import com.foodservice.entities.Message;
import com.foodservice.entities.data.UserType;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional //isolation by default to improve performance
public class UserCommonDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Gets systemStatus for users with for users with defined identifiers
     * @param identifiers
     * @return List of systemStatus relatively to each identifier
     */
    public SystemStatus[] getSystemStatus(Integer[] identifiers) {
        Session session = sessionFactory.getCurrentSession();
        SystemStatus[] statuses = new SystemStatus[identifiers.length];
        Query query = session.createQuery("SELECT u.systemStatus from User u where u.id = :id");
        int i = 0;
        for (Integer id : identifiers) {
            query.setParameter("id", id);
            statuses[i++] = (SystemStatus) query.uniqueResult();
        }
        return statuses;
    }

    public boolean changeSystemStatus(Integer id, SystemStatus status) {
        Session session = sessionFactory.getCurrentSession();
        int res = session.createQuery("update User u set u.systemStatus = :status where u.id = :id")
                .setParameter("status", status).setParameter("id", id).executeUpdate();
        return res == 1;
    }

    public User getByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        return (User) session.createQuery("from User u where u.email = :email")
                .setParameter("email", email).uniqueResult();
    }

    /**
     * Returns some paginated users which have already dialogue with user who has
     * this identifier.
     * @param id identifier of user
     * @param firstResult
     * @param maxResults
     * @return
     */
    public List<User> getMessageRelatedUsers(Integer id, int firstResult, int maxResults, UserType userType) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(User.class, "u");

        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Message.class, "m");
        detachedCriteria.add(Restrictions.or(
                Restrictions.and(
                        Restrictions.eq("m.senderId", id), Restrictions.eqProperty("m.receiverId", "u.id")),
                Restrictions.and(
                        Restrictions.eqProperty("m.senderId", "u.id"), Restrictions.eq("m.receiverId", id))));
        criteria.add(Subqueries.exists(detachedCriteria));
        if (userType != null)
            criteria.add(Restrictions.eq("m.userType", userType));
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }
}
