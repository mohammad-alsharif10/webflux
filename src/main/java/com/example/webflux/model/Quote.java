package com.example.webflux.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@Document(collection = "Quote")
public final class Quote {

    @Id
    private String id;
    private String book;
    private String content;

}
