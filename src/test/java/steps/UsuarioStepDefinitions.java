package steps;

import com.projeto.entity.UsuarioEntity;
import com.projeto.repository.UsuarioRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
public class UsuarioStepDefinitions {

    private UsuarioEntity usuario;
    private UsuarioEntity usuarioSalvo;
    private Optional<UsuarioEntity> resultadoBusca;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Given("um usuário com nome {string}")
    public void um_usuario_com_nome(String nome) {
        usuario = new UsuarioEntity();
        usuario.setNome(nome);
    }

    @When("o usuário é salvo no sistema")
    public void o_usuario_e_salvo() {
        usuarioSalvo = usuarioRepository.save(usuario);
    }

    @Then("o usuário deve ser persistido com sucesso")
    public void o_usuario_deve_ser_persistido() {
        assertThat(usuarioSalvo).isNotNull();
        assertThat(usuarioSalvo.getId()).isNotNull();
    }

    @Given("um usuário salvo com nome {string}")
    public void um_usuario_salvo_com_nome(String nome) {
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setNome(nome);
        usuarioRepository.save(usuario);
    }

    @When("busco pelo nome {string}")
    public void busco_pelo_nome(String nome) {
        resultadoBusca = usuarioRepository.findByNome(nome);
    }

    @Then("o usuário encontrado deve ter o nome {string}")
    public void o_usuario_encontrado_deve_ter_nome(String nomeEsperado) {
        assertThat(resultadoBusca).isPresent();
        assertThat(resultadoBusca.get().getNome()).isEqualTo(nomeEsperado);
    }
}
