package es.cic.curso25.proy009.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.cic.curso25.proy009.model.Arbol;
import es.cic.curso25.proy009.service.ArbolService;

@RestController
@RequestMapping("/arboles")
public class ArbolController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArbolController.class);

    @Autowired
    private ArbolService arbolService;

    // create
    @PostMapping
    public Arbol create(@RequestBody Arbol arbol) {
        LOGGER.info("crear arbol");
        return arbolService.create(arbol);
    }

    // read
    @GetMapping
    public List<Arbol> obtenerArboles() {
        LOGGER.info("leer todos los árboles");
        return arbolService.findAll();
    }   
    
    // update
    @PutMapping("/{id}")
    public Arbol update(@PathVariable Long id, @RequestBody Arbol arbol) {
        LOGGER.info("Solicitud para actualizar árbol con ID: {}", id);
        return arbolService.update(id, arbol);
    }

    // delete
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        LOGGER.info("Solicitud para eliminar árbol con ID: {}", id);
        arbolService.delete(id);
    }
    
}
