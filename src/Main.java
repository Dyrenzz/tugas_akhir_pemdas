import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.text.DecimalFormat;

public class Main {
    static Scanner input = new Scanner(System.in);
    static int memberIncrement = 0;
    static int peminjamanIncrement = 0;
    static int itemIncrement = 0;
    static DecimalFormat decimalFormat = new DecimalFormat("0000");
    static ArrayList<Item> daftarItemPinjam = new ArrayList<>();
    static ArrayList<Buku> daftarPinjamBuku = new ArrayList<>();
    static ArrayList<Buku> hasilCari = new ArrayList<>();

    public static void main(String[] args) {
        Perpustakaan perpus = new Perpustakaan("Pustaka Majapahit", "Jl. Bakso Without Tepung No. 5");

        // Tambah contoh buku
        inisialisasiBuku(perpus);

        // Tambah petugas
        Petugas mas_roy = new Petugas("P001", "Roy");

        int pilihanMasuk = 0;
        int pilihanMenu = 0;
        char pilihanLooping = ' ';
        boolean isTerpinjam = false;
        boolean isTersedia = false;
        Member currentMember;

        System.out.println(perpus);
        do {
            tampilkanMenuMasuk();
            System.out.print("Masukkan pilihan (0-2): ");
            pilihanMasuk = input.nextInt();

            // Hapus newline di buffer
            input.nextLine();

            switch (pilihanMasuk) {

                case 1:
                    System.out.print("Masukkan ID Member: ");
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

                                case 1: // Tampilkan Daftar Buku
                                    perpus.tampilkanDaftarBuku();
                                    break;

                                case 2: // Cari Judul Buku
                                    System.out.print("Masukkan judul: ");
                                    String judul = input.nextLine();
                                    for (Buku buku : perpus.getDaftarBuku()) {
                                        if (buku.getJudul().toLowerCase().contains(judul.toLowerCase()))
                                            hasilCari.add(buku);
                                    }

                                    System.out.printf("\nHasil pencarian dari (%s):%n", judul);
                                    if (hasilCari.size() > 0) {
                                        System.out.println("=".repeat(80));
                                        for (Buku buku : hasilCari) {
                                            System.out.println(buku);
                                        }
                                        System.out.println("=".repeat(80));
                                    } else {
                                        System.out.println("Buku tidak ditemukan!");
                                    }
                                    hasilCari.clear();
                                    break;

                                case 3: // Pinjam buku by id
                                    perpus.tampilkanDaftarBuku();

                                    do {
                                        System.out.print("Masukkan id buku yang ingin dipinjam: ");
                                        String idBuku = input.next().strip().toUpperCase();

                                        isTerpinjam = false;
                                        isTersedia = false;
                                        for (Item itemPj : daftarItemPinjam)
                                            if (itemPj.getBuku().getId().equals(idBuku) && itemPj.getStatus() == false)
                                                isTerpinjam = true;
                                        for (Buku bukuPk : daftarPinjamBuku)
                                            if (bukuPk.getId().equals(idBuku))
                                                isTerpinjam = true;
                                        if (!isTerpinjam)
                                            for (Buku buku : perpus.getDaftarBuku()) {
                                                if (buku.getId().equals(idBuku)) {
                                                    daftarPinjamBuku.add(buku);
                                                    isTersedia = true;
                                                    System.out.println("Buku tersedia! Buku berhasil diambil!");
                                                }
                                            }
                                        else
                                            System.out.println("Buku sedang dipinjam!");

                                        if (!isTersedia)
                                            System.out.println("Buku tidak tersedia!");

                                        System.out.print("\nPinjam buku lagi (y/n): ");
                                        pilihanLooping = input.next().strip().toLowerCase().charAt(0);
                                    } while (pilihanLooping != 'n');

                                    System.out.println("\n" + "=".repeat(30));
                                    if (daftarPinjamBuku.size() > 0) {
                                        peminjamanIncrement++;
                                        Peminjaman pm = mas_roy.prosesPeminjaman(new Peminjaman(
                                                String.format("P%s", decimalFormat.format(peminjamanIncrement)), 5,
                                                currentMember, mas_roy));

                                        for (Buku bk : daftarPinjamBuku) {
                                            itemIncrement++;
                                            daftarItemPinjam.add(new Item(
                                                    String.format("T%s", decimalFormat.format(itemIncrement)), pm, bk));
                                        }

                                        daftarPinjamBuku.clear();
                                        System.out.println("| Peminjaman berhasil!       |");
                                        System.out.println("| Id Peminjaman: " + pm.getId() + "       |");
                                    } else {
                                        System.out.println("Peminjaman gagal!");
                                    }
                                    System.out.println("=".repeat(30));
                                    break;

                                case 4: // Kembalikan buku
                                    System.out.println("=".repeat(94));
                                    for (Item itemPj : daftarItemPinjam)
                                        if (itemPj.getPj().getMember() == currentMember && itemPj.getStatus() == false)
                                            System.out.println(itemPj);
                                    System.out.println("=".repeat(94));

                                    System.out.print("Masukkan id peminjaman: ");
                                    String idItemPj = input.next().trim().toUpperCase();

                                    isTersedia = false;
                                    for (Item itemPj : daftarItemPinjam)
                                        if (itemPj.getPj().getMember() == currentMember && itemPj.getId().equals(idItemPj) && itemPj.getStatus() == false) {
                                            mas_roy.prosesPengembalian(itemPj);
                                            isTersedia = true;
                                        }

                                    System.out.println("\n" + "=".repeat(30));
                                    if (isTersedia)
                                        System.out.println("|   Pengembalian berhasil!   |");
                                    else {
                                        System.out.println("| Peminjaman tidak ditemukan |");
                                        System.out.println("| Pengembalian gagal!        |");
                                    }
                                    System.out.println("=".repeat(30));
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
        Member memberBaru = perpus.tambahMember(
                new Member(String.format("M%s", decimalFormat.format(memberIncrement)), nama, alamat, noTelp));

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
                new ArrayList<>(Arrays.asList("Andi Pratama"))));

        perpus.tambahBuku(new Buku(
                "B002",
                "Struktur Data dan Algoritma",
                2020,
                new ArrayList<>(Arrays.asList("Budi Setiawan"))));

        perpus.tambahBuku(new Buku(
                "B003",
                "Basis Data Lanjutan",
                2018,
                new ArrayList<>(Arrays.asList("Sinta Dewi"))));

        perpus.tambahBuku(new Buku(
                "B004",
                "Jaringan Komputer",
                2021,
                new ArrayList<>(Arrays.asList("Rahmat Hidayat"))));

        perpus.tambahBuku(new Buku(
                "B005",
                "Sistem Operasi",
                2017,
                new ArrayList<>(Arrays.asList("Fitriani"))));

        perpus.tambahBuku(new Buku(
                "B006",
                "Pemrograman Web",
                2022,
                new ArrayList<>(Arrays.asList("Dewi Lestari"))));

        perpus.tambahBuku(new Buku(
                "B007",
                "Kecerdasan Buatan",
                2021,
                new ArrayList<>(Arrays.asList("Hendra Wijaya"))));

        perpus.tambahBuku(new Buku(
                "B008",
                "Machine Learning",
                2023,
                new ArrayList<>(Arrays.asList("Nanda Prasetyo"))));

        perpus.tambahBuku(new Buku(
                "B009",
                "Analisis dan Perancangan Sistem",
                2016,
                new ArrayList<>(Arrays.asList("Lina Marlina"))));

        perpus.tambahBuku(new Buku(
                "B010",
                "Metode Numerik",
                2019,
                new ArrayList<>(Arrays.asList("Agus Saputra"))));
    }
}
