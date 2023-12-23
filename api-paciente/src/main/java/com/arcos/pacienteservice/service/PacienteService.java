package com.arcos.pacienteservice.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.arcos.pacienteservice.entity.Paciente;

public interface PacienteService {
	public List<Paciente>findAll(Pageable page);
	public List<Paciente>findByNumerodocumento(String dni, Pageable page);
	public Paciente findById(int id);
	public Paciente save(Paciente paciente);
	public Paciente update(Paciente paciente);
	public void delete(int id);
	
}
