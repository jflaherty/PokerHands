package com.github.wsppan.cardgames.poker.sequences;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

import com.github.wsppan.cardgames.playingcards.french.Card;
import com.github.wsppan.cardgames.playingcards.french.Rank;
import com.github.wsppan.cardgames.playingcards.french.Suit;
import com.github.wsppan.cardgames.poker.hands.PokerHandRank;
import com.github.wsppan.cardgames.poker.sequences.ISequence;
import com.github.wsppan.cardgames.poker.sequences.StraightSequence;

public class StraightSequenceTest {

    @Test
    public void shouldCompareWithHigherStraight() {
	// given
	List<Card> straight = new ArrayList<Card>();
	straight.add(new Card(Rank.NINE, Suit.DIAMONDS));
	straight.add(new Card(Rank.TEN, Suit.HEARTS));
	straight.add(new Card(Rank.JACK, Suit.HEARTS));
	straight.add(new Card(Rank.QUEEN, Suit.HEARTS));
	straight.add(new Card(Rank.KING, Suit.HEARTS));
	ISequence straightSequence = new StraightSequence(PokerHandRank.STRAIGHT, straight);

	List<Card> otherStraight = new ArrayList<Card>();
	otherStraight.add(new Card(Rank.TEN, Suit.DIAMONDS));
	otherStraight.add(new Card(Rank.JACK, Suit.CLUBS));
	otherStraight.add(new Card(Rank.QUEEN, Suit.CLUBS));
	otherStraight.add(new Card(Rank.KING, Suit.CLUBS));
	otherStraight.add(new Card(Rank.ACE_H, Suit.CLUBS));
	ISequence higherStraight = new StraightSequence(PokerHandRank.STRAIGHT, otherStraight);
	// when
	int comparisonResult = straightSequence.compareTo(higherStraight);
	// then
	assertTrue(comparisonResult < 0);
    }

    @Test
    public void shouldCompareWithLowerStraight() {
	// given
	List<Card> straight = new ArrayList<Card>();
	straight.add(new Card(Rank.NINE, Suit.DIAMONDS));
	straight.add(new Card(Rank.TEN, Suit.HEARTS));
	straight.add(new Card(Rank.JACK, Suit.HEARTS));
	straight.add(new Card(Rank.QUEEN, Suit.HEARTS));
	straight.add(new Card(Rank.KING, Suit.HEARTS));
	ISequence straightSequence = new StraightSequence(PokerHandRank.STRAIGHT, straight);

	List<Card> otherStraight = new ArrayList<Card>();
	otherStraight.add(new Card(Rank.TWO, Suit.DIAMONDS));
	otherStraight.add(new Card(Rank.THREE, Suit.CLUBS));
	otherStraight.add(new Card(Rank.FOUR, Suit.CLUBS));
	otherStraight.add(new Card(Rank.FIVE, Suit.CLUBS));
	otherStraight.add(new Card(Rank.ACE_H, Suit.CLUBS));
	StraightSequence lowerStraight = new StraightSequence(PokerHandRank.STRAIGHT, otherStraight);
	// when
	int comparisonResult = straightSequence.compareTo(lowerStraight);
	// then
	assertTrue(comparisonResult > 0);
    }

    @Test
    public void shouldCompareSameStraights() {
	// given
	List<Card> straight = new ArrayList<Card>();
	straight.add(new Card(Rank.NINE, Suit.DIAMONDS));
	straight.add(new Card(Rank.TEN, Suit.HEARTS));
	straight.add(new Card(Rank.JACK, Suit.HEARTS));
	straight.add(new Card(Rank.QUEEN, Suit.HEARTS));
	straight.add(new Card(Rank.KING, Suit.HEARTS));
	ISequence straightSequence = new StraightSequence(PokerHandRank.STRAIGHT, straight);

	List<Card> otherStraight = new ArrayList<Card>();
	otherStraight.add(new Card(Rank.KING, Suit.SPADES));
	otherStraight.add(new Card(Rank.QUEEN, Suit.CLUBS));
	otherStraight.add(new Card(Rank.JACK, Suit.CLUBS));
	otherStraight.add(new Card(Rank.TEN, Suit.CLUBS));
	otherStraight.add(new Card(Rank.NINE, Suit.CLUBS));
	ISequence sameStraight = new StraightSequence(PokerHandRank.STRAIGHT, otherStraight);
	// when
	int comparisonResult = straightSequence.compareTo(sameStraight);
	// then
	assertTrue(comparisonResult == 0);
    }

}
