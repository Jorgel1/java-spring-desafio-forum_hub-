package com.jorge.forumHub.domain.resposta.validacoes.cadastro;

import com.jorge.forumHub.domain.ValidacaoException;
import com.jorge.forumHub.domain.resposta.DadosCadastroResposta;
import com.jorge.forumHub.domain.resposta.RespostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("ValidadorDeDuplicidadeDeMensagem")
public class ValidadorMensagemRepetida implements ValidadorCadastroResposta{

    @Autowired
    private RespostaRepository respostaRepository;

    public void validar(Long topicoId, DadosCadastroResposta dadosCadastroResposta){
        boolean existeResposta = respostaRepository.existsByTopicoIdAndMensagem(topicoId, dadosCadastroResposta.mensagem());
        if (existeResposta) {
            throw new ValidacaoException("Já existe uma resposta com essa mensagem para o tópico informado.");
        }
    }
}
