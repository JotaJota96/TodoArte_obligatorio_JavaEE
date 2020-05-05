package com.TodoArte.Classes;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.*;

@Entity
@Table(name = "sitio")
public class Sitio implements Serializable {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
	
	@Lob
    @Column(name = "imagenPortada")
    private byte[] imagenPortada;
	
	@Column(name = "precioPremium")
    private float precioPremium;
    
	@Column(name = "colorDeFondo")
    private String colorDeFondo;
	
	@Column(name = "colorDeMenu")
    private String colorDeMenu;
	
	@Column(name = "colorDeTexto")
    private String colorDeTexto;
	
	@Column(name = "rrssYouTube")
    private String rrssYouTube;
	
	@Column(name = "rrssFacebook")
    private String rrssFacebook;
	
	@Column(name = "rrssInstagram")
    private String rrssInstagram;
	
	@Column(name = "rrssTwitter")
    private String rrssTwitter;
    
	@Column(name = "seccionTwitter")
    private int seccionTwitter;
	
	@ManyToOne()
    @JoinColumn(name = "idCategoria")
    private CategoriaSitio miCategoria;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nomber_Artista")
    private Artista miArtista;
	
	@ManyToOne()
    @JoinColumn(name = "idFuente")
    private Fuente miFuente;
    
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_Sitio")
	@MapKey(name = "id")
    private Map<Integer, FanSigueSitio> MisFans;
    
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_Sitio")
	@MapKey(name = "id")
    private Map<Integer, QyAProgramado> MisQyA;
    
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_Sitio")
	@MapKey(name = "id")
    private Map<Integer, Contenido> MisContenidos;

    public Sitio() {
        this.MisFans = new TreeMap<Integer, FanSigueSitio>();
        this.MisQyA = new TreeMap<Integer, QyAProgramado>();
        this.MisContenidos = new TreeMap<Integer, Contenido>();
    }
  
    public Sitio(int id, byte[] imagenPortada, float precioPremium, String colorDeFondo, String colorDeMenu, String colorDeTexto, String rrssYouTube, String rrssFacebook, String rrssInstagram, String rrssTwitter, int seccionTwitter, CategoriaSitio miCategoria, Artista miArtista, Fuente miFuente) {
		this.id = id;
		this.imagenPortada = imagenPortada;
		this.precioPremium = precioPremium;
		this.colorDeFondo = colorDeFondo;
		this.colorDeMenu = colorDeMenu;
		this.colorDeTexto = colorDeTexto;
		this.rrssYouTube = rrssYouTube;
		this.rrssFacebook = rrssFacebook;
		this.rrssInstagram = rrssInstagram;
		this.rrssTwitter = rrssTwitter;
		this.seccionTwitter = seccionTwitter;
		this.miCategoria = miCategoria;
		this.miArtista = miArtista;
		this.miFuente = miFuente;
        this.MisFans = new TreeMap<Integer, FanSigueSitio>();
        this.MisQyA = new TreeMap<Integer, QyAProgramado>();
        this.MisContenidos = new TreeMap<Integer, Contenido>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte[] getImagenPortada() {
		return imagenPortada;
	}

	public void setImagenPortada(byte[] imagenPortada) {
		this.imagenPortada = imagenPortada;
	}

	public float getPrecioPremium() {
		return precioPremium;
	}

	public void setPrecioPremium(float precioPremium) {
		this.precioPremium = precioPremium;
	}

	public String getColorDeFondo() {
		return colorDeFondo;
	}

	public void setColorDeFondo(String colorDeFondo) {
		this.colorDeFondo = colorDeFondo;
	}

	public String getColorDeMenu() {
		return colorDeMenu;
	}

	public void setColorDeMenu(String colorDeMenu) {
		this.colorDeMenu = colorDeMenu;
	}

	public String getColorDeTexto() {
		return colorDeTexto;
	}

	public void setColorDeTexto(String colorDeTexto) {
		this.colorDeTexto = colorDeTexto;
	}

	public String getRrssYouTube() {
		return rrssYouTube;
	}

	public void setRrssYouTube(String rrssYouTube) {
		this.rrssYouTube = rrssYouTube;
	}

	public String getRrssFacebook() {
		return rrssFacebook;
	}

	public void setRrssFacebook(String rrssFacebook) {
		this.rrssFacebook = rrssFacebook;
	}

	public String getRrssInstagram() {
		return rrssInstagram;
	}

	public void setRrssInstagram(String rrssInstagram) {
		this.rrssInstagram = rrssInstagram;
	}

	public String getRrssTwitter() {
		return rrssTwitter;
	}

	public void setRrssTwitter(String rrssTwitter) {
		this.rrssTwitter = rrssTwitter;
	}

	public int getSeccionTwitter() {
		return seccionTwitter;
	}

	public void setSeccionTwitter(int seccionTwitter) {
		this.seccionTwitter = seccionTwitter;
	}

	public CategoriaSitio getMiCategoria() {
		return miCategoria;
	}

	public void setMiCategoria(CategoriaSitio miCategoria) {
		this.miCategoria = miCategoria;
	}

	public Artista getMiArtista() {
		return miArtista;
	}

	public void setMiArtista(Artista miArtista) {
		this.miArtista = miArtista;
	}

	public Fuente getMiFuente() {
		return miFuente;
	}

	public void setMiFuente(Fuente miFuente) {
		this.miFuente = miFuente;
	}

	public Map<Integer, FanSigueSitio> getMisFans() {
		return MisFans;
	}

	public void setMisFans(Map<Integer, FanSigueSitio> misFans) {
		MisFans = misFans;
	}

	public Map<Integer, QyAProgramado> getMisQyA() {
		return MisQyA;
	}

	public void setMisQyA(Map<Integer, QyAProgramado> misQyA) {
		MisQyA = misQyA;
	}

	public Map<Integer, Contenido> getMisContenidos() {
		return MisContenidos;
	}

	public void setMisContenidos(Map<Integer, Contenido> misContenidos) {
		MisContenidos = misContenidos;
	}

    

    
}