package br.com.api;

import br.com.api.dto.ClienteDTO;
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
@TestPropertySource("/application-test.properties")
public class ClienteTeste extends ApiiTeste {

    @Test
    public void deveRetornar201PostNovoCliente() {

        final ClienteDTO clienteDTO = criarClienteDTO();

        clienteDTO.adicionarPet(criarPetDTO());
        clienteDTO.adicionarContato(criarContatoDTO());
        clienteDTO.adicionarEndereco(criarEnderecoDTO());

        given()
                .contentType(ContentType.JSON)
                .header(header)
                .body(clienteDTO)
                .when()
                .post("/cliente")
                .then()
                .statusCode(201);

    }

    @Test
    public void deveRetornar200Listagem() {

        given()
                .contentType(ContentType.JSON)
                .header(header)
                .when()
                .get("/cliente")
                .then()
                .statusCode(200)
                .body("", is(not(empty())));
    }

    @Test
    public void deveRetornar200Exclusao() {

        given()
                .pathParam("clienteId", 100)
                .contentType(ContentType.JSON)
                .header(header)
                .when()
                .delete("/cliente/{clienteId}")
                .then()
                .statusCode(200);
    }

    @Test
    public void deveRetornar404BuscandoClienteInativo() {

      this.deveRetornar200Exclusao();

        given()
                .pathParam("clienteId", 100)
                .contentType(ContentType.JSON)
                .header(header)
                .when()
                .get("/cliente/{clienteId}")
                .then()
                .statusCode(404);
    }

    @Test
    public void deveRetornar200AtualizarNomeCliente() throws JsonProcessingException {

        String responseJson = given()
                .pathParam("clienteId", 100)
                .contentType(ContentType.JSON)
                .header(header)
                .when()
                .get("/cliente/{clienteId}")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .getBody()
                .asString();

        final ClienteDTO clienteDTO =
                objectMapper.readValue(responseJson, ClienteDTO.class);
        clienteDTO.setNome("Editado");

        given()
                .contentType(ContentType.JSON)
                .header(header)
                .body(clienteDTO)
                .when()
                .put("/cliente")
                .then()
                .statusCode(200);
    }

    @Test
    public void deveValidarAtualizacaoNomeCliente() throws JsonProcessingException {

        this.deveRetornar200AtualizarNomeCliente();

        String responseJson = given()
                .pathParam("clienteId", 100)
                .contentType(ContentType.JSON)
                .header(header)
                .when()
                .get("/cliente/{clienteId}")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .getBody()
                .asString();

        final ClienteDTO clienteDTO = objectMapper.readValue(responseJson, ClienteDTO.class);

        Assert.assertTrue(clienteDTO.getNome().equals("Editado"));
    }
}
