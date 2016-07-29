package com.github.wsppan.cardgames.poker.sequences.sets;

import static org.junit.Assert.*;

import java.util.*;

import static com.github.wsppan.cardgames.playingcards.french.Rank.*;
import static com.github.wsppan.cardgames.playingcards.french.Suit.*;
import static com.github.wsppan.cardgames.poker.hands.PokerHandRank.*;

import org.junit.Test;

import com.github.wsppan.cardgames.playingcards.french.Card;
import com.github.wsppan.cardgames.poker.sequences.ISequence;
import com.github.wsppan.cardgames.poker.sequences.sets.SequenceDetector;

public class SequenceDetectorTest {
    private SequenceDetector detector = new SequenceDetector();

    @Test
    public void shouldDetectHighCard() {
	// given
	SortedSet<Card> cards = new TreeSet<Card>();
	cards.add(new Card(ACE_H, CLUBS));
	cards.add(new Card(FIVE, CLUBS));
	cards.add(new Card(FOUR, CLUBS));
	cards.add(new Card(JACK, CLUBS));
	cards.add(new Card(TWO, SPADES));
	// when
	ISequence detected = detector.detectSequence(cards);
	// then
	assertEquals(HIGH_CARD, detected.getHandRank());
    }

    @Test
    public void shouldDetectPair() {
	// given
	SortedSet<Card> cards = new TreeSet<Card>();
	cards.add(new Card(ACE_H, CLUBS));
	cards.add(new Card(FIVE, CLUBS));
	cards.add(new Card(THREE, HEARTS));
	cards.add(new Card(TWO, CLUBS));
	cards.add(new Card(TWO, SPADES));
	// when
	ISequence detected = detector.detectSequence(cards);
	// then
	assertEquals(PAIR, detected.getHandRank());
    }

    @Test
    public void shouldDetectThree() {
	// given
	SortedSet<Card> cards = new TreeSet<Card>();
	cards.add(new Card(TWO, DIAMONDS));
	cards.add(new Card(FIVE, CLUBS));
	cards.add(new Card(THREE, HEARTS));
	cards.add(new Card(TWO, CLUBS));
	cards.add(new Card(TWO, SPADES));
	// when
	ISequence detected = detector.detectSequence(cards);
	// then
	assertEquals(THREE_OF_A_KIND, detected.getHandRank());
    }

    @Test
    public void shouldDetectFour() {
	// given
	SortedSet<Card> cards = new TreeSet<Card>();
	cards.add(new Card(TWO, DIAMONDS));
	cards.add(new Card(TWO, HEARTS));
	cards.add(new Card(THREE, HEARTS));
	cards.add(new Card(TWO, CLUBS));
	cards.add(new Card(TWO, SPADES));
	// when
	ISequence detected = detector.detectSequence(cards);
	// then
	assertEquals(FOUR_OF_A_KIND, detected.getHandRank());
    }

    @Test
    public void shouldDetectFullHouse() {
	// given
	SortedSet<Card> cards = new TreeSet<Card>();
	cards.add(new Card(ACE_H, CLUBS));
	cards.add(new Card(ACE_H, DIAMONDS));
	cards.add(new Card(ACE_H, HEARTS));
	cards.add(new Card(TWO, CLUBS));
	cards.add(new Card(TWO, SPADES));
	// when
	ISequence detected = detector.detectSequence(cards);
	// then
	assertEquals(FULL_HOUSE, detected.getHandRank());
    }

    @Test
    public void shouldDetectTwoPairs() {
	// given
	SortedSet<Card> cards = new TreeSet<Card>();
	cards.add(new Card(ACE_H, CLUBS));
	cards.add(new Card(ACE_H, DIAMONDS));
	cards.add(new Card(TEN, HEARTS));
	cards.add(new Card(TWO, CLUBS));
	cards.add(new Card(TWO, SPADES));
	// when
	ISequence detected = detector.detectSequence(cards);
	// then
	assertEquals(TWO_PAIRS, detected.getHandRank());
    }

    @Test
    public void shouldDetectStraightWithLowAce() {
	// given
	SortedSet<Card> cards = new TreeSet<Card>();
	cards.add(new Card(ACE_H, CLUBS));
	cards.add(new Card(TWO, DIAMONDS));
	cards.add(new Card(THREE, HEARTS));
	cards.add(new Card(FOUR, CLUBS));
	cards.add(new Card(FIVE, SPADES));
	// when
	ISequence detected = detector.detectSequence(cards);
	// then
	assertEquals(STRAIGHT, detected.getHandRank());
    }

    @Test
    public void shouldDetectAnotherStraight() {
	// given
	SortedSet<Card> cards = new TreeSet<Card>();
	cards.add(new Card(SIX, CLUBS));
	cards.add(new Card(TWO, DIAMONDS));
	cards.add(new Card(THREE, HEARTS));
	cards.add(new Card(FOUR, CLUBS));
	cards.add(new Card(FIVE, SPADES));
	// when
	ISequence detected = detector.detectSequence(cards);
	// then
	assertEquals(STRAIGHT, detected.getHandRank());
    }

    @Test
    public void shouldDetectStraightWithHighAce() {
	// given
	SortedSet<Card> cards = new TreeSet<Card>();
	cards.add(new Card(TEN, CLUBS));
	cards.add(new Card(JACK, DIAMONDS));
	cards.add(new Card(ACE_H, HEARTS));
	cards.add(new Card(KING, CLUBS));
	cards.add(new Card(QUEEN, SPADES));
	// when
	ISequence detected = detector.detectSequence(cards);
	// then
	assertEquals(STRAIGHT, detected.getHandRank());
    }

    @Test
    public void shouldDetectFlush() {
	// given
	SortedSet<Card> cards = new TreeSet<Card>();
	cards.add(new Card(SIX, CLUBS));
	cards.add(new Card(JACK, CLUBS));
	cards.add(new Card(ACE_H, CLUBS));
	cards.add(new Card(KING, CLUBS));
	cards.add(new Card(QUEEN, CLUBS));
	// when
	ISequence detected = detector.detectSequence(cards);
	// then
	assertEquals(FLUSH, detected.getHandRank());
    }

    @Test
    public void StraightFlush() {
	// given
	SortedSet<Card> cards = new TreeSet<Card>();
	cards.add(new Card(TEN, CLUBS));
	cards.add(new Card(JACK, CLUBS));
	cards.add(new Card(ACE_H, CLUBS));
	cards.add(new Card(KING, CLUBS));
	cards.add(new Card(QUEEN, CLUBS));
	// when
	ISequence detected = detector.detectSequence(cards);
	// then
	assertEquals(STRAIGHT_FLUSH, detected.getHandRank());
    }
}