package com.franconeta.clinicaodontologica.controller;

import com.franconeta.clinicaodontologica.model.dto.OdontologoDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OdontologoControllerTest {

     private TestRestTemplate testRestTemplate;
     @Autowired
     private RestTemplateBuilder restTemplateBuilder;
     @LocalServerPort
     private int port;

     @BeforeEach
     void setUp() {
          restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
          testRestTemplate = new TestRestTemplate(restTemplateBuilder);
     }

     @Test
     void getOdontologoById() {
     }


     @DisplayName("Comprobar la creacion de un odontologo")
     @Test
     void createOdontologoTest() {
//          Se preparan los headers
          HttpHeaders headers = new HttpHeaders();
//          Para indicar que recibimos y enviamos  json
          headers.setContentType(MediaType.APPLICATION_JSON);
          headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

//          El json que se envia
          String json = """
                  {
                      "nombre":"Franco",
                      "apellido":"Dreer",
                      "matricula":"123"
                  }
             """;

          HttpEntity<String> request = new HttpEntity<>(json, headers);
          ResponseEntity<OdontologoDTO> response =
                  testRestTemplate.exchange("/odontologo", HttpMethod.POST, request, OdontologoDTO.class);

          OdontologoDTO odontologo = response.getBody();

          Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
          Assertions.assertEquals(1L, odontologo.getId());
          Assertions.assertEquals("123", odontologo.getMatricula());
     }

     @Test
     void getAllOdontologosTest() {
          ResponseEntity<OdontologoDTO[]> response =
                  testRestTemplate.getForEntity("/odontologo", OdontologoDTO[].class);

          Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

//          Convierte la respuesta a una lista
          List<OdontologoDTO> odontologos = Arrays.asList(response.getBody());
          System.out.println(odontologos.size());
     }
}