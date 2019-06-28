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
import com.ic.SysAgenda.model.Pessoa;
import com.ic.SysAgenda.service.PessoaService;

@RestController
@RequestMapping("/pessoa")
public class PessoaResource {

	@Autowired
	private PessoaService pessoaService;

	public PessoaResource() {
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Pessoa> salvar(@RequestBody Pessoa pessoa) {
		Pessoa p = this.pessoaService.salvar(pessoa);
		return new ResponseEntity<Pessoa>(p, HttpStatus.OK);
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Pessoa>> listarTodos() {
		List<Pessoa> pessoas = (List<Pessoa>) this.pessoaService.listarTodos();
		return new ResponseEntity<List<Pessoa>>(pessoas, HttpStatus.OK);
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Pessoa> atualizar(@RequestBody Pessoa pessoa) {
		try {
			Pessoa p = this.pessoaService.atualizar(pessoa);
			return new ResponseEntity<Pessoa>(p, HttpStatus.OK);
		} catch (NaoEncontradoException e) {
			return new ResponseEntity<Pessoa>(HttpStatus.NOT_FOUND);
		}

	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deletar(@PathVariable Long id) {
		try {
			return new ResponseEntity<String>(this.pessoaService.deletar(id), HttpStatus.OK);
		} catch (NaoEncontradoException e) {
			return new ResponseEntity<String>("Pessoa não encontrada", HttpStatus.NOT_FOUND);
		}
	}

}
