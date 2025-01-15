package com.dfp.student_service.service;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.dfp.student_service.model.Alumno;
import com.dfp.student_service.repository.AlumnoRepository;
import com.dfp.student_service.service.impl.AlumnoServiceImpl;
import com.dfp.student_service.utils.Constants;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class AlumnoServiceImplTest {
      @InjectMocks
    private AlumnoServiceImpl alumnoService;

    @Mock
    private AlumnoRepository alumnoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllAlumnosActivos() {
       
        Alumno alumno1 = new Alumno();
        alumno1.setId(1);
        alumno1.setNombre("Andre");
        alumno1.setApellido("Pantoja");
        alumno1.setEstado("activo");
        alumno1.setEdad(22);

        Alumno alumno2 = new Alumno();
        alumno2.setId(1);
        alumno2.setNombre("karen");
        alumno2.setApellido("fani");
        alumno2.setEstado("activo");
        alumno2.setEdad(22);
        when(alumnoRepository.findByEstado(Constants.ESTADO_ACTIVO))
                .thenReturn(Flux.fromIterable(Arrays.asList(alumno1, alumno2)));

    
        Flux<Alumno> result = alumnoService.getAllAlumnosActivos();

        
        StepVerifier.create(result)
                .expectNext(alumno1)
                .expectNext(alumno2)
                .verifyComplete();

        verify(alumnoRepository, times(1)).findByEstado(Constants.ESTADO_ACTIVO);
    }

    @Test
    void testGuardarAlumnoNewAlumno() {
        // Arrange
        Alumno alumno = new Alumno();
        alumno.setId(1);
        alumno.setNombre("Andre");
        alumno.setApellido("Pantoja");
        alumno.setEstado("activo");
        alumno.setEdad(22);
        when(alumnoRepository.existsById(alumno.getId())).thenReturn(Mono.just(false));
        when(alumnoRepository.save(alumno)).thenReturn(Mono.just(alumno));

        // Act
        Mono<Void> result = alumnoService.saveAlumno(alumno);

        // Assert
        StepVerifier.create(result)
                .verifyComplete();

        verify(alumnoRepository, times(1)).existsById(alumno.getId());
        verify(alumnoRepository, times(1)).save(alumno);
    }

    @Test
    void testGuardarAlumnoExistingAlumno() {
        // Arrange
        Alumno alumno = new Alumno();
        alumno.setId(1);
        alumno.setNombre("Andre");
        alumno.setApellido("Pantoja");
        alumno.setEstado("activo");
        alumno.setEdad(22);
        when(alumnoRepository.existsById(alumno.getId())).thenReturn(Mono.just(true));

        // Act
        Mono<Void> result = alumnoService.saveAlumno(alumno);

        // Assert
        StepVerifier.create(result)
                .expectErrorMatches(throwable -> throwable instanceof IllegalArgumentException &&
                        Constants.ERROR_ID_REPETIDO.equals(throwable.getMessage()))
                .verify();

        verify(alumnoRepository, times(1)).existsById(alumno.getId());
        verify(alumnoRepository, never()).save(alumno);
    }
}
