package com.ic.SysAgenda.service;

import java.util.List;
import java.util.Optional;

import com.ic.SysAgenda.model.Compromisso;

public interface CompromissoService {

	Compromisso salvar(Compromisso compromisso);

	Compromisso atualizar(Compromisso compromisso);

	List<Compromisso> listarTodos();

	Optional<Compromisso> buscarPorId(Long id);

	String deletar(Long id);
}
