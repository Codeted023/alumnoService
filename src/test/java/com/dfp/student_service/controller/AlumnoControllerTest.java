package com.dfp.student_service.controller;

import com.dfp.student_service.model.Alumno;
import com.dfp.student_service.service.AlumnoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AlumnoControllerTest {

    @Mock
    private AlumnoService alumnoService;

    @InjectMocks
    private AlumnoController alumnoController;

    private WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        webTestClient = WebTestClient.bindToController(alumnoController).build();
    }

    @Test
    void testGetAllAlumnos() {
        // Arrange
        Alumno alumno1 = new Alumno();
        alumno1.setId(1);
        alumno1.setNombre("Juan");
        alumno1.setApellido("Perez");
        alumno1.setEstado("activo");
        alumno1.setEdad(22);

        Alumno alumno2 = new Alumno();
        alumno1.setId(1);
        alumno1.setNombre("maria");
        alumno1.setApellido("lopez");
        alumno1.setEstado("activo");
        alumno1.setEdad(22);

        when(alumnoService.getAllAlumnosActivos()).thenReturn(Flux.just(alumno1, alumno2));

        webTestClient.get()
                .uri("/alumnos")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Alumno.class)
                .hasSize(2)
                .contains(alumno1, alumno2);
    }

    @Test
    void testGuardarAlumno() {

        Alumno alumno1 = new Alumno();
        alumno1.setId(1);
        alumno1.setNombre("Juan");
        alumno1.setApellido("Perez");
        alumno1.setEstado("activo");
        alumno1.setEdad(22);

        when(alumnoService.saveAlumno(any(Alumno.class))).thenReturn(Mono.empty());

        // Act & Assert
        webTestClient.post()
                .uri("/alumnos")
                .bodyValue(alumno1)
                .exchange()
                .expectStatus().isOk()
                .expectBody().isEmpty();

        verify(alumnoService, times(1)).saveAlumno(any(Alumno.class));
    }

    @Test
    void testGuardarAlumnoValidationFailure() {
        Alumno alumno1 = new Alumno();
        alumno1.setId(1);
        alumno1.setNombre(null);
        alumno1.setApellido("Perez");
        alumno1.setEstado("activo");
        alumno1.setEdad(22);


        webTestClient.post()
                .uri("/alumnos")
                .bodyValue(alumno1)
                .exchange()
                .expectStatus().isBadRequest();
    }

}
