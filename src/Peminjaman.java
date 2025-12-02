import java.time.*;

public class Peminjaman {
    private String id;
    private LocalDate tanggalPinjam;
    private Member member;
    private Pustakawan petugas;
    private static int nomorUrut;

    public Peminjaman(Member member, Pustakawan petugas) {
        this.tanggalPinjam = LocalDate.now();
        // this.tanggalKembali = LocalDate.now().plusDays(lamaPinjam);
        this.member = member;
        this.petugas = petugas;

        nomorUrut++;
        this.id = Util.buatId("PJ", nomorUrut);
    }

    public String getId() {
        return id;
    }
    public LocalDate getTanggalPinjam() {
        return tanggalPinjam;
    }

    public Member getMember() {
        return member;
    }
    public Pustakawan getPetugas() {
        return petugas;
    }

    @Override
    public String toString() {
        // return "ID | TanggalPinjam | Tanggal Kembali | Nama Member | Nama Petugas";
        return String.format("%-5s | %-12s | %-12s | %24s | %24s", id, tanggalPinjam.toString(), member.getNama(), petugas.getNama());
    }
}
