package br.com.yvestaba.blackjack.business.printer;

import br.com.yvestaba.blackjack.business.jogo.Jogador;
import br.com.yvestaba.blackjack.business.jogo.Jogo;

import java.util.ArrayList;
import java.util.List;

public class InicioJogoPrinter {

    private InicioJogoPrinter(){

    }

    public static Jogo iniciarJogo(int qtdeJogadores) throws InterruptedException {
        System.out.println("Iniciando jogo. Aguarde...");
        Thread.sleep(1000L);
        List<Jogador> jogadores = new ArrayList<>();
        for(int i = 0; i < qtdeJogadores; i++){
            jogadores.add(new Jogador());
        }
        return new Jogo(jogadores);
    }
}
