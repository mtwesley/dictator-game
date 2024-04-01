package com.mtwesley.dictator.model.game.account;

public abstract class Transaction {
    protected Account from;
    protected Account to;
    protected int amount;
    protected Status status;

    public enum Status {
        PENDING,
        ACCEPTED,
        REJECTED
    }


}