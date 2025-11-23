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
    public String getNama() {
        return nama;
    }

    public Peminjaman prosesPeminjaman(Peminjaman invoice) {
        return invoice;
    }
    public void prosesPengembalian(Item itemPj) {
        itemPj.ubahStatus(true);
    }
}
