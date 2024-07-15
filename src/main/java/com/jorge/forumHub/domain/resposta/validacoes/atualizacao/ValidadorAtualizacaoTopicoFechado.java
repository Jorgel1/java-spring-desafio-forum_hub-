package com.jorge.forumHub.domain.resposta.validacoes.atualizacao;

import com.jorge.forumHub.domain.ValidacaoException;
import com.jorge.forumHub.domain.resposta.DadosAtualizacaoResposta;
import com.jorge.forumHub.domain.resposta.RespostaRepository;
import com.jorge.forumHub.domain.topico.StatusTopico;
import com.jorge.forumHub.domain.topico.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("ValidadorAtualizacaoDeTopicoFechado")
public class ValidadorAtualizacaoTopicoFechado implements ValidadorAtualizacaoResposta{
    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private RespostaRepository respostaRepository;

    public void validar(Long respostaId, DadosAtualizacaoResposta dadosAtualizacaoResposta){
        var resposta = respostaRepository.getReferenceById(respostaId);
        if (StatusTopico.FECHADO.equals(topicoRepository.getReferenceById(resposta.getTopico().getId()).getStatus())){
            throw new ValidacaoException("Tópicos resolvidos não permitem edição de suas respostas!");
        }
    }
}
