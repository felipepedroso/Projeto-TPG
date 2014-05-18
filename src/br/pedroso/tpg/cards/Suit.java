/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.pedroso.tpg.cards;

/**
 *
 * @author felipe.pedroso
 */
public enum Suit {
    Diamonds("\u2666"), Spades("\u2660"), Hearts("\u2665"), Clubs("\u2663");
    
    private String suitRepresentation;

    Suit(String suitRepresentation) {
        this.suitRepresentation = suitRepresentation;
    }

    @Override
    public String toString() {
        return suitRepresentation;
    }
}
