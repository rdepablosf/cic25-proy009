package es.cic.curso25.proy009.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.cic.curso25.proy009.model.Arbol;
import es.cic.curso25.proy009.model.Rama;
import es.cic.curso25.proy009.service.ArbolService;

@SpringBootTest
@AutoConfigureMockMvc
public class ArbolControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ArbolService arbolService;

    private Arbol arbol;
    private Rama rama;

    @BeforeEach
    void setUp() {
        rama = new Rama();
        rama.setId(1L);
        rama.setLongitud(2.5);

        arbol = new Arbol();
        arbol.setId(1L);
        arbol.setEspecie("Roble");
        arbol.setAltura(10.0);
        arbol.setRamas(Collections.singletonList(rama));
    }

    @Test
    void testCreateArbol() throws Exception {
        when(arbolService.create(any(Arbol.class))).thenReturn(arbol);

        mockMvc.perform(MockMvcRequestBuilders.post("/arboles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(arbol)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(arbol.getId()))
                .andExpect(jsonPath("$.especie").value(arbol.getEspecie()))
                .andExpect(jsonPath("$.altura").value(arbol.getAltura()));
    }

    @Test
    void testGetAllArboles() throws Exception {
        when(arbolService.findAll()).thenReturn(Arrays.asList(arbol));

        mockMvc.perform(MockMvcRequestBuilders.get("/arboles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(arbol.getId()))
                .andExpect(jsonPath("$[0].especie").value(arbol.getEspecie()));
    }

    @Test
    void testUpdateArbol() throws Exception {
        Arbol actualizado = new Arbol();
        actualizado.setId(1L);
        actualizado.setEspecie("Pino");
        actualizado.setAltura(12.0);
        actualizado.setRamas(Collections.singletonList(rama));

        when(arbolService.update(eq(1L), any(Arbol.class))).thenReturn(actualizado);

        mockMvc.perform(MockMvcRequestBuilders.put("/arboles/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(actualizado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.especie").value("Pino"))
                .andExpect(jsonPath("$.altura").value(12.0));
    }

    @Test
    void testDeleteArbol() throws Exception {
        doNothing().when(arbolService).delete(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/arboles/1"))
                .andExpect(status().isOk());
    }
}
