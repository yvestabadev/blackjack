package br.com.yvestaba.blackjack.business.regras;

import br.com.yvestaba.blackjack.business.jogo.Jogador;

import static br.com.yvestaba.blackjack.business.regras.CalculadoraDePontos.calcularMelhorPontuacao;

public enum StatusMao {

    MENOR_QUE_21, VINTE_E_UM, BLACKJACK, BUST;

    public static StatusMao getStatusMaoByJogador(Jogador jogador){
        Integer pontuacao = calcularMelhorPontuacao(jogador);
        if(pontuacao == 21){
            return jogador.getMao().size() == 2 ? BLACKJACK : VINTE_E_UM;
        }
        if(pontuacao == -1){
            return BUST;
        }
        return MENOR_QUE_21;
    }
}
