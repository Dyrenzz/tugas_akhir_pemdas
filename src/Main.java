import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.text.DecimalFormat;

public class Main {
    static Scanner input = new Scanner(System.in);
    static int memberIncrement = 0;
    static int peminjamanIncrement = 0;
    static DecimalFormat decimalFormat = new DecimalFormat("0000");

    public static void main(String[] args) {
        Perpustakaan perpus = new Perpustakaan("Perpus Melia", "Jl. Bakso Without Tepung No. 5");

        // Tambah contoh buku
        inisialisasiBuku(perpus);

        // Tambah petugas
        Petugas petugas1 = new Petugas("P001", "Roy");

        int pilihanMasuk = 0;
        int pilihanMenu = 0;
        Member currentMember;
        do {
            tampilkanMenuMasuk();
            System.out.print("Masukkan pilihan (0-2): ");
            pilihanMasuk = input.nextInt();

            // Hapus newline di buffer
            input.nextLine();

            switch (pilihanMasuk) {

                case 1:
                    System.out.print("Masukkan Member ID: ");
                    String idMember = input.next();

                    currentMember = perpus.cariIdMember(idMember);
                    if (currentMember != null) {
                        do {
                            tampilkanMenuPerpustakaan();
                            System.out.print("Pilih (0-4): ");
                            pilihanMenu = input.nextInt();

                            // Hapus newline di buffer
                            input.nextLine();

                            switch (pilihanMenu) {

                                case 1:
                                    perpus.tampilkanDaftarBuku();
                                    break;

                                case 2:
                                    System.out.print("Masukkan judul: ");
                                    String judul = input.nextLine();
                                    Buku hasil = perpus.cariJudulBuku(judul);
                                    if (hasil != null) {
                                        System.out.println("=".repeat(79));
                                        hasil.tampilkan();
                                        System.out.println("=".repeat(79));
                                    } else {
                                        System.out.println("Buku tidak ditemukan!");
                                    }
                                    break;

                                case 3:
                                    perpus.tampilkanDaftarBuku();
                                    System.out.print("Masukkan id buku yang ingin dipinjam: ");
                                    String idBuku = input.next();
                                    Buku bk = perpus.cariIdBuku(idBuku);

                                    if (bk != null && bk.cekKetersediaan()) {
                                        peminjamanIncrement++;
                                        Peminjaman pm = petugas1.prosesPeminjaman(String.format("P%s", decimalFormat.format(peminjamanIncrement)), 5, bk, currentMember, petugas1);
                                        perpus.tambahPeminjaman(pm);

                                        System.out.println("Peminjaman berhasil! \nId Peminjaman: " + pm.getId());
                                    } else {
                                        System.out.println("Buku tidak ditemukan / sedang dipinjam.");
                                    }
                                    break;
                                
                                case 4:
                                    currentMember.tampilkanDaftarPeminjaman();
                                    System.out.print("Masukkan id peminjaman: ");
                                    String idPeminjaman = input.next();

                                    Peminjaman invoice = perpus.cariIdPeminjaman(idPeminjaman);
                                    if (invoice != null) {
                                        petugas1.prosesPengembalian(invoice);
                                    }
                                    else {
                                        System.out.println("Peminjaman tidak ditemukan");
                                        System.out.println("Pengembalian gagal!");
                                    }
                                    break;

                                default:
                                    break;
                            }
                        } while (pilihanMenu != 0); 
                    } else {
                        System.out.println("Member tidak ditemukan!");
                    }
                    break;

                case 2:
                    jalankanDaftarMember(perpus);
                    break;

                default:
                    break;
            }
        } while (pilihanMasuk != 0);

        System.out.println("Program selesai.");
    }

    static public void tampilkanMenuMasuk() {
        System.out.println("\n==== Validasi Member ====");
        System.out.println("1. Masuk Member");
        System.out.println("2. Daftar Member");
        System.out.println("0. Keluar \n");
    }

    static public void jalankanDaftarMember(Perpustakaan perpus) {
        System.out.println("\n==== Daftar Member ====");
        System.out.print("Masukkan Nama: ");
        String nama = input.nextLine();
        System.out.print("Masukkan Alamat: ");
        String alamat = input.nextLine();
        System.out.print("Masukkan Nomor Telepon: ");
        String noTelp = input.nextLine();

        memberIncrement++;
        Member memberBaru = perpus.tambahMember(String.format("M%s", decimalFormat.format(memberIncrement)), nama, alamat, noTelp);

        System.out.println("\nSelamat member berhasil didaftarkan!");
        System.out.println("Member id kamu: " + memberBaru.getId());
        System.out.println("Tips: member id digunakan untuk akses perpustakaan!");
    }

    static public void tampilkanMenuPerpustakaan() {
        System.out.println("\n==== MENU PERPUSTAKAAN ====");
        System.out.println("1. Tampilkan Daftar Buku");
        System.out.println("2. Cari Buku");
        System.out.println("3. Pinjam Buku");
        System.out.println("4. Kembalikan Buku");
        System.out.println("0. Keluar\n");
    }

    static public void inisialisasiBuku(Perpustakaan perpus) {
        perpus.tambahBuku(new Buku(
                "B001",
                "Pemrograman Java Dasar",
                2019,
                5,
                new ArrayList<>(Arrays.asList("Andi Pratama"))));

        perpus.tambahBuku(new Buku(
                "B002",
                "Struktur Data dan Algoritma",
                2020,
                4,
                new ArrayList<>(Arrays.asList("Budi Setiawan"))));

        perpus.tambahBuku(new Buku(
                "B003",
                "Basis Data Lanjutan",
                2018,
                3,
                new ArrayList<>(Arrays.asList("Sinta Dewi"))));

        perpus.tambahBuku(new Buku(
                "B004",
                "Jaringan Komputer",
                2021,
                6,
                new ArrayList<>(Arrays.asList("Rahmat Hidayat"))));

        perpus.tambahBuku(new Buku(
                "B005",
                "Sistem Operasi",
                2017,
                2,
                new ArrayList<>(Arrays.asList("Fitriani"))));

        perpus.tambahBuku(new Buku(
                "B006",
                "Pemrograman Web",
                2022,
                7,
                new ArrayList<>(Arrays.asList("Dewi Lestari"))));

        perpus.tambahBuku(new Buku(
                "B007",
                "Kecerdasan Buatan",
                2021,
                5,
                new ArrayList<>(Arrays.asList("Hendra Wijaya"))));

        perpus.tambahBuku(new Buku(
                "B008",
                "Machine Learning",
                2023,
                4,
                new ArrayList<>(Arrays.asList("Nanda Prasetyo"))));

        perpus.tambahBuku(new Buku(
                "B009",
                "Analisis dan Perancangan Sistem",
                2016,
                3,
                new ArrayList<>(Arrays.asList("Lina Marlina"))));

        perpus.tambahBuku(new Buku(
                "B010",
                "Metode Numerik",
                2019,
                6,
                new ArrayList<>(Arrays.asList("Agus Saputra"))));
    }
}
