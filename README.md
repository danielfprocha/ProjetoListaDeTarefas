# Sistema de Lista de Tarefas
Projeto em java criado para facilitar o gerenciamento de tarefas. Usa armazenamento em arquivos, suporte a múltiplos usuários (a partir de um sistema de login e senha criptografada), categorias de tarefas personalizadas e menu interativo com o usuário via console. O projeto inclui dois planos diferentes para a utilização do sistema: Alpes e Everest (o Alpes sendo grátis e o Everest pago).

## Funcionalidades do Sistema
- Escolha de planos no sistema (Alpes/Grátis ou Everest/Pago)
- Cadastro e login de usuários (com senha protegida por hash SHA-256)
- Criação, listagem, edição e exclusão de tarefas e categorias
- Associação de tarefas a categorias
- Cada usuário visualiza apenas suas próprias tarefas e categorias
- Persistência dos dados em arquivos.txt
- Menus interativos extremamente intuitivos

## Características dos Planos do Sistema
### Plano Alpes (grátis)
- Criar, editar e excluir tarefas
- Marcar tarefas como "pendentes" ou "terminadas"
- Categorização de tarefas
- Criar, editar e excluir categorias
- Até 20 tarefas ativas por vez
- Até 5 categorias de tarefas ativas por vez

### Plano Everest (R$4,99/mês)
- Criar, editar e excluir tarefas
- Marcar tarefas como "pendentes" ou "terminadas"
- Categorização de tarefas
- Criar, editar e excluir categorias
- Tarefas ilimitadas
- Categorias de tarefas ilimitadas

## Estrutura das Classes
### Usuário
- Nome
- Email (cadastro/login)
- Senha (armazenada no sistema como hash)

### Tarefa
- Nome da tarefa
- Status (pendente ou terminada)
- Categoria
- Data de início
- Prazo final
- Email do usuário da tarefa

### Categoria
- Nome da categoria
- Respectivas tarefas
- Email do usuário da categoria

## Arquitetura do Projeto

data/ (Pasta onde os arquivos .txt são armazenados)
    usuarios.txt
    tarefas.txt
    categorias.txt
src/ (Pasta que compõe a maior parte do projeto)
    Menus/ (Interfaces de console com o usuário)
        MenuCategoria.java
        MenuConfig.java
        MenuLogin.java
        MenuPrincipal.java
        MenuTarefa.java
    Classes/ (Classes de modelo)
        Categoria.java
        Tarefa.java
        Usuario.java
    Repositorios/ (Manipulação e persistência dos arquivos)
        RepositorioCategorias.java
        RepositorioTarefa.java
        RepositorioUsuario.java
    Servicos/ (Lógica de aplicação)
        ServicoCategoria.java
        ServicoTarefa.java
        ServicoUsuario.java
    Main.java (Interação principal com o usuário)
    ErroDadoRepetido.java (Garante que usuários com o mesmo email, e tarefas ou categorias com nomes iguais não sejam criados)

## Considerações Finais
- É de conhecimento do autor que o sistema de encriptografia não é de segurança máxima, e esse era exatamente o objetivo. Considerando que era um projeto de faculdade, e que a aplicação não exigia um nível de segurança extrema, foi decidido que apenas o sistema com o algoritmo hash e Base64 era suficiente, e que a eficiência era a prioridade. Caso fosse um serviço no contexto empresarial, ou mesmo em um programa que precisasse de um nível maior de segurança, seriam feitas diversas melhorias (principalmente a inclusão de "salt" no projeto).
- Os "planos" do projeto tem valor fictício. Apesar de existir a escolha dos planos no menu interativo com o usuário pelo console, não existe nenhum pagamento externo, e os valores têm um propósito puramente acadêmico.
- O tema de "montanha" do projeto foi inspirado no meu professor de faculdade "Alexandre Montanha", que me ensinou disciplinas como "Programação de Soluções Computacionais" e "Sistemas Computacionais e Segurança".
- Esse projeto foi de grande importância para meu aprendizado de programação e cibersegurança, e sou extremamente grato pela minha evolução nestes tópicos da tecnologia.
