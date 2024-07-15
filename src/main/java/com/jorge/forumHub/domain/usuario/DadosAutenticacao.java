package com.jorge.forumHub.domain.usuario;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DadosAutenticacao(@JsonAlias({"email", "login"})String login, String senha) {
}

