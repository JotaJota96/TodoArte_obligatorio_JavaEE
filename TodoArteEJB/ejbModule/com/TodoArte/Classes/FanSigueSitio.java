package com.TodoArte.Classes;

public class FanSigueSitio {
    private int id;
    private String nickArrista;
    private boolean bloqueado;
    private boolean premiun;
    private Fan miFan;

    public FanSigueSitio() {
    }

    public FanSigueSitio(int id, String nickArrista, boolean bloqueado, boolean premiun, Fan fan) {
        this.id = id;
        this.nickArrista = nickArrista;
        this.bloqueado = bloqueado;
        this.premiun = premiun;
        this.miFan = fan;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickArrista() {
        return this.nickArrista;
    }

    public void setNickArrista(String nickArrista) {
        this.nickArrista = nickArrista;
    }

    public boolean getBloqueado() {
        return this.bloqueado;
    }

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    public boolean getPremiun() {
        return this.premiun;
    }

    public void setPremiun(boolean premiun) {
        this.premiun = premiun;
    }

	public Fan getMiFan() {
		return miFan;
	}

	public void setMiFan(Fan miFan) {
		this.miFan = miFan;
	}
}