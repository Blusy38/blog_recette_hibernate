/**
 * 
 */
package com.blogrecette.pojos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.*;

/**
 * @author HB
 *
 */
@Entity
@Table(name = "Recette")
public class Recette {
	//Attributs de la class RecetteServlet
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int idRecette;

	//One to many bi-directionnel un membre pour une recette plusieur recettes pour un membre
	@ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = {CascadeType.PERSIST})
	@JoinColumn(name = "idMembre")
	private Membre membre;

	//One to many bi-directionnel
	@ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = {CascadeType.PERSIST})
	@JoinColumn(name = "idCategorie")
	private Categorie categorie;

	//One to many bi-directionnel une recette par commentaires plusieurs commentaires pour une recette
	@OneToMany(mappedBy = "recette", targetEntity = Commentaire.class,orphanRemoval = true, cascade = CascadeType.PERSIST)
	private Collection<Commentaire> commentaires;

	//One to many bi-directionnel une recette par ingredients plusieurs ingredients pour une recette
	@OneToMany(mappedBy = "recette",orphanRemoval = true, cascade = CascadeType.PERSIST)
	private Collection<Ingredient> ingredients;
	
    // Many to many bi-directionnel
    @ManyToMany(/*mappedBy = "recettes",*/ fetch = FetchType.EAGER)
    private Collection<Tag> tags;

	@Column(name = "titre")
	private String titre;

	@Column(name = "description")
	private String description;

	@Column(name = "photo")
	private String photo;

	@Temporal(TemporalType.DATE)
	private Date dateCreation;

	//attributs suplementaires
	@Transient
	private Double moyNote;

	/**
	 * 
	 */
	public Recette() {
		super();
		this.commentaires = new ArrayList<Commentaire>();
		this.ingredients = new ArrayList<Ingredient>();
		this.tags = new ArrayList<Tag>();
	}

	/**
	 * @param titre
	 * @param description
	 * @param photo
	 * @param dateCreation
	 * @param moyNote
	 * @param idCat 
	 */
	public Recette(String titre, String description, String photo, Date dateCreation, Double moyNote) {
		super();
		this.titre = titre;
		this.description = description;
		this.photo = photo;
		this.dateCreation = dateCreation;
		this.moyNote = moyNote;

		this.commentaires = new ArrayList<Commentaire>();
		this.ingredients = new ArrayList<Ingredient>();
		this.tags = new ArrayList<Tag>();
	}

	public int getIdRecette() {
		return idRecette;
	}

	public void setIdRecette(int idRecette) {
		this.idRecette = idRecette;
	}

	public Membre getMembre() {
		return membre;
	}

	public void setMembre(Membre membre) {
		this.membre = membre;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Double getMoyNote() {
		return moyNote;
	}

	public void setMoyNote(Double l) {
		this.moyNote = l;
	}
	//********************COMMENTAIRE************************** 
	public Collection<Commentaire> getCommentaires() {
		return commentaires;
	}

	public void addCommentaire(Commentaire commentaire){
		commentaires.add(commentaire);
		commentaire.setRecette(this);
	}

	public void removeCommentaire(Commentaire commentaire){
		commentaires.remove(commentaire);
		commentaire.setRecette(null);
	}

	//***********************INGREDIENTS************************    
	public Collection<Ingredient> getIngredients() {
		return ingredients;
	}

	public Collection<Ingredient> addCommentaire(Ingredient ingredient){
		ingredients.add(ingredient);
		return  ingredients;
	}

	public Collection<Ingredient> deleteCommentaire(Ingredient ingredient){
		ingredients.remove(ingredient);
		return ingredients;
	}
	//**********************TAG*****************************
	  public Collection<Tag> getTags(){
	        return tags;
	    }

	    public Collection<Tag> addTag(Tag tag){
	        tags.add(tag);
	        return  tags;
	    }
	    
	    public void resetTag(){
	      this.tags = new ArrayList<Tag>();
	    }

	    public Collection<Tag> deleteTag(Tag tag){
	        tags.remove(tag);
	        return tags;
	    }
	    
	@Override
	public String toString() {
		return "Recette [idRecette=" + idRecette + ", membre=" + membre + ", titre="
				+ titre + ", description=" + description + ", photo=" + photo + ", dateCreation=" + dateCreation
				+ ", moyNote=" + moyNote +", nbTags="+tags.size()+"]";
	}

	//Fin de la class RecetteServlet

}
