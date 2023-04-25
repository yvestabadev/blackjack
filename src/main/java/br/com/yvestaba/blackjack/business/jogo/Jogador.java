package br.com.yvestaba.blackjack.business.jogo;

import br.com.yvestaba.blackjack.business.materiais.Baralho;
import br.com.yvestaba.blackjack.business.materiais.Carta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Jogador {

    protected List<Carta> mao = new ArrayList<>();

    public Carta puxar(Baralho baralho){
        var carta = baralho.puxar();
        mao.add(carta);
        return carta;
    }

    public List<Carta> getMao() {
        return Collections.unmodifiableList(mao);
    }
}
