package com.dfp.student_service.model;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

import com.dfp.student_service.utils.Constants;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Data
@Table("alumnos")
public class Alumno {
    @NotNull(message = Constants.ERROR_ID)
    private int id;
    @NotNull(message = Constants.ERROR_NOMBRE)
    @NotBlank(message = Constants.ERROR_NOMBRE)
    private String nombre;
    @NotNull(message = Constants.ERROR_APELLIDO)
    @NotBlank(message = Constants.ERROR_APELLIDO)
    private String apellido;
    @Pattern(regexp = "^(activo|inactivo)$", message = Constants.ERROR_ESTADO)
    private String estado;
    @Min(value = 1, message = Constants.ERROR_EDAD)
    private int edad;

}
