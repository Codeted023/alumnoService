package com.dfp.student_service.repository;

import com.dfp.student_service.model.Alumno;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.stereotype.Repository;

@Repository
public interface AlumnoRepository extends ReactiveCrudRepository<Alumno, Integer> {

    Flux<Alumno> findByEstado(String estado);

    Mono<Boolean> existsById(int id);

}
