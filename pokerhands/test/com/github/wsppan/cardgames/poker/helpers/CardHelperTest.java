package com.github.wsppan.cardgames.poker.helpers;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

import com.github.wsppan.cardgames.playingcards.french.Card;
import com.github.wsppan.cardgames.playingcards.french.Rank;
import com.github.wsppan.cardgames.playingcards.french.Suit;
import com.github.wsppan.cardgames.poker.helpers.CardHelper;

public class CardHelperTest {

    @Test
    public void shouldReplaceAce() {
	// given
	List<Card> cards = new ArrayList<>();
	cards.add(new Card(Rank.ACE_H, Suit.HEARTS));
	cards.add(new Card(Rank.TWO, Suit.SPADES));
	cards.add(new Card(Rank.FIVE, Suit.DIAMONDS));
	cards.add(new Card(Rank.FOUR, Suit.CLUBS));
	cards.add(new Card(Rank.THREE, Suit.SPADES));
	// when
	Collection<Card> cardsModified = CardHelper.reevaluateAceInSequence(cards);
	boolean hasNoHighAce = cardsModified.stream().allMatch(c -> !c.getRank().equals(Rank.ACE_H));
	boolean hasLowAce = cardsModified.stream().anyMatch(c -> c.getRank().equals(Rank.ACE_L));
	// then
	assertTrue(hasNoHighAce && hasLowAce);
    }

    @Test
    public void shouldReplaceAllAces() {
	// given
	List<Card> cards = new ArrayList<>();
	cards.add(new Card(Rank.ACE_H, Suit.HEARTS));
	cards.add(new Card(Rank.ACE_H, Suit.SPADES));
	cards.add(new Card(Rank.ACE_H, Suit.DIAMONDS));
	cards.add(new Card(Rank.FIVE, Suit.CLUBS));
	cards.add(new Card(Rank.ACE_H, Suit.SPADES));
	// when
	Collection<Card> cardsModified = CardHelper.reevaluateAceInSequence(cards);
	boolean hasNoHighAce = cardsModified.stream().allMatch(c -> !c.getRank().equals(Rank.ACE_H));
	boolean hasLowAce = cardsModified.stream().anyMatch(c -> c.getRank().equals(Rank.ACE_L));
	boolean sizeUnchanged = cards.size() == cardsModified.size();
	// then
	assertTrue(hasNoHighAce && hasLowAce && sizeUnchanged);
    }

    @Test
    public void shouldNotModifyList() {
	// given
	List<Card> cards = new ArrayList<>();
	cards.add(new Card(Rank.SIX, Suit.HEARTS));
	cards.add(new Card(Rank.TWO, Suit.SPADES));
	cards.add(new Card(Rank.FIVE, Suit.DIAMONDS));
	cards.add(new Card(Rank.FOUR, Suit.CLUBS));
	cards.add(new Card(Rank.THREE, Suit.SPADES));
	// when
	Collection<Card> cardsModified = CardHelper.reevaluateAceInSequence(cards);
	// then
	assertTrue(cardsModified.equals(cards));
    }

    @Test
    public void shouldParseSingleCard1() {
	// given
	String cardString = "TD";
	// when
	Card card = CardHelper.parseCard(cardString);
	// then
	assertEquals(new Card(Rank.TEN, Suit.DIAMONDS), card);
    }

    @Test
    public void shouldParseSingleCard2() {
	// given
	String cardString = "AH";
	// when
	Card card = CardHelper.parseCard(cardString);
	// then
	assertEquals(new Card(Rank.ACE_H, Suit.HEARTS), card);
    }

    @Test
    public void shouldParseSingleCard3() {
	// given
	String cardString = "2S";
	// when
	Card card = CardHelper.parseCard(cardString);
	// then
	assertEquals(new Card(Rank.TWO, Suit.SPADES), card);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowErrorWhenInputIsToShort() {
	// given
	@SuppressWarnings("unused")
	Card card = CardHelper.parseCard("A");
    }

    @Test
    public void shouldProperlyParseCardsIntoList() {
	// given
	String cardsCollection = "2S 3D 4H 5D 6C";
	// then
	Collection<Card> cards = CardHelper.parseCardsFromString(cardsCollection);
	// then
	int expectedCardsAmount = 5;
	assertEquals(expectedCardsAmount, cards.size());
    }

    @Test
    public void shouldProperlyParseOneCardIntoList() {
	// given
	String cardsCollection = "AH";
	// then
	Collection<Card> cards = CardHelper.parseCardsFromString(cardsCollection);
	// then
	int expectedCardsAmount = 1;
	assertEquals(expectedCardsAmount, cards.size());
    }
}
