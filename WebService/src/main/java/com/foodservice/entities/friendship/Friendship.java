package com.foodservice.entities.friendship;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.foodservice.entities.data.LazyClonable;
import com.foodservice.entities.data.State;
import com.foodservice.entities.user.SimpleUser;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;

@JsonIgnoreProperties(value = {"applicant", "acceptor"})
@Entity
@javax.persistence.Table(name = "friendship", uniqueConstraints=
@UniqueConstraint(columnNames = {"applicant_id", "acceptor_id"}))
public class Friendship implements LazyClonable<Friendship> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @Transient
    @ManyToOne(targetEntity = SimpleUser.class, fetch=FetchType.LAZY)
    @JoinColumn(name = "applicant_id", insertable = false, updatable = false)
    private SimpleUser applicant;

//    @Transient
    @ManyToOne(targetEntity = SimpleUser.class, fetch=FetchType.LAZY)
    @JoinColumn(name = "acceptor_id", insertable = false, updatable = false)
    private SimpleUser acceptor;


    /** foreign key */
    @Column(name = "applicant_id")
    private Integer applicantId;

    /** foreign key */
    @Column(name = "acceptor_id")
    private Integer acceptorId;


    @Enumerated(EnumType.STRING)
    private State state;

    public Friendship(int applicantId, int acceptorId, State state) {
        this.applicantId = applicantId;
        this.acceptorId = acceptorId;
        this.state = state;
    }

    public Friendship() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Integer getAcceptorId() {
        return acceptorId;
    }

    public void setAcceptorId(Integer acceptorId) {
        this.acceptorId = acceptorId;
    }

    public Integer getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(Integer applicantId) {
        this.applicantId = applicantId;
    }

    public SimpleUser getApplicant() {
        return applicant;
    }
//
    public void setApplicant(SimpleUser applicant) {
        this.applicant = applicant;
    }
//
    public SimpleUser getAcceptor() {
        return acceptor;
    }

    public void setAcceptor(SimpleUser acceptor) {
        this.acceptor = acceptor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Friendship that = (Friendship) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Friendship{" +
                "id=" + id +
                ", applicantId=" + applicantId +
                ", acceptorId=" + acceptorId +
                ", state=" + state +
                '}';
    }

    @Override
    public Friendship clone() {
        Friendship friendship = new Friendship();
        friendship.setId(this.id);
        friendship.setState(this.state);
        friendship.setAcceptorId(this.acceptorId);
        friendship.setApplicantId(this.applicantId);
        return friendship;
    }
}
