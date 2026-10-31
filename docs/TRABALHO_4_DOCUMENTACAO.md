# Trabalho 4 — Arquitetura MVVM
## Documentação do processo e das decisões

**Projeto:** Contaê  
**Disciplina/turma:** preencher  
**Integrantes:** preencher com os nomes do trio  
**Repositório:** https://github.com/aguustinn/Contae-pt3  
**Branch:** `refatoracao-mvvm-room`

---

## 1. Situação do projeto ao final do Trabalho 3

No Trabalho 3, o aplicativo Contaê passou a utilizar o Room para persistir as informações de categorias e despesas. A aplicação já possuía entidades, DAOs, banco de dados e telas para realizar as operações previstas.

O principal ponto de melhoria identificado para o Trabalho 4 foi a concentração de responsabilidades. Quando a interface conhece diretamente o DAO e também controla tarefas assíncronas, a tela fica responsável por desenhar os componentes, buscar dados, executar operações e controlar atualizações.

Essa organização aumenta o acoplamento. Uma mudança na forma de obter os dados pode exigir alterações em várias telas. O Trabalho 4 foi utilizado para reorganizar o código, mantendo as funcionalidades do Trabalho 3 e separando as responsabilidades em camadas.

## 2. O que mudou na reorganização

A estrutura foi dividida em quatro áreas principais:

| Pacote | Responsabilidade | Exemplos |
|---|---|---|
| `ui` | Telas e componentes Compose | `screens`, `navigation`, `components`, `theme` |
| `data` | Persistência e modelos | `AppDatabase`, `model`, `dao` |
| `data.repository` | Mediação entre ViewModels e DAOs | `CategoriaRepository`, `DespesaRepository` |
| `viewmodel` | Estado da interface e ações | ViewModels, UiStates e factories |

As entidades e os DAOs continuam pertencendo à camada de dados. A diferença é que as telas não conversam diretamente com os DAOs: essa comunicação passa pelos ViewModels e Repositories.

### 2.1 Fluxo de comunicação

```text
Tela Compose
    ↓ observa uiState e chama ações
ViewModel
    ↓ solicita operações
Repository
    ↓ delega consultas e comandos
DAO
    ↓
Room
```

O fluxo de atualização da listagem funciona no sentido inverso: o Room emite alterações pelo Flow; o Repository disponibiliza esse fluxo; o ViewModel o observa e atualiza o StateFlow; a tela coleta o UiState e recompõe a interface quando necessário.

## 3. Decisões de arquitetura

### 3.1 Por que o Repository existe?

O Repository foi introduzido para ser a única camada que conversa diretamente com o DAO. Ele encapsula o acesso aos dados e oferece métodos ligados ao domínio da aplicação, como `buscarTodos()`, `buscarPorId()`, `inserir()`, `atualizar()` e `deletar()`.

No Contaê existem dois Repositories:

- **CategoriaRepository:** recebe o `CategoriaDao` e encaminha as operações relacionadas às categorias.
- **DespesaRepository:** recebe o `DespesaDao` e encaminha as operações relacionadas às despesas, incluindo consulta por categoria.

Essa separação evita que cada ViewModel conheça detalhes do Room. Caso futuramente os dados venham de uma API ou de outra fonte, a alteração pode ficar concentrada na implementação do Repository, preservando a interface utilizada pelos ViewModels.

### 3.2 Por que o DAO usa Flow?

As funções `buscarTodos()` dos dois DAOs retornam `Flow<List<Categoria>>` e `Flow<List<Despesa>>`, respectivamente.

O Flow permite observar a consulta ao longo do tempo. Quando uma categoria ou despesa é inserida, atualizada ou removida, o Room pode emitir uma nova lista. Dessa forma, o ViewModel recebe os dados atualizados sem que a tela precise executar uma nova consulta manual depois de cada operação.

As operações de escrita e as consultas pontuais continuam sendo funções `suspend`.

### 3.3 Por que utilizar UiState?

O UiState agrupa as informações necessárias para desenhar uma tela em um único objeto. Em vez de manter diversas variáveis independentes de estado, o ViewModel publica uma estrutura que representa a situação atual da interface.

**CategoriaUiState**
- `categorias`: lista de categorias apresentada pela interface.
- `despesas`: lista de despesas usada nas telas que relacionam gastos às categorias.
- `carregando`: indica o estado de carregamento.
- `erro`: permite representar uma falha ocorrida durante uma operação.

**DespesaUiState**
- `despesas`: lista de despesas apresentada pela interface.
- `categorias`: lista de categorias usada para selecionar e identificar a categoria de uma despesa.
- `carregando`: indica o estado de carregamento.
- `mensagem`: permite disponibilizar uma mensagem relacionada a uma operação.

As listas auxiliares são necessárias porque algumas telas precisam relacionar as duas entidades, por exemplo, para exibir o nome da categoria associada a uma despesa ou calcular valores por categoria.

### 3.4 Por que MutableStateFlow privado e StateFlow público?

Cada ViewModel mantém um `MutableStateFlow` privado, utilizado internamente para atualizar o estado. Para a interface, é exposto um `StateFlow` público e somente de leitura.

Esse padrão impede que uma tela altere diretamente o estado do ViewModel. A interface apenas observa o estado e solicita ações por meio dos métodos disponibilizados pelo ViewModel.

As operações assíncronas são executadas com `viewModelScope`, que acompanha o ciclo de vida do ViewModel. A tela não precisa criar seu próprio `CoroutineScope` nem acessar `Dispatchers.IO` para executar operações do banco.

### 3.5 Por que utilizar ViewModelFactory?

Os ViewModels recebem Repositories pelo construtor. Como o Android não consegue criar automaticamente um ViewModel com esses parâmetros sem uma factory ou mecanismo de injeção, foram implementadas factories em `ViewModelFactories.kt`.

Na navegação, os Repositories são criados a partir dos DAOs do banco e entregues às factories. Assim, o ViewModel recebe as dependências necessárias no momento de sua criação.

Essa decisão mantém a criação das dependências fora das telas e permite testar os ViewModels com implementações alternativas de Repository futuramente.

## 4. Dificuldades, correções e aprendizados

A refatoração exige cuidado porque os arquivos mudam de pacote e as referências entre telas, modelos e classes de dados precisam acompanhar a nova estrutura.

Durante a reorganização, foi necessário:
- Atualizar os imports das entidades, DAOs e telas para os novos pacotes.
- Ajustar a navegação para obter os ViewModels por meio das factories.
- Centralizar as operações de dados nos Repositories.
- Adaptar os ViewModels para observar os Flows e publicar UiStates.
- Remover das telas o acesso direto aos DAOs e a responsabilidade por executar operações de banco.

**Relato real do trio — completar antes da entrega:** descrevam quais erros apareceram durante a compilação ou execução, em quais arquivos ocorreram e como foram resolvidos. Não registrem problemas hipotéticos como se tivessem acontecido.

| Integrante | Dificuldade encontrada | Como foi resolvida |
|---|---|---|
| Nome do integrante 1 | Preencher com a experiência real | Preencher |
| Nome do integrante 2 | Preencher com a experiência real | Preencher |
| Nome do integrante 3 | Preencher com a experiência real | Preencher |

### 4.1 Funcionalidades que precisam ser conferidas após a refatoração

Como o Trabalho 4 não propõe novas funcionalidades, a validação deve confirmar que o comportamento do Trabalho 3 continua disponível:

- [ ] Cadastrar e listar categorias.
- [ ] Editar e excluir categorias.
- [ ] Cadastrar e listar despesas.
- [ ] Editar e excluir despesas.
- [ ] Consultar detalhes de categorias e despesas.
- [ ] Conferir orçamento e relatório.
- [ ] Fechar e abrir o aplicativo e verificar que os dados continuam salvos.

**Resultado dos testes:** preencher após executar o aplicativo no Android Studio, emulador ou dispositivo físico. Registrar eventuais falhas e correções.

## 5. Evidências do aplicativo

Inserir imagens ou links de vídeos curtos mostrando o aplicativo em funcionamento. As evidências devem ser capturadas após a execução real do projeto.

| Evidência | Inserir print ou link |
|---|---|
| Tela inicial e navegação | Inserir imagem/link |
| Listagem de categorias | Inserir imagem/link |
| Cadastro ou edição de categoria | Inserir imagem/link |
| Listagem de despesas | Inserir imagem/link |
| Cadastro ou edição de despesa | Inserir imagem/link |
| Persistência após fechar e abrir o app | Inserir imagem/link |
| Orçamento e relatório | Inserir imagem/link |

## 6. Conclusão

O Trabalho 4 teve como foco a evolução estrutural do Contaê. A separação entre interface, dados, Repository e ViewModel torna mais explícito o papel de cada parte do sistema. O Repository concentra o acesso aos dados, o Flow mantém as listas reativas, o UiState agrupa as informações necessárias para a interface e as factories permitem fornecer as dependências aos ViewModels.

A principal mudança não está no que o aplicativo faz, mas em como o código está organizado para que as funcionalidades existentes possam ser mantidas e evoluídas com menor acoplamento.

---

> **Antes de entregar:** preencher os nomes dos integrantes, o relato real de dificuldades, o resultado dos testes e as evidências. Esta documentação descreve a arquitetura implementada no repositório; não substitui a comprovação de que o aplicativo foi compilado e executado com sucesso.
