package com.mesannonces;

import java.util.Date;
import java.util.List;

public class Annonce {

    private int id, prix;
    private String titre, description, pseudo, emailContact, telConctact, ville, cp;
    private List<String> images;
    private Date dateAnnonce;

    public Annonce(int id, int prix, String titre, String description, String pseudo, String emailContact, String telContact, String ville, String cp, List<String> images, Date dateAnnonce) {
        this.id = id;
        this.prix = prix;
        this.titre = titre;
        this.description = description;
        this.pseudo = pseudo;
        this.emailContact = emailContact;
        this.telConctact = telContact;
        this.ville = ville;
        this.cp = cp;
        this.images = images;
        this.dateAnnonce = dateAnnonce;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
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

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getEmailContact() {
        return emailContact;
    }

    public void setEmailContact(String emailContact) {
        this.emailContact = emailContact;
    }

    public String getTelConctact() {
        return telConctact;
    }

    public void setTelConctact(String telConctact) {
        this.telConctact = telConctact;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public Date getDateAnnonce() {
        return dateAnnonce;
    }

    public void setDateAnnonce(Date dateAnnonce) {
        this.dateAnnonce = dateAnnonce;
    }
}
