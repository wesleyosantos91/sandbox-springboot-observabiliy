package io.github.wesleyosantos91.controller;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import de.huxhorn.sulky.ulid.ULID;
import io.github.wesleyosantos91.domain.request.PersonQueryRequest;
import io.github.wesleyosantos91.domain.request.PersonRequest;
import io.github.wesleyosantos91.domain.response.PersonResponse;
import io.github.wesleyosantos91.exception.core.ResourceNotFoundException;
import io.github.wesleyosantos91.mapper.PersonMapper;
import io.github.wesleyosantos91.service.PersonService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/persons")
public record PersonController(PersonService service, PersonMapper mapper) {

    private static final Logger logger = LoggerFactory.getLogger(PersonService.class);

    @PostMapping
    public ResponseEntity<PersonResponse> create(@Valid @RequestBody PersonRequest request) {
        logger.info("Function started 'create person'");
        var person = service.create(request);
        logger.info("finished function with sucess 'create person'");
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(person));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PersonResponse> getById(@PathVariable String id) throws ResourceNotFoundException {
        logger.info("Function started 'getById person'");
        var person = service.findById(ULID.parseULID(id));
        logger.info("finished function with sucess 'getById person'");
        return ResponseEntity.ok().body(mapper.toResponse(person));
    }

    @GetMapping
    public ResponseEntity<Page<PersonResponse>> search(PersonQueryRequest query, Pageable page) {
        logger.info("Function started 'find person'");
        var pageEntity = service.search(query, page);
        logger.info("finished function with sucess 'find person'");
        return ResponseEntity.ok().body(mapper.toPageResponse(pageEntity));
    }

    @PutMapping(value ="/{id}")
    public ResponseEntity<PersonResponse> update(@PathVariable String id, @RequestBody PersonRequest request) {
        logger.info("Function started 'update person'");
        var person = service.update(ULID.parseULID(id), request);
        logger.info("finished function with sucess 'update person'");
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toResponse(person));
    }

    @DeleteMapping(value ="/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        logger.info("Function started 'delete person'");
        service.delete(ULID.parseULID(id));
        logger.info("finished function with sucess 'delete person'");
        return ResponseEntity.noContent().build();
    }
}
