package com.dfp.student_service.controller;

import com.dfp.student_service.model.Alumno;
import com.dfp.student_service.service.AlumnoService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.WebExchangeBindException;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/alumnos")
public class AlumnoController {
    private final AlumnoService alumnoService;

    public AlumnoController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    @GetMapping
    public Flux<Alumno> getAllStudents() {
        return alumnoService.getAllAlumnosActivos();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> createStudent(@Valid @RequestBody Alumno alumno) {
        return alumnoService.saveAlumno(alumno)
                .then()
                .onErrorMap(WebExchangeBindException.class, ex -> new RuntimeException("Validation failed", ex))
                .onErrorMap(IllegalArgumentException.class, ex -> new RuntimeException(ex.getMessage(), ex));
    }

}
