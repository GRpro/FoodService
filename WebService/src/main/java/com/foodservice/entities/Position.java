package com.foodservice.entities;

import com.foodservice.entities.data.LazyClonable;

import javax.persistence.*;

@Entity
@javax.persistence.Table(name = "position")
public class Position implements LazyClonable<Position> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Transient
    @ManyToOne(targetEntity = Dish.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "dish_id", insertable = false, updatable = false)
    private Dish dish;

    @Transient
    @ManyToOne(targetEntity = Ordering.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "ordering_id", insertable = false, updatable = false)
    private Ordering ordering;

    /** foreign key */
    @Column(name = "dish_id")
    private Integer dishId;

    /** foreign key */
    @Column(name = "ordering_id")
    private Integer orderingId;

    private Integer number;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getOrderingId() {
        return orderingId;
    }

    public void setOrderingId(Integer orderingId) {
        this.orderingId = orderingId;
    }

    public Integer getDishId() {
        return dishId;
    }

    public void setDishId(Integer dishId) {
        this.dishId = dishId;
    }

//    public Ordering getOrdering() {
//        return ordering;
//    }
//
//    public void setOrdering(Ordering ordering) {
//        this.ordering = ordering;
//    }
//
//    public Dish getDish() {
//        return dish;
//    }
//
//    public void setDish(Dish dish) {
//        this.dish = dish;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (id != null ? !id.equals(position.id) : position.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Position{" +
                "id=" + id +
                ", dishId=" + dishId +
                ", orderingId=" + orderingId +
                ", number=" + number +
                '}';
    }

    @Override
    public Position clone() {
        Position position = new Position();
        position.setId(this.getId());
        position.setDishId(this.getDishId());
        position.setNumber(this.getNumber());
        position.setOrderingId(this.getOrderingId());
        return position;
    }
}
