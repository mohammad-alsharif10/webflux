package com.example.webflux.controller;

import com.example.webflux.database.QuoteMongoReactiveRepository;
import com.example.webflux.model.Quote;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class QuoteReactiveController {

    private static final int DELAY_PER_ITEM_MS = 100;

    private final QuoteMongoReactiveRepository quoteMongoReactiveRepository;

    public QuoteReactiveController(final QuoteMongoReactiveRepository quoteMongoReactiveRepository) {
        this.quoteMongoReactiveRepository = quoteMongoReactiveRepository;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/quotes-reactive")
    public Flux<Quote> getQuoteFlux() {
        return quoteMongoReactiveRepository.findAll().delayElements(Duration.ofMillis(DELAY_PER_ITEM_MS*10));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/quotes-reactive-paged")
    public Flux<Quote> getQuoteFlux(final @RequestParam(name = "page") int page,
                                    final @RequestParam(name = "size") int size) {
        return quoteMongoReactiveRepository.findAllByIdNotNull(PageRequest.of(page, size))
                .delayElements(Duration.ofMillis(DELAY_PER_ITEM_MS));
    }

}
