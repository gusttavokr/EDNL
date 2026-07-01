# Implementação de Dijkstra e A* em Grafo (Labirinto)

Este projeto implementa dois algoritmos clássicos de busca em grafos aplicados à resolução de um labirinto representado por uma matriz:

- Dijkstra (caminho mínimo sem heurística)
- A* (caminho mínimo com heurística)

---

## Representação do problema

O labirinto é lido de um arquivo `.dat` e convertido em uma matriz:

- `1` → célula caminhável (vira vértice)
- `0` → parede (não entra no grafo)

Cada posição válida da matriz vira um **vértice do grafo**, com coordenadas `(x, y)`.

Os vértices são conectados por arestas com custo `1` nas direções:

- cima
- baixo
- esquerda
- direita

---

## Estrutura do Grafo

Cada vértice possui:

- um elemento (identificador)
- lista de arestas
- coordenadas `(x, y)`
- estado de processamento (`processado`)


# Algoritmo de Dijkstra

## Objetivo

Encontrar o menor caminho entre origem e destino **sem heurística**.


## Funcionamento

O algoritmo segue estas etapas:

### 1. Inicialização

- Distância da origem = 0
- Demais vértices = infinito
- Antecessores = null

---

### 2. Relaxamento

Para cada vizinho do vértice atual:


novoG = custo_atual + custo_aresta


Se `novoG` for menor que o valor armazenado:

- atualiza distância
- define antecessor

---

### 3. Escolha do próximo vértice

Sempre escolhe o vértice não processado com:

- menor custo acumulado (g)

---

### 4. Finalização

O processo continua até:

- todos os vértices serem processados
ou
- o destino ser alcançado

---

## Reconstrução do caminho

O caminho final é reconstruído usando o vetor de antecessores:

- começa no destino
- volta até a origem
- inverte a lista

---

## Resultado

Retorna uma lista:


origem → ... → destino


---

# Algoritmo A* (the_star)

## Objetivo

Encontrar o menor caminho de forma mais eficiente usando uma heurística.

---

## Diferença principal

O A* usa a função:


f(n) = g(n) + h(n)


Onde:

- `g(n)` = custo real da origem até o nó atual
- `h(n)` = estimativa do custo até o destino (heurística)

---

## Heurística usada

Distância de Manhattan:


h(n) = |x1 - x2| + |y1 - y2|


Essa heurística funciona bem para movimentos em grade.

---

## Funcionamento do A*

### 1. Inicialização

- origem recebe g = 0
- demais vértices recebem infinito
- origem é adicionada na lista `abertos`

---

### 2. Lista aberta

O algoritmo mantém uma lista chamada:

abertos

Ela contém os nós que ainda podem ser explorados.

---

### 3. Escolha do próximo nó

Sempre escolhe o vértice com menor valor:


f(n) = g(n) + h(n)


Esse é o nó mais promissor no momento.

---

### 4. Relaxamento

Para cada vizinho:

- calcula custo real (g novo)
- se for menor que o existente:
  - atualiza custo
  - define antecessor
  - adiciona na lista `abertos`

---

### 5. Encerramento

O algoritmo para quando:

- encontra o destino
ou
- não há mais nós abertos

---

## Reconstrução do caminho

Assim como no Dijkstra:

- usa `antecessores`
- começa no destino
- volta até a origem
- inverte a lista

---

## Resultado

Retorna o caminho otimizado:


origem → caminho otimizado → destino


---

# Comparação entre os algoritmos

| Algoritmo | Usa heurística | Estratégia |
|-----------|----------------|------------|
| Dijkstra  | Não            | Custo real |
| A*        | Sim            | Custo + estimativa |

---

# Conclusão

- Dijkstra garante menor caminho, mas explora mais nós
- A* é mais eficiente porque direciona a busca com heurística
- Ambos usam o mesmo grafo e estrutura de antecessores