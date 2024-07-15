package com.jorge.forumHub.domain.resposta.validacoes.atualizacao;

import com.jorge.forumHub.domain.resposta.DadosAtualizacaoResposta;

public interface ValidadorAtualizacaoResposta {
    void validar(Long respostaId, DadosAtualizacaoResposta dadosAtualizacaoResposta);
}
