package com.jorge.forumHub.domain.resposta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoResposta(
        String mensagem,
        String solucao
) {
}
