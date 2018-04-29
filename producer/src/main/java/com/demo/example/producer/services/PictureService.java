package com.demo.example.producer.services;

import com.demo.example.producer.enums.PictureStatus;
import com.demo.example.producer.models.Picture;
import com.demo.example.producer.repository.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PictureService {

    @Autowired
    PictureRepository pictureRepository;

    List<Picture> findAll() {
        return this.pictureRepository.findAll();
    }

    PictureStatus checkIfExists(Picture picture) {
        Optional<Picture> optinal = this.pictureRepository.findById(picture.getId());
        if (optinal.isPresent()) {
            return optinal.get().equals(picture) ? PictureStatus.EQUALS : PictureStatus.UPDATE;
        }
        return PictureStatus.INSERT;
    }

    @Transactional
    Picture savePicture(Picture picture) {
        return this.pictureRepository.save(picture);
    }

    @Transactional
    void deletePicture(Picture picture) {
        this.pictureRepository.delete(picture);
    }

    @Transactional
    Picture updatePicture(Picture picture) {
        Optional<Picture> optionalPicture = this.pictureRepository.findById(picture.getId());
        if (optionalPicture.isPresent()) {
            Picture savedPicture = optionalPicture.get();
            savedPicture.setCreatedAt(picture.getCreatedAt());
            savedPicture.setName(picture.getName());
            savedPicture.setImgUrl(picture.getImgUrl());
        }
        return picture;
    }
}
