package com.projeto.service;

import com.projeto.entity.UsuarioEntity;
import com.projeto.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final UsuarioRepository repo;

    public UsuarioService(UsuarioRepository repo) {
        this.repo = repo;
    }

    public UsuarioEntity salvar(String nome) {
        UsuarioEntity u = new UsuarioEntity();
        u.setNome(nome);
        return repo.save(u);
    }
}
