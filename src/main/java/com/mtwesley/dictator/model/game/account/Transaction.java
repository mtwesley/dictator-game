package com.mtwesley.dictator.model.game.account;

import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Transaction {
    protected String id;
    protected String type;
    protected int amount;
    protected Account from;
    protected Account to;
    protected TransactionStatus status = TransactionStatus.PENDING;

    public enum TransactionStatus {
        PENDING,
        ACCEPTED,
        REJECTED
    }

    public Transaction(String type, int amount, Account from, Account to) {
        this.type = type;
        this.amount = amount;
        this.from = from;
        this.to = to;
    }

    public Transaction(String type, int amount, Account from, Account to, TransactionStatus status) {
        this.type = type;
        this.amount = amount;
        this.from = from;
        this.to = to;
        this.status = status;
    }

    public Transaction(String type, int amount, Account from, Account to, String status) {
        this.type = type;
        this.amount = amount;
        this.from = from;
        this.to = to;
        this.status = TransactionStatus.valueOf(status);
    }
}