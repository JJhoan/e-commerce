package com.ecommerce.orchestrator.research.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import com.ecommerce.orchestrator.research.domain.TrendProduct;
import com.ecommerce.orchestrator.research.domain.TrendQuery;
import com.ecommerce.orchestrator.shared.domain.CountryCode;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SerpApiTrendRepositoryTest {

    private final SerpApiClient client = mock(SerpApiClient.class);
    private SerpApiTrendRepository repository;

    @BeforeEach
    void setUp() {
        repository = new SerpApiTrendRepository(client, "test-api-key");
    }

    @Test
    void shouldMapShoppingResultsToTrendProducts() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode response =
                mapper.readTree(
                        "{\"shopping_results\":["
                                + "{\"position\":1,\"title\":\"Xiaomi Redmi A5\","
                                + "\"product_id\":\"13148989753018775759\","
                                + "\"price\":\"$1,499.00\",\"extracted_price\":1499.0,"
                                + "\"old_price\":\"$1,999\",\"extracted_old_price\":1999,"
                                + "\"rating\":4.9,\"reviews\":17000,"
                                + "\"source\":\"Chedraui\","
                                + "\"thumbnail\":\"https://example.com/img.jpg\","
                                + "\"tag\":\"OFERTA\",\"multiple_sources\":true}]}");

        when(client.search(any(), any(), any(), any(), any(), any())).thenReturn(response);

        List<TrendProduct> products =
                repository.search(new TrendQuery("phones", CountryCode.of("MX")));

        assertThat(products).hasSize(1);
        TrendProduct product = products.get(0);
        assertThat(product.title()).isEqualTo("Xiaomi Redmi A5");
        assertThat(product.price()).isEqualByComparingTo("1499.0");
        assertThat(product.currency()).isEqualTo("$");
        assertThat(product.rating()).isEqualByComparingTo("4.9");
        assertThat(product.reviews()).isEqualTo(17000);
        assertThat(product.source()).isEqualTo("Chedraui");
        assertThat(product.thumbnail()).isEqualTo("https://example.com/img.jpg");
        assertThat(product.productId()).isEqualTo("13148989753018775759");
        assertThat(product.tag()).isEqualTo("OFERTA");
    }

    @Test
    void shouldReturnEmptyList_whenNoShoppingResults() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode response = mapper.readTree("{\"error\":\"no results\"}");

        when(client.search(any(), any(), any(), any(), any(), any())).thenReturn(response);

        List<TrendProduct> products =
                repository.search(new TrendQuery("phones", CountryCode.of("MX")));

        assertThat(products).isEmpty();
    }
}
