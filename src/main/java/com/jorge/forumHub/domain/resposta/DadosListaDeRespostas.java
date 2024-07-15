package com.jorge.forumHub.domain.resposta;

import java.time.LocalDateTime;

public record DadosListaDeRespostas(
        Long id,
        String mensagem,
        Long topicoId,
        LocalDateTime dataCriacao,
        Long autorId,
        String solucao
) {

    public DadosListaDeRespostas(Resposta resposta) {
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