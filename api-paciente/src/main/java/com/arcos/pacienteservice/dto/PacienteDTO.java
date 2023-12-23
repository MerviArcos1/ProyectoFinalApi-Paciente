package com.arcos.pacienteservice.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class PacienteDTO {
	private int id;
	private String dni;
	private String nombre;
	private Date fecha;
	private String sintoma;
	private String diagnostico;
}
