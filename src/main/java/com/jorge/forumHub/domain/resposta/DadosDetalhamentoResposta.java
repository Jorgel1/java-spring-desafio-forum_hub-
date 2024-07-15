package com.jorge.forumHub.domain.resposta;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.time.LocalDateTime;

public record DadosDetalhamentoResposta(
        Long id,
        String mensagem,
        @JsonAlias({"id_topico", "topico_id", "topico"})
        Long topicoId,
        LocalDateTime dataCriacao,
        Long autorId,
        String solucao
) {
        public DadosDetalhamentoResposta(Resposta resposta){
                this(
                        resposta.getId(),
                        resposta.getMensagem(),
                        resposta.getTopico().getId(),
                        resposta.getDataCriacao(),
                        resposta.getAutor().getId(),
                        resposta.getSolucao()
                );
        }
}
