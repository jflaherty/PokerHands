package com.github.jflaherty.cardgames.poker.sequences;

import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.github.jflaherty.cardgames.playingcards.french.Card;
import com.github.jflaherty.cardgames.poker.hands.IHandRank;

public class CardSequence implements ISequence {
    private final IHandRank handRank;
    private SortedSet<Card> cardsInSequence;

    public CardSequence(IHandRank handRank, SortedSet<Card> cards) {
	this.handRank = handRank;
	this.cardsInSequence = cards;
    }

    @Override
    public int sequenceRank() {
	SortedSet<Integer> cardRanksSorted = new TreeSet<Integer>(cardsInSequence
		.stream()
		.mapToInt(c -> c.getRank().asInt()).distinct().boxed().collect(Collectors.toSet()));
	int multiplier = 1;
	final int multiplicationFactor = 15;
	int sequenceRank = 0;
	for (Integer cardRank : cardRanksSorted) {
	    sequenceRank += cardRank * multiplier;
	    multiplier *= multiplicationFactor;
	}
	return sequenceRank;
    };

    @Override
    public SortedSet<Card> getCardsInSequence() {
	return cardsInSequence;
    }

    @Override
    public int compareTo(ISequence other) {
	// for performance reasons to avoid unnecessary strength calculation
	if (this.handRank.equals(other.getHandRank())) {
	    return this.sequenceRank() - other.sequenceRank();
	}
	return this.getHandRank().value() - other.getHandRank().value();
    }

    @Override
    public IHandRank getHandRank() {
	return handRank;
    }
}