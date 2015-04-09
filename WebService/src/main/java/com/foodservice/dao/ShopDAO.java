package com.foodservice.dao;

import com.foodservice.entities.Rating;
import com.foodservice.entities.Shop;
import com.foodservice.entities.user.ShopAdminUser;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Repository
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
public class ShopDAO implements CRUD<Integer, Shop> {
    
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Integer create(Shop object) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(object);
        return object.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public Shop get(Integer object) {
        Session session = sessionFactory.getCurrentSession();
        Shop shop = (Shop) session.get(Shop.class, object);
        return shop;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Shop> getSome(Integer firstResult, Integer maxResults) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Shop.class);
        if (firstResult != null) criteria.setFirstResult(firstResult);
        if (maxResults != null) criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Transactional(readOnly = true)
    public List<Shop> getByShopAdminUserID(Integer shopAdminUserId) {
        Session session = sessionFactory.getCurrentSession();
        List<Shop> shops = session.createQuery(
                "from Shop s where s.shopAdminUserId = :shopAdminUserId")
                .setParameter("shopAdminUserId", shopAdminUserId).list();
        return shops;
    }
    
    @Transactional(readOnly = true)
    public List<Shop> getByCriterion(Map<String, Object> criterionParameters) {
        Session session = sessionFactory.getCurrentSession();
//        System.out.println("nameLike" + criterionParameters.get("nameLike"));
//        System.out.println("minRating"+ criterionParameters.get("minRating"));
//        System.out.println("maxRating"+ criterionParameters.get("maxRating"));
//        System.out.println("countryLike"+ criterionParameters.get("regionLike"));
//        System.out.println("cityLike"+ criterionParameters.get("cityLike"));
//        System.out.println("streetLike"+ criterionParameters.get("streetLike"));
//        System.out.println("buildingLike"+ criterionParameters.get("buildingLike"));
        Criteria criteria = session.createCriteria(Shop.class, "s");
        criteria.setReadOnly(true);
        String param;
        if (!(param = (String) criterionParameters.get("nameLike")).equals("")) {
            criteria.add(Restrictions.ilike("name", "%" + param + "%"));
        }
        if (!(param = (String) criterionParameters.get("regionLike")).equals("")) {
            criteria.add(Restrictions.ilike("location.region", "%" + param + "%"));
        }
        if (!(param = (String) criterionParameters.get("cityLike")).equals("")) {
            criteria.add(Restrictions.ilike("location.city", "%" + param + "%"));
        }
        if (!(param = (String) criterionParameters.get("streetLike")).equals("")) {
            criteria.add(Restrictions.ilike("location.street", "%" + param + "%"));
        }
        if (!(param = (String) criterionParameters.get("buildingLike")).equals("")) {
            criteria.add(Restrictions.ilike("location.building", "%" + param + "%"));
        }

        DetachedCriteria avg = DetachedCriteria.forClass(Rating.class, "r")
                .setProjection(Projections.avg("r.value"))
                .add(Restrictions.eqProperty("r.shopId", "s.id"));
        double temp;
        if ((temp = (Double) criterionParameters.get("maxRating")) != 5) {
            criteria.add(Subqueries.gt(temp, avg));
        }
        if ((temp = (Double) criterionParameters.get("minRating")) != 0) {
            criteria.add(Subqueries.lt(temp, avg));
        }
        return criteria.list();
    }

    @Override
    public void update(Shop object) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(object);
    }

    @Override
    public void delete(Shop object) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(object);
    }
}
