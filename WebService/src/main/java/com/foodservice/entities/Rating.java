package com.foodservice.entities;

import com.foodservice.entities.data.LazyClonable;
import com.foodservice.entities.user.SimpleUser;

import javax.persistence.*;

@Entity
@javax.persistence.Table(name = "rating")
public class Rating implements LazyClonable<Rating> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Float value;
    private String comment;

    @Transient
    @ManyToOne(targetEntity = Shop.class)
    @JoinColumn(name = "shop_id", insertable = false, updatable = false)
    private Shop shop;

    /** foreign key */
    @Column(name = "shop_id")
    private Integer shopId;

    @Transient
    @ManyToOne(targetEntity = SimpleUser.class)
    @JoinColumn(name = "simple_user_id", insertable = false, updatable = false)
    private SimpleUser simpleUser;

    /** foreign key */
    @Column(name = "simple_user_id")
    private Integer simpleUserId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSimpleUserId() {
        return simpleUserId;
    }

    public void setSimpleUserId(Integer simpleUserId) {
        this.simpleUserId = simpleUserId;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rating rating = (Rating) o;

        if (id != null ? !id.equals(rating.id) : rating.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", value=" + value +
                ", comment='" + comment + '\'' +
                ", shopId=" + shopId +
                ", simpleUserId=" + simpleUserId +
                '}';
    }

    @Override
    public Rating clone() {
        Rating rating = new Rating();
        rating.setId(this.getId());
        rating.setSimpleUserId(this.getSimpleUserId());
        rating.setComment(this.getComment());
        rating.setShopId(this.getShopId());
        rating.setValue(this.getValue());
        return rating;
    }
}
