package br.com.yvestaba.blackjack.business.printer;

import java.util.Scanner;

public class QuantidadeJogadoresPrinter {

    private QuantidadeJogadoresPrinter(){

    }

    public static Integer perguntarQuantidadeJogadores(Scanner scanner) {
        System.out.println("Digite apenas números. Qual a quantidade de jogadores, além do Dealer?");
        System.out.println("Escolha de 1 a 7");
        while(true) {
            Integer check;
            try{
                check = Integer.parseInt(scanner.next());
            } catch(NumberFormatException e){
                System.out.println();
                System.out.println("Digite apenas números, de 1 a 7");
                System.out.println("Qual a quantidade de jogadores, além do Dealer?");
                continue;
            }

            if(check < 1 || check > 7){
                System.out.println();
                System.out.println("Insira números válidos. De 1 a 7");
                System.out.println("Qual a quantidade de jogadores, além do Dealer?");
                continue;
            }
            return check;
        }
    }
}
