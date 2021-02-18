package br.com.api;

import br.com.api.dto.ClienteContatoDTO;
import br.com.api.dto.ClienteEnderecoDTO;
import br.com.api.repository.entities.ClienteContato;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.http.ContentType;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static br.com.api.util.DtoStore.criarContatoDTO;
import static br.com.api.util.DtoStore.criarEnderecoDTO;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestPropertySource("/application-test.properties")
public class ClienteContatoTeste extends ApiiTeste {

    @Test
    public void deveRetornar201PostNovoContato() {

        final ClienteContatoDTO contatoDTO = criarContatoDTO();
        contatoDTO.setClienteId(100L);

        given()
                .contentType(ContentType.JSON)
                .header(header)
                .body(contatoDTO)
                .when()
                .post("/contato")
                .then()
                .statusCode(201);
    }

    @Test
    public void deveRetornar200Listagem() {

        given()
                .contentType(ContentType.JSON)
                .header(header)
                .when()
                .get("/contato")
                .then()
                .statusCode(200)
                .body("", is(not(empty())));
    }

    @Test
    public void deveRetornar200Exclusao() {

        given()
                .pathParam("contatoId", 100)
                .contentType(ContentType.JSON)
                .header(header)
                .when()
                .delete("/contato/{contatoId}")
                .then()
                .statusCode(200);
    }

    @Test
    public void deveRetornar404BuscandoContatoInativo() {

      this.deveRetornar200Exclusao();

        given()
                .pathParam("contatoId", 100)
                .contentType(ContentType.JSON)
                .header(header)
                .when()
                .get("/contato/{contatoId}")
                .then()
                .statusCode(404);
    }

    @Test
    public void deveRetornar200AtualizarTipoContato() throws JsonProcessingException {

        String responseJson = given()
                .pathParam("contatoId", 100)
                .contentType(ContentType.JSON)
                .header(header)
                .when()
                .get("/contato/{contatoId}")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .getBody()
                .asString();

        final ClienteContatoDTO contatoDTO =
                objectMapper.readValue(responseJson, ClienteContatoDTO.class);
        contatoDTO.setTipo("FIXO");

        given()
                .contentType(ContentType.JSON)
                .header(header)
                .body(contatoDTO)
                .when()
                .put("/contato")
                .then()
                .statusCode(200);
    }

    @Test
    public void deveValidarAtualizacaoTipoContato() throws JsonProcessingException {

        this.deveRetornar200AtualizarTipoContato();

        String responseJson = given()
                .pathParam("contatoId", 100)
                .contentType(ContentType.JSON)
                .header(header)
                .when()
                .get("/contato/{contatoId}")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .getBody()
                .asString();

        final ClienteContatoDTO contatoDTO = objectMapper.readValue(responseJson, ClienteContatoDTO.class);

        Assert.assertTrue(contatoDTO.getTipo().equals("FIXO"));
    }
}
