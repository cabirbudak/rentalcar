package com.example.rentalcar.Controller;

import com.example.rentalcar.Entity.User.Role;
import com.example.rentalcar.Service.CarPhotoService;
import com.example.rentalcar.Service.DocumentPhotoService;
import com.example.rentalcar.Service.UserService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
public class FileStorageController {

    private final DocumentPhotoService documentPhotoService;

    private final UserService userService;

    private final CarPhotoService carPhotoService;

    public FileStorageController(DocumentPhotoService documentPhotoService,
                                 UserService userService,
                                 CarPhotoService carPhotoService) {
        this.documentPhotoService = documentPhotoService;
        this.userService = userService;
        this.carPhotoService = carPhotoService;
    }
    @GetMapping(value = "/account/personalData/{userId}/{fileName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<InputStreamResource> getDocumentsPhoto(@PathVariable Long userId,
                                                                 @PathVariable String fileName) throws IOException {
        Path path = documentPhotoService.getPathToDocumentPhoto(userService.getCurrentUser(), userId, fileName);
        return path != null
                ? ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .contentLength(Files.size(path))
                .body(new InputStreamResource(Files.newInputStream(path)))
                : ResponseEntity.notFound().build();
    }
    @GetMapping(value = "/cars/{carId}/{fileName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<InputStreamResource> getPhotoCar(@PathVariable Long carId,
                                                           @PathVariable String fileName) throws IOException {
        Path path = carPhotoService.getPathToCarPhoto(carId, fileName);
        return path != null
                ? ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .contentLength(Files.size(path))
                .body(new InputStreamResource(Files.newInputStream(path)))
                : ResponseEntity.notFound().build();
    }
}
