package com.github.jflaherty.cardgames.poker.hands;

import java.util.Map;
import java.util.HashMap;

public enum PokerHandRank implements IHandRank {
    HIGH_CARD(0, 0), 
    PAIR(1, 1), 
    TWO_PAIRS(2, PAIR.rank() * 2), 
    THREE_OF_A_KIND(3, 4), 
    STRAIGHT(4, 8), 
    FLUSH(5, 16), 
    FULL_HOUSE(6, PAIR.rank() + THREE_OF_A_KIND.rank()), 
    FOUR_OF_A_KIND(7, 32), 
    STRAIGHT_FLUSH(8, STRAIGHT.rank() + FLUSH.rank());
    


    private int value;
    private int rank;

    private static Map<Integer, PokerHandRank> pokerHandRankMap = new HashMap<Integer, PokerHandRank>(9, 1.0f);
    static {
	for (PokerHandRank pokerHandRank : PokerHandRank.values()) {
	    pokerHandRankMap.put(pokerHandRank.rank, pokerHandRank);
	}
    }

    PokerHandRank(int value, int rank) {
	this.value = value;
	this.rank = rank;
    }

    public int value() {
	return value;
    }

    public int rank() {
	return rank;
    }

    public static PokerHandRank fromHandRankMap(int rank) {
	return pokerHandRankMap.get(rank);
    }
}