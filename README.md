<h1>Teste Blackjack</h1>
<p>Projeto concluído usando interatividade do Scanner do Java. Siga as instruções no console para jogar e validar.</p>
<h2>Regras usadas:</h2>
<ul>
<li>
Todos os que obterem BlackJack (um às e mais uma carta que vale 10) ganham.
</li>
<li>
A carta ÁS pode valer 1 ou 11 pontos. Sempre na contagem final é considerado a melhor pontuação possível.
</li>
<li>
O Dealer joga por útimo.
</li>
<li>
O Dealer deve puxar cartas até obter uma mão com um valor mínimo de 17.
</li>
<li>
Em caso de empate, o jogador não ganha nem perde.
</li>
<li>
Até 7 pessoas, além do Dealer, podem jogar ao mesmo tempo.
</li>
<li>
Após o jogador passar o valor de 21, automaticamente passa para o próximo jogador.
</li>
</ul>

<h2>Usando contêiner:</h2>
<ol>
<li>
Faça o build com Maven (aproveite para rodar os testes unitários!) com <i>mvn clean package</i>
</li>
<li>
Faça um build simples no Docker com <i>docker build .</i>
</li>
<li>
Use o contêiner gerado no modo interativo com <i>docker run -it [nome-ou-id]</i>
</li>
</ol>
