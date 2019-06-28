package com.ic.SysAgenda.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ic.SysAgenda.exception.NaoEncontradoException;
import com.ic.SysAgenda.model.Compromisso;
import com.ic.SysAgenda.service.CompromissoService;

@RestController
@RequestMapping("/compromisso")
public class CompromissoResource {

	@Autowired
	private CompromissoService compromissoService;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Compromisso> salvar(@RequestBody Compromisso compromisso) {
		Compromisso aux = this.compromissoService.salvar(compromisso);
		return new ResponseEntity<Compromisso>(aux, HttpStatus.OK);
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Compromisso>> listarTodos() {
		List<Compromisso> aux = this.compromissoService.listarTodos();
		return new ResponseEntity<List<Compromisso>>(aux, HttpStatus.OK);
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Compromisso> atualizar(@RequestBody Compromisso compromisso) {
		try {
			Compromisso c = this.compromissoService.atualizar(compromisso);
			return new ResponseEntity<Compromisso>(c, HttpStatus.OK);
		} catch (NaoEncontradoException e) {
			return new ResponseEntity<Compromisso>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deletar(@PathVariable Long id) {
		try {
			return new ResponseEntity<String>(this.compromissoService.deletar(id), HttpStatus.OK);
		} catch (NaoEncontradoException e) {
			return new ResponseEntity<String>("Compromisso não encontrado", HttpStatus.NOT_FOUND);
		}
	}
}
