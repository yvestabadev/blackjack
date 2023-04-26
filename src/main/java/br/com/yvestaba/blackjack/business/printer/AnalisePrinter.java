package br.com.yvestaba.blackjack.business.printer;

import br.com.yvestaba.blackjack.business.jogo.Jogador;
import br.com.yvestaba.blackjack.business.regras.StatusMao;

import static br.com.yvestaba.blackjack.business.regras.CalculadoraDePontos.calcularPorJogador;
import static br.com.yvestaba.blackjack.business.regras.CalculadoraDePontos.calcularPorJogadorString;

public class AnalisePrinter {

    private AnalisePrinter(){

    }

    public static void analisar(Jogador jogadorDaVez) throws InterruptedException {
        System.out.println();
        var pontos = calcularPorJogador(jogadorDaVez);
        var status = StatusMao.getStatusMaoByJogador(jogadorDaVez);
        if(status == StatusMao.BLACKJACK || status == StatusMao.VINTE_E_UM){
            System.out.println("Você já tem a quantidade máxima de pontos! Pode parar de puxar");
            Thread.sleep(3000L);
            return;
        }
        if(pontos.size() == 2 && jogadorDaVez.getMao().size() == 1){
            System.out.println("Puxe outra carta e tente o BlackJack! É seguro, pois sua pontuação é 1 ou 11");
            Thread.sleep(3000L);
            return;
        }
        if(pontos.size() == 2){
            System.out.println("Você possui duas pontuações possíveis: " + calcularPorJogadorString(jogadorDaVez) +
                    ". Se estiver satisfeito(a) com a maior pontuação, pode parar, mas é totalmente seguro puxar uma carta");
            Thread.sleep(3000L);
            return;
        }
        var ponto = pontos.get(0);
        if(ponto < 12){
            System.out.println("É totalmente seguro puxar outra carta, pois sua mão suporta a maior carta ainda. Sua pontuação é " + ponto);
            Thread.sleep(3000L);
            return;
        }
        if(ponto < 15){
            System.out.println("Existem algumas chances de bust. Sua pontuação é " + ponto);
            Thread.sleep(3000L);
            return;
        }
        System.out.println("A chance de bust é alta. Sua pontuação é " + ponto);
        Thread.sleep(3000L);
    }
}
