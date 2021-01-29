package br.edu.ifsp.arq.dw2s6.ordens_servico_informatica.domain.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import com.sun.istack.NotNull;

import br.edu.ifsp.arq.dw2s6.ordens_servico_informatica.domain.model.Cliente;

@Entity
public class ClienteEntity {
	
	@Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer codigo;
	
	@Column
	@NotNull
	@NotBlank
    @Size(min=3, max = 100)
	private String nome;
	
	@Column
	@NotNull
	@NotBlank
    @Email
    private String email;
	
	@Column
	private int telefone;
	
	@Column
	@NotNull
	@NotBlank
	@CPF
	private String cpf;
	
	@Embedded
	@Column
	EnderecoEntity endereco;
	
	public ClienteEntity() {
		
	}
	
	public ClienteEntity(Cliente model) {
		this.codigo = model.getCodigo();
		this.nome = model.getNome();
		this.telefone = model.getTelefone();
		this.email = model.getEmail();
		this.cpf = model.getCpf();
		this.endereco = new EnderecoEntity(model.getEndereco());
	}
	
	public Cliente toModel() {
		Cliente cliente = new Cliente();
		cliente.setCodigo(this.codigo);
		cliente.setEmail(this.email);
		cliente.setNome(this.nome);
		cliente.setTelefone(this.telefone);
		cliente.setCpf(cpf);
		cliente.setEndereco(endereco.toModel());
		return cliente;
	}
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getTelefone() {
		return telefone;
	}
	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public EnderecoEntity getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoEntity endereco) {
		this.endereco = endereco;
	}
	
	
}
