package com.jorge.forumHub.domain.topico;

import com.jorge.forumHub.domain.ValidacaoException;
import com.jorge.forumHub.domain.curso.CursoRepository;
import com.jorge.forumHub.domain.usuario.AutenticacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    public DadosDetalhamentoTopico cadastrar(DadosCadastroTopico dados){
        if (!cursoRepository.existsById(dados.cursoId())) throw new ValidacaoException("Nenhum curso cadastrado com o ID informado!");
        var curso = cursoRepository.findById(dados.cursoId()).get();
        var usuario = AutenticacaoService.getUsuarioLogado();
        var topico = new Topico(dados, usuario, curso);
        topicoRepository.save(topico);
        return new DadosDetalhamentoTopico(topico);
    }
}
