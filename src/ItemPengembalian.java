public class ItemPengembalian {
    private String id;
    private Pengembalian pg;
    private Buku buku;
    private Peminjaman pj;
    private static int nomorUrut;

    public ItemPengembalian(Pengembalian pg, Buku buku) {
        this.pg = pg;
        this.buku = buku;

        nomorUrut++;
        this.id = Util.buatId("IPG", nomorUrut);
    }

    public String getId() {
        return id;
    }
    public Buku getBuku() {
        return buku;
    }
    public Pengembalian getPg() {
        return pg;
    }

    @Override
    public String toString() {
        // return "ID | Buku | TanggalKembali ";
        return String.format("| %-5s | %-36s | %-12s | %-12s | %-13s |", id, buku.getJudul(),
                pg.getTanggalKembali());
    }
}
