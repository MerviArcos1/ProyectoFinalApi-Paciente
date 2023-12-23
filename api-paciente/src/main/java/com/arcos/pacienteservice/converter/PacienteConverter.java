package com.arcos.pacienteservice.converter;

import org.springframework.stereotype.Component;

import com.arcos.pacienteservice.dto.PacienteDTO;
import com.arcos.pacienteservice.entity.Paciente;


@Component
public class PacienteConverter extends AbstractConverter<Paciente, PacienteDTO> {

	@Override
	public PacienteDTO fromEntity(Paciente entity) {
		if(entity==null) return null;
		return PacienteDTO.builder()
				.id(entity.getId())
				.dni(entity.getDni())
				.fecha(entity.getFecha())
				.nombre(entity.getNombre())
				.sintoma(entity.getSintoma())
				.diagnostico(entity.getDiagnostico())
				.build();
	}

	@Override
	public Paciente fromDTO(PacienteDTO dto) {
		if(dto==null) return null;
		return Paciente.builder()
				.id(dto.getId())
				.dni(dto.getDni())
				.fecha(dto.getFecha())
				.nombre(dto.getNombre())
				.sintoma(dto.getSintoma())
				.diagnostico(dto.getDiagnostico())
				.build();
	}

}
