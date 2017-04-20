package com.timeron.timeronwallet.constant;

/**
 * Created by Timeron on 2017-03-13.
 *
 */

public enum Type {
    BANK(1, "Bank"),
    SWINKA(2, "Świnka"),
    ANITA(3, "Anita"),
    SODEXO(4, "Sodexo"),
    PIZZA(5, "Pizza"),
    ART_S(6, "Artykuły spożywcze"),
    PIWO(7, "Piwo"),
    ROZRYWKA(8, "Rozrywka, kultura"),
    PREZENTY(9, "Prezenty"),
    POSZUKIWANIE(10, "Poszukiwanie"),
    WYNAGRODZENIE(11, "Wynagrodzenie, przychód"),
    RESTAURACJA(12, "Jedzenie poza domem"),
    PALIWO(13, "Paliwo"),
    TRANSPORT(14, "Transport"),
    TEL(15, "Telefon, internet"),
    SPORT(16, "Sport"),
    CZYNSZ(17, "Czynsz"),
    WAKACJE(18, "Wakacje"),
    INNE(19, "Inne"),
    ADMINISTRACJA(20, "Administracja"),
    CZYSTOSC(21, "Środki czystości"),
    DOSKONALENIE(22, "Doskonalenie"),
    ZAKLADY(23, "Zaklady"),
    PODATEK(24, "Podatek"),
    MED(25, "Med"),
    SAMOCHOD(26, "Samochód"),
    ELEKTRONIKA(27, "Elektronika"),
    DOM(28, "Dom"),
    GARDEROBA(29, "Garderoba"),
    KOSMETYKI(30, "Kosmetyki");

    private int id;
    private String type;

    Type(int id, String type) {
        this.id = id;
        this.type = type;
    }

    @Override
    public String toString(){
        return type;
    }

    public int getId(){
        return id;
    }


    public String getName() {
        return type;
    }
}
