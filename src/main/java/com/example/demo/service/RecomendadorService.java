package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecomendadorService {

    public List<String> sugerirProductos(List<String> productosRecibidos) {
        String unicaCategoria = obtenerUnicaCategoria(productosRecibidos);

        if(unicaCategoria != null)
            return restoProductos(unicaCategoria, productosRecibidos);
        return restoProductosDosCategorias(productosRecibidos);
    }
    public List<String> restoProductosDosCategorias(List<String> productosRecibidos) {
        Map<String, List<String>> productosPorCategoria = obtenerProductosPorCategoria();
        List<String> sugerencias = new ArrayList<>();
        List<String> categoriasEncontradas = new ArrayList<>();

        for (String producto : productosRecibidos) {
            for (Map.Entry<String, List<String>> entry : productosPorCategoria.entrySet()) {
                String categoria = entry.getKey();

                if (entry.getValue().contains(producto) && !categoriasEncontradas.contains(categoria)) {
                    List<String> productos = new ArrayList<>(entry.getValue());
                    productos.removeAll(productosRecibidos);
                    categoriasEncontradas.add(categoria);
                    sugerencias.addAll(productos);
                }
            }

            if (categoriasEncontradas.size() == 2) {
                break;
            }
        }

        return sugerencias;
    }

    public List<String> restoProductos(String categoria, List<String> productosRecibidos) {
        Map<String, List<String>> productosPorCategoria = obtenerProductosPorCategoria();
        List<String> productos = new ArrayList<>(productosPorCategoria.get(categoria));
        productos.removeAll(productosRecibidos);
        return productos;
    }

    public String obtenerUnicaCategoria(List<String> productosRecibidos) {
        Map<String, List<String>> productosPorCategoria = obtenerProductosPorCategoria();
        String categoriaEncontrada = null;

        for (String producto : productosRecibidos) {
            for (Map.Entry<String, List<String>> entry : productosPorCategoria.entrySet()) {
                if (entry.getValue().contains(producto)) {
                    if (categoriaEncontrada == null) {
                        categoriaEncontrada = entry.getKey();
                    } else if (!categoriaEncontrada.equals(entry.getKey())) {
                        return null;
                    }
                    break;
                }
            }
        }

        return categoriaEncontrada;
    }

    private static Map<String, List<String>> obtenerProductosPorCategoria() {
        Map<String, List<String>> productosPorCategoria = new HashMap<>();

        // Categoría: Frutas y Verduras
        List<String> frutasYVerduras = new ArrayList<>();
        frutasYVerduras.add("manzana");
        frutasYVerduras.add("banana");
        frutasYVerduras.add("zanahoria");
        productosPorCategoria.put("Frutas y Verduras", frutasYVerduras);

        // Categoría: Lácteos
        List<String> lacteos = new ArrayList<>();
        lacteos.add("leche");
        lacteos.add("queso");
        lacteos.add("yogur");
        productosPorCategoria.put("Lacteos", lacteos);

        // Categoría: Panadería
        List<String> panaderia = new ArrayList<>();
        panaderia.add("pan blanco");
        panaderia.add("pan integral");
        panaderia.add("croissants");
        productosPorCategoria.put("Panaderia", panaderia);

        // Categoría: Carnes
        List<String> carnes = new ArrayList<>();
        carnes.add("pollo");
        carnes.add("ternera");
        carnes.add("pescado");
        productosPorCategoria.put("Carnes", carnes);

        // Categoría: Cuidado Personal
        List<String> cuidadoPersonal = new ArrayList<>();
        cuidadoPersonal.add("jabon");
        cuidadoPersonal.add("shampoo");
        cuidadoPersonal.add("cepillo de dientes");
        productosPorCategoria.put("Cuidado Personal", cuidadoPersonal);

        return productosPorCategoria;
    }


}