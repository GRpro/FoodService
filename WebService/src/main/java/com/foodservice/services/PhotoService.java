package com.foodservice.services;

import com.foodservice.dao.PhotoDAO;
import com.foodservice.entities.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
public class PhotoService {

    private PhotoDAO photoDAO;

    @Autowired
    public void setPhotoDAO(PhotoDAO photoDAO) {
        this.photoDAO = photoDAO;
    }

    public Integer create(Photo object) {
        return photoDAO.create(object);
    }

    @Transactional(readOnly = true)
    public Photo get(Integer object) {
        return photoDAO.get(object);
    }

    @Transactional(readOnly = true)
    public List<Photo> getSome(Integer firstResult, Integer maxResults) {
        return photoDAO.getSome(firstResult, maxResults);
    }

    public void update(Photo object) {
        photoDAO.update(object);
    }

    public void delete(Photo object) {
        photoDAO.delete(object);
    }
}
