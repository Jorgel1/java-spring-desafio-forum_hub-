package com.jorge.forumHub.domain.resposta.validacoes.cadastro;

import com.jorge.forumHub.domain.resposta.DadosCadastroResposta;

public interface ValidadorCadastroResposta {

    void validar(Long topicoId, DadosCadastroResposta dadosCadastroResposta);
}
