package com.arcos.pacienteservice.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arcos.pacienteservice.entity.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
	List<Paciente>findByNumerodocumentoContaining(String dni, Pageable page);
	Paciente findByNumerodocumento(String dni);
}
