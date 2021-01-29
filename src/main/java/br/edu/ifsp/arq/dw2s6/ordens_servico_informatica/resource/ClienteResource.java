package br.edu.ifsp.arq.dw2s6.ordens_servico_informatica.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.arq.dw2s6.ordens_servico_informatica.domain.entity.ClienteEntity;
import br.edu.ifsp.arq.dw2s6.ordens_servico_informatica.domain.entity.EnderecoEntity;
import br.edu.ifsp.arq.dw2s6.ordens_servico_informatica.domain.entity.Usuario;
import br.edu.ifsp.arq.dw2s6.ordens_servico_informatica.domain.model.Cliente;
import br.edu.ifsp.arq.dw2s6.ordens_servico_informatica.domain.repository.UsuarioRepository;
import br.edu.ifsp.arq.dw2s6.ordens_servico_informatica.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteResource {

	@Autowired
    private ClienteService clienteService;
	
	@GetMapping("/list")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CLIENTE') and #oauth2.hasScope('read')")
    public ResponseEntity<List<Cliente>> listAll() {
        return ResponseEntity.ok(clienteService.listAll());
    }
	
    @GetMapping("/{code}")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_CLIENTE') and #oauth2.hasScope('read')")
    public ResponseEntity<Cliente> searchByCode(@PathVariable("code") final Integer codigo) {
    	Cliente cliente = this.clienteService.searchByCode(codigo);
    	if( cliente != null ) {
    		return ResponseEntity.ok(cliente);
    	}
    	return ResponseEntity.notFound().build();
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_CLIENTE') and #oauth2.hasScope('write')")
    public ResponseEntity<Cliente> addCliente(@Valid @RequestBody ClienteEntity cliente) {
    	Cliente clienteNovo = clienteService.saveOrUpdateCliente(cliente);
    	if( clienteNovo != null ) {
    		return ResponseEntity.ok(clienteNovo);
    	}
    	return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{code}")
    @PreAuthorize("hasAuthority('ROLE_DELETAR_CLIENTE') and #oauth2.hasScope('write')")
    public ResponseEntity<String> deleteCliente(@PathVariable("code") final Integer codigo) {
    	if ( clienteService.deleteCliente(codigo) ) {
    		return ResponseEntity.status(HttpStatus.OK).body("Cliente deletado");
    	} 
    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro. Tente novamente!");
    }
    
    @PutMapping("/atualizar-cliente")
    @PreAuthorize("hasAuthority('ROLE_ATUALIZAR_CLIENTE') and #oauth2.hasScope('write')")
    public ResponseEntity<Cliente> updateCliente(@Valid @RequestBody ClienteEntity cliente) {
    	if( cliente != null ) {
    		Cliente clienteAtualizado = clienteService.saveOrUpdateCliente(cliente);
    		return ResponseEntity.ok(clienteAtualizado);
    	} 
    	return ResponseEntity.notFound().build();
    }
    
    @PutMapping("/atualizar-endereco/{code}")
    @PreAuthorize("hasAuthority('ROLE_ATUALIZAR_ENDERECO') and #oauth2.hasScope('write')")
    public ResponseEntity<Cliente> updateEndereco(
		@PathVariable("code") final Integer codigo, 
		@Valid @RequestBody EnderecoEntity endereco ) {
		Cliente clienteAtualizado = clienteService.updateEndereco(codigo, endereco);
		if( clienteAtualizado != null ) {
    		return ResponseEntity.ok(clienteAtualizado);
    	} 
    	return ResponseEntity.notFound().build();
    }
}