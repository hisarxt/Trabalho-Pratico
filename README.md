UnB - Universidade de Brasilia

FGA - Faculdade do Gama

OO - Orientação por Objetos  

Prof. André Luiz Peron Martins Lanna  

### Trabalho Prático de Orientação por Objetos
#### Alunos: 
 - Arthur Fernandes Alencar - 222037559
 - Caio Silva Nápoles - 222024292
---
### Estrutura e Fluxo do Programa
#### Inicialização dos Cadastros e Menus:

O programa começa inicializando os objetos necessários para cadastro de professores, disciplinas e turmas, e então apresenta um menu principal ao usuário.

Menu Principal:

O menu principal permite ao usuário escolher entre gerenciar professores, disciplinas ou turmas.

Interação com o Usuário:

Para cada uma dessas categorias, há opções para cadastrar, pesquisar, atualizar ou remover entradas. Quando se trata de turmas, há verificações adicionais para garantir que todas as informações necessárias estejam preenchidas e corretas.

### Cadastro de Alunos
---
#### Objetivo:

Gerenciar alunos, permitindo operações de cadastro, pesquisa, atualização e remoção.
Fluxo de Execução:

#### Cadastrar Aluno: O usuário insere o nome, matrícula, e outros detalhes do aluno. Se qualquer campo estiver vazio, uma CampoEmBrancoException é lançada.
#### Pesquisar Aluno: O usuário fornece a matrícula do aluno para pesquisa. Se o aluno não for encontrado, uma mensagem de erro é exibida.
#### Atualizar Aluno: O usuário pesquisa o aluno pela matrícula e atualiza as informações. Todos os campos são validados para não estarem vazios.
#### Remover Aluno: O usuário pesquisa e remove o aluno pela matrícula. Uma mensagem de erro é exibida se o aluno não for encontrado.
#### Tratamento de Exceções:

CampoEmBrancoException é utilizada para garantir que nenhum campo seja deixado em branco durante o cadastro ou atualização.
### Cadastro de Professores
---
#### Objetivo:

Gerenciar professores, permitindo cadastro, pesquisa, atualização e remoção de registros.
Fluxo de Execução:

#### Cadastrar Professor: O usuário insere o nome e a matrícula do professor. Se algum campo estiver vazio, uma CampoEmBrancoException é lançada.
#### Pesquisar Professor: O usuário fornece a matrícula para pesquisa. Se o professor não for encontrado, uma mensagem de erro é exibida.
#### Atualizar Professor: O usuário pesquisa o professor pela matrícula e atualiza as informações. Novamente, os campos são validados para não estarem vazios.
#### Remover Professor: O usuário pesquisa e remove o professor pelo ID. Se não for encontrado, uma mensagem de erro é exibida.
#### Tratamento de Exceções:

CampoEmBrancoException é usada para garantir que nenhum campo seja deixado em branco durante o cadastro ou atualização.
### Cadastro de Disciplinas
---
#### Objetivo:

Gerenciar disciplinas, permitindo operações de cadastro, pesquisa, atualização e remoção.
Fluxo de Execução:

#### Cadastrar Disciplina: O usuário insere o nome e o código da disciplina. Uma CampoEmBrancoException é lançada se algum campo estiver vazio.
#### Pesquisar Disciplina: O usuário insere o código da disciplina para pesquisa. Se não for encontrada, uma mensagem de erro é exibida.
#### Atualizar Disciplina: O usuário pesquisa a disciplina pelo código e atualiza os dados, com validação de campos em branco.
#### Remover Disciplina: O usuário pesquisa e remove a disciplina pelo código. Uma mensagem de erro é exibida se a disciplina não for encontrada.
#### Tratamento de Exceções:

CampoEmBrancoException garante que todos os campos sejam preenchidos durante cadastro e atualização.
### Cadastro de Turmas
---
#### Objetivo:

Gerenciar turmas, associando-as a um professor e uma disciplina. Permite cadastro, pesquisa, atualização e remoção de turmas.
Fluxo de Execução:

#### Cadastrar Turma: O usuário fornece o código da turma, Matricula Fub do professor e código da disciplina. Verifica se os campos estão preenchidos e se o professor e a disciplina existem.
Se o campo do código da turma estiver vazio, lança CampoEmBrancoException.
Se o professor não for encontrado, lança ProfessorNaoAtribuidoException.
Se a disciplina não for encontrada, lança DisciplinaNaoAtribuidaException.
#### Pesquisar Turma: O usuário pesquisa uma turma pelo código. Uma mensagem de erro é exibida se a turma não for encontrada.
#### Atualizar Turma: Permite atualizar os dados de uma turma existente após pesquisa pelo código. Valida campos em branco e a existência de professor e disciplina.
#### Matricular Aluno: Matricula os alunos que o usuario pedir em alguma turma existente. Valida campos em branco, a existência do aluno e da turma.
#### Imprimir lista de presenca: Imprime a lista de presenca de alunos e apresenta o nome da turma, juntamente com disciplina e o nome do professor. Valida campos em branco e existencia da turma.
#### Remover Turma: Remove uma turma após pesquisa pelo código. Exibe uma mensagem de erro se a turma não for encontrada.
#### Tratamento de Exceções:

CampoEmBrancoException para validar campos preenchidos.
ProfessorNaoAtribuidoException e DisciplinaNaoAtribuidaException garantem que a turma tenha um professor e uma disciplina válidos.

## Exemplos de execução
### Exemplo 1:
Entrada do Usuário:

1. Entrada do Usuário:
   O usuário pesquisa um professor usando a matrícula FUB '12345'.

   2. Saida:
    Uma mensagem de erro é mostrada ao usuário:
    Professor não encontrado - matrícula FUB inválida?

### Exemplo 2:
1. Entrada do Usuário:

O usuário seleciona a opção "Cadastro de Alunos" no menu.
Preenche os seguintes campos no formulário de cadastro:
- Nome: Joao
- CPF: 1234
- E-mail: joao123@.com
- Matrícula: 321
- Curso: Engenharia Automotiva
2. Saida:
  Uma mensagem de confirmacao e exibida ao usuario:
  
  Aluno 'Joao' cadastrado com sucesso!

  ### Exemplo 3
