package com.ic.SysAgenda.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Compromisso implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "compromisso_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "compromisso_seq", sequenceName = "compromisso_seq", initialValue = 1, allocationSize = 1)
	private Long id;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "data")
	private LocalDate data;
	
	@Column(name = "hora")
	private LocalTime hora;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@NotNull(message = "O campo pessoa é obrigatório")
	private Pessoa pessoa;
	
}
