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

import com.TodoArte.Enums.MensajesExcepciones;


@Entity
@Table(name = "valoracion")
public class Valoracion implements Serializable{
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "val")
	private int val;
	
	@ManyToOne()
	@JoinColumn(name = "id_Fan")
	private Fan miFan;

	public Valoracion() {
	}

	public Valoracion(int id, int val, Fan miFan) {
		if(val>0 && val<6){
            throw new RuntimeException(MensajesExcepciones.valoracion);
		}
		if(miFan == null){
            throw new RuntimeException(MensajesExcepciones.miFan);
		}
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
		if(val>0 && val<6){
            throw new RuntimeException(MensajesExcepciones.valoracion);
		}
		this.val = val;
	}

	public Fan getMiFan() {
		return miFan;
	}

	public void setMiFan(Fan miFan) {
		if(miFan == null){
            throw new RuntimeException(MensajesExcepciones.miFan);
		}
		this.miFan = miFan;
	}
}