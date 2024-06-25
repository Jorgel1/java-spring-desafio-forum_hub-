package com.jorge.forumHub.domain.curso;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


//@Table(name= "cursos")
//@Entity(name = "Curso")
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode(of ="id")
public class Curso {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
    private String nome;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

//    @OneToMany(mappedBy = "curso")
//    private List<Topico> topicos = new ArrayList<>();

    public Curso(DadosCurso dadosCurso){
        this.nome = dadosCurso.nome();
        this.categoria = dadosCurso.categoria();

    }

    public void atualizarInformacoes(DadosCurso dados) {
        if (dados.nome() != null){
            this.nome = dados.nome();
        }
        if (dados.categoria() != null){
            this.categoria = dados.categoria();
        }
    }
}
