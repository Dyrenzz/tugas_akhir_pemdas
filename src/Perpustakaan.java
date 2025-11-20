public class Perpustakaan {

    String nama;
    String alamat;

    Buku[] daftarBuku = new Buku[50];
    Member[] daftarMember = new Member[50];
    Petugas[] daftarPetugas = new Petugas[20];
    Peminjaman[] daftarPeminjaman = new Peminjaman[100];

    int jumlahBuku = 0;
    int jumlahMember = 0;
    int jumlahPetugas = 0;
    int jumlahPeminjaman = 0;

    public Perpustakaan(String nama, String alamat) {
        this.nama = nama;
        this.alamat = alamat;
    }

    public void tambahBuku(Buku buku) {
        daftarBuku[jumlahBuku] = buku;
        jumlahBuku++;
    }

    public Buku cariBuku(String judul) {
        for (int i = 0; i < jumlahBuku; i++) {
            if (daftarBuku[i].judul.equalsIgnoreCase(judul)) {
                return daftarBuku[i];
            }
        }
        return null;
    }

    public void tampilkanDaftarBuku() {
        for (int i = 0; i < jumlahBuku; i++) {
            daftarBuku[i].tampilkan();
            System.out.println("-------------------");
        }
    }

    public Peminjaman buatPeminjaman(Buku buku, Member member, Petugas petugas) {
        Peminjaman p = new Peminjaman((jumlahPeminjaman + 1), buku, member, petugas);
        daftarPeminjaman[jumlahPeminjaman] = p;
        jumlahPeminjaman++;
        buku.dipinjam();
        return p;
    }
}
