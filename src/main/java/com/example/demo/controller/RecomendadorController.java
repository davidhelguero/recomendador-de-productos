package com.example.demo.controller;

import com.example.demo.service.RecomendadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("api/v1/recomendador")
public class RecomendadorController {

    @Autowired
    RecomendadorService recomendadorService;

    @GetMapping("/productosSugeridos")
    List<String> obtenerProductosSugeridos(@RequestBody List<String> productos){

        //
        return recomendadorService.sugerirProductos(productos);
    }
}
