package com.ic.SysAgenda.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ic.SysAgenda.exception.NaoEncontradoException;
import com.ic.SysAgenda.model.Pessoa;
import com.ic.SysAgenda.repository.PessoaRepository;
import com.ic.SysAgenda.service.PessoaService;

import lombok.var;

@Service
public class PessoaServiceImpl implements PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Override
	public Pessoa salvar(Pessoa pessoa) {
		Pessoa pes = pessoaRepository.save(pessoa);
		return pes;
	}

	@Override
	public Pessoa atualizar(Pessoa pessoa) throws NaoEncontradoException {
		var atualizada = buscarPorId(pessoa.getId()).map(aux -> {
			aux.setNome(pessoa.getNome());
			aux.setTelefone(pessoa.getTelefone());
			aux.setDataNascimento(pessoa.getDataNascimento());
			return (Pessoa) pessoaRepository.save(aux);
		}).orElseThrow(NaoEncontradoException::new);

		return atualizada;
	}

	@Override
	public List<Pessoa> listarTodos() {
		List<Pessoa> pessoas = (List<Pessoa>) pessoaRepository.findAll();
		return pessoas;
	}

	@Override
	public Optional<Pessoa> buscarPorId(Long id) {
		return pessoaRepository.findById(id);
	}

	@Override
	public String deletar(Long id) {
		return buscarPorId(id).map(aux -> {
			pessoaRepository.deleteById(id);
			return "Deletado com sucesso!";
		}).orElseThrow(NaoEncontradoException::new);
	}

}
