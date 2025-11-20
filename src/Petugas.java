public class Petugas {
    private String id;
    private String nama;

    public Petugas(String id, String nama) {
        this.id = id;
        this.nama = nama;
    }
    public String getIdPetugas() {
        return id;
    }
    public void setIdPetugas(String idPetugas) {
        this.id = idPetugas;
    }
    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    @Override
    public String toString() {
        return "Petugas{" +
                "idPetugas='" + id + '\'' +
                ", nama='" + nama + '\'' +
                '}';
    }

}
