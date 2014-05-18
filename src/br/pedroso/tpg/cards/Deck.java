/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pedroso.tpg.cards;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author felipe.pedroso
 */
public class Deck {

    protected LinkedList<Card> cards;

    public Deck() {
        cards = new LinkedList<>();

        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.push(new Card(rank, suit));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards, new Random(System.nanoTime()));
    }
    
    public void addDeck(Deck deck){
        if (deck != null) {
            cards.addAll(deck.getCards());
        }
    }

    public void sort(SortType sortType) {
        Comparator<Card> comparator = null;

        switch (sortType) {
            case Rank:
                comparator = new RankComparator();
                break;
            case Suit:
                comparator = new SuitComparator();
                break;
            case Both:
                comparator = new RankSuitComparator();
            default:
                break;
        }

        if (comparator != null) {
            Collections.sort(cards, comparator);
        }
    }

    private Collection<? extends Card> getCards() {
        return cards;
    }

    public void removeAllCards() {
        cards.clear();
    }
    
    public Card drawCard(){
        return cards.pop();
    }

    public enum SortType {
        Rank,
        Suit,
        Both
    }

    public class RankComparator implements Comparator<Card> {

        @Override
        public int compare(Card card1, Card card2) {
            return card1.getRank().ordinal() - card2.getRank().ordinal();
        }
    }

    public class SuitComparator implements Comparator<Card> {
        @Override
        public int compare(Card card1, Card card2) {
            return card1.getSuit().ordinal() - card2.getSuit().ordinal();
        }
    }
    
    public class RankSuitComparator extends SuitComparator{
        @Override
        public int compare(Card card1, Card card2) {
            int suitComparison = super.compare(card1, card2);
            
            if(suitComparison == 0){
                return new RankComparator().compare(card1, card2);
            }
            
            return suitComparison;
        }
    }

    @Override
    public String toString() {
        return cards.toString();
    }

    public static void main(String[] args) {
        Deck deck = new Deck();

        System.out.println(deck);

        deck.shuffle();
        deck.sort(SortType.Rank);
        System.out.println(deck);
        
        deck.shuffle();
        deck.sort(SortType.Suit);
        System.out.println(deck);
        
        deck.shuffle();
        deck.sort(SortType.Both);
        System.out.println(deck);
        
        Deck deck2 = new Deck();
        deck2.addDeck(deck);
        deck2.sort(SortType.Both);
        
        System.out.println(deck2);
        
        
    }
}
