//package com.jorge.forumHub.resposta;
//
//import com.jorge.forumHub.domain.topico.Topico;
//import com.jorge.forumHub.usuario.Usuario;
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.time.LocalDateTime;
//
//@Entity(name = "Resposta")
//@Table(name= "respostas")
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@EqualsAndHashCode(of ="id")
//public class Resposta {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String mensagem;
//    private LocalDateTime dataCriacao;
//    private boolean solucao;
//
//    @ManyToOne
//    @JoinColumn(name = "autor_id")
//    private Usuario autor;
//
//    @ManyToOne
//    @JoinColumn(name = "topico_id")
//    private Topico topico;
//
//}
