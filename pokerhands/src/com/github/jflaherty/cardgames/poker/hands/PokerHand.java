package com.github.jflaherty.cardgames.poker.hands;

import java.util.Collection;

import com.github.jflaherty.cardgames.playingcards.french.Card;
import com.github.jflaherty.cardgames.poker.sequences.sets.ICardGameSequenceSet;
import com.github.jflaherty.cardgames.poker.sequences.sets.PokerSequenceSet;

public class PokerHand implements ICardGameHand {
    private ICardGameSequenceSet sequenceSet;

    public PokerHand(Collection<Card> cardsOnHand) {
	this.sequenceSet = new PokerSequenceSet(cardsOnHand);
    }

    @Override
    public ICardGameSequenceSet getSequenceSet() {
	return sequenceSet;
    }

    @Override
    public int compareTo(ICardGameHand other) {
	return sequenceSet.compareTo(other.getSequenceSet());
    }

}