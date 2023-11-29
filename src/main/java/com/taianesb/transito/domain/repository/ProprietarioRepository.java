package com.taianesb.transito.domain.repository;

import com.taianesb.transito.domain.model.Proprietario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProprietarioRepository extends JpaRepository<Proprietario, Long> {

    List<Proprietario> findByNome(String nome);

    List<Proprietario> findByNomeContaining(String nome);
}
