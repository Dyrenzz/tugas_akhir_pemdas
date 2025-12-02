// import java.time.LocalDate;

public class Member {
    private String id;
    private String nama;
    private String alamat;
    private String noTelepon;
    private String NIS;
    private String email;
    private static int nomorUrut;

    public Member(String nama, String alamat, String noTelepon, String NIS, String email) {
        this.nama = nama;
        this.alamat = alamat;
        this.noTelepon = noTelepon;
        this.NIS = NIS;
        this.email = email;
    

        nomorUrut++;
        this.id = Util.buatId("MB", nomorUrut);
    }

    public String getId() {
        return id;
    }
    public String getNama() {
        return nama;
    }
    public String getAlamat() {
        return alamat;
    }
    public String getNoTelepon() {
        return noTelepon;
    }
    public String getNIS() {
        return NIS;
    }
}
