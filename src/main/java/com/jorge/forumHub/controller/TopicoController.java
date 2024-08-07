package com.jorge.forumHub.controller;

import com.jorge.forumHub.domain.topico.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @Autowired
    TopicoService topicoService;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroTopico dadosCadastroTopico, UriComponentsBuilder uriBuilder){
       var dadosDetalhamentoTopico = topicoService.cadastrar(dadosCadastroTopico);
        var uri = uriBuilder.path("/topicos/{id}")
                .buildAndExpand(dadosDetalhamentoTopico.id()).toUri();
        return ResponseEntity.created(uri).body(dadosDetalhamentoTopico);
    }



//    Que tal exibir os 10 primeiros resultados ordenados pela data de criação do tópico em ordem ASC?
//    http://localhost:8080/topicos?size=10&sort=dataCriacao
    @GetMapping
    public ResponseEntity<Page<DadosListagemTopico>> listar(@PageableDefault(size=10, sort = {"dataCriacao"}) Pageable paginacao){
        var page = repository.findAll(paginacao).map(DadosListagemTopico::new);
        return ResponseEntity.ok(page);
    }



    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoTopico dados){
        var topico = repository.getReferenceById(dados.id());
        topico.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }

//    EXCLUSÃO FÍSICA
//    @DeleteMapping("/{id}")
//    @Transactional
//    public void excluir(@PathVariable Long id){
//        repository.deleteById(id);
//    }

    //exclusão lógica
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        var topico = repository.getReferenceById(id);
        topico.finalizarTopico();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity detalhar(@PathVariable Long id){
        var topico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }

//    @GetMapping("/{id}")
//    public Topico detalhar(@PathVariable Long id){
//        Optional<Topico> topico = repository.findById(id);
//        if (topico.isPresent()) {
//            return topico.get();
//        } else {
//            throw new IllegalStateException("Tópico não encontrado");
//        }
//    }

}
