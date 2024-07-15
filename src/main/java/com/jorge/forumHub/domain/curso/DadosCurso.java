package com.jorge.forumHub.domain.curso;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCurso(
        @NotBlank(message = "Nome do curso deve ser informado para cadastrar um curso!")
        String nome,
        @NotNull(message = "Categoria do curso deve ser informada para cadastrar um curso!")
        Categoria categoria
) {
}
