package com.perez.christophe.topquiz.model;

/**
 * Created by christophe on 12/05/2021.
 */
public class User {

    // for stock the firstname of user
    private String mFirstname;


    // for access (pour recuperer ) of value of mFirstname
    public String getFirstname() {
        return mFirstname;
    }

    // pour modifier la valeur de la variable mFirstname ( pour mettre à jour le prénom)
    public void setFirstname(String firstname) {
        mFirstname = firstname;
    }

    // pour afficher le contenu de la valeur des differents champs de l'objet
    @Override
    public String toString() {
        return "User{" +
                "mFirstname='" + mFirstname + '\'' +
                '}';
    }
}
