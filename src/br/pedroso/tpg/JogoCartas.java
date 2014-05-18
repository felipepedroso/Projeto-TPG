/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pedroso.tpg;

import br.pedroso.tpg.cards.Card;
import br.pedroso.tpg.cards.Rank;
import br.pedroso.tpg.cards.Suit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author felipe.pedroso
 */
public class JogoCartas {

    public List<Card> criarBaralho() {
        Suit[] naipes = Suit.values();
        Rank[] valores = Rank.values();

        List<Card> baralho = new ArrayList<>();

        for (int i = 0; i < naipes.length; i++) {
            for (int j = 0; j < valores.length; j++) {
                Card carta = new Card(valores[j], naipes[i]);
                baralho.add(carta);
            }
        }
        return baralho;

    }

    public void embaralhar(List<Card> cartas) {
        Random random = new Random(System.nanoTime());

        for (int i = 0; i < cartas.size(); i++) {
            int j = random.nextInt(cartas.size());

            if (i != j) {
                Card aux = cartas.get(i);
                cartas.set(i, cartas.get(j));
                cartas.set(j, aux);
            }
        }
    }

    public List<Deck> distribuirCartas(List<Card> baralho, int quantidadeJogadores, int quantidadeCartasPorJogador) {
        List<Deck> deckJogadores = new ArrayList<>();
        
        int quantidadeCartas = quantidadeJogadores * quantidadeCartasPorJogador;
        
        if (quantidadeCartas <= baralho.size()) {
            for (int i = 0; i < quantidadeJogadores; i++) {
                deckJogadores.add(new Deck(String.format("Jogador %d", i)));
            }
            
            for (int i = 0; i < quantidadeCartas; i++) {
                Card carta = baralho.remove(i);
                deckJogadores.get(i % quantidadeJogadores).adicionarCarta(carta);
            }
        }
        
        return deckJogadores;
    }

    private class Deck {

        private final String nomeJogador;
        private final LinkedList<Card> cartas;

        public Deck(String nomeJogador) {
            this.nomeJogador = nomeJogador;
            this.cartas = new LinkedList<>();
        }

        public void adicionarCarta(Card carta) {
            if (carta != null) {
                cartas.add(carta);
            }
        }

        public String getNomeJogador() {
            return nomeJogador;
        }

        public LinkedList<Card> getCartas() {
            return cartas;
        }

        @Override
        public String toString() {
            return String.format("%s - %s", nomeJogador, cartas.toString());
        }
    }

    public static void main(String[] args) {
        JogoCartas jogo = new JogoCartas();

        // Testando a criação do baralho
        List<Card> baralho1 = jogo.criarBaralho();
        final int quantidadeMaximaCartas = Rank.values().length * Suit.values().length;
        System.out.println("-----------------------------------");
        System.out.println("Teste do algoritmo \"CriarBaralho\"");
        System.out.println(String.format("Testando se a lista de cartas não retornou nula: %b", baralho1 != null));

        if (baralho1 != null && !baralho1.isEmpty()) {
            System.out.println(String.format("Baralho criado: %s", baralho1.toString()));
            System.out.println(String.format("Testando se todas as combinações de cartas foram criadas: %b", baralho1.size() == quantidadeMaximaCartas));
            System.out.println(String.format("Testando se o baralho sempre é criado da mesma maneira: %b", baralho1.containsAll(jogo.criarBaralho())));

            boolean duplicacasCriarBaralho = false;
            for (Card carta : baralho1) {
                //System.out.println(String.format("Frequency of %s inside the deck: %d", carta.toString(), Collections.frequency(baralho1, carta)));
                if (Collections.frequency(baralho1, carta) > 1) {
                    duplicacasCriarBaralho = true;
                    break;
                }
            }
            System.out.println(String.format("Testanto se existem cartas duplicadas: %b", !duplicacasCriarBaralho));
        } else {
            return;
        }

        // Testando o embaralhamento
        List<Card> copiaBaralho = Collections.EMPTY_LIST;
        Collections.copy(baralho1, copiaBaralho);
        System.out.println("-----------------------------------");
        System.out.println("Teste do algoritmo \"Embaralhar\"");
        jogo.embaralhar(baralho1);
        System.out.println(String.format("Baralho após embaralhamento: %s", baralho1.toString()));
        System.out.println(String.format("Testando se nenhuma carta foi removida: %b", baralho1.size() == quantidadeMaximaCartas));
        System.out.println(String.format("Testando se todas as cartas estão no baralho original após embaralhar: %b", baralho1.containsAll(copiaBaralho)));

        boolean testeDuplicadasEmbaralhar = false;
        for (Card carta : baralho1) {
            //System.out.println(String.format("Frequency of %s inside the deck: %d", carta.toString(), Collections.frequency(baralho1, carta)));
            if (Collections.frequency(baralho1, carta) > 1) {
                testeDuplicadasEmbaralhar = true;
                break;
            }
        }
        System.out.println(String.format("Testanto se existem cartas duplicadas após embaralhar: %b", !testeDuplicadasEmbaralhar));

        // Testando a distribuição de cartas
        System.out.println("-----------------------------------");
        System.out.println("Teste do algoritmo \"DistribuirCartas\"");
        final int quantidadeJogadores = 5;
        final int quantidadeCartasPorJogador = 4;
        List<Deck> deckJogadores = jogo.distribuirCartas(baralho1, quantidadeJogadores, quantidadeCartasPorJogador);

        System.out.println(String.format("Testando se o método distribuirCartas não retorna null: %b", deckJogadores != null));

        if (deckJogadores != null && !deckJogadores.isEmpty()) {
            System.out.println(String.format("Testando se foram gerados decks para todos os jogadores: %b", deckJogadores.size() == quantidadeJogadores));
            System.out.println(String.format("Testando se a quantidade de cartas distribuidas foram removidas do baralho: %b", baralho1.size() == quantidadeMaximaCartas - (quantidadeJogadores * quantidadeCartasPorJogador)));

            System.out.println("Testando a quantidade de cartas na mão para cada jogador:");

            for (Deck deck : deckJogadores) {
                System.out.println(String.format("\t> Testando se o jogador \"%s\" tem a quantidade de cartas adequada no deck: %b", deck.getNomeJogador(), deck.getCartas().size() == quantidadeCartasPorJogador));
                System.out.println(String.format("\t> Testando se as cartas do jogador \"%s\" foram removidas do baralho: ", deck.getNomeJogador()));
                for (Card carta : deck.getCartas()) {
                    System.out.println(String.format("\t\t- Testando se a carta %s foi removida do baralho: %b", carta.toString(), !baralho1.contains(carta)));
                }
            }

            List<Card> cartasDistribuidas = new ArrayList<>();
            for (int i = 0; i < deckJogadores.size(); i++) {
                cartasDistribuidas.addAll(deckJogadores.get(i).getCartas());
            }
            
            boolean duplicadasDistribuir = false;
            for(Card carta : cartasDistribuidas){
                if (Collections.frequency(cartasDistribuidas, carta) > 1) {
                    duplicadasDistribuir = true;
                    break;
                }
            }
            System.out.println(String.format("Testando se existem cartas duplicadas entre as cartas distribuidas: %b", !duplicadasDistribuir));

        } else {
            return;
        }

        System.out.println(String.format("Testando se o metodo retorna um array vazio quando a quantidade de cartas é insuficiente: %b", jogo.distribuirCartas(baralho1, 10, 10).isEmpty()));

    }
}
