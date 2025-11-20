public class Member {
    private String nama;
    private String alamat;
    private String id;
    private String noTelepon;

    public Member(String id, String nama, String alamat, String noTelepon) {
        this.id = id;
        this.nama = nama;
        this.alamat = alamat;
        this.noTelepon = noTelepon;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public String getAlamat() {
        return alamat;
    }
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
    public String getNoTelepon() {
        return noTelepon;
    }
    public void setNoTelepon(String noTelepon) {
        this.noTelepon = noTelepon;
    }
    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", nama='" + nama + '\'' +
                ", alamat='" + alamat + '\'' +
                ", noTelepon='" + noTelepon + '\'' +
                '}';
    }
    


}
