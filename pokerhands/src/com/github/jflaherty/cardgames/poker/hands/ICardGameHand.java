package com.github.jflaherty.cardgames.poker.hands;

import com.github.jflaherty.cardgames.poker.sequences.sets.ICardGameSequenceSet;

public interface ICardGameHand extends Comparable<ICardGameHand> {

    public ICardGameSequenceSet getSequenceSet();

    public int compareTo(ICardGameHand other);

}