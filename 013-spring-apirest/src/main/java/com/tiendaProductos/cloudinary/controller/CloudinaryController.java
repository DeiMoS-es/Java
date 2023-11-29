package com.tiendaProductos.cloudinary.controller;

import com.tiendaProductos.cloudinary.dto.Mensaje;
import com.tiendaProductos.cloudinary.service.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/cloudinary")
@CrossOrigin
public class CloudinaryController {

    @Autowired
    CloudinaryService cloudinaryService;
    @PostMapping("/upload")
    public ResponseEntity<Map> upload(@RequestParam MultipartFile multipartFile) throws IOException {
        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
        if(bi==null){
            return new ResponseEntity(new Mensaje("Imagen no valida."), HttpStatus.BAD_REQUEST);
        }
        Map result = cloudinaryService.upload(multipartFile);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{idImagen}")
    public ResponseEntity<Map> upload(@PathVariable("idImagen") String idImagen) throws IOException {
        Map result = cloudinaryService.delete(idImagen);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
