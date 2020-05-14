# Copa do mundo no Brasil.
A copa do mundo sediada no Brasil em 2014, contou com 12 cidade sedes dos jogos. Marcos é um brasileiro que mora na Nigéria, ele ama futebol e decidiu assistir todos os jogos da Nigéria durante a fase de grupo. Porém Marcos teve problemas, o primeiro jogo que ele gostaria de assistir iria acontecer na cidade de Curitiba e a cidade não tem aeroporto internacional. Então Marcos deve desembarcar em algum aeroporto internacional (São Paulo, Rio de Janeiro, Brasília ou Natal)  para depois conseguir ir até Curitiba. Marcos então decide desembarcar em São Paulo.
Por causa do grande volumes de voos nacionais, a ANAC (Agência Nacional de Aviação Civil) determinou que os aviões que trafegavam entre as cidades sedes dos jogos só poderiam ficar entre duas cidades específicas, ou seja, se um avião sai de São Paulo rumo a Curitiba, ele só poderá trafegar entre essas duas cidades indo e voltando. O mapa das rotas aéreas nacionais entre as cidades sedes dos jogos da copa com seus respectivos preços da passagem é representado abaixo:

![1](https://user-images.githubusercontent.com/44576048/81983165-4b466c80-9609-11ea-8447-7cdf65a32401.png)

As próximas cidades visitadas por Marcos para assistir os jogos eram: Porto Alegre, Cuiabá e Brasília, respectivamente. Antes de ir embora para Nigéria, Marcos desejava visitar sua família em Fortaleza e por fim ir ao aeroporto internacional de São Paulo.
Para conseguir resolver esse problema e fazer com que Marcos gaste o mínimo possível com as passagens aéreas, representamos o problema na forma de Grafos. Grafos são compostos por vértices (representados pelas cidades sedes dos jogos) e arestas (representadas pelos voos entre às cidades sedes). Além disso, as arestas possuem pesos, que são representados pelos preços das passagens de cada voo e todas elas são bidirecionais. Diante disso, o Grafo que ilustra o problema é apresentado na figura a seguir: 

![3](https://user-images.githubusercontent.com/44576048/81983290-83e64600-9609-11ea-9877-1dc3cba13f81.png)

Para descobrir qual o caminho que ele terá que fazer para gastar o mínimo possível, utilizamos o algoritmo de Djikstra, onde dadas as informações de todos os voos e os pontos de origem e destino, o algoritmo informará qual o caminho mais barato. Às entradas no algoritmo são representadas nas colunas 1 e 2 da tabela a seguir. Na coluna 3 é retornado qual o caminho mais barato entre as entradas informadas. Na coluna 4 é apresentado o preço do caminho.

![WhatsApp Image 2020-05-14 at 17 38 54](https://user-images.githubusercontent.com/44576048/81983467-d4f63a00-9609-11ea-9469-5c6ed3eed214.jpeg)

https://github.com/freddomingues/ProgramacaoDinamica/blob/master/Grafo/src/CopaDoMundo.java
