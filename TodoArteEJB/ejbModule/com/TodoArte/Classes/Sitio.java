package com.TodoArte.Classes;

import java.util.Map;
import java.util.TreeMap;

public class Sitio {
    private int id;
    private byte[] imagenPortada;
    private float precioPremium;
    private String colorDeFondo;
    private String colorDeMenu;
    private String colorDeTexto;
    private String rrssYouTube;
    private String rrssFacebook;
    private String rrssInstagram;
    private String rrssTwitter;
    private int seccionTwitter;
    private CategoriaSitio miCategoria;
    private Artista miArtista;
    private Fuente miFuente;
    private Map<Integer, FanSigueSitio> MisFans;
    private Map<Integer, QyAProgramado> MisQyA;
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