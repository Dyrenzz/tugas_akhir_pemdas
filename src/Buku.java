import java.util.ArrayList;

public class Buku {
    private String id;
    private String judul;
    private ArrayList<String> penulis;
    private int tahunTerbit;

    public Buku(String id, String judul, int tahunTerbit, ArrayList<String> penulis) {
        this.id = id;
        this.judul = judul;
        this.tahunTerbit = tahunTerbit;
        this.penulis = penulis;
    }

    public String getId() {
        return id;
    }
    public String getJudul() {
        return judul;
    }
    public int getTahunTerbit() {
        return tahunTerbit;
    }
    public ArrayList<String> getPenulis() {
        return penulis;
    }

    public String getPenulisString() {
        String names = "";

        if (penulis.size() <= 0) {
            names += "Anomaly";
        }
        else if (penulis.size() > 2) {
            names += penulis.get(0);
            names += ", dkk.";
        } 
        else {
            for (String name: penulis) {
                names += name;
                names += ", ";
            }
        }
        return names;
    }

    @Override
    public String toString() {
        return String.format("| %-4s | %-36s | %-30s |", id, judul, getPenulisString());
    }
}
