public class ItemPeminjaman {
    private String id;
    private Peminjaman pj;
    private Buku buku;
    private static int nomorUrut;

    public ItemPeminjaman(Peminjaman pj, Buku buku) {
        this.pj = pj;
        this.buku = buku;

        nomorUrut++;
        this.id = Util.buatId("IPJ", nomorUrut);
    }

    public String getId() {
        return id;
    }
    public Buku getBuku() {
        return buku;
    }
    public Peminjaman getPj() {
        return pj;
    }


    @Override
    public String toString() {
        // return "ID | Buku | TanggalPinjam | TanggalKembali | StatusKembali ";
        return String.format(" %-7s | %-36s | %-15s%n", id, buku.getJudul(), pj.getTanggalPinjam());
    }
}
