package br.com.api;

import br.com.api.dto.ClienteDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import org.flywaydb.core.Flyway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static br.com.api.util.DtoStore.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ApiiTeste {

    @Value("${spring.datasource.username}")
    private String dbUsername;

    @Value("${spring.datasource.password}")
    private String dbPassword;

    @Value("${spring.datasource.url}")
    private String dbUrl;

    protected ObjectMapper objectMapper;

    protected String accessToken;

    protected Header header;

    @Before
    public void init() {

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        RestAssured.port = 8280;
        RestAssured.basePath = "/api";

        this.objectMapper = new ObjectMapper();

        Flyway.configure()
                .schemas("apiitest")
                .dataSource(dbUrl, dbUsername, dbPassword)
                .locations("classpath:db/testdata").load().migrate();

        accessToken = getAcessToken();
        header = new Header("Authorization", "Bearer " .concat(accessToken));
    }

    public String getAcessToken() {

        return given()
                    .header("Authorization", "Basic YXBpaTpzZW5oYQ==")
                    .contentType(ContentType.URLENC)
                    .formParam("username", "teste@teste.com")
                    .formParam("password", "teste")
                    .formParam("grant_type", "password")
                    .post("/oauth/token")
                .then()
                    .statusCode(200)
                .extract()
                    .response()
                    .getBody()
                    .jsonPath()
                    .get("access_token");
    }



}
