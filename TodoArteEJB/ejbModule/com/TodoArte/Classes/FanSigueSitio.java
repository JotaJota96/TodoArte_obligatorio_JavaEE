package com.TodoArte.Classes;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "comentario")
public class FanSigueSitio implements Serializable{
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
	
	@Column(name = "nickArtista")
    private String nickArtista;
    
	@Column(name = "bloqueado")
    private boolean bloqueado;
    
	@Column(name = "premiun")
    private boolean premiun;
    
    @OneToMany(cascade = CascadeType.ALL, 
            orphanRemoval = true)
    @JoinColumn(name = "nombre_Fan")
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