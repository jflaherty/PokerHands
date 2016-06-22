package com.github.wsppan.cardgames.poker.sequences;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.*;

import com.github.wsppan.cardgames.playingcards.french.Card;
import com.github.wsppan.cardgames.playingcards.french.Rank;
import com.github.wsppan.cardgames.playingcards.french.Suit;
import com.github.wsppan.cardgames.poker.hands.PokerHandRank;

public class FullHouseTest {

    @Test
    public void shouldCompareToLowerFullHouse() {
	// given
	Set<Card> fullHouse = new TreeSet<Card>();
	fullHouse.add(new Card(Rank.EIGHT, Suit.HEARTS));
	fullHouse.add(new Card(Rank.EIGHT, Suit.CLUBS));
	fullHouse.add(new Card(Rank.JACK, Suit.HEARTS));
	fullHouse.add(new Card(Rank.JACK, Suit.CLUBS));
	fullHouse.add(new Card(Rank.JACK, Suit.SPADES));
	ISequence fullHouseSequence = new FullHouseSequence(PokerHandRank.FULL_HOUSE, fullHouse);

	Set<Card> otherFullHouse = new TreeSet<Card>();
	otherFullHouse.add(new Card(Rank.TWO, Suit.CLUBS));
	otherFullHouse.add(new Card(Rank.TWO, Suit.SPADES));
	otherFullHouse.add(new Card(Rank.FOUR, Suit.HEARTS));
	otherFullHouse.add(new Card(Rank.FOUR, Suit.DIAMONDS));
	otherFullHouse.add(new Card(Rank.FOUR, Suit.CLUBS));
	ISequence lowerFullHouse = new FullHouseSequence(PokerHandRank.FULL_HOUSE, otherFullHouse);
	// when
	int comparisonResult = fullHouseSequence.compareTo(lowerFullHouse);
	// then
	assertTrue(comparisonResult > 0);
    }

    @Test
    public void shouldCompareToHigherFullHouse() {
	// given
	Set<Card> fullHouse = new TreeSet<Card>();
	fullHouse.add(new Card(Rank.ACE_H, Suit.HEARTS));
	fullHouse.add(new Card(Rank.ACE_H, Suit.CLUBS));
	fullHouse.add(new Card(Rank.THREE, Suit.HEARTS));
	fullHouse.add(new Card(Rank.THREE, Suit.CLUBS));
	fullHouse.add(new Card(Rank.THREE, Suit.SPADES));
	ISequence fullHouseSequence = new FullHouseSequence(PokerHandRank.FULL_HOUSE, fullHouse);

	List<Card> otherFullHouse = new ArrayList<Card>();
	otherFullHouse.add(new Card(Rank.TWO, Suit.CLUBS));
	otherFullHouse.add(new Card(Rank.TWO, Suit.SPADES));
	otherFullHouse.add(new Card(Rank.FOUR, Suit.HEARTS));
	otherFullHouse.add(new Card(Rank.FOUR, Suit.DIAMONDS));
	otherFullHouse.add(new Card(Rank.FOUR, Suit.CLUBS));
	ISequence higherFullHouse = new FullHouseSequence(PokerHandRank.FULL_HOUSE, otherFullHouse);
	// when
	int comparisonResult = fullHouseSequence.compareTo(higherFullHouse);
	// then
	assertTrue(comparisonResult < 0);
    }

}
