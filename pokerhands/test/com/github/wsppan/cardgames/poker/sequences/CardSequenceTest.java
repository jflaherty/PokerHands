package com.github.wsppan.cardgames.poker.sequences;

import static org.junit.Assert.*;

import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.Test;

import com.github.wsppan.cardgames.playingcards.french.Card;
import com.github.wsppan.cardgames.poker.hands.PokerHandRank;
import com.github.wsppan.cardgames.poker.sequences.CardSequence;
import com.github.wsppan.cardgames.poker.sequences.ISequence;

public class CardSequenceTest {
    
    SortedSet<Card> cards = new TreeSet<Card>();

    @Test
    public void shouldCompareTwoSequencesSecondLower() {
	// given
	CardSequence firstCardSequence = new CardSequence(PokerHandRank.FLUSH, cards) {
	    @Override
	    public int sequenceRank() {
		return 0;
	    }
	};
	CardSequence secondCardSequence = new CardSequence(PokerHandRank.PAIR, cards) {
	    @Override
	    public int sequenceRank() {
		return 0;
	    }
	};
	// when
	int comparisonResult = firstCardSequence.compareTo(secondCardSequence);
	// then
	assertTrue(comparisonResult > 0);
    }

    @Test
    public void shouldCompareTwoSequencesSecondHigher() {
	// given
	CardSequence firstCardSequence = new CardSequence(PokerHandRank.THREE_OF_A_KIND, cards) {
	    @Override
	    public int sequenceRank() {
		return 0;
	    }
	};
	CardSequence secondCardSequence = new CardSequence(PokerHandRank.STRAIGHT, cards) {
	    @Override
	    public int sequenceRank() {
		return 0;
	    }
	};
	// when
	int comparisonResult = firstCardSequence.compareTo(secondCardSequence);
	// then
	assertTrue(comparisonResult < 0);
    }

    @Test
    public void shouldCompareTwoEqualSequences() {
	// given
	CardSequence firstCardSequence = new CardSequence(PokerHandRank.FOUR_OF_A_KIND, cards) {
	    @Override
	    public int sequenceRank() {
		return 0;
	    }
	};
	CardSequence secondCardSequence = new CardSequence(PokerHandRank.FOUR_OF_A_KIND, cards) {
	    @Override
	    public int sequenceRank() {
		return 0;
	    }
	};
	// when
	int comparisonResult = firstCardSequence.compareTo(secondCardSequence);
	// then
	assertTrue(comparisonResult == 0);
    }

    @Test
    public void shouldReturnZeroStrengthForNoCards() {
	// given
	ISequence cardSequence = new CardSequence(PokerHandRank.HIGH_CARD, cards);
	// when
	int strength = cardSequence.sequenceRank();
	// then
	assertEquals(0, strength);
    }
}