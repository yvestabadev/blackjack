package br.com.yvestaba.blackjack.business.regras;

import br.com.yvestaba.blackjack.business.jogo.Jogador;
import br.com.yvestaba.blackjack.business.materiais.Carta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static java.util.Objects.isNull;

public class CalculadoraDePontos {

    private CalculadoraDePontos(){

    }

    public static List<Integer> calcularPorJogador(Jogador jogador){
        var mao = jogador.getMao();
        if(mao.isEmpty()) return singletonList(0);

        Map<Boolean, List<Carta>> cartaByMaisDeUmValor =
                mao.stream().collect(Collectors.groupingBy(c -> c.getValores().size() > 1));
        var cartaUmValor = cartaByMaisDeUmValor.get(false);
        var cartaDoisValores = cartaByMaisDeUmValor.get(true);

        /*
            o às possui valor de 1 ou 11, então o cálculo pode ser feito pelo tamanho da lista de às
            ou o seu tamanho + 10
         */
        if(isNull(cartaUmValor)){
            return asList(cartaDoisValores.size(), cartaDoisValores.size() + 10);
        }

        int somaCartaUmValor = cartaUmValor.stream().mapToInt(c -> c.getValores().get(0)).sum();
        if(isNull(cartaDoisValores)){
            return singletonList(somaCartaUmValor);
        }

        List<Integer> ret = new ArrayList<>();
        ret.add(somaCartaUmValor + cartaDoisValores.size());
        int somaMaior = somaCartaUmValor + cartaDoisValores.size() + 10;
        if(somaMaior < 22) {
            ret.add(somaMaior);
        }
        return ret;
    }

    public static String calcularPorJogadorString(Jogador jogador){
        return String.join(" ou ", calcularPorJogador(jogador).stream().map(i -> String.valueOf(i)).toList());
    }

    public static Integer calcularMelhorPontuacao(Jogador jogador){
        if(jogador.getMao().isEmpty()){
            return 0;
        }
        var listaPontos = calcularPorJogador(jogador);
        Integer pontuacao = -1;
        for(var pontos : listaPontos){
            if(pontos > pontuacao && pontos < 22){
                pontuacao = pontos;
            }
        }
        return pontuacao;
    }
}
