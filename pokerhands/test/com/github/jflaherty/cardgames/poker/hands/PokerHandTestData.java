package com.github.jflaherty.cardgames.poker.hands;

import java.util.ArrayList;
import java.util.Collection;

import com.github.jflaherty.cardgames.playingcards.french.Card;
import com.github.jflaherty.cardgames.poker.helpers.CardHelper;

public class PokerHandTestData {
    private Collection<Card> playerOneCards;
    private Collection<Card> playerTwoCards;
    private boolean playerOneWins;
    private String testName;

    private PokerHandTestData(Builder builder) {
	this.playerOneCards = builder.playerOneCards;
	this.playerTwoCards = builder.playerTwoCards;
	this.playerOneWins = builder.playerOneWins;
	this.testName = builder.testName;
    }

    public Collection<Card> getPlayerOneCards() {
	return playerOneCards;
    }

    public Collection<Card> getPlayerTwoCards() {
	return playerTwoCards;
    }

    public boolean playerOneWins() {
	return playerOneWins;
    }

    public String getTestName() {
	return testName;
    }

    public static Builder testBuilderFactory() {
	return new Builder();
    }

    static class Builder {
	private Collection<Card> playerOneCards;
	private Collection<Card> playerTwoCards;
	private boolean playerOneWins;
	private String testName;

	public Builder() {
	    this.playerOneCards = new ArrayList<Card>();
	    this.playerTwoCards = new ArrayList<Card>();
	}

	public Builder withPlayerOneCards(String cards) {
	    this.playerOneCards.addAll(CardHelper.parseCardsFromString(cards));
	    return this;
	}

	public Builder withPlayerTwoCards(String cards) {
	    this.playerTwoCards.addAll(CardHelper.parseCardsFromString(cards));
	    return this;
	}

	public Builder andPlayerOneWins(boolean win) {
	    this.playerOneWins = win;
	    return this;
	}

	public Builder withTestName(String name) {
	    this.testName = name;
	    return this;
	}

	public PokerHandTestData build() {
	    return new PokerHandTestData(this);
	}
    }
}
