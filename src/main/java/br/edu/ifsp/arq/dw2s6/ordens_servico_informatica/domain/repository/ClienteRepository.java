package br.edu.ifsp.arq.dw2s6.ordens_servico_informatica.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifsp.arq.dw2s6.ordens_servico_informatica.domain.entity.ClienteEntity;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Integer>  {

}
