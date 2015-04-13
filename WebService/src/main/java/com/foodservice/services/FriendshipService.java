package com.foodservice.services;

import com.foodservice.entities.data.State;
import com.foodservice.entities.friendship.Friendship;
import com.foodservice.dao.FriendshipDAO;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
public class FriendshipService {

    private FriendshipDAO friendshipDAO;

    @Autowired
    public void setFriendshipDAO(FriendshipDAO friendshipDAO) {
        this.friendshipDAO = friendshipDAO;
    }

    public Integer createRelations(int applicantId, int acceptorId, State state) {
        return friendshipDAO.create(new Friendship(applicantId, acceptorId, state));
    }

    public boolean changeState(int applicantId, int acceptorId, State state) {
        return friendshipDAO.changeState(applicantId, acceptorId, state);
    }

    public boolean changeState(int id, State state) {
        return friendshipDAO.changeState(id, state);
    }

    @Transactional(readOnly = true)
    public Friendship get(Integer object) {
        return friendshipDAO.get(object);
    }
    @Transactional(readOnly = true)
    public Friendship get(int user1Id, int user2Id) {
        return friendshipDAO.get(user1Id, user2Id);
    }
    public void update(Friendship object) {
        friendshipDAO.update(object);
    }

    public void delete(int user1Id, int user2Id) {
        friendshipDAO.delete(user1Id, user2Id);
    }

    public void delete(Friendship object) {
        friendshipDAO.delete(object);
    }

}
