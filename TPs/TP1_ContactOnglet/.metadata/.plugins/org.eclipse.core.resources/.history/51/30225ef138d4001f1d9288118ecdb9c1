package V1;

public class Contact {

    // ---------------{ MÉTHODES DE CLASSE }----------------

    protected void setId(int i){
        this.id = i;
    }

    // ---------------{ ATTRIBUTS D'INSTANCE }----------------

    private int id,codePostal,pfp;
    private String nom, prenom, numTel, adresse, email, metier, situation;


    // ---------------{ CONSTRUCTEURS }----------------

    public Contact(int ide,String last_name, String first_name,
                   String tel, String adress, int code, String mail, String job, String situa, int pfp){

        // false if at least one var is empty
        boolean str_verif = !last_name.equals("") && !first_name.equals("") &&
                            !tel.equals("") && !adress.equals("") && !mail.equals("") &&
                            !job.equals("") && !situa.equals("");

        if (str_verif && code > 9999 && code < 100000){
            setId(ide);
            setNom(last_name);
            setPrenom(first_name);
            setNumTel(tel);
            setAdresse(adress);
            setCodePostal(code);
            setEmail(mail);
            setMetier(job);
            setSituation(situa);
            setPFP(pfp);
        }

    }


    // ---------------{ MÉTHODES }----------------

    public int getID() {
        return this.id ;
    }

    public String getNom(){
        return this.nom;
    }

    public void setNom(String name){
        this.nom = name;
    }

    public String getPrenom(){
        return this.prenom;
    }

    public void setPrenom(String name){
        this.prenom = name;
    }

    public String getNumTel(){
        return this.numTel;
    }

    public void setNumTel(String n){
        this.numTel = n;
    }

    public String getAdresse(){
        return this.adresse;
    }

    public void setAdresse(String n){
        this.adresse = n;
    }

    public int getCodePostal(){
        return this.codePostal;
    }

    public void setCodePostal(int n){
        this.codePostal = n;
    }

    public int getPFP(){
        return this.pfp;
    }

    public void setPFP(int n){
        this.pfp = n;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String n){
        this.email = n;
    }

    public String getMetier(){
        return this.metier;
    }

    public void setMetier(String n) {
        this.metier = n;
    }

    public String getSituation() {
        return this.situation;
    }

    public void setSituation(String n) {
        this.situation = n;
    }
}
