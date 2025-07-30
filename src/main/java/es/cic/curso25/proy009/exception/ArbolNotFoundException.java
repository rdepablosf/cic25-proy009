package es.cic.curso25.proy009.exception;

/**
 * Excepción personalizada para cuando no se encuentra un árbol.
 */
public class ArbolNotFoundException extends RuntimeException {
    public ArbolNotFoundException(Long id) {
        super("Árbol no encontrado con id: " + id);
    }
}