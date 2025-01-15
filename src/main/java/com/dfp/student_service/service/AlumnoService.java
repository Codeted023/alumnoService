package com.dfp.student_service.service;

import com.dfp.student_service.model.Alumno;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AlumnoService {
    Flux<Alumno> getAllAlumnosActivos();

    Mono<Void> saveAlumno(Alumno alumno);
}
