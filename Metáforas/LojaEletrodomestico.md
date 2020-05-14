# Loja Eletrodomésticos
O gerente de uma das maiores lojas de eletrodomésticos do Brasil decidiu comemorar o aniversário da loja de um jeito diferente. Através de um sorteio, um participante teria um carrinho de compras e 5 minutos para pegar qualquer item da loja. Marília se inscreveu e foi a sorteada. Quando foi chamada para participar, ela recebeu um carrinho de compras que suportava um peso de 300kg e teve a sua disposição todos os itens da loja. Marília deseja selecionar os itens mais valiosos da loja de forma que a soma dos pesos de todos eles não ultrapasse a capacidade máxima do carrinho. A tabela a seguir está representando o peso e valor dos itens mais valiosos disponíveis na loja: 

![WhatsApp Image 2020-05-14 at 17 42 29](https://user-images.githubusercontent.com/44576048/81983828-577ef980-960a-11ea-8419-290cd8fcad8e.jpeg)

Para que Marília possa obter o maior lucro entre os itens disponíveis, podemos recorrer a combinatória e testar todas as combinações possíveis até encontrar a melhor delas. Entretanto, Marília tem apenas 5 minutos para testar todas as combinações possíveis e ir atrás dos itens, isso seria inviável. Essa alternativa apresentada é conhecida na técnica de projetos de algoritmos como Backtracking. Backtracking em muitos casos é muito útil, pois ele testa todas as combinações possíveis até encontrar a solução, mas em casos como esse que o tempo é limitado, essa técnica não é a melhor.
Para isso, temos outra técnica de projeto de algoritmos que é a Programação Dinâmica (PD). PD tem como principal objetivo obter a melhor solução (solução ótima) visando o melhor desempenho do algoritmo e por sua vez, levar menos tempo para encontrar a solução ótima. No problema apresentado acima, podemos dividir as informações em dois conjuntos (peso e valor) e submetê-lo ao algoritmo do problema da mochila. 

![WhatsApp Image 2020-05-14 at 17 43 44](https://user-images.githubusercontent.com/44576048/81983954-872e0180-960a-11ea-99ea-505d01f7da1e.jpeg)

Com isso, Marília terá às informações à tempo de quais itens ela deverá pegar para obter o maior lucro. Para esse caso, a solução ótima é apresentada na tabela a seguir:

![WhatsApp Image 2020-05-14 at 17 44 48](https://user-images.githubusercontent.com/44576048/81984066-a75dc080-960a-11ea-9381-b76d2ee663bc.jpeg)
