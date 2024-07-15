package com.jorge.forumHub.domain.resposta;

import com.jorge.forumHub.domain.topico.Topico;

import com.jorge.forumHub.domain.usuario.AutenticacaoService;
import com.jorge.forumHub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "Resposta")
@Table(name= "respostas")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ="id")
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mensagem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico_id")
    private Topico topico;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    private String solucao;

    public Resposta(Topico topico, DadosCadastroResposta dados) {
        this.mensagem = dados.mensagem().trim();
        this.topico = topico;
        this.dataCriacao = LocalDateTime.now();
        this.autor = AutenticacaoService.getUsuarioLogado();
        this.solucao = dados.solucao().trim();
    }


    public void atualizar(DadosAtualizacaoResposta dados) {
        if (dados.mensagem() != null) this.mensagem = dados.mensagem();
        if (dados.solucao() != null)  this.solucao = dados.solucao();
    }
}

