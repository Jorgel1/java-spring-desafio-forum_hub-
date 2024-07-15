package com.jorge.forumHub.domain.resposta;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroResposta(
        @NotBlank(message = "Mensagem é obrigatório")
        String mensagem,
        @NotNull(message = "Solução é obrigatório")
       String solucao
) {

}
