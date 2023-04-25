package br.com.yvestaba.blackjack.business.regras;

import br.com.yvestaba.blackjack.business.jogo.Apostador;
import br.com.yvestaba.blackjack.business.jogo.Jogador;
import br.com.yvestaba.blackjack.business.materiais.Carta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static br.com.yvestaba.blackjack.business.materiais.Naipe.OUROS;
import static br.com.yvestaba.blackjack.business.materiais.Naipe.PAUS;
import static br.com.yvestaba.blackjack.business.materiais.ValorCarta.*;
import static br.com.yvestaba.blackjack.business.regras.StatusJogo.*;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

class StatusJogoTest {

    private Jogador jogador;
    private Apostador apostador;

    @BeforeEach
    void setUp(){
        jogador = new Jogador();
        apostador = new Apostador();
    }

    @Test
    void empatou(){
        var maoJogador = asList(new Carta(OUROS, VALETE), new Carta(OUROS, DAMA));
        ReflectionTestUtils.setField(jogador, "mao", maoJogador);
        var maoApostador = asList(new Carta(PAUS, VALETE), new Carta(PAUS, DAMA));
        ReflectionTestUtils.setField(apostador, "mao", maoApostador);
        assertEquals(EMPATOU ,getStatusJogador(jogador, apostador));
    }

    @Test
    void perdeu(){
        var maoJogador = asList(new Carta(OUROS, NOVE), new Carta(OUROS, DAMA));
        ReflectionTestUtils.setField(jogador, "mao", maoJogador);
        var maoApostador = asList(new Carta(PAUS, VALETE), new Carta(PAUS, DAMA));
        ReflectionTestUtils.setField(apostador, "mao", maoApostador);
        assertEquals(PERDEU ,getStatusJogador(jogador, apostador));
    }

    @Test
    void ganhou(){
        var maoJogador = asList(new Carta(OUROS, DEZ), new Carta(OUROS, AS));
        ReflectionTestUtils.setField(jogador, "mao", maoJogador);
        var maoApostador = asList(new Carta(PAUS, VALETE), new Carta(PAUS, DAMA));
        ReflectionTestUtils.setField(apostador, "mao", maoApostador);
        assertEquals(GANHOU ,getStatusJogador(jogador, apostador));
    }

    @Test
    void ganhouBlackJack(){
        var maoJogador = asList(new Carta(OUROS, DEZ), new Carta(OUROS, AS));
        ReflectionTestUtils.setField(jogador, "mao", maoJogador);
        var maoApostador = asList(new Carta(PAUS, VALETE), new Carta(PAUS, DAMA), new Carta(PAUS, AS));
        ReflectionTestUtils.setField(apostador, "mao", maoApostador);
        assertEquals(GANHOU ,getStatusJogador(jogador, apostador));
    }

}