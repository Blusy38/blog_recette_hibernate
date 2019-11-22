/**
 * 
 */
package com.blogrecette.pojos;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author HB
 *
 */
@Entity
@Table(name = "Ingredient")
public class Ingredient {
	//Attributs de la class Ingredient
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private int idIngredient;
    
    //One to many bi-directionnel
    @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "idRecette")
    private Recette recette;
    
    @Column(name = "nom")
	private String nom;
    
    @Column(name = "quantite")
	private int quantite;
    
    @Column(name = "unit")
	private String unit;
	/**
	 * 
	 */
	public Ingredient() {
		super();
	}
	/**
	 * @param recette
	 * @param nom
	 * @param quantite
	 * @param unit
	 */
	public Ingredient(String nom, int quantite, String unit) {
		super();
		this.nom = nom;
		this.quantite = quantite;
		this.unit = unit;
	}
	public int getIdIngredient() {
		return idIngredient;
	}
	public void setIdIngredient(int idIngredient) {
		this.idIngredient = idIngredient;
	}
	public Recette getRecette() {
		return recette;
	}
	public void setRecette(Recette recette) {
		this.recette = recette;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	@Override
	public String toString() {
		return "Ingredient [idIngredient=" + idIngredient + ", recette=" + recette + ", nom=" + nom + ", quantite="
				+ quantite + ", unit=" + unit + "]";
	}

//Fin de la class Ingredient
}
