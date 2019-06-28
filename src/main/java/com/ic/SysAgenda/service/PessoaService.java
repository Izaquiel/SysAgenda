package com.ic.SysAgenda.service;

import java.util.List;
import java.util.Optional;

import com.ic.SysAgenda.exception.NaoEncontradoException;
import com.ic.SysAgenda.model.Pessoa;

public interface PessoaService {
	
	Pessoa salvar(Pessoa pessoa);

	Pessoa atualizar(Pessoa pessoa) throws NaoEncontradoException;
	
	List<Pessoa> listarTodos();
	
	Optional<Pessoa> buscarPorId(Long id);
	
	String deletar(Long id);
}
