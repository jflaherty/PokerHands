package com.github.jflaherty.cardgames.poker.sequences;

import java.util.SortedSet;

import com.github.jflaherty.cardgames.playingcards.french.Card;
import com.github.jflaherty.cardgames.poker.hands.IHandRank;

public interface ISequence extends Comparable<ISequence> {
    public SortedSet<Card> getCardsInSequence();

    public int sequenceRank();

    public IHandRank getHandRank();

    public int compareTo(ISequence other);
}
