package com.TodoArte.Classes;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "fanSigueSitio")
public class FanSigueSitio implements Serializable {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
	
	@Column(name = "nickArtista")
    private String nickArtista;
    
	@Column(name = "bloqueado")
    private boolean bloqueado;
    
	@Column(name = "premiun")
    private boolean premiun;
    
    @ManyToOne
    @JoinColumn(name = "id_fan")
    private Fan miFan;

    public FanSigueSitio() {
    }

    public FanSigueSitio(int id, String nickArtista, boolean bloqueado, boolean premiun, Fan fan) {
        this.id = id;
        this.nickArtista = nickArtista;     
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

    public String getNickArtista() {
        return this.nickArtista;
    }

    public void setNickArtista(String nickArtista) {
        this.nickArtista = nickArtista;
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