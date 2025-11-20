import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Perpustakaan perpus = new Perpustakaan("Perpus Melia", "Jl. Mawar No. 5");

        // Tambah contoh buku
        Buku b1 = new Buku("B001", "Pemrograman Java", 2020);
        b1.tambahPenulis("Andi");
        perpus.tambahBuku(b1);

        Buku b2 = new Buku("B002", "Struktur Data", 2021);
        b2.tambahPenulis("Budi");
        perpus.tambahBuku(b2);

        // Tambah member
        Member m1 = new Member("M001", "Melia", "Jakarta", "0812345678");

        // Tambah petugas
        Petugas p1 = new Petugas("P001", "Dimas");

        int pilihan = 0;

        do {
            System.out.println("\n=== MENU PERPUSTAKAAN ===");
            System.out.println("1. Tampilkan Buku");
            System.out.println("2. Cari Buku");
            System.out.println("3. Pinjam Buku");
            System.out.println("0. Keluar");
            System.out.print("Pilih: ");
            pilihan = sc.nextInt();
            sc.nextLine(); // buffer

            switch (pilihan) {

                case 1:
                    perpus.tampilkanDaftarBuku();
                    break;

                case 2:
                    System.out.print("Masukkan judul: ");
                    String judul = sc.nextLine();
                    Buku hasil = perpus.cariBuku(judul);
                    if (hasil != null) hasil.tampilkan();
                    else System.out.println("Buku tidak ditemukan!");
                    break;

                case 3:
                    System.out.print("Masukkan judul buku yang ingin dipinjam: ");
                    String j = sc.nextLine();
                    Buku bk = perpus.cariBuku(j);

                    if (bk != null && bk.cekKetersediaan()) {
                        Peminjaman pm = perpus.buatPeminjaman(bk, m1, p1);
                        System.out.println("Peminjaman berhasil! ID: " + pm.idPeminjaman);
                    } else {
                        System.out.println("Buku tidak ditemukan / sedang dipinjam.");
                    }
                    break;
            }

        } while (pilihan != 0);

        System.out.println("Program selesai.");
    }
}
