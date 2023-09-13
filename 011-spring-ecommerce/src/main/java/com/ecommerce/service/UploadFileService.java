package com.ecommerce.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class UploadFileService {
    private String folder="images//";
    public String saveImage(MultipartFile multipartFile) throws IOException {
        if(!multipartFile.isEmpty()){
            //Pasamos la imagena  bytes
            byte [] bytes = multipartFile.getBytes();
            Path path = Paths.get(folder + multipartFile.getOriginalFilename()); //Ruta donde se almacena la imagen concatenando el nombre de la iamgen
            //Le decimos que escriba en el directorio
            Files.write(path, bytes);//Le pasamos la ruta y la imagen en bytes
            return multipartFile.getOriginalFilename();//retornamos el nombre de la imagen, para guardarla en el campo imagen en la tabla de producto
        }
        return "default.jpg";
    }

    //Método para eliminar una imagen cuando se elimina el producto
    public void deleteImage(String nombreImagen){
        String ruta = "images//";//Apunta al directorio images
        File file = new File(ruta + nombreImagen);
        file.delete();
    }
}
