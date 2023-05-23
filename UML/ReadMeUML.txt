-----------------
|   Package 1   |
-----------------

- Classe Grah

Graph: inicializa a classe Graph
1º buildGraph: gera um gráfico aleatoriamente (-r)
2º buidGraph: gera o gráfico do ficheiro (-f)
printGraph: print do gráfico
getNodes: retorna o nº de nós
getEdges: retorna o nº de arestas
clearGraph: limpa o gráfico (apaga o que foi feito no buildGraph)
deleteGraph: elimina o gráfico
isHamiltonian: o gráfico tem um ciclo hamiltoniano? 0 ou 1
setPheromoneLevel: quando a formiga encontra um ciclo hamiltoniano dá update ao nível de feromonas das arestas
getWeight: retorna o peso da aresta

- Classe Edge

Edge: inicializa a classe Edge

-----------------
|   Package 2   |
-----------------

- Classe Ant

Ant: inicializa a classe Ant
isHamiltonian: o path é hamiltonian? 0 ou 1
restart: depois da formiga acabar o ciclo, reinicializa os atributos
move: a formiga escolhe a próxima aresta
setPheromoneLevel: quando a formiga encontra um ciclo hamiltoniano dá update ao nível de feromonas das arestas
