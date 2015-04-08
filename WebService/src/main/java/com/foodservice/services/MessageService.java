package com.foodservice.services;

import com.foodservice.dao.MessageDAO;
import com.foodservice.entities.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
public class MessageService {

    private MessageDAO messageDAO;

    @Autowired
    public void setMessageDAO(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    public Integer create(Message object) {
        return messageDAO.create(object);
    }

    @Transactional(readOnly = true)
    public Message get(Integer object) {
        return messageDAO.get(object);
    }

    @Transactional(readOnly = true)
    public List<Message> getDialogMessages(int user1Id, int user2Id, int firstResult, int maxResults) {
        return messageDAO.getDialogMessages(user1Id, user2Id, firstResult, maxResults);
    }

    public void update(Message object) {
        messageDAO.update(object);
    }

    public void delete(Message object) {
        messageDAO.delete(object);
    }
}
