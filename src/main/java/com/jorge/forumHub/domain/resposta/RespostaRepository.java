package com.jorge.forumHub.domain.resposta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RespostaRepository extends JpaRepository<Resposta, Long> {

@Query("SELECT r FROM Resposta r WHERE r.topico.id = :topicoId")
    Page<Resposta> findAllByTopicoId(Pageable paginacao, Long topicoId);

    @Query("SELECT r FROM Resposta r WHERE r.topico.id = :topicoId AND r.mensagem = :mensagem")
    Boolean existsByTopicoIdAndMensagem(Long topicoId, String mensagem);
}
