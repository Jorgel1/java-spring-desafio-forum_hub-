package controller;

import com.jorge.forumHub.domain.curso.Categoria;
import com.jorge.forumHub.domain.curso.DadosCurso;
import com.jorge.forumHub.domain.resposta.*;
import com.jorge.forumHub.domain.topico.DadosCadastroTopico;
import com.jorge.forumHub.domain.topico.Topico;
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
class RespostaControllerTest {


    @Autowired
    private JacksonTester<DadosCadastroResposta> dadosCadastroRespostaJacksonTester;

    @MockBean
    RespostaRepository repository;

    @Autowired
    private MockMvc mvc;

    @Autowired
    JacksonTester<DadosDetalhamentoResposta> dadosDetalhamentoRespostaJacksonTester;

    @MockBean
    private ListaDeRespostas listaDeRespostas;

    @Test
    @DisplayName("Deveria devolver o código 400 quando informacoes estao invalidas")
    @WithMockUser
    void cadastrarResposta_cenario1() throws Exception {
        var mensagem = "Erro de no algoritmo";
        var response = mvc.perform(
                        post("/respostas/1"))
                .andReturn().getResponse();


        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver o código http 201 quando informacoes estao válidas")
    @WithMockUser
    void cadastrarResposta_cenario2() throws Exception {

        var dadosCadastroResposta = new DadosCadastroResposta(
                "teste",
                "solucao");

        var  dadosCadastroTopico= new DadosCadastroTopico(
                "Titulo do tópico", "mensagem do topico", dadosCurso());

        var autor = new Usuario();
        var topico = new Topico(dadosCadastroTopico, autor);
        var dadosDetalhamentoResposta = new DadosDetalhamentoResposta(null, "teste", 1L, LocalDateTime.now(), 1L, "solucao");

        when(listaDeRespostas.salvarResposta(any(Long.class), any())).thenReturn(dadosDetalhamentoResposta);


        var response = mvc
                .perform(
                        post("/respostas/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(dadosCadastroRespostaJacksonTester.write(dadosCadastroResposta).getJson()))
                .andReturn().getResponse();


        var jsonEsperado = dadosDetalhamentoRespostaJacksonTester.write(dadosDetalhamentoResposta).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

    private DadosCurso dadosCurso(){
        return new DadosCurso(
                "java",
                Categoria.BACKEND
        );
    }
}