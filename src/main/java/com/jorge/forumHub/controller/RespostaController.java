package com.jorge.forumHub.controller;

import com.jorge.forumHub.domain.resposta.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("respostas")
public class RespostaController {

    @Autowired
    private RespostaRepository repository;

    @Autowired
    private ListaDeRespostas listagem;

    @PostMapping("/{topicoId}")
    @Transactional
    public ResponseEntity cadastrarResposta(@PathVariable Long topicoId,
                                            @RequestBody @Valid DadosCadastroResposta dados,
                                            UriComponentsBuilder uriBuilder) {
        var dto = listagem.salvarResposta(topicoId, dados);
        System.out.println(dados);
        var uri = uriBuilder.path("/respostas/{respostaId}")
                .buildAndExpand(dto.id()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/{respostaId}")
    @Transactional
    public ResponseEntity atualizar(@PathVariable Long respostaId, @RequestBody @Valid DadosAtualizacaoResposta dados) {
        var resposta = listagem.atualizar(respostaId, dados);
        return ResponseEntity.ok().body(resposta);
    }

    @GetMapping("/{topicoId}")
    public ResponseEntity<Page<DadosListaDeRespostas>> listar(@PathVariable Long topicoId, @PageableDefault(size = 10, sort = {"id"}) Pageable paginacao) {
        var page = repository.findAllByTopicoId(paginacao, topicoId).map(DadosListaDeRespostas::new);
        return ResponseEntity.ok(page);
    }

    @DeleteMapping("/{respostaId}")
    @Transactional
    public ResponseEntity excluirResposta (@PathVariable Long respostaId){
        listagem.excluir(respostaId);
        return ResponseEntity.noContent().build();
    }
}
