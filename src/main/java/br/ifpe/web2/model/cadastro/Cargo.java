package br.ifpe.web2.model.cadastro;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import br.ifpe.web2.model.geral.ObjetoGeral;

@Entity
public class Cargo extends ObjetoGeral {

	@Column(length = 25, nullable = false)
	@NotBlank(message = "Cargo não pode esta vazio")
	private String nome;
	@Min(value = 1000, message = "Salario não pode ser menor que R$ 1000")
	private double salario;

	public Cargo() {
	}

	public Cargo(String _nome) {
		setNome(_nome);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

}
