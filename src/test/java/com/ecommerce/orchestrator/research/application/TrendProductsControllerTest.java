package com.ecommerce.orchestrator.research.application;

import static com.ecommerce.orchestrator.research.domain.TrendMother.randomList;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import com.ecommerce.orchestrator.research.domain.TrendProduct;
import com.ecommerce.orchestrator.research.domain.TrendRepository;

import org.junit.jupiter.api.Test;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class TrendProductsControllerTest {

    @InjectMock TrendRepository repository;

    @Test
    void shouldReturnTrends_whenResultsExist() {
        List<TrendProduct> products = randomList();
        when(repository.search(any())).thenReturn(products);

        given().queryParam("country", "MX")
                .queryParam("query", "phones")
                .when()
                .get("/research/trends")
                .then()
                .statusCode(200)
                .body("products", hasSize(products.size()));
    }

    @Test
    void shouldReturn404_whenNoResults() {
        when(repository.search(any())).thenReturn(List.of());

        given().queryParam("country", "MX")
                .queryParam("query", "phones")
                .when()
                .get("/research/trends")
                .then()
                .statusCode(404);
    }
}
