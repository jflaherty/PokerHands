package com.github.jflaherty.cardgames.poker.sequences;

import java.util.Collection;
import java.util.TreeSet;

import com.github.jflaherty.cardgames.playingcards.french.Card;
import com.github.jflaherty.cardgames.poker.hands.PokerHandRank;
import com.github.jflaherty.cardgames.poker.helpers.CardHelper;

public class StraightSequence extends CardSequence {

    public StraightSequence(PokerHandRank sequence, Collection<Card> cardsInSequence) {
	super(sequence, new TreeSet<Card>(cardsInSequence));
    }

    @Override
    public int sequenceRank() {
	return CardHelper.reevaluateAceInSequence(getCardsInSequence())
		.stream()
		.max((c1, c2) -> c1.getRank().asInt() - c2.getRank().asInt())
		.get()
		.getRank()
		.asInt();
    }
}