package com.tiendaProductos.cloudinary.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class CloudinaryService {

    Cloudinary cloudinary;

    private Map<String, String> valuesMap = new HashMap<>();

    //Cuando se cree una instancia de cloudinary se conectará a mi cuenta
    public CloudinaryService() {
        valuesMap.put("cloud_name", "dfoozreth");
        valuesMap.put("api_key", "451634811291998");
        valuesMap.put("api_secret", "_T0WWA82dV970hZq3_lTOtDbBRs");
        cloudinary = new Cloudinary(valuesMap);
    }

    public Map upload(MultipartFile multipartFile) throws IOException {
        File file = convert(multipartFile);
        Map result = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
        //eliminamos el output stream
        file.delete();
        return result;
    }
    public Map delete(String idCloudinary) throws IOException {
        Map result = cloudinary.uploader().destroy(idCloudinary, ObjectUtils.emptyMap());
        return result;
    }

    //Convertir el MultipartFile en un File
    private File convert(MultipartFile multipartFile) throws IOException {
        File file = new File(multipartFile.getOriginalFilename());
        //Escribimos
        FileOutputStream fo = new FileOutputStream(file);
        fo.write(multipartFile.getBytes());
        fo.close();
        return file;
    }
}
