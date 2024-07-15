package controller;

import com.jorge.forumHub.domain.curso.Categoria;
import com.jorge.forumHub.domain.curso.Curso;
import com.jorge.forumHub.domain.curso.DadosCurso;
import com.jorge.forumHub.domain.topico.DadosCadastroTopico;
import com.jorge.forumHub.domain.topico.DadosDetalhamentoTopico;
import com.jorge.forumHub.domain.topico.Topico;
import com.jorge.forumHub.domain.topico.TopicoRepository;
import com.jorge.forumHub.domain.usuario.AutenticacaoService;
import com.jorge.forumHub.domain.usuario.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class TopicoControllerTest {
//
    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DadosCadastroTopico> dadosCadastroTopicoJacksonTester;

    @Autowired
    private JacksonTester<DadosDetalhamentoTopico> dadosDetalhamentoTopicoJacksonTester;

    @MockBean
    private TopicoRepository repository;

    @Test
    @DisplayName("Deveria devolver codigo http 400 quando informacoes estao invalidas")
    @WithMockUser
    void cadastrar_cenario1() throws Exception {
        var response = mvc
                .perform(post("/topicos"))
                .andReturn().getResponse();

        assertThat(response.getStatus())
                .isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver codigo http 200 quando informacoes estao validas")
    @WithMockUser
    void cadastrar_cenario2() throws Exception {
        var dadosCadastro = new DadosCadastroTopico(
                "titulo",
                "mensagem de titulo",
                dadosCurso()
                );
//        var usuario = AutenticacaoService.getUsuarioLogado();
        var usuario = new Usuario(1L, "nome", "login", "senha", null, null, null, null);
        when(repository.save(any())).thenReturn(new Topico(dadosCadastro, usuario));

        var response = mvc
                .perform(post("/topicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosCadastroTopicoJacksonTester.write(dadosCadastro).getJson()))
                .andReturn().getResponse();

        var dadosDetalhamento = new DadosDetalhamentoTopico(
                null,
                dadosCadastro.titulo(),
                dadosCadastro.mensagem(),
                LocalDateTime.now(),
                new Curso(dadosCadastro.curso())
        );
        var jsonEsperado = dadosDetalhamentoTopicoJacksonTester.write(dadosDetalhamento).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

    private DadosCurso dadosCurso(){
        return new DadosCurso(
                "Nome do curso",
                Categoria.BACKEND
        );
    }

}


