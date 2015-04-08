package com.foodservice.entities;

import com.foodservice.entities.data.LazyClonable;
import com.foodservice.entities.data.OrderingStatus;
import com.foodservice.entities.user.SimpleUser;
import com.foodservice.entities.user.ManagerUser;

import javax.persistence.*;
import java.util.Date;

@Entity
@javax.persistence.Table(name = "ordering")
public class Ordering implements LazyClonable<Ordering> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "order_date")
    private Date orderDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "finish_date")
    private Date finishDate;

    @Transient
    @ManyToOne(targetEntity = SimpleUser.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "simple_user_id", insertable = false, updatable = false)
    private SimpleUser simpleUser;

    @Transient
    @ManyToOne(targetEntity = ManagerUser.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_user_id", insertable = false, updatable = false)
    private ManagerUser managerUser;

    /** foreign key */
    @Column(name = "simple_user_id")
    private Integer simpleUserId;

    /** foreign key */
    @Column(name = "manager_user_id")
    private Integer managerUserId;

    @Enumerated(EnumType.STRING)
    private OrderingStatus orderingStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OrderingStatus getOrderingStatus() {
        return orderingStatus;
    }

    public void setOrderingStatus(OrderingStatus orderingStatus) {
        this.orderingStatus = orderingStatus;
    }

    public Integer getSimpleUserId() {
        return simpleUserId;
    }

    public void setSimpleUserId(Integer simpleUserId) {
        this.simpleUserId = simpleUserId;
    }

    public Integer getManagerUserId() {
        return managerUserId;
    }

    public void setManagerUserId(Integer managerUserId) {
        this.managerUserId = managerUserId;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

//    public SimpleUser getSimpleUser() {
//        return simpleUser;
//    }
//
//    public void setSimpleUser(SimpleUser simpleUser) {
//        this.simpleUser = simpleUser;
//    }
//
//    public ManagerUser getManagerUser() {
//        return managerUser;
//    }
//
//    public void setManagerUser(ManagerUser managerUser) {
//        this.managerUser = managerUser;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ordering ordering = (Ordering) o;

        if (id != null ? !id.equals(ordering.id) : ordering.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Ordering{" +
                "id=" + id +
                ", orderDate=" + orderDate +
                ", finishDate=" + finishDate +
                ", simpleUserId=" + simpleUserId +
                ", managerUserId=" + managerUserId +
                ", orderingStatus=" + orderingStatus +
                '}';
    }

    @Override
    public Ordering clone() {
        Ordering ordering = new Ordering();
        ordering.setId(this.getId());
        ordering.setManagerUserId(this.getManagerUserId());
        ordering.setSimpleUserId(this.getSimpleUserId());
        ordering.setOrderingStatus(this.getOrderingStatus());
        ordering.setFinishDate(this.getFinishDate());
        ordering.setOrderDate(this.getOrderDate());
        return ordering;
    }
}
