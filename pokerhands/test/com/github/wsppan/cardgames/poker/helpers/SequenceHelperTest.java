package com.github.wsppan.cardgames.poker.helpers;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.Test;

import com.github.wsppan.cardgames.playingcards.french.Card;
import com.github.wsppan.cardgames.playingcards.french.Rank;
import com.github.wsppan.cardgames.playingcards.french.Suit;
import com.github.wsppan.cardgames.poker.helpers.CardHelper;
import com.github.wsppan.cardgames.poker.helpers.SequenceHelper;

public class SequenceHelperTest {

    @Test
    public void shouldFindSequencePair() {
	// given
	List<Card> cards = new ArrayList<>();
	cards.add(new Card(Rank.EIGHT, Suit.DIAMONDS));
	cards.add(new Card(Rank.TEN, Suit.DIAMONDS));
	cards.add(new Card(Rank.QUEEN, Suit.HEARTS));
	cards.add(new Card(Rank.TWO, Suit.SPADES));
	cards.add(new Card(Rank.TEN, Suit.CLUBS));
	// when
	List<Card> expectedCards = new ArrayList<>();
	expectedCards.add(new Card(Rank.TEN, Suit.DIAMONDS));
	expectedCards.add(new Card(Rank.TEN, Suit.CLUBS));
	Collection<Card> foundCards = SequenceHelper.findRepeats(cards, Card::getRank, 2);
	// then
	assertEquals(expectedCards, foundCards);
    }

    @Test
    public void shouldFindSequenceThreeOfAKind() {
	// given
	List<Card> cards = new ArrayList<>();
	cards.add(new Card(Rank.EIGHT, Suit.DIAMONDS));
	cards.add(new Card(Rank.TEN, Suit.DIAMONDS));
	cards.add(new Card(Rank.TEN, Suit.HEARTS));
	cards.add(new Card(Rank.TWO, Suit.SPADES));
	cards.add(new Card(Rank.TEN, Suit.CLUBS));
	// when
	List<Card> expectedCards = new ArrayList<>();
	expectedCards.add(new Card(Rank.TEN, Suit.DIAMONDS));
	expectedCards.add(new Card(Rank.TEN, Suit.HEARTS));
	expectedCards.add(new Card(Rank.TEN, Suit.CLUBS));
	Collection<Card> foundCards = SequenceHelper.findRepeats(cards, Card::getRank, 3);
	// then
	assertEquals(expectedCards, foundCards);
    }

    @Test
    public void shouldFindNoSequence() {
	// given
	List<Card> cards = new ArrayList<>();
	cards.add(new Card(Rank.EIGHT, Suit.DIAMONDS));
	cards.add(new Card(Rank.TEN, Suit.DIAMONDS));
	cards.add(new Card(Rank.TEN, Suit.HEARTS));
	cards.add(new Card(Rank.TWO, Suit.SPADES));
	cards.add(new Card(Rank.TEN, Suit.CLUBS));
	// when
	List<Card> expectedCards = new ArrayList<>();
	Collection<Card> foundCards = SequenceHelper.findRepeats(cards, Card::getRank, 4);
	// then
	assertEquals(expectedCards, foundCards);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenNullParameterGiven() {
	// given
	SequenceHelper.findRepeats(null, Card::getRank, 0);
    }

    @Test
    public void shouldDetectFlush() {
	// given
	List<Card> cards = new ArrayList<>();
	cards.add(new Card(Rank.EIGHT, Suit.DIAMONDS));
	cards.add(new Card(Rank.TEN, Suit.DIAMONDS));
	cards.add(new Card(Rank.JACK, Suit.DIAMONDS));
	cards.add(new Card(Rank.ACE_H, Suit.DIAMONDS));
	cards.add(new Card(Rank.TWO, Suit.DIAMONDS));
	// when
	Collection<Card> foundCards = SequenceHelper.findRepeats(cards, Card::getSuit, 5);
	// then
	assertEquals(cards, foundCards);
    }

    @Test
    public void shouldFindStraight() {
	// given
	List<Card> cards = new ArrayList<>();
	cards.add(new Card(Rank.SIX, Suit.HEARTS));
	cards.add(new Card(Rank.THREE, Suit.DIAMONDS));
	cards.add(new Card(Rank.FIVE, Suit.DIAMONDS));
	cards.add(new Card(Rank.SEVEN, Suit.CLUBS));
	cards.add(new Card(Rank.FOUR, Suit.SPADES));
	// when
	boolean straight = SequenceHelper.isMonotonicByOne(cards);
	// then
	assertTrue(straight);
    }

    @Test
    public void shouldFindStraightWithHighAce() {
	// given
	List<Card> cards = new ArrayList<>();
	cards.add(new Card(Rank.ACE_H, Suit.HEARTS));
	cards.add(new Card(Rank.QUEEN, Suit.SPADES));
	cards.add(new Card(Rank.KING, Suit.DIAMONDS));
	cards.add(new Card(Rank.TEN, Suit.CLUBS));
	cards.add(new Card(Rank.JACK, Suit.SPADES));
	// when
	boolean straight = SequenceHelper.isMonotonicByOne(cards);
	// then
	assertTrue(straight);
    }

    @Test
    public void shouldFindStraightWithLowAce() {
	// given
	List<Card> cards = new ArrayList<>();
	cards.add(new Card(Rank.ACE_H, Suit.HEARTS));
	cards.add(new Card(Rank.TWO, Suit.SPADES));
	cards.add(new Card(Rank.FIVE, Suit.DIAMONDS));
	cards.add(new Card(Rank.FOUR, Suit.CLUBS));
	cards.add(new Card(Rank.THREE, Suit.SPADES));
	// when
	boolean straight = SequenceHelper.isMonotonicByOne(cards)
		|| SequenceHelper.isMonotonicByOne(CardHelper.reevaluateAceInSequence(cards));
	// then
	assertTrue(straight);
    }

    @Test
    public void shouldNotFindStraight() {
	// given
	List<Card> cards = new ArrayList<>();
	cards.add(new Card(Rank.EIGHT, Suit.HEARTS));
	cards.add(new Card(Rank.SIX, Suit.SPADES));
	cards.add(new Card(Rank.FIVE, Suit.DIAMONDS));
	cards.add(new Card(Rank.FOUR, Suit.CLUBS));
	cards.add(new Card(Rank.THREE, Suit.SPADES));
	// when
	boolean straight = SequenceHelper.isMonotonicByOne(cards);

	// then
	assertFalse(straight);
    }

    @Test
    public void shouldFindStraightEvenWhenIncomplete() {
	// given
	List<Card> cards = new ArrayList<>();
	cards.add(new Card(Rank.SIX, Suit.SPADES));
	cards.add(new Card(Rank.FIVE, Suit.DIAMONDS));
	cards.add(new Card(Rank.FOUR, Suit.CLUBS));
	cards.add(new Card(Rank.THREE, Suit.SPADES));
	// when
	boolean straight = SequenceHelper.isMonotonicByOne(cards);
	// then
	assertTrue(straight);
    }

    @Test
    public void shouldDetectPair() {
	// given
	SortedSet<Card> cards = new TreeSet<Card>();
	cards.add(new Card(Rank.ACE_H, Suit.CLUBS));
	cards.add(new Card(Rank.FIVE, Suit.CLUBS));
	cards.add(new Card(Rank.SIX, Suit.HEARTS));
	cards.add(new Card(Rank.TWO, Suit.CLUBS));
	cards.add(new Card(Rank.TWO, Suit.SPADES));
	// when
	boolean hasPair = SequenceHelper.hasRepeats(cards, Card::getRank, 2);
	// then
	assertTrue(hasPair);
    }

    @Test
    public void shouldDetectThree() {
	// given
	SortedSet<Card> cards = new TreeSet<Card>();
	cards.add(new Card(Rank.ACE_H, Suit.CLUBS));
	cards.add(new Card(Rank.FIVE, Suit.CLUBS));
	cards.add(new Card(Rank.TWO, Suit.HEARTS));
	cards.add(new Card(Rank.TWO, Suit.CLUBS));
	cards.add(new Card(Rank.TWO, Suit.SPADES));
	// when
	boolean hasThree = SequenceHelper.hasRepeats(cards, Card::getRank, 3);
	// then
	assertTrue(hasThree);
    }

    @Test
    public void shouldDetectTwoPairs() {
	// given
	SortedSet<Card> cards = new TreeSet<Card>();
	cards.add(new Card(Rank.ACE_H, Suit.CLUBS));
	cards.add(new Card(Rank.ACE_H, Suit.DIAMONDS));
	cards.add(new Card(Rank.TEN, Suit.HEARTS));
	cards.add(new Card(Rank.TWO, Suit.CLUBS));
	cards.add(new Card(Rank.TWO, Suit.SPADES));
	// when
	boolean hasTwoPairs = SequenceHelper.findRepeats(cards, Card::getRank, 2).size() == 4;
	// then
	assertTrue(hasTwoPairs);
    }
}
