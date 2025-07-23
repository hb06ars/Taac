package com.projeto;

import com.projeto.entity.UsuarioEntity;
import com.projeto.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UsuarioEntityRepositoryTest {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16")
            .withDatabaseName("taac")
            .withUsername("user")
            .withPassword("pass");

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl); // Ex: jdbc:postgresql://localhost:random_port/taac
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "update"); // Para criar automaticamente as tabelas, pelo docker.
    }

    @Autowired
    UsuarioRepository repository;

    @Test
    void deveSalvarUsuarioQuandoNomeInformado() {
        // Given: Um novo usuário com nome válido
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setNome("Carlos");

        // When: O usuário é salvo
        UsuarioEntity salvo = repository.save(usuarioEntity);

        // Then: O usuário é persistido com ID e nome corretos
        assertThat(salvo.getId()).isNotNull();
        assertThat(salvo.getNome()).isEqualTo("Carlos");
    }

    @Test
    void deveBuscarUsuarioPorNomeQuandoExistente() {
        // Given: Um usuário existente no banco
        var usuario = new UsuarioEntity();
        usuario.setNome("Ana");
        repository.save(usuario);

        // When: Buscamos pelo nome
        Optional<UsuarioEntity> resultado = repository.findByNome("Ana");

        // Then: O usuário é encontrado corretamente
        assertThat(resultado).isPresent();
        assertThat(resultado.get().getNome()).isEqualTo("Ana");
    }

    @Test
    void naoDeveRetornarUsuarioQuandoNomeInexistente() {
        // Given: Nenhum usuário com o nome informado
        var usuario = new UsuarioEntity();
        usuario.setNome("João");
        repository.save(usuario);

        // When: Buscamos por nome inexistente
        Optional<UsuarioEntity> resultado = repository.findByNome("Maria");

        // Then: Nada é retornado
        assertThat(resultado).isEmpty();
    }

}
