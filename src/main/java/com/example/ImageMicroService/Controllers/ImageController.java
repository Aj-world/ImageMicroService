package com.example.ImageMicroService.Controllers;

import com.example.ImageMicroService.Entity.ImageEntity;
import com.example.ImageMicroService.Service.ImageServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;


@RestController
@RequestMapping("/api/images")
public class ImageController {

    @Autowired
    private ImageServices imageService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            imageService.uploadImage(file);
            return ResponseEntity.ok().body("Image uploaded successfully.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image.");
        }
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable Integer id) {
        try {
            Optional<ImageEntity> imageOptional = imageService.getImageById(id);
            if (imageOptional.isPresent()) {
                ImageEntity image = imageOptional.get();
                return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image.getData());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteImage(@PathVariable Integer id) {
        try {
            imageService.deleteImage(id);
            return ResponseEntity.ok().body("Image deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete image.");
        }
    }
}

