package com.ecommerce.orchestrator.research.application;

import static com.ecommerce.orchestrator.research.domain.TrendMother.randomList;
import static com.ecommerce.orchestrator.research.domain.TrendMother.randomQuery;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import com.ecommerce.orchestrator.research.domain.TrendNotFound;
import com.ecommerce.orchestrator.research.domain.TrendProduct;
import com.ecommerce.orchestrator.research.domain.TrendQuery;
import com.ecommerce.orchestrator.research.domain.TrendRepository;

import org.junit.jupiter.api.Test;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
class TrendFinderTest {

    @InjectMock TrendRepository repository;

    @Inject TrendFinder finder;

    @Test
    void shouldReturnTrendProducts_whenResultsExist() {
        List<TrendProduct> expected = randomList();
        when(repository.search(any(TrendQuery.class))).thenReturn(expected);

        List<TrendProduct> actual = finder.find(randomQuery());

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldThrowTrendNotFound_whenNoResults() {
        when(repository.search(any(TrendQuery.class))).thenReturn(List.of());

        assertThatThrownBy(() -> finder.find(randomQuery()))
                .isInstanceOf(TrendNotFound.class)
                .hasMessageContaining("No trends found");
    }
}
