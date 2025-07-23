# language: pt
# encoding: UTF-8

Funcionalidade: Cadastro de usuário

  Cenário: Cadastro de um usuário com nome válido
  Dado um usuário com nome "Carlos"
  Quando o usuário é salvo no sistema
  Então o usuário deve ser persistido com sucesso

  Cenário: Busca por usuário existente
  Dado um usuário salvo com nome "Ana"
  Quando busco pelo nome "Ana"
  Então o usuário encontrado deve ter o nome "Ana"

