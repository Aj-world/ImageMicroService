package com.example.ImageMicroService.Repository;

import com.example.ImageMicroService.Entity.ImageEntity;
import org.springframework.data.repository.CrudRepository;

public interface ImageRepository extends CrudRepository<ImageEntity,Integer> {
}
