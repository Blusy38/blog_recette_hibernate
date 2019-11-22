/**
 * 
 */
package com.blogrecette.pojos;

import java.util.Date;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author HB
 *
 */
@Entity
@Table(name = "membre")
public class Membre {
	//Attributs de la class utilisateur
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
	private int idMembre;
    
    @Column(name = "nom")
	private String nom;
    
    @Column(name = "pseudo")
	private String pseudo;
    
    @Column(name = "email")
	private String email;
    
    @Column(name = "mdp")
	private String mdp;
    
    @Temporal(TemporalType.DATE)
	private Date dateInscription;
    
    //One to many bi-directionnel un membre par recette plusieurs recettes pour un membre
    @OneToMany(mappedBy = "membre")
    private Collection<Recette> recettes;
	/**
	 * 
	 */
	public Membre() {
		super();
		this.recettes = new ArrayList<Recette>();
	}
	/**
	 * @param nom
	 * @param pseudo
	 * @param email
	 * @param mdp
	 * @param dateInscription
	 */
	public Membre(String nom, String pseudo, String email, String mdp, Date dateInscription) {
		super();
		this.nom = nom;
		this.pseudo = pseudo;
		this.email = email;
		this.mdp = mdp;
		this.dateInscription = dateInscription;
	}
	public int getIdMembre() {
		return idMembre;
	}
	public void setIdMembre(int idMembre) {
		this.idMembre = idMembre;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	public Date getDateInscription() {
		return dateInscription;
	}
	public void setDateInscription(Date dateInscription) {
		this.dateInscription = dateInscription;
	}
	//********************COMMENTAIRE************************** 
	
	public Collection<Recette> getRecettes() {
		return recettes;
	}
	public Collection<Recette> addCommentaire(Recette recette){
		recettes.add(recette);
		return  recettes;
	}
	public Collection<Recette> deleteCommentaire(Recette recette){
		recettes.remove(recette);
		return recettes;
	}

	
	//Fin de la class Membre
}
