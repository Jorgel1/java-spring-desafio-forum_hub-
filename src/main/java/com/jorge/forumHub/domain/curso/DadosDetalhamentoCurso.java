package com.jorge.forumHub.domain.curso;

public record DadosDetalhamentoCurso(Long cursoId, String nome, Categoria categoria) {
    public DadosDetalhamentoCurso(Curso curso) {
        this(curso.getId(), curso.getNome(), curso.getCategoria());
    }
}
