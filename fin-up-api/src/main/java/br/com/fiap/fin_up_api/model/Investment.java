package br.com.fiap.fin_up_api.model;

import java.util.Random;

public class Investment {
    private Long id;
    private String name;
    private String icon;

    public Investment(Long id, String name, String icon) {
        this.id = Math.abs(new Random().nextLong());
        this.name = name;
        this.icon = icon;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIcon() {
        return icon;
    }
}
