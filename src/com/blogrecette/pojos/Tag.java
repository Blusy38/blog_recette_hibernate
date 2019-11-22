package com.blogrecette.pojos;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Tag {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTag")
	private int idTag;

	@Column(name = "nom")
	private String nom;

	@ManyToMany(mappedBy = "tags",fetch = FetchType.EAGER)
	private Collection<Recette> recettes;

	/**
	 * 
	 */
	public Tag() {
		super();
		this.recettes = new ArrayList<Recette>();
	}
	
	/**
	 * @param nom
	 */
	public Tag(String nom) {
		super();
		this.nom = nom;
		this.recettes = new ArrayList<Recette>();
	}

	//********************RECETTE************************** 

	public Collection<Recette> getRecettes(){
		return  recettes;
	}

	public Collection<Recette> addRecette(Recette recette){
		recettes.add(recette);
		return  recettes;
	}

	public Collection<Recette> removeRecette(Recette recette){
		recettes.remove(recette);
		return  recettes;
	}

	public int getIdTag() {
		return idTag;
	}

	public void setIdTag(int idTag) {
		this.idTag = idTag;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

    public void resetRecettes(){
	      this.recettes = new ArrayList<Recette>();
	    }
    
	@Override
	public String toString() {
		return "Tag [idTag=" + idTag + ", nom=" + nom +", nbRecette="+recettes.size()+"]";
	}


	//Fin de la class Tag
}