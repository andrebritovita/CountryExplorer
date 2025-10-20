# 🌍 Country Explorer

<p align="center">   <img alt="Linguagem" src="https://img.shields.io/badge/Linguagem-Kotlin-7F52FF?style=for-the-badge&logo=kotlin">   <img alt="Arquitetura" src="https://img.shields.io/badge/Arquitetura-Clean%20%7C%20MVVM-blue?style=for-the-badge&logo=google-cloud">   <img alt="API" src="https://img.shields.io/badge/API-24%2B-brightgreen?style=for-the-badge&logo=android"> </p>

Country Explorer é um aplicativo Android nativo, desenvolvido em Kotlin com Jetpack Compose, como parte do desafio de certificação da comunidade DevSpace. O app consome a API pública REST Countries para oferecer uma plataforma de exploração de informações sobre países de todo o mundo.

O projeto foi construído seguindo os princípios da Clean Architecture e do padrão MVVM, demonstrando uma estrutura de código escalável e testável.

## Features

-   **Tela de Boas-Vindas:** Uma introdução amigável e moderna ao aplicativo, ativada por um botão de ação do usuário.
-   **Lista de Países:** Exibição de todos os países com bandeira, nome, capital e região.
-   **Busca em Tempo Real:** Filtro dinâmico da lista de países pelo nome.
-   **Filtro por Região:** Seleção de continentes (África, Américas, Ásia, etc.) para refinar a visualização.
-   **Tela de Detalhes:** Informações detalhadas sobre um país selecionado, incluindo população, idiomas, moedas e países vizinhos.

## :camera_flash: Screenshots
| | | | |
|:--------------------------------------------------------------------------------------------------:|:--------------------------------------------------------------------------------------------------:|:--------------------------------------------------------------------------------------------------:|:--------------------------------------------------------------------------------------------------:|
| <img src="https://github.com/user-attachments/assets/e7493367-bf4b-4ac4-a3e6-f4e5e4afae1b" width=180/> | <img src="https://github.com/user-attachments/assets/21a6dde7-4b15-4a71-827b-8202a1f7fe47" width=180/> | <img src="https://github.com/user-attachments/assets/2d0a4a63-9357-48df-9673-874513803ca9" width=180/> | <img src="https://github.com/user-attachments/assets/7025401d-6edd-485b-8f49-f6175348c8dd" width=180/> 

## :movie_camera: Demo

<img src="https://github.com/user-attachments/assets/7aa72f28-51d5-409f-a681-d69db30b1a9b" alt="Demonstração do EasyRecipes App" width="180">

*O GIF de demonstração pode demorar alguns segundos para carregar, pois é mais pesado que as imagens. Basta aguardar e ele aparecerá abaixo.*


## Tech Stack

-   **Linguagem:** 100% [Kotlin](https://kotlinlang.org/)
-   **UI:** [Jetpack Compose](https://developer.android.com/jetpack/compose) para uma interface declarativa e moderna.
-   **Arquitetura:** Clean Architecture com padrão MVVM (Model-View-ViewModel).
-   **Injeção de Dependência:** [Hilt](https://dagger.dev/hilt/) para gerenciar dependências e facilitar a testabilidade.
-   **Assincronismo:** [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) e StateFlow para gerenciamento de estado reativo.
-   **Rede:** [Retrofit](https://square.github.io/retrofit/) para consumir a API REST [REST Countries](https://restcountries.com/).
-   **Carregamento de Imagens:** [Coil](https://coil-kt.github.io/coil/) para carregar as bandeiras de forma eficiente.
-   **Navegação:** [Navigation Compose](https://developer.android.com/jetpack/compose/navigation) para gerenciar o fluxo entre as telas.
-   **Testes:** [JUnit](https://junit.org/junit5/) e [Mockito](https://site.mockito.org/) para testes unitários do ViewModel, garantindo a lógica de negócio.

## Estrutura do Projeto

O projeto segue os princípios da **Clean Architecture**, separando o código em três camadas principais para garantir desacoplamento, testabilidade e manutenibilidade.

-   **Camada `data`:** Contém a implementação do repositório, os DTOs (Data Transfer Objects) da API e a lógica de mapeamento (mappers) para os modelos de domínio.
-   **Di:** Módulos do Hilt que fornecem as dependências (Retrofit, Repository, etc).
-   **Camada `domain`:** Contém a lógica de negócio principal, os modelos de dados da aplicação (`CountryList`, `CountryDetail`), a interface do `Repository` e os `UseCases` (casos de uso).
-   **Camada `ui`:** Camada de apresentação, com as telas (Screens) em Compose, os ViewModels e componentes reutilizáveis.
-   **Navigation:** Lógica do grafo de navegação do Compose.

## Arquitetura

O Country Explorer foi estruturado com foco na separação de responsabilidades, testabilidade e escalabilidade.

### Implementação Atual:
- **UI (Compose Screens):** As telas (SplashScreen, CountryListScreen, CountryDetailScreen) são responsáveis apenas pela exibição dos dados e pela captura de eventos do usuário, delegando toda a lógica para os ViewModels.
- **ViewModel:** Atua como intermediário, chamando os UseCases e expondo o estado da UI através de um StateFlow encapsulado na classe Resource (Loading, Success, Error).
- **Use Cases:** Cada caso de uso (GetAllCountriesUseCase, GetCountryDetailUseCase, etc.) contém uma única regra de negócio, invocando os métodos da interface do Repository.
- **Repository:** A interface CountryRepository (na camada domain) define o contrato para a obtenção de dados, enquanto a CountryRepositoryImpl (na camada data) implementa esse contrato, consumindo a API e mapeando os resultados.
 
### Pontos-Chave da Arquitetura:
-  **Inversão de Dependência:** As camadas ui e data dependem das interfaces da camada de domínio, não de implementações concretas.
-  **Fluxo de Dados Unidirecional (UDF):** O estado flui do ViewModel para a UI, e os eventos fluem da UI para o ViewModel, criando um ciclo de dados previsível.
-  **Testabilidade:** A separação de camadas e o uso de injeção de dependência permitem testar os ViewModels e UseCases de forma isolada, mockando suas dependências.


## Como Executar o Projeto

O projeto não requer chaves de API.
1.  Clone o repositório: `git clone https://github.com/seu-usuario/CountryExplorer.git`
2.  Abra o projeto no Android Studio.
3.  Aguarde a sincronização do Gradle ser concluída.
4.  Execute o app em um emulador ou dispositivo físico (API 24+).

## Aprendizados
### Habilidades Técnicas:
- **Implementação de Clean Architecture:** Ganhei experiência prática na estruturação de um projeto em camadas (data, domain, ui), entendendo o fluxo de responsabilidades e a inversão de dependências.
- **Injeção de Dependência com Hilt:** Ganhei experiência prática na configuração e uso do Hilt para fornecer dependências em todas as camadas, desde o Retrofit até os ViewModels.
- **Gerenciamento de Estado Reativo:** Utilizei StateFlow e uma classe Resource selada para gerenciar os estados da UI (Loading, Success, Error) de forma reativa.
- **Escrita de Testes Unitários:** Aprendi a escrever testes para ViewModels, utilizando Mockito para mockar dependências e garantir que a lógica de apresentação funciona como esperado em diferentes cenários.
 
### Crescimento Pessoal e Profissional:
- **Visão de Produto:** A construção de um app do zero, desde a análise dos requisitos até o polimento final da UI, aprimorou minha visão do ciclo de desenvolvimento.
- **Organização e Boas Práticas:** A adesão a um fluxo de trabalho com Git e a uma arquitetura limpa me deu mais experiência prática como desenvolvedor.

## Ideias de Melhorias Futuras

-   **Offline First:** Implementar um banco de dados local com Room para permitir o uso do app sem conexão com a internet.
-   **Navegação por Fronteiras:** Permitir que o usuário clique nos países vizinhos na tela de detalhes para navegar até eles.
-   **Mais Opções de Filtros:** Implementar filtros por tamanho populacional.
-   **Tema Escuro:** Adicionar suporte completo ao tema escuro do sistema.
-   **Favoritos:** Marcar/desmarcar e visualizar lista de favoritos.


## License
```
The MIT License (MIT)
Copyright (c) [2025] [André Brito Vita]

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights to
use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
the Software, and to permit persons to whom the Software is furnished to do so,
subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
```
