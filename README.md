# Weather-App
Aplicativo de previsão do tempo com arquitetura de software limpa, utilizando o que há de mais recente nos componentes da arquitetura Android, Kotlin Coroutines e outras bibliotecas modernas.

O usuário insere uma cidade. Caso a mesma não seja localizada o aplicativo avisa através de uma mensagem Toast, e caso encontrar o usuário navega para uma outra tela com a previsão do tempo para o dia seguinte.

Componentes de arquitetura usados pelo aplicativo:

- Navigation Component
- ViewModel
- Repositório
- LiveData
- Retrofit
- Hilt (injeção de dependências)
- Lifecycle
- Mockito para testagem do display e chamada a API

O aplicativo segue a arquitetura "limpa" e foi dividido em camadas por recurso.

O fluxo geral do aplicativo é:

View se comunica com um ViewModel, então ViewModel executa um UseCase que acessa um repositório (fazendo então a chamada da API). O resultado é trazido de volta à visualização por meio do fluxo de LiveData ou pela retomada de uma coroutine que foi iniciada pela visualização dentro de um escopo de ciclo de vida.

O aplicativo está usando o Hilt como uma estrutura de injeção de dependência. Cada recurso declara seu próprio módulo que é usado para a construção de todo o gráfico de dependência.

NOTAS:

- Para os testes, o framework "Mockito" foi selecionado pela sua estrutura eficaz e capacidade de cria esboço dos objetos necessários ao invés de instanciá-los de fato, dando controle total sobre esse objeto e suas interações.
