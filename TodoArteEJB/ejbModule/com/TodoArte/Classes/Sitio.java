package com.TodoArte.Classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.TodoArte.Enums.MensajesExcepciones;
import com.TodoArte.JPAControllerClasses.ContenidoJpaController;
import com.TodoArte.JPAControllerClasses.FanSigueSitioJpaController;
import com.TodoArte.JPAControllerClasses.QyAProgramadoJpaController;


@Entity
@Table(name = "sitio")
public class Sitio implements Serializable {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
	
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
    @JoinColumn(name = "id_Categoria")
    private CategoriaSitio miCategoria;
	
	@OneToOne(mappedBy = "miSitio")
    private Artista miArtista;
	
	@ManyToOne()
    @JoinColumn(name = "id_Fuente")
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
    	if(precioPremium < 0){
            throw new RuntimeException(MensajesExcepciones.precio);
		}
    	if(miCategoria == null){
            throw new RuntimeException(MensajesExcepciones.categoria);
		}
    	/*
    	if(miArtista == null){
            throw new RuntimeException(MensajesExcepciones.artista);
		}
    	*/
    	if(miFuente == null){
            throw new RuntimeException(MensajesExcepciones.fuente);
		}
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
    //**********************************************************************
	public void comprarPremium(String idFan) {
		// encontrar FanSigueASitio de ese fan y actualizarlo, tambien en BDD
		
		FanSigueSitio fss = this.obtenerSeguidor(idFan);
		if(fss != null){
			fss.setPremiun(true);
			try {
				new FanSigueSitioJpaController().edit(fss);
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		
	}
    
	public FanSigueSitio agregarSeguidor(Fan fan) {
		// crear un nuevo FanSigueSitio , agregarlo a coleccion, persistirlo y devolverlo
		
		FanSigueSitio fanNuevo = new FanSigueSitio(0, this.getMiArtista().getNikname(), false, fan);
		new FanSigueSitioJpaController().create(fanNuevo);		
		this.MisFans.put(fanNuevo.getId(), fanNuevo);
		return fanNuevo;
	}
    
	public void bloquearDesbloquearUsuarioDeSitio(String idFan) {
		// busca el fanSigueSitio entre los seguidores del sitio
		// actualiza el estado del bloqueo
		FanSigueSitio fss = this.obtenerSeguidor(idFan);
		if (fss != null) {
			fss.setBloqueado( ! fss.getBloqueado());
			try {
				new FanSigueSitioJpaController().edit(fss);
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}
	
	public ArrayList<String> obtenerIdDeFans(){
		// recorre los FanSigueSitio obteniendo los nicknames de cada fan y agregandolos al array de retorno
		ArrayList<String> ret = new ArrayList<String>();
		for (Map.Entry<Integer, FanSigueSitio> entry : this.MisFans.entrySet()) {
			ret.add(entry.getValue().getMiFan().getNikname());
		}
		return ret;
	}
	
	public QyAProgramado programarQyA(QyAProgramado qyaProgramado) {
		// crea el QyA, lo agrega a la coleccion y lo devuelve
		qyaProgramado.setId(0);
		qyaProgramado.setFechaYHoraFin(null);
		new QyAProgramadoJpaController().create(qyaProgramado);
		this.MisQyA.put(qyaProgramado.getId(), qyaProgramado);
		return qyaProgramado;
	}
	
	public Contenido agregarContenido(Contenido contenido) {
		// persistir el contenido
		// agregarlo a la coleccion y devolverlo
		new ContenidoJpaController().create(contenido);
		this.MisContenidos.put(contenido.getId(), contenido);
		return contenido;
	}

	public void eliminarContenido(int idContenido) {
		// eliminar el contenido de la coleccion y de la base de datos
		
		Contenido contenido = null;
		
		for (Map.Entry<Integer, Contenido> entry : this.MisContenidos.entrySet()) {
			if(entry.getValue().getId() == idContenido) {
				contenido = entry.getValue();
			}
		}
		contenido.setEliminado(true);
		
		try {
			new ContenidoJpaController().edit(contenido);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		
	}

	public boolean esFan(String idFan) {
		// devuelve si el fan es seguidor del sitio
		return (obtenerSeguidor(idFan) != null);
	}
	
	public boolean fanBloqueado(String idFan) {
		// devuelve si el fan se encuentra bloqueado de este sitio
		return obtenerSeguidor(idFan).getBloqueado();
	}
	
	public boolean fanEsPremium(String idFan) {
		// devuelve si el fan es premium
		return obtenerSeguidor(idFan).getPremiun();
	}
	
	private FanSigueSitio obtenerSeguidor(String idFan) {
		for (Map.Entry<Integer, FanSigueSitio> entry : this.MisFans.entrySet()) {
			// entrada del mapa -> elemento -> fan -> nikname -> igual a -> idFan
			if (entry.getValue().getMiFan().getNikname().equals(idFan)) {
				return entry.getValue();
			}
		}
		return null;
	}
	
    //**********************************************************************
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
		if(precioPremium <= 0){
            throw new RuntimeException(MensajesExcepciones.precio);
		}
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
		if(miCategoria == null){
            throw new RuntimeException(MensajesExcepciones.categoria);
		}
		this.miCategoria = miCategoria;
	}

	public Artista getMiArtista() {
		return miArtista;
	}

	public void setMiArtista(Artista miArtista) {
		if(miArtista == null){
            throw new RuntimeException(MensajesExcepciones.artista);
		}
		this.miArtista = miArtista;
	}

	public Fuente getMiFuente() {
		return miFuente;
	}

	public void setMiFuente(Fuente miFuente) {
		if(miFuente == null){
            throw new RuntimeException(MensajesExcepciones.fuente);
		}
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