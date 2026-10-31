# Contaê — Trabalho 4 (Repository + MVVM)

O aplicativo mantém as funcionalidades do Trabalho 3: cadastro, listagem, edição, exclusão e persistência de categorias e despesas usando Room.

## Organização dos pacotes
- `ui/screens`: telas Compose; observam os ViewModels e encaminham ações.
- `ui/navigation`: navegação.
- `ui/components`: componentes reutilizáveis.
- `data/model`: entidades Room Categoria e Despesa.
- `data/dao`: interfaces Room; `buscarTodos()` emite `Flow<List<T>>`.
- `data/repository`: CategoriaRepository e DespesaRepository; única camada que chama DAO.
- `viewmodel`: ViewModels, UiStates e factories.

## Fluxo de dados
`Tela → ViewModel → Repository → DAO → Room`. Os Flows do Room atualizam os StateFlows dos ViewModels automaticamente, e a interface coleta `uiState` com `collectAsState()`.

## UiStates
- CategoriaUiState: categorias, despesas (para calcular gastos por categoria), carregamento e erro.
- DespesaUiState: despesas, categorias (para selecionar e identificar a categoria), carregamento e mensagem.

## Repository
Centraliza o acesso aos dados e desacopla os ViewModels do Room. Uma futura API pode substituir a fonte de dados alterando a implementação do Repository.

## Regras preservadas
- As entidades e seus campos permanecem os mesmos.
- Ao excluir uma categoria, as despesas associadas são removidas primeiro.
- Os dados demonstrativos continuam sendo inseridos uma única vez quando o banco está vazio.
- As telas não acessam DAO, CoroutineScope ou Dispatchers.IO.

## Como validar
1. Abra no Android Studio e sincronize o Gradle.
2. Execute em emulador/aparelho.
3. Teste inserir, listar, editar e excluir categorias e despesas.
4. Feche e reabra o app para confirmar persistência.
5. Confira detalhes, orçamento e relatório.

## Evidências
Adicionar prints e vídeos reais: telas de categorias/despesas, edição, exclusão e persistência após reiniciar.

## Reflexão do trio
Completar com as experiências reais:
- O que mudou em relação ao Trabalho 3.
- Como o trio dividiu os pacotes.
- Por que o Repository foi adotado.
- Por que cada UiState contém esses campos.
- Problemas encontrados e como foram corrigidos.
- Dificuldades de cada integrante.

## Checklist
- [x] Pacotes ui, data, data.repository e viewmodel com arquivos.
- [x] Dois repositories, um por entidade.
- [x] DAOs reativos com Flow.
- [x] Dois ViewModels e UiStates com StateFlow.
- [x] Factories para os ViewModels.
- [ ] Compilar e executar no Android Studio.
- [ ] Testar CRUD e persistência.
- [ ] Inserir evidências reais e completar reflexão.
