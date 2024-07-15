package com.jorge.forumHub.domain.resposta;

import com.jorge.forumHub.domain.ValidacaoException;
import com.jorge.forumHub.domain.resposta.validacoes.atualizacao.ValidadorAtualizacaoResposta;
import com.jorge.forumHub.domain.resposta.validacoes.cadastro.ValidadorCadastroResposta;
import com.jorge.forumHub.domain.resposta.validacoes.exclusao.ValidadorExclusaoResposta;
import com.jorge.forumHub.domain.topico.TopicoRepository;
import com.jorge.forumHub.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ListaDeRespostas {

    @Autowired
    private RespostaRepository respostaRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ValidadorCadastroResposta validadorCadastroResposta;

    @Autowired
    private ValidadorExclusaoResposta validadorExclusaoResposta;

    @Autowired
    private ValidadorAtualizacaoResposta validadorAtualizacaoResposta;

    public DadosDetalhamentoResposta salvarResposta(Long topicoId, DadosCadastroResposta dados){
        if (!topicoRepository.existsById(topicoId))
            throw new ValidacaoException("Nenhum tópico encontrado com o ID fornecido!");

        validadorCadastroResposta.validar(topicoId, dados);

        var topico = topicoRepository.getReferenceById(topicoId);
        var resposta = new Resposta(topico, dados);

        respostaRepository.save(resposta);

        return new DadosDetalhamentoResposta(resposta);
    }

    public DadosDetalhamentoResposta atualizar(Long respostaId, DadosAtualizacaoResposta dados) {
        if (!respostaRepository.existsById(respostaId)) throw new ValidacaoException("Nenhuma resposta encontrada com o ID fornecido!");

       validadorAtualizacaoResposta.validar(respostaId, dados);
        var resposta = respostaRepository.getReferenceById(respostaId);
        resposta.atualizar(dados);

        return new DadosDetalhamentoResposta(resposta);
    }

    public void excluir(Long respostaId) {

        if (!respostaRepository.existsById(respostaId)) {
            throw new ValidacaoException("Id da resposta informado não existe!");
        }

        validadorExclusaoResposta.validar(respostaId);

        respostaRepository.deleteById(respostaId);
    }
}
