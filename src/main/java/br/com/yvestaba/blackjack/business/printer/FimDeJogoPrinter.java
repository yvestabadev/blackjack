package br.com.yvestaba.blackjack.business.printer;

import br.com.yvestaba.blackjack.business.jogo.Jogador;
import br.com.yvestaba.blackjack.business.jogo.Jogo;
import br.com.yvestaba.blackjack.business.regras.StatusJogo;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static br.com.yvestaba.blackjack.Main.main;
import static java.util.Objects.nonNull;

public class FimDeJogoPrinter {

    private FimDeJogoPrinter(){

    }


    public static void fimDeJogo(Jogo jogo, Scanner scanner) throws InterruptedException {
        System.out.println("O jogo encerrou! Aguarde...");
        Thread.sleep(1500L);
        Map<StatusJogo, List<Jogador>> getByStatus = jogo.fimDeJogo();
        var vencedores = getByStatus.get(StatusJogo.GANHOU);
        var empatadores = getByStatus.get(StatusJogo.EMPATOU);
        var perdedores = getByStatus.get(StatusJogo.PERDEU);
        if(nonNull(vencedores)){
            System.out.println();
            System.out.println("Lista de quem venceu:");
            System.out.println(String.join(", ", vencedores.stream().map(j -> getNomeJogador(jogo, j)).toList()));
        }
        if(nonNull(empatadores)){
            System.out.println();
            System.out.println("Lista de quem leva a aposta de volta:");
            System.out.println(String.join(", ", empatadores.stream().map(j -> getNomeJogador(jogo, j)).toList()));
        }
        if(nonNull(perdedores)){
            System.out.println();
            System.out.println("Lista de quem perdeu:");
            System.out.println(String.join(", ", perdedores.stream().map(j -> getNomeJogador(jogo, j)).toList()));
        }

        Thread.sleep(5000L);
        System.out.println();
        System.out.println("Gostaria de jogar de novo?");
        System.out.println("Digite apenas números, 1 ou 2");
        System.out.println("1- Sim");
        System.out.println("2- Não");

        Integer check;
        while(true) {
            try{
                check = Integer.parseInt(scanner.next());
            } catch(NumberFormatException e){
                System.out.println();
                System.out.println("Digite apenas números, 1 ou 2");
                System.out.println("1- Jogar de novo");
                System.out.println("2- Não jogar");
                continue;
            }

            if(check != 1 && check != 2){
                System.out.println();
                System.out.println("Insira números válidos. 1 ou 2");
                System.out.println("1- Jogar de novo");
                System.out.println("2- Não jogar");
                continue;
            }
            if(check == 2){
                System.out.println("Tudo bem, até logo!");
                return;
            }
            main(null);
        }
    }

    private static String getNomeJogador(Jogo jogo, Jogador jogador) {
        int idJogador = jogo.getIdJogador(jogador);
        return idJogador == 0 ? "DEALER" : "JOGADOR " + idJogador;
    }
}
