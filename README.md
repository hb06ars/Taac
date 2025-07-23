# TAAC

### OBSERVAÇÃO
Quando você usa o Testcontainers, os dados ficam salvos no banco do container Docker apenas durante a execução do teste. 
Depois que o teste termina e o container é parado, todos os dados são apagados automaticamente.

### SUBIR COM DOCKER COMPOSE LOCAL (OPCIONAL)
- Para subir a imagem basta executar o **docker-compose.yaml**

### EXECUTAR TESTE:
- Classe: UsuarioEntityRepositoryTest.java

### BANCO (LOCAL)
- DB: taac
- Usuário: user
- Senha: pass
- url: jdbc:postgresql://localhost:5432/taac

