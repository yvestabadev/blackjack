package br.com.yvestaba.blackjack.business.printer;

import br.com.yvestaba.blackjack.business.jogo.Apostador;
import br.com.yvestaba.blackjack.business.jogo.Jogador;
import br.com.yvestaba.blackjack.business.jogo.Jogo;
import br.com.yvestaba.blackjack.business.materiais.Carta;
import br.com.yvestaba.blackjack.business.regras.StatusMao;

import java.util.List;
import java.util.Scanner;

import static br.com.yvestaba.blackjack.business.regras.CalculadoraDePontos.calcularPorJogadorString;

public class JogoPuxarPrinter {

    /**
     *
     * @param jogo
     * @param scanner
     * @return se tem proxima rodada
     * @throws InterruptedException
     */
    public static boolean perguntarSeQuerPuxar(Jogo jogo, Scanner scanner) throws InterruptedException {
        String jogador = getNomeJogador(jogo);
        Jogador jogadorDaVez = jogo.getJogadorDaVez();

        System.out.println();
        System.out.println("Vez do " + jogador + ". Aguarde...");
        Thread.sleep(2000L);
        System.out.println();
        System.out.println("Sua pontuação é de " + calcularPorJogadorString(jogadorDaVez));
        Thread.sleep(1000L);
        System.out.println();
        System.out.println(jogador + ", digite apenas números. Escolha uma opção. ");
        System.out.println("1- puxar nova carta");
        System.out.println("2- passar a vez");
        Integer check;
        while(true) {
            try{
                check = Integer.parseInt(scanner.next());
            } catch(NumberFormatException e){
                System.out.println();
                System.out.println("Digite apenas números, 1 ou 2");
                System.out.println("1- puxar nova carta");
                System.out.println("2- passar a vez");
                continue;
            }

            if(check != 1 && check != 2){
                System.out.println();
                System.out.println("Insira números válidos. 1 ou 2");
                System.out.println("1- puxar nova carta");
                System.out.println("2- passar a vez");
                continue;
            }
            if(check == 2){
                boolean temProximaRodada = jogo.proximoPasso(false);
                verSePassouParaODealer(jogo, jogadorDaVez);
                return temProximaRodada;
            }
            boolean temProximaRodada = jogo.proximoPasso(true);
            List<Carta> mao = jogadorDaVez.getMao();
            System.out.println();
            System.out.println(jogador + " puxou " + mao.get(mao.size() - 1) + ". Aguarde...");
            Thread.sleep(2000L);
            if(StatusMao.getStatusMaoByJogador(jogadorDaVez) == StatusMao.BUST){
                System.out.println("Houve um Bust. Iniciando próxima rodada...");
            }
            verSePassouParaODealer(jogo, jogadorDaVez);
            return temProximaRodada;
        }
    }

    private static String getNomeJogador(Jogo jogo) {
        int idJogador = jogo.getIdJogador();
        return idJogador == 0 ? "DEALER" : "JOGADOR " + idJogador;
    }


    private static void verSePassouParaODealer(Jogo jogo, Jogador jogadorAnterior) throws InterruptedException {
        if(!(jogadorAnterior instanceof Apostador) && jogo.getJogadorDaVez() instanceof Apostador) {
            printPrimeiraMaoDealer(jogo.getJogadorDaVez());
        }
    }


    private static void printPrimeiraMaoDealer(Jogador jogadorDaVez) throws InterruptedException {
        Thread.sleep(1500L);
        System.out.println("O DEALER vai puxar até obter no mínimo 17");
        Thread.sleep(1000L);
        List<Carta> mao = jogadorDaVez.getMao();
        for(var carta : mao){
            System.out.println("O DEALER puxou " + carta);
            Thread.sleep(1000L);
        }
        if(StatusMao.getStatusMaoByJogador(jogadorDaVez) == StatusMao.BUST){
            System.out.println("O DEALER teve um bust. Encerrando o jogo...");
            Thread.sleep(1000L);
        }
    }
}
