package com.crio.starter.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.Instant;

@Data
@Document(collection = "memes")
public class MemeEntity {

    @Id
    private String id;

    private String name;
    private String caption;
    private String url;

    private Instant createdAt = Instant.now();

    public MemeEntity() {}

    public MemeEntity(String name, String caption, String url) {
        this.name = name;
        this.caption = caption;
        this.url = url;
    }
}