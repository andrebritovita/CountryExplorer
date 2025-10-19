# 🌍 Country Explorer

Desafio técnico desenvolvido para a Certificação da comunidade DevSpace. O aplicativo permite que os usuários explorem informações sobre países de todo o mundo de forma interativa e visualmente agradável.

## ✨ Funcionalidades

-   **Tela de Boas-Vindas:** Uma introdução amigável e moderna ao aplicativo.
-   **Lista de Países:** Exibição de todos os países com bandeira, nome, capital e região.
-   **Busca em Tempo Real:** Filtro dinâmico da lista de países pelo nome.
-   **Filtro por Região:** Seleção de continentes (África, Américas, Ásia, etc.) para refinar a visualização.
-   **Tela de Detalhes:** Informações completas sobre um país selecionado, incluindo população, idiomas, moedas e países vizinhos.

## 🛠️ Tecnologias e Bibliotecas

-   **Linguagem:** 100% [Kotlin](https://kotlinlang.org/)
-   **UI:** [Jetpack Compose](https://developer.android.com/jetpack/compose) para uma interface declarativa e moderna.
-   **Arquitetura:** Clean Architecture com padrão MVVM (Model-View-ViewModel).
-   **Injeção de Dependência:** [Hilt](https://dagger.dev/hilt/) para gerenciar dependências e facilitar a testabilidade.
-   **Assincronismo:** [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) e Flow para operações assíncronas.
-   **Rede:** [Retrofit](https://square.github.io/retrofit/) para consumir a API REST [REST Countries](https://restcountries.com/).
-   **Carregamento de Imagens:** [Coil](https://coil-kt.github.io/coil/) para carregar as bandeiras de forma eficiente.
-   **Navegação:** [Navigation Compose](https://developer.android.com/jetpack/compose/navigation) para gerenciar o fluxo entre as telas.
-   **Testes:** [JUnit](https://junit.org/junit5/) e [Mockito](https://site.mockito.org/) para testes unitários do ViewModel, garantindo a lógica de negócio.

## 🏛️ Arquitetura

O projeto segue os princípios da **Clean Architecture**, separando o código em três camadas principais para garantir desacoplamento, testabilidade e manutenibilidade.

-   **Camada `data`:** Responsável por obter os dados da API (Retrofit) e mapeá-los para os modelos do domínio. Contém a implementação do `Repository`.
-   **Camada `domain`:** Contém a lógica de negócio principal, os modelos de dados da aplicação (`CountryList`, `CountryDetail`), a interface do `Repository` e os `UseCases` (casos de uso).
-   **Camada `ui`:** Responsável pela apresentação. Usa o padrão **MVVM**, onde os `ViewModels` preparam os dados para as `Screens` (feitas em Jetpack Compose), que são reativas às mudanças de estado.

## 🚀 Como Executar o Projeto

1.  Clone o repositório: `git clone https://github.com/seu-usuario/CountryExplorer.git`
2.  Abra o projeto no Android Studio.
3.  Aguarde a sincronização do Gradle ser concluída.
4.  Execute o app em um emulador ou dispositivo físico.

## 🔮 Melhorias Futuras

-   **Offline First:** Implementar um banco de dados local com Room para permitir o uso do app sem conexão com a internet.
-   **Navegação por Fronteiras:** Permitir que o usuário clique nos países vizinhos na tela de detalhes para navegar até eles.
-   **Tema Escuro:** Adicionar suporte completo ao tema escuro do sistema.