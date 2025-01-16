package V1;

public class Contact {

    // ---------------{ MÉTHODES DE CLASSE }----------------

    protected void setId(int i){
        _id = i;
    }

    // ---------------{ ATTRIBUTS D'INSTANCE }----------------

    private int _id, _codePostal, _pfp;
    private String _nom, _prenom, _numTel, _adresse, _email, _metier, _situation;


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
        return _id ;
    }

    public String getNom(){
        return _nom;
    }

    public void setNom(String name){
        _nom = name;
    }

    public String getPrenom(){
        return _prenom;
    }

    public void setPrenom(String name){
        _prenom = name;
    }

    public String getNumTel(){
        return _numTel;
    }

    public void setNumTel(String n){
        _numTel = n;
    }

    public String getAdresse(){
        return _adresse;
    }

    public void setAdresse(String n){
        _adresse = n;
    }

    public int getCodePostal(){
        return _codePostal;
    }

    public void setCodePostal(int n){
        _codePostal = n;
    }

    public int getPFP(){
        return _pfp;
    }

    public void setPFP(int n){
        _pfp = n;
    }

    public String getEmail(){
        return _email;
    }

    public void setEmail(String n){
        _email = n;
    }

    public String getMetier(){
        return _metier;
    }

    public void setMetier(String n) {
        _metier = n;
    }

    public String getSituation() {
        return _situation;
    }

    public void setSituation(String n) {
        _situation = n;
    }
}
