package br.edu.ifsp.arq.dw2s6.ordens_servico_informatica.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifsp.arq.dw2s6.ordens_servico_informatica.domain.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	public Optional<Usuario> findByEmail(String email);
	
}
