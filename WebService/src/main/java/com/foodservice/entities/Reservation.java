package com.foodservice.entities;

import com.foodservice.entities.data.LazyClonable;
import com.foodservice.entities.data.State;
import com.foodservice.entities.user.ManagerUser;
import com.foodservice.entities.user.SimpleUser;

import javax.persistence.*;
import java.util.Date;

@Entity
@javax.persistence.Table(name = "reservation")
public class Reservation implements LazyClonable<Reservation> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Transient
    @ManyToOne(targetEntity = Table.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "table_id", insertable = false, updatable = false)
    private Table table;

    @Transient
    @ManyToOne(targetEntity = SimpleUser.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "simple_user_id", insertable = false, updatable = false)
    private SimpleUser simpleUser;

    @Transient
    @ManyToOne(targetEntity = ManagerUser.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_user_id", insertable = false, updatable = false)
    private ManagerUser managerUser;

    /** foreign key */
    @Column(name = "table_id")
    private Integer tableId;

    /** foreign key */
    @Column(name = "simple_user_id")
    private Integer simpleUserId;

    /** foreign key */
    @Column(name = "manager_user_id")
    private Integer managerUserId;



    @Temporal(TemporalType.TIMESTAMP)
    private Date startpoint;

    @Temporal(TemporalType.TIMESTAMP)
    private Date endpoint;

    @Enumerated(EnumType.STRING)
    private State state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public Date getStartpoint() {
        return startpoint;
    }

    public void setStartpoint(Date startpoint) {
        this.startpoint = startpoint;
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

    public Date getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(Date endpoint) {
        this.endpoint = endpoint;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

//    public Table getTable() {
//        return table;
//    }
//
//    public void setTable(Table table) {
//        this.table = table;
//    }

//    public ManagerUser getManagerUser() {
//        return managerUser;
//    }
//
//    public void setManagerUser(ManagerUser managerUser) {
//        this.managerUser = managerUser;
//    }
//
//    public SimpleUser getSimpleUser() {
//        return simpleUser;
//    }
//
//    public void setSimpleUser(SimpleUser simpleUser) {
//        this.simpleUser = simpleUser;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reservation that = (Reservation) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", tableId=" + tableId +
                ", simpleUserId=" + simpleUserId +
                ", managerUserId=" + managerUserId +
                ", startpoint=" + startpoint +
                ", endpoint=" + endpoint +
                ", state=" + state +
                '}';
    }

    @Override
    public Reservation clone() {
        Reservation reservation = new Reservation();
        reservation.setId(this.getId());
        reservation.setSimpleUserId(this.getSimpleUserId());
        reservation.setManagerUserId(this.getManagerUserId());
        reservation.setEndpoint(this.getEndpoint());
        reservation.setStartpoint(this.getStartpoint());
        reservation.setState(this.getState());
        reservation.setTableId(this.getTableId());
        return reservation;
    }
}
