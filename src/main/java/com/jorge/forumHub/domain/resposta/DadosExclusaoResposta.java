package com.jorge.forumHub.domain.resposta;

import jakarta.validation.constraints.NotNull;

public record DadosExclusaoResposta(
        @NotNull
        Long idResposta,

        @NotNull
        MotivoExclusao motivo) {
}