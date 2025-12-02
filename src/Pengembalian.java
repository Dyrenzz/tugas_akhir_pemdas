import java.time.LocalDate;

public class Pengembalian {
    private String id;
    private LocalDate tanggalKembali;
    private Member member;
    private Pustakawan pst;
    private static int nomorUrut;

    public Pengembalian(Member member, Pustakawan pst) {
        this.tanggalKembali = LocalDate.now();
        this.member = member;
        this.pst = pst;

        nomorUrut++;
        this.id = Util.buatId("PG", nomorUrut);
    }

    public String getIdPengembalian() {
        return id;
    }

    public LocalDate getTanggalKembali() {
        return tanggalKembali;
    }

    public Member getMember() {
        return member;
    }

    public Pustakawan getPustakawan() {
        return pst;
    }

    @Override
    public String toString() {
        return "Pengembalian{" +
                "id='" + id + '\'' +
                ", tanggalKembali=" + tanggalKembali +
                ", member=" + (member != null ? member.toString() : "null") +
                ", petugas=" + (pst != null ? pst.toString() : "null") +
                '}';
    }
}