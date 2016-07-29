package com.github.wsppan.cardgames.poker.sequences.sets;

import java.util.ArrayList;
import java.util.List;

import com.github.wsppan.cardgames.poker.hands.PokerHandRank;

public class PokerSequenceSetTestDataSet {

    public static List<SequenceTestData> getTestData() {
	List<SequenceTestData> testData = new ArrayList<>();

	testData.add(SequenceTestData.testBuilderFactory().withAddedMultipleCardsToHand("5S 3C 2D TS AH")
		.withExpectedPrimaryPokerSequenceOf(PokerHandRank.HIGH_CARD).withNameOf("High card of Ten").build());

	testData.add(SequenceTestData.testBuilderFactory().withAddedMultipleCardsToHand("5S 3C 2D 3S AH")
		.withExpectedPrimaryPokerSequenceOf(PokerHandRank.PAIR).withRemainingHighestAuxillaryCard("AH").withNameOf("Pair of Threes and high card Ace")
		.build());

	testData.add(SequenceTestData.testBuilderFactory().withAddedMultipleCardsToHand("5S 3C AD 3S AH")
		.withExpectedPrimaryPokerSequenceOf(PokerHandRank.TWO_PAIRS).withRemainingHighestAuxillaryCard("5S")
		.withNameOf("Two pairs (Aces, Threes) and high card Five").build());

	testData.add(SequenceTestData.testBuilderFactory().withAddedMultipleCardsToHand("TS TD 9S TC JS")
		.withExpectedPrimaryPokerSequenceOf(PokerHandRank.THREE_OF_A_KIND).withRemainingHighestAuxillaryCard("JS")
		.withNameOf("Three of a kind (Ten) and high card Jack").build());

	testData.add(SequenceTestData.testBuilderFactory().withAddedMultipleCardsToHand("AS 2S 3D 5H 4C")
		.withExpectedPrimaryPokerSequenceOf(PokerHandRank.STRAIGHT).withNameOf("Straight from lower Ace").build());

	testData.add(SequenceTestData.testBuilderFactory().withAddedMultipleCardsToHand("AS KS QD TH JC")
		.withExpectedPrimaryPokerSequenceOf(PokerHandRank.STRAIGHT).withNameOf("Straight to higher Ace").build());

	testData.add(SequenceTestData.testBuilderFactory().withAddedMultipleCardsToHand("TS QS 9S 2S JS")
		.withExpectedPrimaryPokerSequenceOf(PokerHandRank.FLUSH).withNameOf("Flush of Spades").build());

	testData.add(SequenceTestData.testBuilderFactory().withAddedMultipleCardsToHand("2C 5C 5H 5D 2S")
		.withExpectedPrimaryPokerSequenceOf(PokerHandRank.FULL_HOUSE).withNameOf("Full house with Fives and Twos").build());

	testData.add(SequenceTestData.testBuilderFactory().withAddedMultipleCardsToHand("2C 5C 5H 5D 5S")
		.withExpectedPrimaryPokerSequenceOf(PokerHandRank.FOUR_OF_A_KIND).withRemainingHighestAuxillaryCard("2C").withNameOf("Four of a kind - Fives")
		.build());

	testData.add(SequenceTestData.testBuilderFactory().withAddedMultipleCardsToHand("4D 5D 7D 8D 6D")
		.withExpectedPrimaryPokerSequenceOf(PokerHandRank.STRAIGHT_FLUSH).withNameOf("Straight flush from Four").build());

	testData.add(SequenceTestData.testBuilderFactory().withAddedMultipleCardsToHand("TH JH QH KH AH")
		.withExpectedPrimaryPokerSequenceOf(PokerHandRank.STRAIGHT_FLUSH).withNameOf("ROYAL FLUSH").build());

	testData.add(SequenceTestData.testBuilderFactory().withAddedMultipleCardsToHand("AS AS AS AS AS")
		.withExpectedExceptionClass(IllegalArgumentException.class).withNameOf("THE ACE_H OF SPADES! THE ACE_H OF SPADES!").build());

	return testData;
    }
}
