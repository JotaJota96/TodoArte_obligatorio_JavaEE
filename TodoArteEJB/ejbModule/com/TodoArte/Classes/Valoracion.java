package com.TodoArte.Classes;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "valoracion")
public class Valoracion implements Serializable{
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
	private int id;
	
	@Column(name = "val")
	private int val;
	
	@OneToMany(cascade = CascadeType.ALL, 
            orphanRemoval = true)
    @JoinColumn(name = "nombre_Fan")
	private Fan miFan;

	public Valoracion() {
	}

	public Valoracion(int id, int val, Fan miFan) {
		this.id = id;
		this.val = val;
		this.miFan = miFan;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int Id) {
		this.id = Id;
	}

	public int getVal() {
		return this.val;
	}

	public void setVal(int val) {
		this.val = val;
	}

	public Fan getMiFan() {
		return miFan;
	}

	public void setMiFan(Fan miFan) {
		this.miFan = miFan;
	}
}