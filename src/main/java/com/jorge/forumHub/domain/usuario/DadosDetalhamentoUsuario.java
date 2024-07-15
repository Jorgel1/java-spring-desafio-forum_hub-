package com.jorge.forumHub.domain.usuario;

import com.jorge.forumHub.domain.topico.Topico;

public record DadosDetalhamentoUsuario(Long id) {

    public DadosDetalhamentoUsuario(Usuario usuario) {
        this(usuario.getId());
    }
}

