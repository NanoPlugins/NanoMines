# NanoAutoClicker

É um sistema de autoclick para o seu servidor, que tem como função atacar mobs de forma automática.

## Configuração

```yml
# nanoplugins.com | nanoplugins.com.br | nanoplugins.fun

#Definições
settings:
  fortune: true #Uso de fortuna (valor * fortuna)
  actionbar: true #Uso de actionbar
  message: false #Uso de mensagem no chat

#Mensagens
messages:
  actionbar: "&aMinerou um %block% &ae ganhou &7%money% &acoins! &d(%bonus%% | %fortune%)" #Actionbar ao minerar
  message: "&aMinerou um %block% &ae ganhou &f%money% &acoins! &d(%bonus%% | %fortune%)" #Mensagem no chat ao minerar

#Blocos
blocks:
  lapis: #Key (coloque o que quiser desde que não repita)
    type: 21 #Id do bloco
    data: 0 #Data do bloco
    name: "&9Minério de Lapis Azul" #Nome para aparecer nas mensagens
    money: 1000.0 #Dinheiro que dá ao minerar
  gold:
    type: 14
    data: 0
    name: "&6Minério de Ouro"
    money: 1000000.0

#Bonus
bonus:
  vip: #Key (coloque o que quiser desde que não repita)
    group: "vip" #Nome do grupo no plugin de permissões
    value: 1.3 #Bonus que é atribuido (100 * 1.3 = 130)
  vipiron:
    group: "vipferro"
    value: 1.4
```

## API

**Evento quando minera** <br>
`NanoMinesBreakEvent`
```java
@EventHandler  
public void onCall(NanoMinesBreakEvent event) {  
  System.out.println("BlockModel: " + event.getBlockModel());  
  System.out.println("Bonus: " + event.getBonus());
  System.out.println("Money: " + event.getMoney());
  System.out.println("Bloco: " + event.getBlock()); 
}
```
