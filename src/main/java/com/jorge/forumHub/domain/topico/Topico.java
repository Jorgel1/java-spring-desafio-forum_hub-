package com.jorge.forumHub.domain.topico;

import com.jorge.forumHub.domain.curso.Curso;
import com.jorge.forumHub.domain.resposta.Resposta;
import com.jorge.forumHub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensagem;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @Enumerated(EnumType.STRING)
    private StatusTopico status;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @OneToMany(mappedBy = "topico", fetch = FetchType.LAZY)
    private List<Resposta> respostas;

//    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private List<Resposta> resposta;

    public Topico(DadosCadastroTopico dadosCadastroTopico, Usuario autor,  Curso curso) {
        this.titulo = dadosCadastroTopico.titulo();
        this.mensagem = dadosCadastroTopico.mensagem();
        this.dataCriacao = LocalDateTime.now();
        this.status = StatusTopico.ABERTO;
        this.autor = autor;
        this.curso = curso;
    }

    public void atualizarInformacoes(DadosAtualizacaoTopico dados) {
        if (dados.titulo() != null){
            this.titulo = dados.titulo().trim();
        }
        if (dados.mensagem() != null){
            this.mensagem = dados.mensagem().trim();
        }

        if (dados.curso() != null){
            this.curso.atualizarInformacoes(dados.curso());
        }

    }

    public void finalizarTopico() {
        this.status = StatusTopico.FECHADO;
    }
}
