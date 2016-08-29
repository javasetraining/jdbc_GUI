
package com.isoft.framecujdbc.domain;

public class Angajat {
    private int id;
    private String nume;
    private String prenume;
    private int salariu;
    private Departament departament;

    public Angajat() {
        departament=new Departament();
    }

    public Angajat(String nume, String prenume, int salariu) {
        this.nume = nume;
        this.prenume = prenume;
        this.salariu = salariu;
                departament=new Departament();
    }

    public Angajat(int id, String nume, String prenume, int salariu) {
        this.id = id;
        this.nume = nume;
        this.prenume = prenume;
        this.salariu = salariu;
                departament=new Departament();
    }

      public Angajat(String nume, String prenume, int salariu, Departament departament) {
     
        this.nume = nume;
        this.prenume = prenume;
        this.salariu = salariu;
        this.departament = departament;
    }
    
    public Angajat(int id, String nume, String prenume, int salariu, Departament departament) {
        this.id = id;
        this.nume = nume;
        this.prenume = prenume;
        this.salariu = salariu;
        this.departament = departament;
    }
    
    

    public int getSalariu() {
        return salariu;
    }

    public void setSalariu(int salariu) {
        this.salariu = salariu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public Departament getDepartament() {
        return departament;
    }

    public void setDepartament(Departament departament) {
        this.departament = departament;
    }

    @Override
    public String toString() {
        return "Angajat{" + "id=" + id + ", nume=" + nume + ", prenume=" + prenume + ", salariu=" + salariu + ", departament=" + departament.getDenumire() + '}';
    }

    public String toShortString() {
        return "Angajat{" + "id=" + id + ", nume=" + nume + ", prenume=" + prenume + ", salariu=" + salariu + ", departament=" + departament.getDenumire()+'}';
    }
    
}
