/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isoft.framecujdbc.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author iurasun
 */
public class Departament {
  private int id;
  private String denumire;
  private Set<Angajat> angajati;

    public Departament() {
         angajati = new HashSet<>();
    }

    public Departament(String denumire) {
        this.denumire = denumire;
         angajati = new HashSet<>();
    }

    public Departament(int id, String denumire) {
        this.id = id;
        this.denumire = denumire;
         angajati = new HashSet<>();
    }

    public Departament(int id, String denumire, Set<Angajat> angajati) {
        this.id = id;
        this.denumire = denumire;
        this.angajati = angajati;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public Set<Angajat> getAngajati() {
        return angajati;
    }

    public void setAngajati(Set<Angajat> angajati) {
        this.angajati = angajati;
    }

    @Override
    public String toString() {
        return denumire;
    }
    
   
    public String toFullString() {
        return "Departament{" + "id=" + id + ", denumire=" + denumire + ", angajati="+ setToString(angajati) + '}';
    }

    private String setToString(Set<Angajat> angajati) {
       StringBuilder sb=new StringBuilder();
       sb.append("lista angajatilor din departamentul "+denumire+":");
        for (Angajat ang : angajati) {
            sb.append(ang);
            sb.append("\n");
        }
        sb.append("\n");
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + this.id;
        hash = 43 * hash + Objects.hashCode(this.denumire);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Departament other = (Departament) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.denumire, other.denumire)) {
            return false;
        }
        return true;
    }
  
  
}
