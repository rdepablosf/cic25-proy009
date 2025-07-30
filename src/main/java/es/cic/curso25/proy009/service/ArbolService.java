package es.cic.curso25.proy009.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso25.proy009.model.Arbol;
import es.cic.curso25.proy009.model.Rama;
import es.cic.curso25.proy009.repository.ArbolRepository;

@Transactional
@Service
public class ArbolService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArbolService.class);
    
    @Autowired
    private ArbolRepository arbolRepository;

    public Arbol create(Arbol arbol) {
        LOGGER.info("se está creando el árbol: {}", arbol);

        for (Rama rama : arbol.getRamas()) {
            rama.setArbol(arbol);
        }

        return arbolRepository.save(arbol);
    }

    public List<Arbol> findAll() {
        LOGGER.info("se están leyendo todos los árboles");
        return arbolRepository.findAll();
    }
    
}
