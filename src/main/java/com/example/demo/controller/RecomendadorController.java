package com.example.demo.controller;

import com.example.demo.service.RecomendadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("api/v1/recomendador")
public class RecomendadorController {

    @Autowired
    RecomendadorService recomendadorService;

    @PostMapping("/productosSugeridos")
    List<String> obtenerProductosSugeridos(@RequestBody List<String> productos){

        //
        return recomendadorService.sugerirProductos(productos);
    }
}
