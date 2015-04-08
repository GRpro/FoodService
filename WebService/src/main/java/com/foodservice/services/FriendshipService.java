package com.foodservice.services;

import com.foodservice.entities.data.State;
import com.foodservice.entities.friendship.Friendship;
import com.foodservice.dao.FriendshipDAO;
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

    public void update(Friendship object) {
        friendshipDAO.update(object);
    }

    public void delete(Friendship object) {
        friendshipDAO.delete(object);
    }

}
