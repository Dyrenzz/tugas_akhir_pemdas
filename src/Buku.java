import java.util.ArrayList;

public class Buku {
    private String id;
    private String judul;
    private ArrayList<String> penulis;
    private int tahunTerbit;
    private int nomorRak;
    private String kategori;
    private String ISBN;
    private int stock;
    private static int nomorUrut;

    public Buku(String ISBN, String judul, ArrayList<String> penulis, int tahunTerbit, String kategori, int nomorRak, int stock) {
        this.ISBN = ISBN;
        this.judul = judul;
        this.penulis = penulis;
        this.tahunTerbit = tahunTerbit;
        this.kategori = kategori;
        this.nomorRak = nomorRak;
        this.stock = stock;

        nomorUrut++;
        this.id = Util.buatId("B", nomorUrut);
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
    public boolean kurangiStock() {
        if (stock <= 0) 
            return false;
        else {
            stock--;
            return true;
        }
    }
    public void tambahStock() {
        stock++;
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
    public int getnomorRak(){
        return nomorRak;
    }
    public String getKategori() {
        return kategori;
    }
    public String getISBN() {
        return ISBN;
    }
    public int getStock() {
        return stock;
    }


    @Override
    public String toString() {
        return String.format(" %-4s | %-40s | %-30s | %d", id, judul, getPenulisString(), stock);
    }
}
