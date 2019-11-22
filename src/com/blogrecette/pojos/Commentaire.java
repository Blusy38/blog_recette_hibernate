package com.blogrecette.pojos;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.util.Date;

@Entity
@Table(name = "Commentaire")
public class Commentaire {
	
	//Attributs de la class Categorie
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private int idCommentaire;
    
  //One to many bi-directionnel
    @ManyToOne(fetch = FetchType.EAGER, optional = false, targetEntity = Recette.class)
    private Recette recette ;
    
    
    @Column(name = "auteur")
	private String auteur;
    
    @Column(name = "contenu")
	private String contenu;
    
    @Column(name = "note")
	private int note;
    
    @Temporal(TemporalType.DATE)
	private Date dateCreation;
	/**
	 * 
	 */
	public Commentaire() {
		super();
	}
	/**
	 * @param idCommentaire
	 * @param recette
	 * @param auteur
	 * @param contenu
	 * @param note
	 * @param dateCreation
	 */
	public Commentaire(String auteur, String contenu, int note, Date dateCreation) {
		super();
		this.auteur = auteur;
		this.contenu = contenu;
		this.note = note;
		this.dateCreation = dateCreation;
	}
	public int getIdCommentaire() {
		return idCommentaire;
	}
	public void setIdCommentaire(int idCommentaire) {
		this.idCommentaire = idCommentaire;
	}
	public Recette getRecette() {
		return recette;
	}
	public void setRecette(Recette recette) {
		this.recette = recette;
	}
	public String getAuteur() {
		return auteur;
	}
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}
	public String getContenu() {
		return contenu;
	}
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	public int getNote() {
		return note;
	}
	public void setNote(int note) {
		this.note = note;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	
	//Fin de la class Commentaire
}
