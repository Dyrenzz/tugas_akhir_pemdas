import java.util.ArrayList;

public class Buku {
    private String id;
    private String judul;
    private ArrayList<String> penulis;
    private int tahunTerbit;
    private int jumlah;

    public Buku(String id, String judul, int tahunTerbit, int jumlah) {
        this.id = id;
        this.judul = judul;
        this.tahunTerbit = tahunTerbit;
        this.jumlah = jumlah;
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
    
    

    public void dipinjam() {
        if (cekKetersediaan()) {
            jumlah--;
        }
    }

    public void dikembalikan() {
        jumlah++;
    }

    public boolean cekKetersediaan() {
        if (jumlah > 0) {
            return true;
        } else {
            return false;
        }
    }
}
