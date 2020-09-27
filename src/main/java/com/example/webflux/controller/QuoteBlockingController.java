package com.example.webflux.controller;

import com.example.webflux.database.QuoteMongoBlockingRepository;
import com.example.webflux.model.Quote;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class QuoteBlockingController {

    private static final int DELAY_PER_ITEM_MS = 100;

    private final QuoteMongoBlockingRepository quoteMongoBlockingRepository;

    public QuoteBlockingController(final QuoteMongoBlockingRepository quoteMongoBlockingRepository) {
        this.quoteMongoBlockingRepository = quoteMongoBlockingRepository;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/addBook")
    public String saveBook(@RequestBody Quote quote) {
        for (int i = 0; i < 100; i++) {
            quote.setId(Integer.toString(i));
            quoteMongoBlockingRepository.save(quote);
        }
        return "Added book with id : ";
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/quotes-blocking")
    public List<Quote> getQuotesBlocking() throws Exception {
        return (List<Quote>) quoteMongoBlockingRepository.findAll();
    }

    @GetMapping("/quotes-blocking-paged")
    public Iterable<Quote> getQuotesBlocking(final @RequestParam(name = "page") int page,
                                             final @RequestParam(name = "size") int size) throws Exception {
        Thread.sleep(DELAY_PER_ITEM_MS * size);
        return quoteMongoBlockingRepository.findAllByIdNotNullOrderByIdAsc(PageRequest.of(page, size));
    }
}
