import java.util.ArrayList;

public class Perpustakaan {
    private String nama;
    private String alamat;
    private ArrayList<Buku> daftarBuku;
    private ArrayList<Member> daftarMember;
    private ArrayList<Petugas> daftarPetugas;
    private ArrayList<Peminjaman> daftarPeminjaman;

    public Perpustakaan(String nama, String alamat) {
        this.nama = nama;
        this.alamat = alamat;
        this.daftarBuku = new ArrayList<>();
        this.daftarMember = new ArrayList<>();
        this.daftarPetugas = new ArrayList<>();
        this.daftarPeminjaman = new ArrayList<>();
    }

    public void tambahBuku(Buku buku) {
        daftarBuku.add(buku);
    }

    public Buku cariJudulBuku(String judul) {
        for(Buku buku: daftarBuku) {
            if (buku.getJudul().equalsIgnoreCase(judul))
                return buku;
        }
        return null;
    }

    public Buku cariIdBuku(String idBuku) {
        for(Buku buku: daftarBuku) {
            if (buku.getId().equalsIgnoreCase(idBuku))
                return buku;
        }
        return null;
    }

    public Member tambahMember(String id, String nama, String alamat, String noTelp) {
        Member memberBaru = new Member(id, nama, alamat, noTelp);
        daftarMember.add(memberBaru);
        return memberBaru;
    }

    public void tambahPeminjaman(Peminjaman invoice) {
        daftarPeminjaman.add(invoice);
    }

    public Peminjaman cariIdPeminjaman(String idPeminjaman) {
        for (Peminjaman invoice: daftarPeminjaman) {
            if (invoice.getId().equals(idPeminjaman)) {
                return invoice;
            }
        }
        return null;
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
        System.out.println("=".repeat(89));
        System.out.printf("| %-4s | %-36s | %-30s | %s |%n", "Id", "Judul", "Penulis", "Jumlah");
        System.out.println("=".repeat(89));
        for (Buku buku: daftarBuku) {
            buku.tampilkan();
        }
        System.out.println("=".repeat(89));
    }


    @Override
    public String toString() {
        return String.format("Selamat datang di %s %n%s", nama, alamat);
    }
}
