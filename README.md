# Relatorio Compilador Parte 1 - Analisador Léxico

## Pre requisitos

Foi usado para desenvolver esse compilador java 14, então talvez um Java de versao 14- talvez não executará de forma correta.

## Como executar 
Após build e com o .jar em mão que se localiza na pasta

```
/build/libs/compiler-1.0.2.jar
```

Todos os casos de teste encontrar na diretorio Resources da parte main

para executar um teste basta usar o comando 

```
java -jar build/libs/compiler-1.0.2.jar src/main/resources/test1
```

Caso queira executar com um caso de teste diferente do caso de teste dado no resources deverá passar o path para o arquivo para ser executado.

```
java -jar build/libs/compiler-1.0.2.jar $(path)
```

## Classes 
### Lexer

A classe Lexer.java é a parte do analisador lexico que lé o arquivo e char por char e retorna um token.

Caso for encontrado algum erro lexico, que nesse caso fora tratado apenas o comentário não fechado lançara um SyntaxError pois não finaliza o comentário.

### SymbolTable
A classe SymbolTable.java é a classe que reserva as palavras do dicionário e que em seguina define metodos para reserva palavras na tabela de symbolos.
Fora usado a classe HashTable por já indexar hashs diretamente e tratar a duplição de maneira automática.

### Token 
A classe token constroi um Token usando uma lexema e um indentificador de Tag (Tag é um enum das palavras reservadas onde para cada alocação de memória do jar gera
uma referencia na memória virtual nunca teremos identificadores iguais)

## Testes 

### Teste 1

Tabela de simbolos:
```
Key: and, Token: AND lex: and
Key: a, Token: ID lex: a
Key: b, Token: ID lex: b
Key: c, Token: ID lex: c
Key: begin, Token: BEGIN lex: begin
Key: write, Token: WRITE lex: write
Key: else, Token: ELSE lex: else
Key: integer, Token: INTEGER lex: integer
Key: init, Token: INIT lex: init
Key: read, Token: READ lex: read
Key: stop, Token: STOP lex: stop
Key: do, Token: DO lex: do
Key: while, Token: WHILE lex: while
Key: real, Token: REAL lex: real
Key: if, Token: IF lex: if
Key: result, Token: ID lex: result
Key: or, Token: OR lex: or
Key: is, Token: IS lex: is
Key: end, Token: END lex: end
Key: string, Token: STRING lex: string
```

Lista de tokens:
```
Token: INIT lex: init
Token: ID lex: a
Token: ID lex: ,
Token: ID lex: b
Token: ID lex: ,
Token: ID lex: $
Token: ID lex: c
Token: ID lex: ,
Token: IS lex: is
Token: INTEGER lex: integer
Token: ID lex: ;
Token: ID lex: result
Token: IS lex: is
Token: REAL lex: real
Token: ID lex: ;
Token: WRITE lex: write
Token: ID lex: (
Token: STRING lex: "Digite o valor de a:"
Token: READ lex: read
Token: ID lex: (
Token: ID lex: a
Token: ID lex: )
Token: ID lex: ;
Token: WRITE lex: write
Token: ID lex: (
Token: STRING lex: "Digite o valor de c:"
Token: READ lex: read
Token: ID lex: (
Token: ID lex: c
Token: ID lex: )
Token: ID lex: ;
Token: ID lex: b
Token: EQ lex: :=
Token: INTEGER lex: 10
Token: ID lex: result
Token: EQ lex: :=
Token: ID lex: (
Token: ID lex: a
Token: ID lex: *
Token: ID lex: c
Token: ID lex: )
Token: ID lex: /
Token: ID lex: (
Token: ID lex: b
Token: ID lex: +
Token: INTEGER lex: 5
Token: ID lex: -
Token: INTEGER lex: 345
Token: ID lex: )
Token: ID lex: ;
Token: WRITE lex: write
Token: ID lex: (
Token: STRING lex: "O resultado e:"
Token: ID lex: )
Token: ID lex: ;
Token: WRITE lex: write
Token: ID lex: (
Token: ID lex: result
Token: ID lex: )
Token: ID lex: ;
Token: STOP lex: stop
Token: ID lex: EOF
```
### Teste 2

Tabela de Simbolos
```
Key: and, Token: AND lex: and
Key: a, Token: ID lex: a
Key: b, Token: ID lex: b
Key: begin, Token: BEGIN lex: begin
Key: write, Token: WRITE lex: write
Key: else, Token: ELSE lex: else
Key: integer, Token: INTEGER lex: integer
Key: init, Token: INIT lex: init
Key: read, Token: READ lex: read
Key: stop, Token: STOP lex: stop
Key: do, Token: DO lex: do
Key: while, Token: WHILE lex: while
Key: real, Token: REAL lex: real
Key: if, Token: IF lex: if
Key: b_1, Token: ID lex: b_1
Key: b_2, Token: ID lex: b_2
Key: valor, Token: ID lex: valor
Key: or, Token: OR lex: or
Key: b1, Token: ID lex: b1
Key: is, Token: IS lex: is
Key: end, Token: END lex: end
Key: Write, Token: ID lex: Write
Key: string, Token: STRING lex: string
```
Lista de Tokens
```
Token: ID lex: a
Token: ID lex: ,
Token: ID lex: _
Token: ID lex: valor
Token: ID lex: ,
Token: ID lex: b_1
Token: ID lex: ,
Token: ID lex: b_2
Token: ID lex: :
Token: INTEGER lex: integer
Token: ID lex: ;
Token: INIT lex: init
Token: WRITE lex: write
Token: ID lex: (
Token: STRING lex: "Entre com o valor de a:"
Token: ID lex: )
Token: ID lex: ;
Token: READ lex: read
Token: ID lex: (
Token: ID lex: a
Token: ID lex: )
Token: ID lex: ;
Token: ID lex: b_1
Token: EQ lex: :=
Token: ID lex: a
Token: ID lex: *
Token: ID lex: a
Token: ID lex: ;
Token: WRITE lex: write
Token: ID lex: (
Token: STRING lex: "O valor de b1 e:"
Token: ID lex: )
Token: ID lex: ;
Token: WRITE lex: write
Token: ID lex: (
Token: ID lex: b_1
Token: ID lex: )
Token: ID lex: ;
Token: ID lex: b_2
Token: ID lex: =
Token: ID lex: b
Token: ID lex: +
Token: ID lex: a
Token: ID lex: /
Token: INTEGER lex: 2
Token: ID lex: *
Token: ID lex: (
Token: ID lex: a
Token: ID lex: +
Token: INTEGER lex: 5
Token: ID lex: )
Token: ID lex: ;
Token: WRITE lex: write
Token: ID lex: (
Token: STRING lex: "O valor de b1 e:"
Token: ID lex: )
Token: ID lex: ;
Token: ID lex: Write
Token: ID lex: (
Token: ID lex: b1
Token: ID lex: )
Token: ID lex: ;
Token: STOP lex: stop
Token: ID lex: EOF
```
### Teste 3

Tabela de Simbolos
```
Key: Calculo, Token: ID lex: Calculo
Key: and, Token: AND lex: and
Key: de, Token: ID lex: de
Key: begin, Token: BEGIN lex: begin
Key: write, Token: WRITE lex: write
Key: else, Token: ELSE lex: else
Key: integer, Token: INTEGER lex: integer
Key: init, Token: INIT lex: init
Key: read, Token: READ lex: read
Key: stop, Token: STOP lex: stop
Key: do, Token: DO lex: do
Key: while, Token: WHILE lex: while
Key: soma, Token: ID lex: soma
Key: real, Token: REAL lex: real
Key: if, Token: IF lex: if
Key: qts, Token: ID lex: qts
Key: or, Token: OR lex: or
Key: media, Token: ID lex: media
Key: is, Token: IS lex: is
Key: idade, Token: ID lex: idade
Key: end, Token: END lex: end
Key: cont, Token: ID lex: cont
Key: INIT, Token: ID lex: INIT
Key: int, Token: ID lex: int
Key: STOP, Token: ID lex: STOP
Key: string, Token: STRING lex: string
```
Lista de Tokens

```
Token: ID lex: Calculo
Token: ID lex: de
Token: ID lex: idade
Token: ID lex: INIT
Token: ID lex: cont
Token: IS lex: is
Token: ID lex: int
Token: ID lex: ;
Token: ID lex: media
Token: ID lex: ,
Token: ID lex: idade
Token: ID lex: ,
Token: ID lex: soma
Token: IS lex: is
Token: INTEGER lex: integer
Token: ID lex: ;
Token: BEGIN lex: begin
Token: ID lex: cont
Token: EQ lex: :=
Token: INTEGER lex: 5
Token: ID lex: ;
Token: ID lex: soma
Token: EQ lex: :=
Token: INTEGER lex: 0
Token: ID lex: ;
Token: DO lex: do
Token: WHILE lex: while
Token: ID lex: (
Token: ID lex: cont
Token: GT lex: >
Token: INTEGER lex: 0
Token: ID lex: )
Token: ID lex: media
Token: EQ lex: :=
Token: ID lex: soma
Token: ID lex: /
Token: ID lex: qts
Token: WRITE lex: write
Token: ID lex: (
Token: STRING lex: "Media: "
Token: ID lex: )
Token: ID lex: ;
Token: WRITE lex: write
Token: ID lex: (
Token: ID lex: media
Token: ID lex: )
Token: ID lex: ;
Token: ID lex: STOP
Token: ID lex: EOF
```

### Teste 4

Tabela de Simbolos

```
Key: total, Token: ID lex: total
Key: and, Token: AND lex: and
Key: a, Token: ID lex: a
Key: begin, Token: BEGIN lex: begin
Key: write, Token: WRITE lex: write
Key: else, Token: ELSE lex: else
Key: integer, Token: INTEGER lex: integer
Key: init, Token: INIT lex: init
Key: i, Token: ID lex: i
Key: nome, Token: ID lex: nome
Key: j, Token: ID lex: j
Key: read, Token: READ lex: read
Key: k, Token: ID lex: k
Key: stop, Token: STOP lex: stop
Key: do, Token: DO lex: do
Key: while, Token: WHILE lex: while
Key: real, Token: REAL lex: real
Key: if, Token: IF lex: if
Key: I, Token: ID lex: I
Key: or, Token: OR lex: or
Key: is, Token: IS lex: is
Key: end, Token: END lex: end
Key: string, Token: STRING lex: string
```
Lista de Tokens

```
Token: INIT lex: init
Token: ID lex: i
Token: ID lex: ,
Token: ID lex: j
Token: ID lex: ,
Token: ID lex: k
Token: ID lex: ,
Token: ID lex: @
Token: ID lex: total
Token: IS lex: is
Token: INTEGER lex: integer
Token: ID lex: ;
Token: ID lex: nome
Token: IS lex: is
Token: STRING lex: string
Token: WRITE lex: write
Token: ID lex: (
Token: STRING lex: "Digite o seu nome: "
Token: ID lex: )
Token: ID lex: ;
Token: READ lex: read
Token: ID lex: (
Token: ID lex: nome
Token: ID lex: )
Token: ID lex: ;
Token: WRITE lex: write
Token: ID lex: (
Token: STRING lex: "Digite um valor inteiro: 
Token: ID lex: )
Token: ID lex: ;
Token: READ lex: read
Token: ID lex: (
Token: ID lex: I
Token: ID lex: )
Token: ID lex: ;
Token: ID lex: k
Token: EQ lex: :=
Token: ID lex: i
Token: ID lex: *
Token: ID lex: (
Token: INTEGER lex: 5
Token: ID lex: -
Token: ID lex: i
Token: ID lex: *
Token: INTEGER lex: 50
Token: ID lex: /
Token: INTEGER lex: 10
Token: ID lex: ;
Token: ID lex: j
Token: EQ lex: :=
Token: ID lex: i
Token: ID lex: *
Token: INTEGER lex: 10
Token: ID lex: ;
Token: ID lex: k
Token: EQ lex: :=
Token: ID lex: i
Token: ID lex: *
Token: ID lex: j
Token: ID lex: /
Token: ID lex: k
Token: ID lex: ;
Token: ID lex: k
Token: EQ lex: :=
Token: INTEGER lex: 4
Token: ID lex: +
Token: ID lex: a
Token: ID lex: $
Token: ID lex: ;
Token: WRITE lex: write
Token: ID lex: (
Token: ID lex: nome
Token: ID lex: )
Token: ID lex: ;
Token: WRITE lex: write
Token: ID lex: (
Token: STRING lex: ", os números gerados sao: "
Token: ID lex: )
Token: ID lex: ;
Token: WRITE lex: write
Token: ID lex: (
Token: ID lex: i
Token: ID lex: )
Token: ID lex: ;
Token: WRITE lex: write
Token: ID lex: (
Token: ID lex: j
Token: ID lex: )
Token: ID lex: ;
Token: WRITE lex: write
Token: ID lex: (
Token: ID lex: k
Token: ID lex: )
Token: ID lex: ;
Token: ID lex: EOF
```

### Teste 5

Tabela de Simbolos

```
Key: total, Token: ID lex: total
Key: and, Token: AND lex: and
Key: a, Token: ID lex: a
Key: begin, Token: BEGIN lex: begin
Key: write, Token: WRITE lex: write
Key: else, Token: ELSE lex: else
Key: integer, Token: INTEGER lex: integer
Key: i, Token: ID lex: i
Key: init, Token: INIT lex: init
Key: nome, Token: ID lex: nome
Key: j, Token: ID lex: j
Key: read, Token: READ lex: read
Key: k, Token: ID lex: k
Key: stop, Token: STOP lex: stop
Key: do, Token: DO lex: do
Key: while, Token: WHILE lex: while
Key: soma, Token: ID lex: soma
Key: real, Token: REAL lex: real
Key: if, Token: IF lex: if
Key: I, Token: ID lex: I
Key: or, Token: OR lex: or
Key: is, Token: IS lex: is
Key: altura, Token: ID lex: altura
Key: end, Token: END lex: end
Key: cont, Token: ID lex: cont
Key: string, Token: STRING lex: string
```

Lista de tokens

```
Token: INIT lex: init
Token: WRITE lex: write
Token: ID lex: (
Token: STRING lex: "Altura:" 
Token: ID lex: )
Token: ID lex: ;
Token: READ lex: read
Token: ID lex: (
Token: ID lex: altura
Token: ID lex: )
Token: ID lex: ;
Token: ID lex: soma
Token: EQ lex: :=
Token: ID lex: soma
Token: ID lex: +
Token: ID lex: altura
Token: ID lex: ;
Token: ID lex: cont
Token: EQ lex: :=
Token: ID lex: cont
Token: ID lex: -
Token: INTEGER lex: 1
Token: ID lex: ;
Token: ID lex: i
Token: ID lex: ,
Token: ID lex: j
Token: ID lex: ,
Token: ID lex: k
Token: ID lex: ,
Token: ID lex: @
Token: ID lex: total
Token: IS lex: is
Token: INTEGER lex: integer
Token: ID lex: ;
Token: ID lex: nome
Token: IS lex: is
Token: STRING lex: string
Token: WRITE lex: write
Token: ID lex: (
Token: STRING lex: "Digite o seu nome: "
Token: ID lex: )
Token: ID lex: ;
Token: READ lex: read
Token: ID lex: (
Token: ID lex: nome
Token: ID lex: )
Token: ID lex: ;
Token: WRITE lex: write
Token: ID lex: (
Token: STRING lex: "Digite um valor inteiro: 
Token: ID lex: )
Token: ID lex: ;
Token: READ lex: read
Token: ID lex: (
Token: ID lex: I
Token: ID lex: )
Token: ID lex: ;
Token: ID lex: k
Token: EQ lex: :=
Token: ID lex: i
Token: ID lex: *
Token: ID lex: (
Token: INTEGER lex: 5
Token: ID lex: -
Token: ID lex: i
Token: ID lex: *
Token: INTEGER lex: 50
Token: ID lex: /
Token: INTEGER lex: 10
Token: ID lex: ;
Token: ID lex: j
Token: EQ lex: :=
Token: ID lex: i
Token: ID lex: *
Token: INTEGER lex: 10
Token: ID lex: ;
Token: ID lex: k
Token: EQ lex: :=
Token: ID lex: i
Token: ID lex: *
Token: ID lex: j
Token: ID lex: /
Token: ID lex: k
Token: ID lex: ;
Token: ID lex: k
Token: EQ lex: :=
Token: INTEGER lex: 4
Token: ID lex: +
Token: ID lex: a
Token: ID lex: $
Token: ID lex: ;
Token: WRITE lex: write
Token: ID lex: (
Token: ID lex: nome
Token: ID lex: )
Token: ID lex: ;
Token: WRITE lex: write
Token: ID lex: (
Token: STRING lex: ", os números gerados sao: "
Token: ID lex: )
Token: ID lex: ;
Token: WRITE lex: write
Token: ID lex: (
Token: ID lex: i
Token: ID lex: )
Token: ID lex: ;
Token: WRITE lex: write
Token: ID lex: (
Token: ID lex: j
Token: ID lex: )
Token: ID lex: ;
Token: WRITE lex: write
Token: ID lex: (
Token: ID lex: k
Token: ID lex: )
Token: ID lex: ;
Token: ID lex: EOF
```
### Teste 6

Tabela de Simbolos
```
Key: and, Token: AND lex: and
Key: a, Token: ID lex: a
Key: b, Token: ID lex: b
Key: c, Token: ID lex: c
Key: begin, Token: BEGIN lex: begin
Key: write, Token: WRITE lex: write
Key: else, Token: ELSE lex: else
Key: integer, Token: INTEGER lex: integer
Key: init, Token: INIT lex: init
Key: banda, Token: ID lex: banda
Key: read, Token: READ lex: read
Key: stop, Token: STOP lex: stop
Key: do, Token: DO lex: do
Key: while, Token: WHILE lex: while
Key: real, Token: REAL lex: real
Key: if, Token: IF lex: if
Key: or, Token: OR lex: or
Key: is, Token: IS lex: is
Key: end, Token: END lex: end
Key: maior, Token: ID lex: maior
Key: string, Token: STRING lex: string
```
Lista de Tokens
```
Token: INIT lex: init
Token: ID lex: a
Token: ID lex: ,
Token: ID lex: b
Token: ID lex: ,
Token: ID lex: c
Token: ID lex: ,
Token: ID lex: maior
Token: IS lex: is
Token: INTEGER lex: integer
Token: ID lex: ;
Token: WRITE lex: write
Token: ID lex: (
Token: STRING lex: "Digite uma idade: "
Token: ID lex: )
Token: ID lex: ;
Token: READ lex: read
Token: ID lex: (
Token: ID lex: a
Token: ID lex: )
Token: ID lex: ;
Token: WRITE lex: write
Token: ID lex: (
Token: STRING lex: "Digite outra idade: "
Token: ID lex: )
Token: ID lex: ;
Token: READ lex: read
Token: ID lex: (
Token: ID lex: b
Token: ID lex: )
Token: ID lex: ;
Token: WRITE lex: write
Token: ID lex: (
Token: STRING lex: "Digite mais uma idade: "
Token: ID lex: )
Token: ID lex: ;
Token: READ lex: read
Token: ID lex: (
Token: ID lex: c
Token: ID lex: ;
Token: ID lex: maior
Token: EQ lex: :=
Token: INTEGER lex: 0
Token: ID lex: ;
Token: IF lex: if
Token: ID lex: (
Token: ID lex: a
Token: GT lex: >
Token: ID lex: banda
Token: GT lex: >
Token: ID lex: c
Token: ID lex: )
Token: ID lex: maior
Token: EQ lex: :=
Token: ID lex: a
Token: ID lex: ;
Token: ELSE lex: else
Token: IF lex: if
Token: ID lex: (
Token: ID lex: b
Token: GT lex: >
Token: ID lex: c
Token: ID lex: )
Token: ID lex: maior
Token: EQ lex: :=
Token: ID lex: b
Token: ID lex: ;
Token: ELSE lex: else
Token: ID lex: maior
Token: EQ lex: :=
Token: ID lex: c
Token: ID lex: ;
Token: WRITE lex: write
Token: ID lex: (
Token: STRING lex: "Maior idade: "
Token: ID lex: )
Token: ID lex: ;
Token: WRITE lex: write
Token: ID lex: (
Token: ID lex: maior
Token: ID lex: )
Token: ID lex: ;
Token: END lex: end
Token: ID lex: EOF
```
### Teste 7
```
Tabela de Simbolos
Key: and, Token: AND lex: and
Key: a, Token: ID lex: a
Key: b, Token: ID lex: b
Key: c, Token: ID lex: c
Key: begin, Token: BEGIN lex: begin
Key: write, Token: WRITE lex: write
Key: else, Token: ELSE lex: else
Key: integer, Token: INTEGER lex: integer
Key: init, Token: INIT lex: init
Key: read, Token: READ lex: read
Key: stop, Token: STOP lex: stop
Key: do, Token: DO lex: do
Key: while, Token: WHILE lex: while
Key: real, Token: REAL lex: real
Key: if, Token: IF lex: if
Key: or, Token: OR lex: or
Key: is, Token: IS lex: is
Key: end, Token: END lex: end
Key: maior, Token: ID lex: maior
Key: string, Token: STRING lex: string
```
Lista de tokens
```
Token: INIT lex: init
Token: ID lex: a
Token: ID lex: ,
Token: ID lex: b
Token: ID lex: ,
Token: ID lex: c
Token: ID lex: ,
Token: ID lex: maior
Token: IS lex: is
Token: INTEGER lex: integer
Token: ID lex: ;
Token: WRITE lex: write
Token: ID lex: (
Token: STRING lex: "Digite uma idade: "
Token: ID lex: )
Token: ID lex: ;
Token: READ lex: read
Token: ID lex: (
Token: ID lex: a
Token: ID lex: )
Token: ID lex: ;
Token: WRITE lex: write
Token: ID lex: (
Token: STRING lex: "Digite outra idade: "
Token: ID lex: )
Token: ID lex: ;
Token: READ lex: read
Token: ID lex: (
Token: ID lex: b
Token: ID lex: )
Token: ID lex: ;
Token: WRITE lex: write
Token: ID lex: (
Token: STRING lex: "Digite mais uma idade: "
Token: ID lex: )
Token: ID lex: ;
Token: READ lex: read
Token: ID lex: (
Token: ID lex: c
Token: ID lex: ;
Token: END lex: end
Token: ID lex: EOF
```
