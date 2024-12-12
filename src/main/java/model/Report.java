package model;

public class Report {
    private String username;
    private int nrCarti;
    private float incasari;
    public Report(String username, int nrCarti, float incasari){
        this.incasari=incasari;
        this.nrCarti=nrCarti;
        this.username=username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getNrCarti() {
        return nrCarti;
    }

    public void setNrCarti(int nrCarti) {
        this.nrCarti = nrCarti;
    }

    public float getIncasari() {
        return incasari;
    }

    public void setIncasari(int incasari) {
        this.incasari = incasari;
    }
}
