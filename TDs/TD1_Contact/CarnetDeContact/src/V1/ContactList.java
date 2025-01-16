package V1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ContactList {


    // ---------------{ ATTRIBUTS D'INSTANCE }----------------

    private ArrayList<Contact> carnet = new ArrayList<Contact>();

    // ---------------{ CONSTRUCTEURS }----------------

    public ContactList(){

    }


    // ---------------{ MÉTHODES }----------------

    public int getSize(){
        return this.carnet.size();
    }

    public Contact getContact(int i){
        if (i < this.carnet.size() && i > -1){
            return this.carnet.get(i);
        } else {
            return null;
        }
    }

    public void addContact(String last_name, String first_name, String tel,
                      String adress, int code, String mail, String job, String situa, int pfp){

        // false if at least one var is empty
        boolean str_verif = !last_name.equals("") && !first_name.equals("") &&
                !tel.equals("") && !adress.equals("") && !mail.equals("") &&
                !job.equals("") && !situa.equals("");

        if (str_verif){

            this.carnet.add(new Contact(this.carnet.size() + 1,last_name,first_name,
                    tel,adress,code,mail,job,situa, pfp));
        }

    }

    public void deleteContact(){
        if (this.carnet.size() > 0){
            this.carnet.remove(this.carnet.size() - 1);
        }
    }

    public void deleteContact(int i){
        i--;
        if (this.carnet.size() > 0 && i > -1 && i < this.carnet.size()){

            this.carnet.remove(i);

            while (i < this.carnet.size()){
                this.carnet.get(i).setId(i+1);
                i++;
            }

        }
    }


    public void modifyContact(int idd,String last_name, String first_name, String tel,
                           String adress, int code, String mail, String job, String situa, int pfp){

        idd--;

        if (idd < this.carnet.size()){


            // false if at least one var is empty
            boolean str_verif = !last_name.equals("") && !first_name.equals("") &&
                    !tel.equals("") && !adress.equals("") && !mail.equals("") &&
                    !job.equals("") && !situa.equals("");

            if (str_verif){

                this.carnet.set(idd,new Contact(idd + 1,last_name,first_name,
                        tel,adress,code,mail,job,situa, pfp));
            }
        }
    }


    public String getSaveText() {

        int n = this.getSize();
        String save = "";
        for (int i = 0; i < n; i++) {
            Contact c = this.carnet.get(i);
            save = save + c.getNom() + "/" +
                    c.getPrenom() + "/" +
                    c.getNumTel() + "/" +
                    c.getAdresse() + "/" +
                    String.valueOf(c.getCodePostal()) + "/" +
                    c.getEmail() + "/" +
                    c.getMetier() + "/" +
                    c.getSituation() + "/" +
                    String.valueOf(c.getPFP()) + ";";
        }
        return save;
    }
    
    public boolean Save() {
    	try {
    	      FileWriter myWriter = new FileWriter("Save.txt");
    	      myWriter.write(getSaveText());
    	      myWriter.close();
    	      System.out.println("Successfully wrote to the file.");
    	      return true;
    	      
    	} catch (IOException e) {
    	      System.out.println("An error occurred.");
    	      e.printStackTrace();
    	      return false;
    	}
    }

    public boolean reloadSave() {
    	
    	try {
    	      File myObj = new File("Save.txt");
    	      Scanner myReader = new Scanner(myObj);
    	      String data = "";
    	      
    	      while (myReader.hasNextLine()) {
    	        data = myReader.nextLine();
    	      }
    	      if (!data.isBlank()) {
    	    	  
    	    	  ArrayList<String> cont = new ArrayList<String>();
                  String temp = "";
                  for (int i = 0; i < data.length(); i++){
                      char c = data.charAt(i);

                      if (c == '/'){
                          cont.add(temp);
                          temp = "";

                      } else if (c == ';') {
                          cont.add(temp);
                          temp = "";

                          addContact(cont.get(0),cont.get(1),cont.get(2)
                                  ,cont.get(3),Integer.valueOf(cont.get(4)),cont.get(5)
                                  ,cont.get(6),cont.get(7),Integer.valueOf(cont.get(8)));

                          cont.clear();
                      } else if (c != '\n') {
                          temp = temp + c;
                      }
                  }
                  
    	      }
    	     
    	      myReader.close();
    	      return true;
    	      
    	} catch (FileNotFoundException e) {
    	      System.out.println("An error occurred.");
    	      e.printStackTrace();
    	      return false;
    	}
    	
    }
    
}
