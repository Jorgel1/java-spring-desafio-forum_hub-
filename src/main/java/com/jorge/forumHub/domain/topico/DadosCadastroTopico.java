package com.jorge.forumHub.domain.topico;

import com.jorge.forumHub.domain.curso.DadosCurso;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroTopico(
        @NotBlank(message = "{titulo.obrigatorio}")
        String titulo,
        @NotBlank(message = "Mensagem é obrigatório")
        String mensagem,
        @NotBlank
        String autor,
        @NotNull
        @Valid DadosCurso curso
) {
}
