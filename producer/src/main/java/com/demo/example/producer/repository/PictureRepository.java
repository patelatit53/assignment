package com.demo.example.producer.repository;

import com.demo.example.producer.models.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureRepository extends JpaRepository<Picture, Long> {
}
