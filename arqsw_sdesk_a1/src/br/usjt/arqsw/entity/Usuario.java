package br.usjt.arqsw.entity;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
/**
 * 
 * @author Andrey
 *
 */
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String LOGADO = "logado";
	
	private String nome;
	
	@NotNull
	@Size(max=100)
	private String senha;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "Usuario [nome=" + nome + ", senha=" + senha + "]";
	}
		

}
