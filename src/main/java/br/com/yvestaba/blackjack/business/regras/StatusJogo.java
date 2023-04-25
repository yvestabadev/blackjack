package br.com.yvestaba.blackjack.business.regras;

import br.com.yvestaba.blackjack.business.jogo.Apostador;
import br.com.yvestaba.blackjack.business.jogo.Jogador;

import static br.com.yvestaba.blackjack.business.regras.CalculadoraDePontos.calcularMelhorPontuacao;

public enum StatusJogo {

    GANHOU, PERDEU, EMPATOU;

    public static StatusJogo getStatusJogador(Jogador jogador, Apostador apostador){
        Integer pontuacaoJogador = calcularMelhorPontuacao(jogador);
        Integer pontuacaoApostador = calcularMelhorPontuacao(apostador);
        if(pontuacaoJogador > pontuacaoApostador) return GANHOU;

        if(pontuacaoApostador > pontuacaoJogador) return PERDEU;

        return EMPATOU;
    }
}
