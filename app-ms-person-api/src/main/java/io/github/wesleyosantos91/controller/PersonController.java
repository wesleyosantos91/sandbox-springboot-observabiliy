package io.github.wesleyosantos91.controller;

import io.github.wesleyosantos91.domain.request.PersonQueryRequest;
import io.github.wesleyosantos91.domain.response.PersonResponse;
import io.github.wesleyosantos91.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/persons")
public record PersonController(PersonService service) {

    private static final Logger logger = LoggerFactory.getLogger(PersonService.class);

    @GetMapping
    public ResponseEntity<Page<PersonResponse>> find(PersonQueryRequest query, Pageable page) {
        logger.info("Function started 'find person'");
        var responsePage = service.find(query, page);
        logger.info("finished function with sucess 'find person'");
        return ResponseEntity.ok().body(responsePage);
    }
}
