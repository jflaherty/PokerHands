package com.github.wsppan.cardgames.poker.sequences.sets;

import static com.github.wsppan.cardgames.poker.hands.PokerHandRank.FLUSH;
import static com.github.wsppan.cardgames.poker.hands.PokerHandRank.FOUR_OF_A_KIND;
import static com.github.wsppan.cardgames.poker.hands.PokerHandRank.PAIR;
import static com.github.wsppan.cardgames.poker.hands.PokerHandRank.STRAIGHT;
import static com.github.wsppan.cardgames.poker.hands.PokerHandRank.THREE_OF_A_KIND;

import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeSet;

import com.github.wsppan.cardgames.playingcards.french.Card;
import com.github.wsppan.cardgames.poker.hands.PokerHandRank;
import com.github.wsppan.cardgames.poker.helpers.CardHelper;
import com.github.wsppan.cardgames.poker.helpers.SequenceHelper;
import com.github.wsppan.cardgames.poker.sequences.CardSequence;
import com.github.wsppan.cardgames.poker.sequences.FullHouseSequence;
import com.github.wsppan.cardgames.poker.sequences.ISequence;
import com.github.wsppan.cardgames.poker.sequences.StraightSequence;

public class SequenceDetector {
    private SortedSet<Card> cardsInSequence = new TreeSet<Card>();

    public ISequence detectSequence(Collection<Card> cards) {
	PokerHandRank handRank = PokerHandRank.fromHandRankMap(getSequenceRank(cards));
	switch (handRank) {
	case STRAIGHT_FLUSH:
	case STRAIGHT:
	    return new StraightSequence(handRank, cardsInSequence);
	case FULL_HOUSE:
	    return new FullHouseSequence(handRank, cardsInSequence);
	case FOUR_OF_A_KIND:
	case THREE_OF_A_KIND:
	case PAIR:
	case TWO_PAIRS:
	    return new CardSequence(handRank, cardsInSequence);
	case FLUSH:
	case HIGH_CARD:
	    return new CardSequence(handRank, new TreeSet<Card>(cards));
	default:
	    throw new IllegalArgumentException("Incorrect card combination!");
	}
    }
    
    private int getSequenceRank(Collection<Card> cards) {
	int sequenceRank = 0;
	if (SequenceHelper.hasRepeats(cards, Card::getSuit, 5)) {
	    sequenceRank += FLUSH.rank();
	}
	if (SequenceHelper.isMonotonicByOne(CardHelper.reevaluateAceInSequence(cards))) {
	    sequenceRank += STRAIGHT.rank();
	    cardsInSequence.addAll(cards);
	    return sequenceRank;
	}
	if (cardsInSequence.addAll(SequenceHelper.findRepeats(cards, Card::getRank, 4))) {
	    sequenceRank += FOUR_OF_A_KIND.rank();
	    return sequenceRank;
	}
	if (cardsInSequence.addAll(SequenceHelper.findRepeats(cards, Card::getRank, 3))) {
	    sequenceRank += THREE_OF_A_KIND.rank();
	}

	Collection<Card> pairs = SequenceHelper.findRepeats(cards, Card::getRank, 2);
	if (cardsInSequence.addAll(pairs)) {
	    sequenceRank += pairs.size() / 2 * PAIR.rank();
	}

	return sequenceRank;
    }
}