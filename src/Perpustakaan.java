public class Perpustakaan {
    private String nama;
    private String alamat;

    public Perpustakaan(String nama, String alamat) {
        this.nama = nama;
        this.alamat = alamat;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    @Override
    public String toString() {
        return "Perpustakaan{" +
                "nama='" + nama + '\'' +
                ", alamat='" + alamat + '\'' +
                '}';
    }
}