package com.github.jflaherty.cardgames.poker.cards;

import static org.junit.Assert.*;

import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

import com.github.jflaherty.cardgames.playingcards.french.Card;
import com.github.jflaherty.cardgames.playingcards.french.Rank;
import com.github.jflaherty.cardgames.playingcards.french.Suit;

public class CardTest {

    @Test
    public void shouldReturnGreaterThanZero() {
	// given
	Card two = new Card(Rank.TWO, Suit.CLUBS);
	Card three = new Card(Rank.THREE, Suit.DIAMONDS);
	// when
	int compareResult = three.compareTo(two);
	// then
	assertTrue(compareResult > 0);
    }

    @Test
    public void shouldReturnLessThanZero() {
	// given
	Card ace = new Card(Rank.ACE_H, Suit.CLUBS);
	Card jack = new Card(Rank.JACK, Suit.HEARTS);
	// when
	int compareResult = jack.compareTo(ace);
	// then
	assertTrue(compareResult < 0);
    }

    @Test
    public void shouldReturnThatSameValueCardsAreNotEqual() {
	// given
	Card firstQueen = new Card(Rank.QUEEN, Suit.DIAMONDS);
	Card secondQueen = new Card(Rank.QUEEN, Suit.HEARTS);
	// when
	int compareResult = secondQueen.compareTo(firstQueen);
	// then
	assertFalse(compareResult == 0);
    }

    @Test
    public void shouldCheckThatAreEqual() {
	// given
	Card firstQueen = new Card(Rank.QUEEN, Suit.SPADES);
	Card secondQueen = new Card(Rank.QUEEN, Suit.SPADES);
	// when
	int isEqual = secondQueen.compareTo(firstQueen);
	// then
	assertEquals(0, isEqual);
    }

    @Test
    public void shouldCheckThatSameValueCardsAreNotEqual() {
	// given
	Card firstQueen = new Card(Rank.QUEEN, Suit.SPADES);
	Card secondQueen = new Card(Rank.QUEEN, Suit.DIAMONDS);
	// when
	boolean isEqual = secondQueen.equals(firstQueen);
	// then
	assertFalse(isEqual);
    }

    @Test
    public void shouldCheckThatAreNotEqual() {
	// given
	Card queen = new Card(Rank.QUEEN, Suit.HEARTS);
	Card king = new Card(Rank.KING, Suit.HEARTS);
	// when
	boolean isEqual = king.equals(queen);
	// then
	assertFalse(isEqual);
    }

    @Test
    public void shouldCorrectlyAddUpToTreeMap() {
	// given
	Set<Card> cardsSet = new TreeSet<Card>();
	cardsSet.add(new Card(Rank.ACE_H, Suit.DIAMONDS));
	cardsSet.add(new Card(Rank.ACE_H, Suit.CLUBS));
	// when
	int setSize = cardsSet.size();
	// then
	int expectedSetSize = 2;
	assertEquals(expectedSetSize, setSize);
    }

    @Test
    public void shouldCorrectlyAddUpToTreeMapWhenSame() {
	// given
	Set<Card> cardsSet = new TreeSet<Card>();
	cardsSet.add(new Card(Rank.ACE_H, Suit.DIAMONDS));
	cardsSet.add(new Card(Rank.ACE_H, Suit.DIAMONDS));
	// when
	int setSize = cardsSet.size();
	// then
	int expectedSetSize = 1;
	assertEquals(expectedSetSize, setSize);
    }

}
