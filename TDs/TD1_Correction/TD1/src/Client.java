public class Client {
    private String[] Infos;
    private int indexPhoto;

    // Constructor with client ID
    public Client(int num) {
        this.Infos = new String[ClientController.LABELS.length];
        this.Infos[0] = Integer.toString(num);
        for (int i = 1; i < Infos.length; i++) {
            this.Infos[i] = "";
        }
        this.indexPhoto = 0;
    }

    // Constructor with client ID and info array
    public Client(int num, String[] infos) {
        this(num);
        for (int i = 1; i < this.Infos.length; i++) {
            this.Infos[i] = infos[i];
        }
    }

    public String[] getInfos() {
        return Infos;
    }

    public int getIndexPhoto() {
        return indexPhoto;
    }

    public void setNum(int num) {
        this.Infos[0] = Integer.toString(num);
    }

    public void setInfo(String info, int index) {
        this.Infos[index] = info;
    }

    public void setIndexPhoto(int indexPhoto) {
        if (indexPhoto > -1 && indexPhoto < 7) {
            this.indexPhoto = indexPhoto;
        }
    }

    public void addIndexPhoto(int var) {
        this.setIndexPhoto(this.indexPhoto + var);
    }

    public String toStringInfos() {
        StringBuilder str = new StringBuilder();
        for (String info : Infos) {
            str.append(info).append("::");
        }
        return str.toString();
    }

    public String toStringIndexPhoto() {
        return Integer.toString(this.indexPhoto);
    }
}
