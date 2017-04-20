package com.timeron.timeronwallet.constant;

/**
 * Created by Timeron on 2017-03-13.
 *
 */

public enum Account {
    MOJE_KONTO(1, "Moje konto"), LOKATA(2, "Lokata"), EURO(3, "Euro"), SWINKA(4, "Świnka");

    private int id;
    private String account;

    Account(int id, String account) {
        this.id = id;
        this.account = account;
    }

    public int getId(){
        return this.id;
    }

    public String getName(){
        return this.account;
    }

    @Override
    public String toString() {
        return this.account;
    }
}
