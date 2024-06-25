package com.jorge.forumHub.controller;

import com.jorge.forumHub.domain.topico.DadosListagemTopico;
import com.jorge.forumHub.domain.topico.Topico;
import com.jorge.forumHub.domain.topico.TopicoRepository;
import com.jorge.forumHub.domain.topico.*;
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
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity CadastrarTopico(@RequestBody @Valid DadosCadastroTopico dadosCadastroTopico, UriComponentsBuilder uriBuilder){
       var topico = new Topico(dadosCadastroTopico);
        repository.save(topico);

        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoTopico(topico));
    }

//    Que tal exibir os 10 primeiros resultados ordenados pela data de criação do tópico em ordem ASC?
//    http://localhost:8080/topicos?size=10&sort=dataCriacao
    @GetMapping
    public ResponseEntity<Page<DadosListagemTopico>> listar(@PageableDefault(size=10, sort = {"dataCriacao"}) Pageable paginacao){
        var page = repository.findAllByStatusTrue(paginacao).map(DadosListagemTopico::new);
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
        topico.excluir();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
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
