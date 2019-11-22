/**
 * 
 */
package com.blogrecette.pojos;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author HB
 *
 */
@Entity
@Table(name = "Categorie")
public class Categorie {
	//Attributs de la class Categorie
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private int idCategorie;
    
    @Column(name = "nom")
	private String nom;
    
    //One to many bi-directionnel
    @OneToMany(mappedBy = "categorie")
    private Collection<Recette> recettes;
	/**
	 * 
	 */
	public Categorie() {
		super();
		this.recettes = new ArrayList<Recette>();
	}
	/**
	 * @param nom
	 * @param recettes
	 */
	public Categorie(String nom) {
		super();
		this.nom = nom;

		this.recettes = new ArrayList<Recette>();
	}
	public int getIdCategorie() {
		return idCategorie;
	}
	public void setIdCategorie(int idCategorie) {
		this.idCategorie = idCategorie;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	//********************RECETTE************************** 
	
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
	@Override
	public String toString() {
		return "Categorie [idCategorie=" + idCategorie + ", nom=" + nom + ", recettes=" + recettes + "]";
	}
}
