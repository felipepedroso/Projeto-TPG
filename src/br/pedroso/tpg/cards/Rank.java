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
public enum Rank {
    Ace, Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King;

    @Override
    public String toString() {
        String rankRepresentation = "";
        switch (this) {
            case Ace:
            case Jack:
            case Queen:
            case King:
                rankRepresentation = this.name().charAt(0) + "";
                break;
            default:
                rankRepresentation = (this.ordinal() + 1) + "";
                break;
        }
        return rankRepresentation;
    }
    
}
