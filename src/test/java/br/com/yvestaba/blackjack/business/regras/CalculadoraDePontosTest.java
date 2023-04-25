package br.com.yvestaba.blackjack.business.regras;

import br.com.yvestaba.blackjack.business.jogo.Jogador;
import br.com.yvestaba.blackjack.business.materiais.Carta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;

import static br.com.yvestaba.blackjack.business.materiais.Naipe.*;
import static br.com.yvestaba.blackjack.business.materiais.ValorCarta.*;
import static br.com.yvestaba.blackjack.business.regras.CalculadoraDePontos.calcularMelhorPontuacao;
import static br.com.yvestaba.blackjack.business.regras.CalculadoraDePontos.calcularPorJogador;
import static java.util.Collections.singletonList;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

class CalculadoraDePontosTest {

    private Jogador jogador;

    @BeforeEach
    void setUp(){
        jogador = new Jogador();
    }

    @Test
    void calculaSemAs(){
        var mao = asList(new Carta(OUROS, VALETE), new Carta(OUROS, DAMA));
        ReflectionTestUtils.setField(jogador, "mao", mao);
        assertEquals(20, calcularMelhorPontuacao(jogador));
        assertEquals(singletonList(20), calcularPorJogador(jogador));
    }

    @Test
    void calculaComAs(){
        var mao = asList(new Carta(OUROS, AS), new Carta(OUROS, DAMA));
        ReflectionTestUtils.setField(jogador, "mao", mao);
        assertEquals(21, calcularMelhorPontuacao(jogador));
        assertEquals(asList(11,21), calcularPorJogador(jogador));
    }

    @Test
    void melhorPontuacaoComAsComUmResultadoBust(){
        var mao = asList(new Carta(OUROS, AS), new Carta(OUROS, NOVE), new Carta(ESPADAS, NOVE));
        ReflectionTestUtils.setField(jogador, "mao", mao);
        assertEquals(19, calcularMelhorPontuacao(jogador));
    }

}