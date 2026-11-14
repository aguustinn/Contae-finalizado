# Contaê — Trabalho 4: Arquitetura MVVM

Continuação do Trabalho 3, com reorganização da aplicação para separar interface, acesso aos dados e estado da tela usando **Repository + MVVM**, sem acrescentar funcionalidades ao aplicativo.

## Objetivo

Manter as operações já existentes de categorias e despesas com persistência em Room, mas organizar o código em camadas para reduzir o acoplamento e facilitar manutenção.

## Organização dos pacotes

Os arquivos da aplicação ficam organizados por responsabilidade:

```text
com.example.contae/
├── data/
│   ├── AppDatabase.kt
│   ├── dao/
│   │   ├── CategoriaDao.kt
│   │   └── DespesaDao.kt
│   ├── model/
│   │   ├── Categoria.kt
│   │   └── Despesa.kt
│   └── repository/
│       ├── CategoriaRepository.kt
│       └── DespesaRepository.kt
├── ui/
│   ├── components/
│   ├── navigation/
│   ├── screens/
│   └── theme/
├── viewmodel/
│   ├── CategoriaUiState.kt
│   ├── DespesaUiState.kt
│   ├── CategoriaViewModel.kt
│   ├── DespesaViewModel.kt
│   └── ViewModelFactories.kt
└── MainActivity.kt
```

- **ui:** telas e componentes Jetpack Compose. Não acessa DAO nem executa operações de banco diretamente.
- **data/model:** entidades Room `Categoria` e `Despesa`.
- **data/dao:** consultas e operações do Room.
- **data/repository:** camada intermediária que concentra o acesso aos DAOs.
- **viewmodel:** estado observável da interface e ações executadas pelas telas.

## Fluxo da arquitetura

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

O Room fornece listas reativas por meio de `Flow<List<T>>`. Os ViewModels observam esses fluxos e publicam o estado atualizado em `StateFlow`. Assim, alterações no banco são refletidas na interface sem que a tela precise buscar novamente os dados manualmente.

## Repository

Há um Repository para cada entidade:

| Repository | Responsabilidade |
|---|---|
| `CategoriaRepository` | Encaminha operações de categorias ao `CategoriaDao`, incluindo listagem reativa e busca por ID. |
| `DespesaRepository` | Encaminha operações de despesas ao `DespesaDao`, incluindo listagem reativa, busca por ID e consulta por categoria. |

Os ViewModels dependem dos Repositories, e não dos DAOs. Isso centraliza o acesso aos dados e permite trocar ou complementar a fonte de dados futuramente sem espalhar alterações pelas telas.

## ViewModels e UiStates

Cada ViewModel possui um estado agrupado em uma classe própria:

- **CategoriaUiState:** lista de categorias, lista de despesas (necessária para apresentar valores e totais relacionados às categorias), indicador de carregamento e mensagem de erro.
- **DespesaUiState:** lista de despesas, lista de categorias (necessária para seleção e identificação da categoria associada), indicador de carregamento e mensagem.

O padrão utilizado é manter um `MutableStateFlow` privado para atualização interna e expor um `StateFlow` público somente para leitura. As operações assíncronas são executadas com `viewModelScope`.

## Injeção com ViewModelFactory

As factories em `ViewModelFactories.kt` recebem os Repositories e os entregam aos construtores dos ViewModels. A criação ocorre na navegação com `viewModel(factory = ...)`, evitando instanciar dependências diretamente dentro das telas.

## Funcionalidades preservadas

A proposta do Trabalho 4 é uma refatoração, não a criação de novas funcionalidades. O aplicativo deve continuar permitindo as operações de categorias e despesas que já existiam no Trabalho 3, além de manter orçamento, relatório, navegação e persistência.

## Como executar e validar

1. Abra o projeto no Android Studio.
2. Aguarde a sincronização do Gradle.
3. Execute em um emulador ou dispositivo Android.
4. Teste inclusão, listagem, edição e exclusão de categorias.
5. Teste inclusão, listagem, edição e exclusão de despesas.
6. Feche e abra novamente o aplicativo para verificar a persistência.
7. Confira as telas de detalhes, orçamento e relatório.

## Evidências

Adicionar nesta seção prints ou vídeos reais da aplicação executando. Sugestões:
- Listagem de categorias e despesas.
- Cadastro e edição de um registro.
- Exclusão de um registro.
- Aplicativo após fechar e abrir novamente, demonstrando persistência.
- Telas de detalhes, orçamento e relatório.

## Checklist de entrega

- [x] Código organizado nos pacotes `ui`, `data`, `data.repository` e `viewmodel`.
- [x] Um Repository para cada entidade principal.
- [x] DAOs com `buscarTodos()` retornando `Flow<List<...>>`.
- [x] No mínimo dois ViewModels com UiStates próprios.
- [x] `MutableStateFlow` privado e `StateFlow` público nos ViewModels.
- [x] ViewModelFactories para injetar os Repositories.
- [x] Telas desacopladas de DAO, `CoroutineScope` e `Dispatchers.IO`.
- [ ] Compilar e executar no Android Studio.
- [ ] Testar todas as operações existentes e persistência.
- [ ] Inserir evidências reais.
- [ ] Completar a reflexão do trio com as experiências efetivamente vividas.

