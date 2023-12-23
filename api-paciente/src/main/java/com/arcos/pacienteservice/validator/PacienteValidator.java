package com.arcos.pacienteservice.validator;

import com.arcos.pacienteservice.entity.Paciente;
import com.arcos.pacienteservice.exceptions.ValidateServiceException;

public class PacienteValidator {
	public static void save(Paciente paciente) {
		if(paciente.getDni()==null||paciente.getDni().isEmpty()) {
			throw new ValidateServiceException("Numero de documento es requerido");
		}
		if(paciente.getDni().length()>100) {
			throw new ValidateServiceException("Numero de DNI es muy largo");
		}
		if(paciente.getFecha()==null) {
			throw new ValidateServiceException("La fecha es requerido");
		}
		if(paciente.getNombre()==null) {
			throw new ValidateServiceException("El Nombre es obligatorio");
		}
		if(paciente.getNombre().length()>3) {
			throw new ValidateServiceException("El Nombre es muy largo");
		}
		if(paciente.getDiagnostico()==null) {
			throw new ValidateServiceException("El Diagnostico es requerido");
		}
		if(paciente.getSintoma()==null) {
			throw new ValidateServiceException("El Sintoma es requerido");
		}
		if(paciente.getSintoma().length()>200) {
			throw new ValidateServiceException("El Sintoma es muy largo");
		}
	}
}
