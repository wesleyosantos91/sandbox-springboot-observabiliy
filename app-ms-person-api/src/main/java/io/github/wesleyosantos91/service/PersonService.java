package io.github.wesleyosantos91.service;

import io.github.wesleyosantos91.Application;
import io.github.wesleyosantos91.domain.entity.PersonEntity;
import io.github.wesleyosantos91.domain.request.PersonQueryRequest;
import io.github.wesleyosantos91.domain.response.PersonResponse;
import io.github.wesleyosantos91.mapper.PersonMapper;
import io.github.wesleyosantos91.metric.annotation.CounterExecution;
import io.github.wesleyosantos91.metric.annotation.TimerExecution;
import io.github.wesleyosantos91.repository.PersonRespository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

@Service
public class PersonService {

    private static final Logger logger = LoggerFactory.getLogger(PersonService.class);
    private final PersonRespository respository;
    private final PersonMapper mapper;

    public PersonService(PersonRespository respository, PersonMapper mapper) {
        this.respository = respository;
        this.mapper = mapper;
    }

    public Page<PersonResponse> find(PersonQueryRequest queryRequest, Pageable pageable) {

        StopWatch stopWatch = new StopWatch();

        try {
            logger.info("Started searching for people");
            stopWatch.start();

            Example<PersonEntity> personEntityExample = Example.of(mapper.toEntity(queryRequest));
            Page<PersonEntity> page = respository.findAll(personEntityExample, pageable);

            stopWatch.stop();

            logger.info("Finished search for people, running time {} (ms)", stopWatch.getTotalTimeMillis());

            return mapper.toPageResponse(page);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        } finally {
            if (stopWatch.isRunning()) {
                stopWatch.stop();
            }
        }
    }

    @CounterExecution(name = "person_counter_saved")
    @TimerExecution(name =   "person_timer_saved")
    @Transactional
    public void save(PersonEntity entity, boolean exception) {
        if(exception) {
            respository.save(entity);
        } else {
            throw new RuntimeException("teste metricas");
        }

    }
}
