package com.jorge.forumHub.domain.usuario;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Entity(name = "Usuario")
@Table(name= "usuarios")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ="id")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String senha;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

//    private boolean status;
//    private String nome;
//    private String email;
//
//    @ManyToMany
//    @JoinTable(name = "usuario_perfil",
//            joinColumns = @JoinColumn(name = "usuario_id"),
//            inverseJoinColumns = @JoinColumn(name = "perfil_id"))
//    private Set<Perfil> perfis = new HashSet<>();
//
//    @OneToMany(mappedBy = "autor")
//    private List<Topico> topicos = new ArrayList<>();
//
//    @OneToMany(mappedBy = "autor")
//    private List<Resposta> respostas = new ArrayList<>();
//
//    public Usuario(DadosUsuario dadosUsuario) {
//
//        this.nome = dadosUsuario.nome();
//        this.email = dadosUsuario.email();
//        this.senha = dadosUsuario.senha();
//    }
}
