package com.arcos.pacienteservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arcos.pacienteservice.entity.Paciente;
import com.arcos.pacienteservice.exceptions.GeneralServiceException;
import com.arcos.pacienteservice.exceptions.NoDataFoundException;
import com.arcos.pacienteservice.exceptions.ValidateServiceException;
import com.arcos.pacienteservice.repository.PacienteRepository;
import com.arcos.pacienteservice.service.PacienteService;
import com.arcos.pacienteservice.validator.PacienteValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PacienteServiceimpl implements PacienteService{

	@Autowired
	private PacienteRepository repository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Paciente> findAll(Pageable page) {
		try {
			return repository.findAll(page).toList();
		}catch(NoDataFoundException e){
			log.info(e.getMessage(),e);
			throw e;
		}catch(Exception e){
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Paciente> findByNumerodocumento(String dni, Pageable page) {
		try {
			return repository.findByNumerodocumentoContaining(dni, page);
		}catch(ValidateServiceException | NoDataFoundException e){
			log.info(e.getMessage(),e);
			throw e;
		}catch(Exception e){
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Paciente findById(int id) {
		try {
			Paciente registro=repository.findById(id).orElseThrow(()->new NoDataFoundException("No existe el regisro con ese ID"));
			return registro;
		}catch(ValidateServiceException | NoDataFoundException e){
			log.info(e.getMessage(),e);
			throw e;
		}catch(Exception e){
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional
	public Paciente save(Paciente paciente) {
		try {
			PacienteValidator.save(paciente);
			if(repository.findByNumerodocumento(paciente.getDni())!=null) {
				throw new ValidateServiceException("Ya existe un registro con ese numero de DNI " +paciente.getDni());
			}
			Paciente registro=repository.save(paciente);
			return registro;
		}catch(ValidateServiceException | NoDataFoundException e){
			log.info(e.getMessage(),e);
			throw e;
		}catch(Exception e){
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional
	public Paciente update(Paciente paciente) {
		try {
			PacienteValidator.save(paciente);
			Paciente registro=repository.findById(paciente.getId()).orElseThrow(()->new NoDataFoundException("No existe el regisro con ese ID"));
			Paciente registroD=repository.findByNumerodocumento(paciente.getDni());
			if(registroD!=null && registroD.getId()!=paciente.getId()) {
				throw new ValidateServiceException("Ya existe un registro con ese numero de DNI " +paciente.getDni());
			}
			registro.setDni(paciente.getDni());
			registro.setFecha(paciente.getFecha());
			registro.setNombre(paciente.getNombre());
			registro.setSintoma(paciente.getSintoma());
			registro.setDiagnostico(paciente.getDiagnostico());
			repository.save(registro);
			return registro;
		}catch(ValidateServiceException | NoDataFoundException e){
			log.info(e.getMessage(),e);
			throw e;
		}catch(Exception e){
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional
	public void delete(int id) {
		try {
			Paciente registro=repository.findById(id).orElseThrow(()->new NoDataFoundException("No existe el regisro con ese ID"));
			repository.delete(registro);
		}catch(ValidateServiceException | NoDataFoundException e){
			log.info(e.getMessage(),e);
			throw e;
		}catch(Exception e){
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
		
	}



}
