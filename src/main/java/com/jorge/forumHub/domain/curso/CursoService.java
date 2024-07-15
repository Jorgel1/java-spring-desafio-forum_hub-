package com.jorge.forumHub.domain.curso;

import com.jorge.forumHub.domain.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;
    public DadosDetalhamentoCurso cadastrar(DadosCurso dados) {
        if (cursoRepository.existsByNomeIgnoreCase(dados.nome().trim())) throw new ValidacaoException("Já existe um curso cadastrado com o nome informado!");
        var curso = new Curso(dados);
        cursoRepository.save(curso);

        return new DadosDetalhamentoCurso(curso);
    }

}
