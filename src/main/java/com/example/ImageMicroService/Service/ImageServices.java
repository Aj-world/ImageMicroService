package com.example.ImageMicroService.Service;


import com.example.ImageMicroService.Entity.ImageEntity;
import com.example.ImageMicroService.Repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageServices {

    @Autowired
    private ImageRepository imageRepository;


    public void uploadImage(MultipartFile file) throws IOException {
        ImageEntity image = new ImageEntity();
        image.setData(file.getBytes());
        imageRepository.save(image);
    }


    public Optional<ImageEntity> getImageById(Integer id) {
        return imageRepository.findById(id);
    }


    public void deleteImage(Integer id) {
        imageRepository.deleteById(id);
    }
}