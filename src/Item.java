public class Item {
    private String id;
    private Peminjaman pj;
    private Buku buku;
    private boolean statusKembali;

    public Item(String id, Peminjaman pj, Buku buku) {
        this.id = id;
        this.pj = pj;
        this.buku = buku;
        this.statusKembali = false;
    }

    public String getId() {
        return id;
    }
    public boolean getStatus() {
        return statusKembali;
    }
    public Buku getBuku() {
        return buku;
    }
    public Peminjaman getPj() {
        return pj;
    }

    public void ubahStatus(boolean status) {
        this.statusKembali = status;
    }

    @Override
    public String toString() {
        // return "ID | Buku | TanggalPinjam | TanggalKembali | StatusKembali ";
        return String.format("| %-5s | %-36s | %-12s | %-12s | %-13s |", id, buku.getJudul(),
                pj.getTanggalPinjam(), pj.getTanggalKembali(), (statusKembali ? "Kembali" : "Belum Kembali"));
    }
}
