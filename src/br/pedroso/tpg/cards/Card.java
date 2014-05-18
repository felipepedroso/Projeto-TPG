/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pedroso.tpg.cards;

import java.util.Objects;

/**
 *
 * @author felipe.pedroso
 */
public class Card implements Comparable<Card>{
    private Suit suit;
    private Rank rank;

    public Card(Rank rank, Suit suit) {
        this.suit = suit;
        this.rank = rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return rank.toString() + suit.toString();
    }
    
    @Override
    public int compareTo(Card otherCard) {
        return getRank().ordinal() - otherCard.getRank().ordinal();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Card) {
            Card otherCard = (Card) obj;
            
            return this.getRank() == otherCard.getRank() && this.getSuit() == otherCard.getSuit();
        }
        
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.suit);
        hash = 13 * hash + Objects.hashCode(this.rank);
        return hash;
    }
    
    
}
