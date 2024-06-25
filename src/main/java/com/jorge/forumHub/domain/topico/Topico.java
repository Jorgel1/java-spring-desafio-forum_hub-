package com.jorge.forumHub.domain.topico;

import com.jorge.forumHub.domain.curso.Curso;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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
    private LocalDateTime dataCriacao;
    private boolean status;
    private String autor;


//    @ManyToOne
//    @JoinColumn(name = "autor_id")
//    private Usuario autor;

//    @ManyToOne
//    @JoinColumn(name = "curso_id")
    @Embedded
    private Curso curso;

//    @OneToMany(mappedBy = "topico")
//    private List<Resposta> respostas = new ArrayList<>();

    public Topico(DadosCadastroTopico dadosCadastroTopico) {
        this.status = true;
        this.titulo = dadosCadastroTopico.titulo();
        this.mensagem = dadosCadastroTopico.mensagem();
        this.dataCriacao = LocalDateTime.now();
        this.autor = dadosCadastroTopico.autor();
        this.curso = new Curso(dadosCadastroTopico.curso());
    }

    public void atualizarInformacoes(DadosAtualizacaoTopico dados) {
        if (dados.titulo() != null){
            this.titulo = dados.titulo();
        }
        if (dados.mensagem() != null){
            this.mensagem = dados.mensagem();
        }
        if (dados.autor() != null){
            this.autor = dados.autor();
        }
        if (dados.curso() != null){
            this.curso.atualizarInformacoes(dados.curso());
        }

    }

    public void excluir() {
        this.status = false;
    }
}
