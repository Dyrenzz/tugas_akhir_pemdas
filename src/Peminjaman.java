import java.time.*;

public class Peminjaman {
    private String id;
    private LocalDate tanggalPinjam;
    private LocalDate tanggalKembali;
    private boolean statusKembali;
    private Buku buku;
    private Member member;
    private Petugas petugas;

    public Peminjaman(String id, int lamaPinjam, Buku buku, Member member, Petugas petugas) {
        this.id = id;
        this.tanggalPinjam = LocalDate.now();
        this.tanggalKembali = this.tanggalKembali.plusDays(lamaPinjam);
        this.buku = buku;
        this.member = member;
        this.petugas = petugas;
        this.statusKembali = false;
    }

    public void ubahStatus(boolean status) {
        this.statusKembali = status;
    }

    @Override
    public String toString() {
        // return "ID | Buku | TanggalPinjam | TanggalKembali | StatusKembali | Member Nama | Petugas Nama";
        return String.format("%s | %s | %s | %s | %s | %s | %s", id, buku.getJudul(), tanggalPinjam.toString(), tanggalKembali.toString(), (statusKembali? "Kembali" : "Belum Kembali"), member.getNama(), petugas.getNama());
    }
}
