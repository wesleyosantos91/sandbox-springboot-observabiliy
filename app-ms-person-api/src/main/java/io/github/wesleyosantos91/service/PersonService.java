package io.github.wesleyosantos91.service;

import de.huxhorn.sulky.ulid.ULID;
import io.github.wesleyosantos91.domain.entity.PersonEntity;
import io.github.wesleyosantos91.domain.request.PersonQueryRequest;
import io.github.wesleyosantos91.domain.request.PersonRequest;
import io.github.wesleyosantos91.exception.core.ResourceNotFoundException;
import io.github.wesleyosantos91.mapper.PersonMapper;
import io.github.wesleyosantos91.repository.PersonRespository;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

import static java.text.MessageFormat.format;

@Service
public class PersonService {

    private static final Logger logger = LoggerFactory.getLogger(PersonService.class);
    private final PersonRespository respository;
    private final PersonMapper mapper;

    public PersonService(PersonRespository respository, PersonMapper mapper) {
        this.respository = respository;
        this.mapper = mapper;
    }

    @Counted(value = "person.service.search")
    @Timed(value = "person.service.search")
    public Page<PersonEntity> search(PersonQueryRequest queryRequest, Pageable pageable) {

        StopWatch stopWatch = new StopWatch();

        try {
            logger.info("Started searching for people");
            stopWatch.start();

            Example<PersonEntity> personEntityExample = Example.of(mapper.toEntity(queryRequest));
            Page<PersonEntity> page = respository.findAll(personEntityExample, pageable);

            stopWatch.stop();

            logger.info("Finished search for people, running time {} (ms)", stopWatch.getTotalTimeMillis());

            return page;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        } finally {
            if (stopWatch.isRunning()) {
                stopWatch.stop();
            }
        }
    }

    @Counted(value = "person.service.save")
    @Timed(value = "person.service.save")
    @Transactional
    public PersonEntity create(PersonRequest request) {
        StopWatch stopWatch = new StopWatch();

        try {
            stopWatch.start();

            PersonEntity personEntity = respository.save(mapper.toEntity(request));
            stopWatch.stop();

            return personEntity;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        } finally {
            if (stopWatch.isRunning()) {
                stopWatch.stop();
            }
        }
    }

    public PersonEntity findById(ULID.Value id) throws ResourceNotFoundException {
        return respository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Not found regitstry with code {0}", id)));
    }

    public void delete(ULID.Value id) {
        StopWatch stopWatch = new StopWatch();

        try {
            stopWatch.start();
            respository.delete(findById(id));
            stopWatch.stop();
        } catch (RuntimeException | ResourceNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (stopWatch.isRunning()) {
                stopWatch.stop();
            }
        }
    }

    public PersonEntity update(ULID.Value id, PersonRequest request) {
        StopWatch stopWatch = new StopWatch();

        try {
            stopWatch.start();

            PersonEntity personEntity = mapper.toEntity(request, findById(id));
            respository.save(personEntity);
            stopWatch.stop();

            return personEntity;
        } catch (RuntimeException | ResourceNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (stopWatch.isRunning()) {
                stopWatch.stop();
            }
        }
    }
}
