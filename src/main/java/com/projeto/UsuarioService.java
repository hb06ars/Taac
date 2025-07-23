package com.projeto;

import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final UsuarioRepository repo;

    public UsuarioService(UsuarioRepository repo) {
        this.repo = repo;
    }

    public Usuario salvar(String nome) {
        Usuario u = new Usuario();
        u.setNome(nome);
        return repo.save(u);
    }
}
