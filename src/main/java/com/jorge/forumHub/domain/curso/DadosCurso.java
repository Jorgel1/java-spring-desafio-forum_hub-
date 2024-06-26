package com.jorge.forumHub.domain.curso;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCurso(
        @NotBlank
        String nome,
        @NotNull
        Categoria categoria
) {
}
