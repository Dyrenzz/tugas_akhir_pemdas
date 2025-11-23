import java.time.*;

public class Peminjaman {
    private String id;
    private LocalDate tanggalPinjam;
    private LocalDate tanggalKembali;
    private Member member;
    private Petugas petugas;

    public Peminjaman(String id, int lamaPinjam, Member member, Petugas petugas) {
        this.id = id;
        this.tanggalPinjam = LocalDate.now();
        this.tanggalKembali = LocalDate.now().plusDays(lamaPinjam);
        this.member = member;
        this.petugas = petugas;
    }

    public String getId() {
        return id;
    }
    public LocalDate getTanggalPinjam() {
        return tanggalPinjam;
    }
    public LocalDate getTanggalKembali() {
        return tanggalKembali;
    }
    public Member getMember() {
        return member;
    }
    public Petugas getPetugas() {
        return petugas;
    }

    @Override
    public String toString() {
        // return "ID | TanggalPinjam | Tanggal Kembali | Nama Member | Nama Petugas";
        return String.format("%-5s | %-12s | %-12s | %24s | %24s", id, tanggalPinjam.toString(), tanggalKembali.toString(), member.getNama(), petugas.getNama());
    }
}
