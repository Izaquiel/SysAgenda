package com.ic.SysAgenda.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ic.SysAgenda.exception.NaoEncontradoException;
import com.ic.SysAgenda.model.Compromisso;
import com.ic.SysAgenda.repository.CompromissoRepository;
import com.ic.SysAgenda.service.CompromissoService;

import lombok.var;

@Service
public class CompromissoServiceImpl implements CompromissoService {

	@Autowired
	private CompromissoRepository compromissoRepository;

	@Override
	public Compromisso salvar(Compromisso compromisso) {
		Compromisso comp = compromissoRepository.save(compromisso);
		return comp;
	}

	@Override
	public Compromisso atualizar(Compromisso compromisso) {
		var atualizado = buscarPorId(compromisso.getId()).map(aux -> {
			aux.setDescricao(compromisso.getDescricao());
			aux.setData(compromisso.getData());
			aux.setHora(compromisso.getHora());
			aux.setPessoa(compromisso.getPessoa());
			return compromissoRepository.save(aux);
		}).orElseThrow(NaoEncontradoException::new);

		return atualizado;
	}

	@Override
	public List<Compromisso> listarTodos() {
		List<Compromisso> comps = (List<Compromisso>) compromissoRepository.findAll();
		return comps;
	}

	@Override
	public Optional<Compromisso> buscarPorId(Long id) {
		return compromissoRepository.findById(id);
	}

	@Override
	public String deletar(Long id) {
		return buscarPorId(id).map(aux -> {
			compromissoRepository.deleteById(id);
			return "Deletado com sucesso";
		}).orElseThrow(NaoEncontradoException::new);
	}

}
