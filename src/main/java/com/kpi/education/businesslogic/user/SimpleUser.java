package com.kpi.education.businesslogic.user;

import com.kpi.education.businesslogic.Ordering;
import com.kpi.education.businesslogic.Rating;
import com.kpi.education.businesslogic.Reservation;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
//@XmlRootElement
@Entity
@javax.persistence.Table(name = "simple_user")
@NamedQueries(value = {
        @NamedQuery(name = "simpleUser.byLogin", query = "from SimpleUser u where u.login = :login"),
        @NamedQuery(name = "simpleUser.byLoginAndPassword", query = "from SimpleUser u where u.login = :login and u.password = :password"),
})
public class SimpleUser extends User {

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Reservation> reservations = new ArrayList<Reservation>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Rating> ratings = new ArrayList<Rating>();

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Ordering> orderings = new ArrayList<Ordering>();

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public List<Ordering> getOrderings() {
        return orderings;
    }

    public void setOrderings(List<Ordering> orderings) {
        this.orderings = orderings;
    }

}
