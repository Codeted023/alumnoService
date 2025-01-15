package com.dfp.student_service.service.impl;

import com.dfp.student_service.model.Alumno;
import com.dfp.student_service.repository.AlumnoRepository;
import com.dfp.student_service.service.AlumnoService;
import com.dfp.student_service.utils.Constants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AlumnoServiceImpl implements AlumnoService {

    @Autowired
    AlumnoRepository alumnoRepository;

    @Override
    public Flux<Alumno> getAllAlumnosActivos() {
        return alumnoRepository.findByEstado(Constants.ESTADO_ACTIVO);
    }

    @Override
    public Mono<Void> saveAlumno(Alumno alumno) {
        return alumnoRepository.existsById(alumno.getId())
                .flatMap(alumnoexists -> {
                    if (alumnoexists) {
                        return Mono.error(new IllegalArgumentException(Constants.ERROR_ID_REPETIDO));
                    }
                    return alumnoRepository.save(alumno);
                }).then();

    }
}
