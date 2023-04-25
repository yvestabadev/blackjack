package br.com.yvestaba.blackjack;

import br.com.yvestaba.blackjack.business.jogo.Jogo;

import java.util.Scanner;

import static br.com.yvestaba.blackjack.business.printer.FimDeJogoPrinter.fimDeJogo;
import static br.com.yvestaba.blackjack.business.printer.InicioJogoPrinter.iniciarJogo;
import static br.com.yvestaba.blackjack.business.printer.JogoPuxarPrinter.perguntarSeQuerPuxar;
import static br.com.yvestaba.blackjack.business.printer.QuantidadeJogadoresPrinter.perguntarQuantidadeJogadores;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        var scanner = new Scanner(System.in);
        System.out.println("Bem-vindo ao blackjack. Desenvolvido por Yves Taba");
        Thread.sleep(1000L);
        int qtdeJogadores = perguntarQuantidadeJogadores(scanner);
        Jogo jogo = iniciarJogo(qtdeJogadores);
        boolean temProximaRodada = true;
        while(temProximaRodada) {
            temProximaRodada = perguntarSeQuerPuxar(jogo, scanner);
        }
        Thread.sleep(1000L);
        fimDeJogo(jogo, scanner);
    }

}
