package br.com.yvestaba.blackjack.business.jogo;

import br.com.yvestaba.blackjack.business.materiais.Baralho;
import br.com.yvestaba.blackjack.business.materiais.Carta;

import java.util.List;

import static br.com.yvestaba.blackjack.business.regras.CalculadoraDePontos.calcularMelhorPontuacao;

public class Apostador extends Jogador{

    public List<Carta> iniciarPuxadas(Baralho baralho){
        while (true) {
            Integer melhorPontuacao = calcularMelhorPontuacao(this);
            if(melhorPontuacao == -1 || melhorPontuacao >= 17){
                break;
            }
            mao.add(baralho.puxar());
        }
        return mao;
    }
}
