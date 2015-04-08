package com.foodservice.entities;

import com.foodservice.entities.data.LazyClonable;
import com.foodservice.entities.user.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@javax.persistence.Table(name = "message")
public class Message implements LazyClonable<Message> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String text;

    @Temporal(TemporalType.TIMESTAMP)
    private Date time;

    @Transient
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", insertable = false, updatable = false)
    private User sender;

    @Transient
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id", insertable = false, updatable = false)
    private User receiver;

    /** foreign key */
    @Column(name = "sender_id")
    private Integer senderId;

    /** foreign key */
    @Column(name = "receiver_id")
    private Integer receiverId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

//    public User getSender() {
//        return sender;
//    }
//
//    public void setSender(User sender) {
//        this.sender = sender;
//    }
//
//    public User getReceiver() {
//        return receiver;
//    }
//
//    public void setReceiver(User receiver) {
//        this.receiver = receiver;
//    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (id != null ? !id.equals(message.id) : message.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", time=" + time +
                ", senderId=" + senderId +
                ", receiverId=" + receiverId +
                '}';
    }

    @Override
    public Message clone() {
        Message message = new Message();
        message.setId(this.getId());
        message.setReceiverId(this.getReceiverId());
        message.setSenderId(this.getSenderId());
        message.setText(this.getText());
        message.setTime(this.getTime());
        return message;
    }
}
