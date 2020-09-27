package com.example.webflux.database;

import com.example.webflux.model.Quote;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Flux;

public interface QuoteMongoReactiveRepository extends ReactiveSortingRepository<Quote, String> {

    Flux<Quote> findAllByIdNotNull(final Pageable page);
}
