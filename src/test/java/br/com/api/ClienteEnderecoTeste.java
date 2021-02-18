package br.com.api;

import br.com.api.dto.ClienteEnderecoDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static br.com.api.util.DtoStore.criarEnderecoDTO;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestPropertySource("/application-test.properties")
public class ClienteEnderecoTeste extends ApiiTeste {

    @Test
    public void deveRetornar201PostNovoEndereco() {

        final ClienteEnderecoDTO enderecoDTO = criarEnderecoDTO();
        enderecoDTO.setClienteId(100L);

        given()
                .contentType(ContentType.JSON)
                .header(header)
                .body(enderecoDTO)
                .when()
                .post("/endereco")
                .then()
                .statusCode(201);
    }

    @Test
    public void deveRetornar200Listagem() {

        given()
                .contentType(ContentType.JSON)
                .header(header)
                .when()
                .get("/endereco")
                .then()
                .statusCode(200)
                .body("", is(not(empty())));
    }

    @Test
    public void deveRetornar200Exclusao() {

        given()
                .pathParam("enderecoId", 100)
                .contentType(ContentType.JSON)
                .header(header)
                .when()
                .delete("/endereco/{enderecoId}")
                .then()
                .statusCode(200);
    }

    @Test
    public void deveRetornar404BuscandoEnderecoInativo() {

      this.deveRetornar200Exclusao();

        given()
                .pathParam("enderecoId", 100)
                .contentType(ContentType.JSON)
                .header(header)
                .when()
                .get("/endereco/{enderecoId}")
                .then()
                .statusCode(404);
    }

    @Test
    public void deveRetornar200AtualizarTipoEndereco() throws JsonProcessingException {

        String responseJson = given()
                .pathParam("enderecoId", 100)
                .contentType(ContentType.JSON)
                .header(header)
                .when()
                .get("/endereco/{enderecoId}")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .getBody()
                .asString();

        final ClienteEnderecoDTO enderecoDTO =
                objectMapper.readValue(responseJson, ClienteEnderecoDTO.class);
        enderecoDTO.setTipo("COMERCIAL");

        given()
                .contentType(ContentType.JSON)
                .header(header)
                .body(enderecoDTO)
                .when()
                .put("/endereco")
                .then()
                .statusCode(200);
    }

    @Test
    public void deveValidarAtualizacaoTipoEndereco() throws JsonProcessingException {

        this.deveRetornar200AtualizarTipoEndereco();

        String responseJson = given()
                .pathParam("enderecoId", 100)
                .contentType(ContentType.JSON)
                .header(header)
                .when()
                .get("/endereco/{enderecoId}")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .getBody()
                .asString();

        final ClienteEnderecoDTO enderecoDTO = objectMapper.readValue(responseJson, ClienteEnderecoDTO.class);

        Assert.assertTrue(enderecoDTO.getTipo().equals("COMERCIAL"));
    }
}
