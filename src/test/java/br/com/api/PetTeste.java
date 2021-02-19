package br.com.api;

import br.com.api.dto.ClienteDTO;
import br.com.api.dto.PetDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static br.com.api.util.DtoStore.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class PetTeste extends ApiiTeste {

    @Test
    public void deveRetornar201PostNovoPet() {

        final PetDTO petDTO = criarPetDTO();
        petDTO.setClienteId(100L);

        given()
                .contentType(ContentType.JSON)
                .header(header)
                .body(petDTO)
                .when()
                .post("/pet")
                .then()
                .statusCode(201);
    }

    @Test
    public void deveRetornar200Listagem() {

        given()
                .contentType(ContentType.JSON)
                .header(header)
                .when()
                .get("/pet")
                .then()
                .statusCode(200)
                .body("", is(not(empty())));
    }

    @Test
    public void deveRetornar200Exclusao() {

        given()
                .pathParam("petId", 100)
                .contentType(ContentType.JSON)
                .header(header)
                .when()
                .delete("/pet/{petId}")
                .then()
                .statusCode(200);
    }

    @Test
    public void deveRetornar404BuscandoPetInativo() {

      this.deveRetornar200Exclusao();

        given()
                .pathParam("petId", 100)
                .contentType(ContentType.JSON)
                .header(header)
                .when()
                .get("/pet/{petId}")
                .then()
                .statusCode(404);
    }

    @Test
    public void deveRetornar200AtualizarPortePet() throws JsonProcessingException {

        String responseJson = given()
                .pathParam("petId", 100)
                .contentType(ContentType.JSON)
                .header(header)
                .when()
                .get("/pet/{petId}")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .getBody()
                .asString();

        final PetDTO petDTO =
                objectMapper.readValue(responseJson, PetDTO.class);
        petDTO.setPorte("GRANDE");

        given()
                .contentType(ContentType.JSON)
                .header(header)
                .body(petDTO)
                .when()
                .put("/pet")
                .then()
                .statusCode(200);
    }

    @Test
    public void deveValidarAtualizacaoPortePet() throws JsonProcessingException {

        this.deveRetornar200AtualizarPortePet();

        String responseJson = given()
                .pathParam("petId", 100)
                .contentType(ContentType.JSON)
                .header(header)
                .when()
                .get("/pet/{petId}")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .getBody()
                .asString();

        final PetDTO petDTO = objectMapper.readValue(responseJson, PetDTO.class);

        Assert.assertTrue(petDTO.getPorte().equals("GRANDE"));
    }
}
