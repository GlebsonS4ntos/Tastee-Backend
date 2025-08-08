package com.Glebson.Tastee.Controllers;

import com.Glebson.Tastee.Services.CloudinaryService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/img")
public class ImageController {

    private final CloudinaryService imgService;

    public ImageController(CloudinaryService imgService) {
        this.imgService = imgService;
    }

    @PutMapping(value = "/{postId}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity uploadImage(@RequestPart MultipartFile img, @PathVariable Long postId){
        imgService.uploadImage(img, postId);
        return ResponseEntity.ok().build();
    }

}
