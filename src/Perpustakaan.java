import java.util.ArrayList;

public class Perpustakaan {
    private String nama;
    private String alamat;
    private ArrayList<Buku> daftarBuku;
    private ArrayList<Member> daftarMember;
    private ArrayList<Petugas> daftarPetugas;

    public Perpustakaan(String nama, String alamat) {
        this.nama = nama;
        this.alamat = alamat;
        this.daftarBuku = new ArrayList<>();
        this.daftarMember = new ArrayList<>();
        this.daftarPetugas = new ArrayList<>();
    }

    public void tambahBuku(Buku buku) {
        daftarBuku.add(buku);
    }

    public ArrayList<Buku> getDaftarBuku() {
        return daftarBuku;
    }

    public Member tambahMember(Member memberBaru) {
        daftarMember.add(memberBaru);
        return memberBaru;
    }

    public void tambahPetugas(Petugas petugas) {
        daftarPetugas.add(petugas);
    }

    public Member cariIdMember(String idMember) {
        for (Member member: daftarMember) {
            if (member.getId().equals(idMember)) {
                return member;
            }
        }
        return null;
    }

    public void tampilkanDaftarBuku() {
        System.out.println("=".repeat(80));
        System.out.printf("| %-4s | %-36s | %-30s |%n", "Id", "Judul", "Penulis");
        System.out.println("=".repeat(80));
        for (Buku buku: daftarBuku) {
            System.out.println(buku);
        }
        System.out.println("=".repeat(80));
    }


    @Override
    public String toString() {
        return String.format("Selamat datang di %s %n%s", nama, alamat);
    }
}
