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

    public Peminjaman prosesPeminjaman(String id, int lamaPinjam, Buku buku, Member member, Petugas petugas) {
        Peminjaman invoice = new Peminjaman(id, 0, buku, member, petugas);
        return invoice;
    }
}
