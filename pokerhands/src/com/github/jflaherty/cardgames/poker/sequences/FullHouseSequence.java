package com.github.wsppan.cardgames.poker.sequences;

import static com.github.wsppan.cardgames.poker.helpers.SequenceHelper.findRepeats;

import java.util.*;

import com.github.wsppan.cardgames.playingcards.french.Card;
import com.github.wsppan.cardgames.poker.hands.PokerHandRank;

public class FullHouseSequence extends CardSequence {

    public FullHouseSequence(PokerHandRank sequence, Collection<Card> cardsInSequence) {
	super(sequence, new TreeSet<Card>(cardsInSequence));
    }

    @Override
    public int sequenceRank() {
	int threeCardValue = findRepeats(getCardsInSequence(), Card::getRank, 3).get(0).getRank().asInt();
	return threeCardValue;
    }
}