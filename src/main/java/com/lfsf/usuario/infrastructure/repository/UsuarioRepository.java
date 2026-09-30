package com.lfsf.usuario.infrastructure.repository;

import com.lfsf.usuario.infrastructure.entity.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsByEmail(String email);

    Optional<Usuario> findByEmail(String email);

    @Transactional
    void deleteByEmail(String email);

}

// Annotations for today(29/09/2026) --> Do a delete method for telefone and endereço...
// First thoughts: We won't need to change anything on the repository, only on UsuarioService and UsuarioControler
// Test with postman and DB online to actually check if it works, then move to FE and add the delete options there as well

