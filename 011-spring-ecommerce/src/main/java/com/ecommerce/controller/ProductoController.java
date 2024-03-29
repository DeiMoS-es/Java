package com.ecommerce.controller;

import com.ecommerce.model.Producto;
import com.ecommerce.model.Usuario;
import com.ecommerce.service.ProductoService;
import com.ecommerce.service.UploadFileService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/productos")
@AllArgsConstructor
public class ProductoController {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);

    private final ProductoService productoService;

    private final UploadFileService uploadFileService;

    @GetMapping("")
    public String show(Model model){
        // Model se importa de: import org.springframework.ui.Model;
        // Los objetos de tipo Model se encargan de mandar objetos a la vista correspondiente
        // Este método recibe un parámetro (nombre/varibale) en la que se val a "almacenar" los datos, en este caso una lista dr proudctos
        // Y el segundo parámetro es la información, la variable o el objeto que tiene la información
        model.addAttribute("productos", productoService.findAll());
        return "productos/show";
    }

    @GetMapping("/create")
    public String create(){
        return "productos/create";
    }

    @PostMapping("/save")
    public String save(Producto producto, @RequestParam("img") MultipartFile multipartFile) throws IOException {
        LOGGER.info("Este es el objeto producto {}", producto);
        Usuario u = new Usuario(1, "","","","","","","");
        producto.setUsuario(u);
        // Lógica para subir imagenes al servidor
        if(producto.getImagen() == null){//cuando se crea un producto
            String nombreImagen = uploadFileService.saveImage(multipartFile);
            producto.setImagen(nombreImagen);
        } else{

        }
        productoService.saveProducto(producto);
        return "redirect:/productos";
    }
    @GetMapping("/edit/{idProducto}")
    public String edit(@PathVariable("idProducto") Integer idPRoducto, Model model){
        Producto producto = new Producto();
        Optional<Producto> optionalProducto = productoService.getProducto(idPRoducto);
        producto = optionalProducto.get();//con el .get() obtenemos el producto
        model.addAttribute("producto", producto);
        LOGGER.info("Producto buscado: {}", producto);
        return "/productos/edit";
    }
    @PostMapping ("/update")
    public String update(Producto producto, @RequestParam("img") MultipartFile multipartFile) throws IOException {
        Producto p = new Producto();
        p = productoService.getProducto(producto.getIdProducto()).get();
        if(multipartFile.isEmpty()){//Si el campo imagen viene vacío se pondrá la imagen que ya tenía antes de actualizar

            producto.setImagen(p.getImagen());//producto nos llega desde la vista
        } else {
            //Eliminar imagen cuando no sea la imagen por defecto, al editar debe eliminarse la iamgen anterior, para sustituir por la nueva
            if(!p.getImagen().equals("default.jpg")){
                uploadFileService.deleteImage(p.getImagen());
            }
            //Obtenemos la imagen nueva al editar
            String nombreImagen = uploadFileService.saveImage(multipartFile);
            producto.setImagen(nombreImagen);
        }
        producto.setUsuario(p.getUsuario());
        productoService.updateProducto(producto);
        return "redirect:/productos";
    }
    @GetMapping("/delete/{idProducto}")
    public String delete(@PathVariable("idProducto") Integer idPRoducto){
        Producto p = new Producto();
        p = productoService.getProducto(idPRoducto).get();
        //Eliminar imagen cuando no sea la imagen por defecto
        if(!p.getImagen().equals("default.jpg")){
            uploadFileService.deleteImage(p.getImagen());
        }
        productoService.deleteProducto(idPRoducto);
        return "redirect:/productos";
    }
}
