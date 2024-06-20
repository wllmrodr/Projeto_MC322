package models;

import java.util.ArrayList;
public class BaralhoMaoController {

    private  BaralhoMao baralhoMao;
    private BaralhoMaoView view;

    public BaralhoMaoController(BaralhoMao baralhoMao, BaralhoMaoView view) {
        this.baralhoMao = baralhoMao;
        this.view = view;
    }

    public void perderCarta() {
        if (!baralhoMao.getBaralho().isEmpty()) {
            baralhoMao.getBaralho().remove(0);
        }
    }
}
