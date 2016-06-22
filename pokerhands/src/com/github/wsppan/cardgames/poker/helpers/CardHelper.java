package com.github.wsppan.cardgames.poker.helpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import com.github.wsppan.cardgames.playingcards.exceptions.IllegalCardException;
import com.github.wsppan.cardgames.playingcards.french.Card;
import com.github.wsppan.cardgames.playingcards.french.Rank;

public class CardHelper {

    public static Collection<Card> reevaluateAceInSequence(Collection<Card> cards) {
	if (cards.stream().anyMatch(card -> card.getRank().asInt() == 5)) {
	    Collection<Card> cardsModified = new ArrayList<Card>();
	    cards.forEach(card -> { 
		if(card.getRank().equals(Rank.ACE_H)) {
		    cardsModified.add(new Card(Rank.ACE_L, card.getSuit()));
		} else {
		    cardsModified.add(card);
		}
	    });
	    return cardsModified;
	}
	return cards;
    }

    public static Collection<Card> parseCardsFromString(String stringToParse) {
	Collection<Card> cards = new ArrayList<Card>();
	Arrays.asList(stringToParse.split(" ")).forEach(card -> cards.add(parseCard(card)));
	return cards;
    }

    public static Card parseCard(String cardAsString) {
	if (cardAsString.length() < 2) {
	    throw new IllegalArgumentException("Input string must be at least two characters");
	}
	try {

	    Card card = new Card(cardAsString);
	    return card;
	} catch (IllegalCardException e) {
	    throw new IllegalArgumentException("Input string must be a valid Poker Hand Notation");
	}
    }
}