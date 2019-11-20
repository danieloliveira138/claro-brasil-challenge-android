Claro Brasil Challenge - Android
===================

Utilizei a arquitetura MVVM, criando uma camada de repositório que tem comunicação com os dados vindos da Api, do banco de dados e sharedpreferences. A camada que controla os componentes visuais eu deixei a cargo de três activitys, cada uma com uma camada de viewModel que faz o intercâmbio do repositório e a view, além de concentrar as regras de negócio das telas.

Para tratar das chamadas assíncronas, tanto na api quanto no banco de dados, eu utilizei o Coroutines.

Utilizei libs para aumentar a produtividade como Anko e KTX.

A baixo esta a lista com as libs.

<img src="https://github.com/danieloliveira138/claro-brasil-challenge-android/tree/master/images/device-2019-11-20-143836.png" width="200" height="395"/> <img src="https://github.com/danieloliveira138/claro-brasil-challenge-android/tree/master/images/device-2019-11-20-143901.png" width="200" height="395"/> <img src="https://github.com/danieloliveira138/claro-brasil-challenge-android/tree/master/images/device-2019-11-20-143918.png" width="200" height="395" />
<img src="https://github.com/danieloliveira138/claro-brasil-challenge-android/tree/master/images/device-2019-11-20-143937.png" width="200" height="395" />

----------------------------

Libraries
----------------------------

- **Glide -> Download e exibição de imagens
- **Retrofit -> Chamadas a Api
- **Gson - json converter
- **OkHttp -> Logger
- **Koin -> injeção de dependências
- **Material Search Bar -> Pesquisa de filmes na toolbar
- **JodaTime -> Tratar datas
- **KTX -> Agilizar o desenvolvimento
- **Room -> Banco de dados
- **ExoPlayer -> Streamming de video
- **Espresso -> Automatização de testes.


----------

O que eu faria se tivesse mais tempo
-------------

#### Funcionalidades

 - Uso de fragments ao invés de colocar as views direto nas activitys. Escolhi essa abordagem para ganhar tempo, me arrependi no final.
 - Criado testes unitários para as respostas da api. O uso do coroutines é muito interessante, mas as informações como _response code_ e as exceptions das chamadas que dão erro são mais dificeis de tratar com essa abordagem.
 - Teria customizado mais o player, colocando volume na tela e mais elementos visuais. Achei bem tranquilo quanto a customização e muito boa a qualidade, eu utilizava o MediaPlayer, fiquei maravilhado com o Exo.
 - Feito mais testes automatizados, infelizmente corri para produzir as telas devido ao pouco tempo

#### Por fim

Agradeço imensamente a oportunidade, o e-mail com o desafio me foi enviado no sábado mas por um erro meu, só vi na segunda. Devido ao tempo, que ficou muito corrido por causa do trabalho, não tive tempo de pensar mais e reaproveitar melhor o código assim criando menos código repetitivo.
