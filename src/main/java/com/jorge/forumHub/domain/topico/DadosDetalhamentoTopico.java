package com.jorge.forumHub.domain.topico;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jorge.forumHub.domain.curso.Curso;

import java.time.LocalDateTime;

public record DadosDetalhamentoTopico(
        Long id,
        String titulo,
        String mensagem,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime dataCriacao,
        Curso curso
) {

    public DadosDetalhamentoTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getDataCriacao(), topico.getCurso());
    }
}
