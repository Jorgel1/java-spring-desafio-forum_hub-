package com.jorge.forumHub.domain.topico;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jorge.forumHub.domain.curso.Curso;
import com.jorge.forumHub.domain.usuario.DadosDetalhamentoUsuario;
import com.jorge.forumHub.domain.usuario.Usuario;

import java.time.LocalDateTime;

public record DadosListagemTopico(
        Long id,
        String titulo,
        String mensagem,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime dataCriacao,
        StatusTopico status,
        DadosDetalhamentoUsuario usuario,
        Curso curso
) {

    public DadosListagemTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getDataCriacao(), topico.getStatus(), new DadosDetalhamentoUsuario(topico.getAutor()), topico.getCurso());
    }
}
