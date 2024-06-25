package com.jorge.forumHub.domain.topico;

import com.jorge.forumHub.domain.curso.DadosCurso;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoTopico(
        @NotNull
        Long id,
        String titulo,
        String mensagem,
        String autor,
        DadosCurso curso
) {
}
