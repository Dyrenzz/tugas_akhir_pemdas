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

    public Buku cariBuku(String judul) {
        for(Buku buku: daftarBuku) {
            if (buku.getJudul().equalsIgnoreCase(judul))
                return buku;
        }
        return null;
    }

    public void tampilkanDaftarBuku() {
        System.out.println("=".repeat(79));
        System.out.printf("| %-4s | %-30s | %-30s | %2s |%n", "Id", "Judul", "Penulis", "Jumlah");
        System.out.println("=".repeat(79));
        for (Buku buku: daftarBuku) {
            buku.tampilkan();
        }
        System.out.println("=".repeat(79));
    }

    public boolean buatPeminjaman(String idPeminjaman, Petugas petugas, Buku buku, Member member) {
        if (buku.cekKetersediaan()) {
            petugas.prosesPeminjaman(idPeminjaman, 5, buku, member, petugas);
            return true;
        } else {
            return false;
        }
    }
}
