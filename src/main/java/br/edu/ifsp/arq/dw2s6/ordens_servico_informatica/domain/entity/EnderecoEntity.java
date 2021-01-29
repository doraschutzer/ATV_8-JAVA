package br.edu.ifsp.arq.dw2s6.ordens_servico_informatica.domain.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import br.edu.ifsp.arq.dw2s6.ordens_servico_informatica.domain.model.Endereco;

@Embeddable
public class EnderecoEntity {

	@Column
	private String logradouro;
	
	@Column
	private int numero;
	
	@Column
	private String complemento;
	
	@Column
	private String bairro;
	
	@Column
	private String cidade;
	
	@Column
	private String estado;
	
	@Column
	private int cep;
	
	public EnderecoEntity() {
		
	}
	
	public EnderecoEntity(Endereco endereco) {
		this.bairro = endereco.getBairro();
		this.cep = endereco.getCep();
		this.cidade = endereco.getCidade();
		this.complemento = endereco.getComplemento();
		this.estado = endereco.getEstado();
		this.logradouro = endereco.getLogradouro();
		this.numero = endereco.getNumero();
	}
	
	public Endereco toModel() {
		Endereco endereco = new Endereco();
		endereco.setBairro(bairro);
		endereco.setCep(cep);
		endereco.setCidade(cidade);
		endereco.setComplemento(complemento);
		endereco.setEstado(estado);
		endereco.setLogradouro(logradouro);
		endereco.setNumero(numero);
		return endereco;
	}
	
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public int getCep() {
		return cep;
	}
	public void setCep(int cep) {
		this.cep = cep;
	}
}
