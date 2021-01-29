package br.edu.ifsp.arq.dw2s6.ordens_servico_informatica.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifsp.arq.dw2s6.ordens_servico_informatica.domain.entity.ClienteEntity;
import br.edu.ifsp.arq.dw2s6.ordens_servico_informatica.domain.entity.EnderecoEntity;
import br.edu.ifsp.arq.dw2s6.ordens_servico_informatica.domain.model.Cliente;
import br.edu.ifsp.arq.dw2s6.ordens_servico_informatica.domain.repository.ClienteRepository;

@Service
public class ClienteService {
	@Autowired
	 private ClienteRepository clienteRepository;
	 
	public Cliente saveOrUpdateCliente(ClienteEntity cliente) {
		 clienteRepository.save(cliente);
		 return cliente.toModel();
	}
	 
	 public Cliente searchByCode(Integer codigo){
		 if ( codigo != null ) {
			 Optional<ClienteEntity> clienteOptional = clienteRepository.findById(codigo);
			 if ( clienteOptional.isPresent() ) {
				 return clienteOptional.get().toModel();
			 }
		 }
		 
		 return null;
	 }
	 
	 public List<Cliente> listAll(){
		 return clienteRepository.findAll().stream().map(a -> a.toModel()).collect(Collectors.toList());
	 }
	 
	 public boolean deleteCliente(Integer codigo) {
		 if ( codigo != null ) {
			 clienteRepository.deleteById(codigo);
			 return true;
		 } return false;
	 }
	 
	 public Cliente updateEndereco(Integer codigo, EnderecoEntity endereco) {
		 if( codigo != null && endereco != null ) { 
			 Cliente cliente = searchByCode(codigo);
			 if ( cliente != null ) {
				 cliente.setEndereco(endereco.toModel());
				 saveOrUpdateCliente(new ClienteEntity(cliente));
				 return cliente;
			 }
		 }
		 return null;
	 }
}
