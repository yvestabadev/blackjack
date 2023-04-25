package br.com.yvestaba.blackjack.business.regras;

import br.com.yvestaba.blackjack.business.jogo.Jogador;
import br.com.yvestaba.blackjack.business.materiais.Carta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static br.com.yvestaba.blackjack.business.materiais.Naipe.*;
import static br.com.yvestaba.blackjack.business.materiais.ValorCarta.*;
import static br.com.yvestaba.blackjack.business.regras.StatusMao.*;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

class StatusMaoTest {

    private Jogador jogador;

    @BeforeEach
    void setUp(){
        jogador = new Jogador();
    }

    @Test
    void menorQue21(){
        var mao = asList(new Carta(OUROS, VALETE), new Carta(OUROS, DAMA));
        ReflectionTestUtils.setField(jogador, "mao", mao);
        assertEquals(MENOR_QUE_21, getStatusMaoByJogador(jogador));
    }

    @Test
    void blackJack(){
        var mao = asList(new Carta(OUROS, VALETE), new Carta(ESPADAS, AS));
        ReflectionTestUtils.setField(jogador, "mao", mao);
        assertEquals(BLACKJACK, getStatusMaoByJogador(jogador));
    }

    @Test
    void vinteEUmComAsNaoBlackjack(){
        var mao = asList(new Carta(OUROS, VALETE), new Carta(PAUS, REI), new Carta(ESPADAS, AS));
        ReflectionTestUtils.setField(jogador, "mao", mao);
        assertEquals(VINTE_E_UM, getStatusMaoByJogador(jogador));
    }

    @Test
    void vinteEUmSemAs(){
        var mao = asList(new Carta(OUROS, VALETE), new Carta(PAUS, NOVE), new Carta(ESPADAS, DOIS));
        ReflectionTestUtils.setField(jogador, "mao", mao);
        assertEquals(VINTE_E_UM, getStatusMaoByJogador(jogador));
    }

    @Test
    void bustSemAs(){
        var mao = asList(new Carta(OUROS, VALETE), new Carta(PAUS, NOVE), new Carta(ESPADAS, TRES));
        ReflectionTestUtils.setField(jogador, "mao", mao);
        assertEquals(BUST, getStatusMaoByJogador(jogador));
    }

    @Test
    void bustComAs(){
        var mao = asList(new Carta(OUROS, VALETE), new Carta(PAUS, NOVE), new Carta(COPAS, AS), new Carta(ESPADAS, TRES));
        ReflectionTestUtils.setField(jogador, "mao", mao);
        assertEquals(BUST, getStatusMaoByJogador(jogador));
    }

}