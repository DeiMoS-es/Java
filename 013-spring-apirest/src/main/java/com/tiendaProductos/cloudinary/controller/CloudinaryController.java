package com.tiendaProductos.cloudinary.controller;

import com.tiendaProductos.cloudinary.dto.Mensaje;
import com.tiendaProductos.cloudinary.entity.Imagen;
import com.tiendaProductos.cloudinary.service.CloudinaryService;
import com.tiendaProductos.cloudinary.service.ImagenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cloudinary")
@CrossOrigin
public class CloudinaryController {

    @Autowired
    CloudinaryService cloudinaryService;
    @Autowired
    ImagenService imagenService;
    @GetMapping("/imagenes")
    public  ResponseEntity<List<Imagen>> list(){
        List<Imagen> list = imagenService.listarImagenes();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam MultipartFile multipartFile) throws IOException {
        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
        if(bi==null){
            return new ResponseEntity(new Mensaje("Imagen no valida."), HttpStatus.BAD_REQUEST);
        }
        Map result = cloudinaryService.upload(multipartFile);
        Imagen imagen = new Imagen((String)result.get("original_filename"), (String)result.get("url"), (String)result.get("public_id"));
        imagenService.saveImagen(imagen);
        return new ResponseEntity<>(new Mensaje("imagen subida."), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{idImagen}")
    public ResponseEntity<?> upload(@PathVariable("idImagen") int idImagen) throws IOException {
        if(!imagenService.exists(idImagen)){
            return new ResponseEntity<>(new Mensaje("No existe la imagen"), HttpStatus.NOT_FOUND);
        }
        Imagen imagen = imagenService.buscarImgenById(idImagen).get();// convierto en imagen con .get()
        Map result = cloudinaryService.delete(imagen.getIdCloudinary());
        imagenService.deleteImagen(idImagen);
        return new ResponseEntity<>(new Mensaje("imagen eliminada."), HttpStatus.OK);
    }
}
