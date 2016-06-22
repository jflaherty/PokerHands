package com.github.wsppan.cardgames.poker.sequences.sets;

import java.util.Collection;
import java.util.TreeSet;

import com.github.wsppan.cardgames.playingcards.french.Card;
import com.github.wsppan.cardgames.poker.hands.PokerHandRank;
import com.github.wsppan.cardgames.poker.sequences.CardSequence;
import com.github.wsppan.cardgames.poker.sequences.ISequence;

public class PokerSequenceSet implements ICardGameSequenceSet {
    private ISequence primarySequence;
    private ISequence auxillarySequence;
    private SequenceDetector detector = new SequenceDetector();

    public PokerSequenceSet(Collection<Card> cards) {
	TreeSet<Card> auxillaryCards = new TreeSet<Card>(cards);
	if (auxillaryCards == null || auxillaryCards.size() < 5) {
	    throw new IllegalArgumentException("Impossible card combination!");
	}
	detectSequences(auxillaryCards);
    }

    public ISequence getPrimary() {
	return primarySequence;
    }

    public int getPrimaryStrength() {
	return primarySequence.sequenceRank();
    }

    private void detectSequences(Collection<Card> cards) {
	primarySequence = detector.detectSequence(cards);
	cards.removeAll(primarySequence.getCardsInSequence());
	auxillarySequence = new CardSequence(PokerHandRank.HIGH_CARD, new TreeSet<Card>(cards));
    }

    @Override
    public int compareTo(ICardGameSequenceSet other) {
	int primaryCompare = primarySequence.compareTo(other.getPrimary());
	return primaryCompare == 0 ? this.getAuxillary().compareTo(other.getAuxillary()) : primaryCompare;
    }

    @Override
    public ISequence getAuxillary() {
	return auxillarySequence;
    }
}