package com.arcos.pacienteservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arcos.pacienteservice.converter.PacienteConverter;
import com.arcos.pacienteservice.dto.PacienteDTO;
import com.arcos.pacienteservice.entity.Paciente;
import com.arcos.pacienteservice.utils.WrapperResponse;
import com.arcos.pacienteservice.service.PacienteService;

import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
	@Autowired
	private PacienteService service;
	
	@Autowired
	private PacienteConverter converter;
	
	@GetMapping()
	public ResponseEntity<List<PacienteDTO>>findAll(
		@RequestParam(value="dni", required=false,defaultValue="")String dni,
		@RequestParam(value="offset", required=false,defaultValue="0")int pageNumber,
		@RequestParam(value="limit", required=false,defaultValue="5")int pageSize
			){
		Pageable page=PageRequest.of(pageNumber, pageSize);
		List<Paciente> pacientes;
		if(dni==null) {
			pacientes=service.findAll(page); 
		}else {
			pacientes=service.findByNumerodocumento(dni, page);
		}
		
		/*if(infracciones.isEmpty()) {
			return ResponseEntity.noContent().build();
		}*/
		List<PacienteDTO>pacientesDTO=converter.fromEntity(pacientes);
		return new WrapperResponse(true,"success",pacientesDTO).createResponse(HttpStatus.OK);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<WrapperResponse<PacienteDTO>> findById(@PathVariable("id") int id){
		Paciente paciente = service.findById(id);
		/*if(paciente==null) {
			return ResponseEntity.notFound().build();
		}*/
		PacienteDTO pacienteDTO=converter.fromEntity(paciente);
		return new WrapperResponse<PacienteDTO>(true,"success",pacienteDTO).createResponse(HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<PacienteDTO> create(@RequestBody PacienteDTO pacienteDTO){
		Paciente registro=service.save(converter.fromDTO(pacienteDTO));
		PacienteDTO registroDTO=converter.fromEntity(registro);
		return new WrapperResponse(true,"success",registroDTO).createResponse(HttpStatus.CREATED);
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<PacienteDTO> update(@PathVariable("id") int id,@RequestBody PacienteDTO pacienteDTO){
		Paciente registro =service.update(converter.fromDTO(pacienteDTO));
		/*if(registro==null) {
			return ResponseEntity.notFound().build();
		}*/
		PacienteDTO registroDTO=converter.fromEntity(registro);
		return new WrapperResponse(true,"success",registroDTO).createResponse(HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<PacienteDTO>delete(@PathVariable("id") int id){
		service.delete(id);
		return new WrapperResponse(true,"success",null).createResponse(HttpStatus.OK);
	}

}
