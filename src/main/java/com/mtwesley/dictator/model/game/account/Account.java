package com.mtwesley.dictator.model.game.account;

public interface Account {
    public static Account SYSTEM = new Account() {
        @Override
        public int getBalance() {
            return 0;
        }
    };

    int getBalance();
}