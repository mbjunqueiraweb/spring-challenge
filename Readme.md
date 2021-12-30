## Mercado Livre Brasil

## Bootcamp - Meli Wave 4 - Digital House

### Spring Challenge

#### Grupo 11

- [Stéphanie da Silva Leal](https://github.com/stephleal)
- [Lucas Garcia Macchione Prudente Correa](https://github.com/LucasGarcia97)
- [Mauro Bergonzoni Junqueira](https://github.com/mbjunqueiraweb)
- [Iberê Abondanza Kuhlmann](https://github.com/ikuhlmann-meli)
- [Matheus Santos Alencar](https://github.com/matheussalencar)

#### Informações úteis

- [Modelo do projeto](https://app.diagrams.net/#G1vXStuYDfln40WaJIKDFrRCuX_WlIKDeN)
- [Enunciado](https://drive.google.com/file/d/15zguFhJ8odRtqGYfxuYro42STlYCTpA7/view)
- [Definições](https://drive.google.com/file/d/1XXDABy-lEhF-MGQkw7Ty91WLscVJ6aQ_/view)
- [Lista de Produtos para popular](https://docs.google.com/spreadsheets/d/1VbpRtZXw6DiYoA7VETG9ezf39ghlsCq4EN0drRTxuS4/edit#gid=0)

#### Documentação para consumir a API

- Uma lista de todos os produtos disponíveis.

``` /products ```

- Uma lista de produtos filtrados por categoria.

``` /products/articles? ```

#### Parâmetros permitidos:
    
String ```name```;
String ```category```;
String ```brand```;
Number ```price```;
Number ```quantity```;
Boolean ```freeShipping```;
String ```prestige``` * | ** | *** | **** | ***** |
Number ```order``` 0 | 1 | 2 | 3 |

paramOrder Description
- 0 - Alfabético crescente.
- 1 - Alfabético decrescente.
- 2 - Maior a menor preço.
- 3 - Menor a maior preço.

#### Exemplos de queries: 
    
``` /products/articles?name=productName&freeShipping=true```