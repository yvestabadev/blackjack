package br.com.yvestaba.blackjack.business.jogo;

import br.com.yvestaba.blackjack.business.materiais.Carta;
import br.com.yvestaba.blackjack.business.materiais.Naipe;
import br.com.yvestaba.blackjack.business.materiais.ValorCarta;
import br.com.yvestaba.blackjack.business.regras.CalculadoraDePontos;
import br.com.yvestaba.blackjack.business.regras.StatusJogo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static br.com.yvestaba.blackjack.business.materiais.Naipe.*;
import static br.com.yvestaba.blackjack.business.materiais.ValorCarta.*;
import static br.com.yvestaba.blackjack.business.regras.StatusJogo.*;
import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static br.com.yvestaba.blackjack.business.regras.CalculadoraDePontos.calcularMelhorPontuacao;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;

class JogoTest {

    private List<Jogador> jogadores;
    private Jogo jogo;
    private int qtdeJogadores = 3;
    private Apostador apostador;

    @BeforeEach
    void setUp(){
        List<Jogador> jogadores = new ArrayList<>();
        for(int i = 0; i < qtdeJogadores; i++){
            jogadores.add(new Jogador());
        }
        this.jogadores = jogadores;
        jogo = new Jogo(jogadores);
        apostador = (Apostador) ReflectionTestUtils.getField(jogo, "apostador");
    }

    @Test
    void jogadoresPerdemEmpatamEGanham(){
        var maoBust = asList(new Carta(OUROS, REI), new Carta(PAUS, NOVE), new Carta(PAUS, SETE));
        var maoBlackJack = asList(new Carta(PAUS, REI), new Carta(PAUS, AS));
        var maoVinte = asList(new Carta(PAUS, REI), new Carta(PAUS, VALETE));

        var maoApostador = asList(new Carta(COPAS, REI), new Carta(COPAS, VALETE));
        ReflectionTestUtils.setField(jogadores.get(0), "mao", maoBust);
        ReflectionTestUtils.setField(jogadores.get(1), "mao", maoBlackJack);
        ReflectionTestUtils.setField(jogadores.get(2), "mao", maoVinte);
        ReflectionTestUtils.setField(apostador, "mao", maoApostador);

        Map<StatusJogo, List<Jogador>> mapJogadores = jogo.fimDeJogo();
        assertEquals(singletonList(jogadores.get(1)), mapJogadores.get(GANHOU));
        assertEquals(singletonList(jogadores.get(0)), mapJogadores.get(PERDEU));
        assertEquals(singletonList(jogadores.get(2)), mapJogadores.get(EMPATOU));
    }

    @Test
    @RepeatedTest(20)
    void jogadoresPassamEApostadorPuxaAte17PontosOuMais(){
        for(int i = 0; i < qtdeJogadores; i++){
            jogo.proximoPasso(false);
        }

        Integer pontuacaoApostador = calcularMelhorPontuacao(apostador);
        assertTrue(pontuacaoApostador == -1 || pontuacaoApostador >= 17);
    }

    @Test
    void jogadoresPassamEAcabaOjogo(){
        boolean temProximoPasso = true;
        for(int i = 0; i < qtdeJogadores; i++){
            temProximoPasso = jogo.proximoPasso(false);
        }

        if(temProximoPasso){
            temProximoPasso = jogo.proximoPasso(false);
        }

        assertFalse(temProximoPasso);
    }
}