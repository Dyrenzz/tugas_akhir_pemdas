public class Pustakawan {
    private String id;
    private String nama;
    private String noHp;
    private String alamat;
    private String NIK;
    private String password;
    private static int nomorUrut;

    public Pustakawan(String nama, String noHp, String alamat, String NIK, String password) {
        this.nama = nama;
        this.noHp = noHp;
        this.alamat = alamat;
        this.NIK = NIK;
        this.password =  password;

        nomorUrut++;
        this.id = Util.buatId("KC", nomorUrut);
    }

    public String getIdPetugas() {
        return id;
    }
    public String getNama() {
        return nama;
    }
    public String getNIK() {
        return NIK;
    }
    public String getPassword() {
        return password;
    }
}
