package com.jorge.forumHub.domain.resposta.validacoes.exclusao;

import com.jorge.forumHub.domain.ValidacaoException;
import com.jorge.forumHub.domain.resposta.RespostaRepository;
import com.jorge.forumHub.domain.topico.StatusTopico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("ValidadorExclusaoRespostaDeTopicoFechado")
public class ValidadorExclusaoRespostaTopicoFechado implements ValidadorExclusaoResposta{

    @Autowired
    private RespostaRepository respostaRepository;

    public void validar(Long respostaId){
        if (StatusTopico.FECHADO.equals(respostaRepository.getReferenceById(respostaId).getTopico().getStatus())){
            throw new ValidacaoException("Tópicos resolvidos não permitem exlusão de respostas!");
        }
    }
}
