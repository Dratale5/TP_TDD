package org.exemple.Models;

import java.time.LocalDate;

public class Adherant {
    private int codeAdherant;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private String civilite;

    public Adherant(int _codeAdherant, String _nom, String _prenom, LocalDate _dateNaissance, String _civilite) {
        codeAdherant = _codeAdherant;
        nom = _nom;
        prenom = _prenom;
        dateNaissance = _dateNaissance;
        civilite = _civilite;
    }
}
