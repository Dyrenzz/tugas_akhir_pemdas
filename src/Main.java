import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static String namaPerpus = "Pustaka Majapahit";
    static String alamatPerpus = "Jl. Bakso Goreng No. 5";

    static Scanner input = new Scanner(System.in);
    static ArrayList<Buku> daftarPinjamBuku = new ArrayList<>();
    static ArrayList<Buku> hasilCari = new ArrayList<>();

    static ArrayList<Buku> daftarBuku = new ArrayList<>();
    static ArrayList<Member> daftarMember = new ArrayList<>();
    static ArrayList<Pustakawan> daftarPustakawan = new ArrayList<>();
    static ArrayList<ItemPeminjaman> daftarItemPeminjaman = new ArrayList<>();
    static ArrayList<ItemPengembalian> daftarItemPengembalian = new ArrayList<>();
    static ArrayList<Denda> daftarDenda = new ArrayList<>();

    // konfigurasi denda
    static int BATAS_HARI_PINJAM = 5;
    static int DENDA_PER_HARI = 1000;

    public static void main(String[] args) {

        // Tambah contoh buku
        inisialisasiBuku();
        daftarMember.add(new Member("Dummy", "Dummy", "0123", "123", "123@gmail.com"));

        // Tambah petugas (hanya 1)
        Pustakawan mas_roy = new Pustakawan("Roy Anatoli", "081234567", "Jl. Bakso No. 1", "32020805304910003",
                "royss123");
        daftarPustakawan.add(mas_roy);

        int pilihan = 0;
        int pilihanM = 0;
        int pilihanP = 0;
        char pilihanLooping = ' ';
        boolean isTerpinjam = false;
        boolean isTersedia = false;
        Member currentMember;

        // Tampilkan identitas perpustakaan
        System.out.println(namaPerpus + ", " + alamatPerpus);

        do { // Menu: pilih sebagai admin atau member
            System.out.println("==== Masuk Sebagai ====");
            System.out.println("1. Pustakawan");
            System.out.println("2. Member");
            System.out.println("0. Keluar");

            System.out.print("Masukkan pilihan: ");
            pilihan = input.nextInt();

            if (pilihan == 1) {
                do { // Menu: sebagai admin
                    System.out.println("==== Pustakawan ====");
                    System.out.println("1. Login Pustakawan");
                    System.out.println("0. Keluar");

                    System.out.print("\n Masukkan pilihan: ");
                    pilihanP = input.nextInt();

                    if (pilihanP == 1) {
                        System.out.println("NIK: ");
                        String NIK_pst = input.next();
                        System.out.println("Password: ");
                        String pass_pst = input.next();

                        // Validasi user id dan password
                        if (mas_roy.getNIK().equals(NIK_pst) && mas_roy.getPassword().equals(pass_pst)) {
                            do {
                                // menu pustakawan
                                System.out.println("\n==== MENU PUSTAKAWAN ====");
                                System.out.println("1. Tampilkan Daftar Peminjaman");
                                System.out.println("2. Tampilkan Daftar Pengembalian");
                                System.out.println("3. Tambahkan Buku");
                                System.out.println("4. Tampilkan Daftar Buku");

                                System.out.println("5. Keluar\n");

                                System.out.print("Masukkan pilihan: ");
                                pilihanP = input.nextInt();
                                
                                if (pilihanP == 1) {
                                    tampilkanDaftarPeminjaman();
                                }
                                else if (pilihanP == 2) {
                                    tampilkanDaftarPengembalian();
                                }
                                else if (pilihanP == 3) {
                                    jalankanRegisterBuku();
                                }
                                else if (pilihanP == 4) {
                                    tampilkanDaftarBuku();
                                }
                                else continue;
                            } while (pilihanP != 5);
                        } else {
                            System.out.println("Login gagal! NIK atau password salah.");
                            continue;
                        }

                    } else
                        continue;
                } while (pilihanP != 0);
            }

            else if (pilihan == 2) {
                do { // Menu: sebagai member
                    tampilkanMenuMasuk();
                    System.out.print("Masukkan pilihan (0-2): ");
                    pilihanM = input.nextInt();

                    // Hapus newline di buffer
                    input.nextLine();

                    switch (pilihanM) {

                        case 1:
                            // Login dengan NIS
                            System.out.print("Masukkan NIS: ");
                            String nisMember = input.next();

                            currentMember = cariIdMember(nisMember);
                            if (currentMember != null) {
                                do {
                                    tampilkanMenuPerpustakaan();
                                    System.out.print("Pilih (0-4): ");
                                    pilihanM = input.nextInt();

                                    // Hapus newline di buffer
                                    input.nextLine();

                                    // Fitur-fitur perpustakaan
                                    switch (pilihanM) {

                                        case 1: // Tampilkan Daftar Buku
                                            tampilkanDaftarBuku();
                                            break;

                                        case 2: // Tampilkan daftar peminjaman
                                            tampilkanDaftarPeminjaman(currentMember);
                                            break;

                                        case 3: // Pinjam buku by id
                                            tampilkanDaftarBuku();

                                            // Maksimal 5 buku
                                            Buku[] itemBuku = new Buku[5];
                                            int counterItPj = 0;

                                            do {
                                                System.out.print("Masukkan id buku yang ingin dipinjam: ");
                                                String idBuku = input.next().strip().toUpperCase();

                                                Buku curBuku = cariIdBuku(idBuku);
                                                if (curBuku != null && curBuku.getStock() > 0
                                                        && curBuku.kurangiStock()) {
                                                    try {
                                                        itemBuku[counterItPj] = curBuku;
                                                        counterItPj++;
                                                        System.out.println("Buku tersedia! Buku berhasil diambil!");
                                                    } catch (ArrayIndexOutOfBoundsException e) {
                                                        System.out.println("Maksimal 5 buku!");
                                                    }
                                                } else if (curBuku.getStock() <= 0)
                                                    System.out.println("Semua buku sedang dipinjam!");
                                                else
                                                    System.out.println("Buku tidak tersedia!");

                                                System.out.print("\nPinjam buku lagi (y/n)?  ");
                                                pilihanLooping = input.next().strip().toLowerCase().charAt(0);
                                            } while (pilihanLooping != 'n');

                                            System.out.println("\n" + "-".repeat(30));
                                            if (itemBuku.length > 0) {
                                                Peminjaman pm = new Peminjaman(currentMember, mas_roy);

                                                for (Buku bk : itemBuku) {
                                                    if (bk != null)
                                                        daftarItemPeminjaman.add(new ItemPeminjaman(pm, bk));
                                                }

                                                System.out.println(" Peminjaman berhasil!");
                                            } else {
                                                System.out.println(" Peminjaman gagal!");
                                            }
                                            System.out.println("-".repeat(30));
                                            break;

                                        case 4: // Kembalikan buku
                                            tampilkanDaftarPeminjaman(currentMember);
                                            ArrayList<ItemPeminjaman> itemBukuPj = new ArrayList<>();

                                            do {
                                                System.out.print("Masukkan id peminjaman: ");
                                                String idItemPj = input.next().trim().toUpperCase();

                                                ItemPeminjaman curItemPj = cariIdItemPj(idItemPj);
                                                if (curItemPj != null) {
                                                    itemBukuPj.add(curItemPj);
                                                    System.out.println("Peminjaman ada! Buku siap dikembalikan.");
                                                } else
                                                    System.out.println("Peminjaman tidak tersedia!");

                                                System.out.print("\nKembalikan buku lagi (y/n)? ");
                                                pilihanLooping = input.next().strip().toLowerCase().charAt(0);
                                            } while (pilihanLooping != 'n');

                                            System.out.println("\n" + "=".repeat(30));
                                            if (itemBukuPj.size() > 0) {
                                                Pengembalian pg = new Pengembalian(currentMember, mas_roy);
                                                int totalDenda = 0;

                                                for (ItemPeminjaman itemPj : itemBukuPj) {
                                                    ItemPengembalian curItemPg = new ItemPengembalian(pg, itemPj.getBuku());
                                                    daftarItemPengembalian.add(curItemPg);
                                                    itemPj.getBuku().tambahStock();

                                                    long selisihHari = ChronoUnit.DAYS.between(
                                                            itemPj.getPj().getTanggalPinjam(),
                                                            LocalDate.now()
                                                    );

                                                    if (selisihHari > BATAS_HARI_PINJAM) {
                                                        long terlambat = selisihHari - BATAS_HARI_PINJAM;
                                                        int nominalDenda = (int) terlambat * DENDA_PER_HARI;
                                                        totalDenda += nominalDenda;

                                                        Denda d = new Denda(curItemPg.getPg(), curItemPg,
                                                                nominalDenda);

                                                        daftarDenda.add(d);
                                                        System.out.println(" Buku terlambat: " + terlambat + " hari");
                                                        System.out.println(" Denda: Rp" + nominalDenda);
                                                        System.out.println("-".repeat(30));
                                                    }
                                                }
                                                if (totalDenda > 0) {
                                                    System.out.println(" Total denda: Rp" + totalDenda);
                                                }
                                                System.out.println("\n Pengembalian berhasil!");

                                            } else {
                                                System.out.println(" Pengembalian gagal!");
                                            }
                                            System.out.println("=".repeat(30));
                                            break;

                                        default:
                                            break;
                                    }
                                } while (pilihanM != 0);
                            } else {
                                System.out.println("Member tidak ditemukan!");
                            }
                            break;

                        case 2:
                            jalankanRegisterMember();
                            break;

                        default:
                            break;
                    }
                } while (pilihanM != 0);
            }

            else
                continue;
        } while (pilihan != 0);

        System.out.println("Program selesai.");
    }

    public static void tampilkanDaftarPeminjaman(Member curMember) {
        System.out.println("=".repeat(68));
        System.out.printf(" %-7s | %-36s | %-15s%n", "Id", "Buku", "Tanggal Pinjam");
        System.out.println("-".repeat(68));
        for (ItemPeminjaman itPj : daftarItemPeminjaman) {
            if (itPj.getPj().getMember().getId().equals(curMember.getId()))
                System.out.println(itPj);
        }
        System.out.println("=".repeat(68));
    }
    public static void tampilkanDaftarPeminjaman() {
        System.out.println("=".repeat(94));
        System.out.printf(" %-7s | %-36s | %-15s | %25s%n", "Id", "Buku", "Tanggal Pinjam", "Peminjam");
        System.out.println("-".repeat(94));
        for (ItemPeminjaman itPj : daftarItemPeminjaman) 
            System.out.printf(" %-7s | %-36s | %-15s | %25s%n", itPj.getId(), itPj.getBuku().getJudul(), itPj.getPj().getTanggalPinjam(), itPj.getPj().getMember().getNama());
        System.out.println("=".repeat(94));
    }
    public static void tampilkanDaftarPengembalian() {
        System.out.println("=".repeat(94));
        System.out.printf(" %-7s | %-36s | %-15s | %25s%n", "Id", "Buku", "Tanggal Kembali", "Peminjam");
        System.out.println("-".repeat(94));
        for (ItemPengembalian itPg : daftarItemPengembalian) 
            System.out.printf(" %-7s | %-36s | %-15s | %25s%n", itPg.getId(), itPg.getBuku().getJudul(), itPg.getPg().getTanggalKembali(), itPg.getPg().getMember().getNama());
        System.out.println("=".repeat(94));
    }
    public static void tampilkanDaftarBuku() {
        System.out.println("=".repeat(90));
        System.out.printf(" %-4s | %-40s | %-30s | %s%n", "Id", "Judul", "Penulis", "Stock");
        System.out.println("-".repeat(90));
        for (Buku buku : daftarBuku) {
            System.out.println(buku);
        }
        System.out.println("=".repeat(90));
    }

    static public void tampilkanMenuMasuk() {
        System.out.println("\n==== Validasi Member ====");
        System.out.println("1. Masuk Member");
        System.out.println("2. Daftar Member");
        System.out.println("0. Keluar \n");
    }
    public static void tampilkanMenuPerpustakaan() {
        System.out.println("\n==== MENU PERPUSTAKAAN ====");
        System.out.println("1. Tampilkan Daftar Buku");
        System.out.println("2. Tampilkan Daftar Peminjaman");
        System.out.println("3. Pinjam Buku");
        System.out.println("4. Kembalikan Buku");
        System.out.println("0. Keluar\n");
    }

    static public void jalankanRegisterMember() {
        System.out.println("\n==== Daftar Member ====");
        System.out.print("Masukkan NIS: ");
        String NIS = input.nextLine();
        System.out.print("Masukkan Nama: ");
        String nama = input.nextLine();
        System.out.print("Masukkan Alamat: ");
        String alamat = input.nextLine();
        System.out.print("Masukkan Nomor Telepon: ");
        String noTelp = input.nextLine();
        System.out.print("Masukkan E-mail: ");
        String email = input.nextLine();

        Member memberBaru = new Member(nama, alamat, noTelp, NIS, email);
        daftarMember.add(memberBaru);

        System.out.println("\nSelamat member berhasil didaftarkan!");
    }
    static public void jalankanRegisterBuku() {
        System.out.print("Masukkan ISBN Buku: ");
        String isbn = input.nextLine();
        System.out.print("Masukkan Judul Buku: ");
        String judul = input.nextLine();
        System.out.print("Masukkan Penulis: ");
        String penulis = input.nextLine();
        String[] strArray = penulis.split(",");
        
        System.out.print("Masukkan Kategori Buku: ");
        String kategori = input.nextLine();
        System.out.print("Masukkan Tahun Terbit Buku: ");
        int tahunTerbit = input.nextInt();
        System.out.print("Simpan di Rak Nomor (1-5): ");
        int noRak = input.nextInt();
        System.out.print("Masukkan Stock Buku: ");
        int stock = input.nextInt();
        // Hapus spasi di buffer
        input.nextLine();

        daftarBuku.add(new Buku(isbn, judul, new ArrayList<>(Arrays.asList(strArray)), tahunTerbit, kategori, noRak, stock));

        System.out.println("\nBuku berhasil ditambahkan!");
    }

    public static Member cariIdMember(String NIS_member) {
        for (Member member : daftarMember) {
            if (member.getNIS().equals(NIS_member)) {
                return member;
            }
        }
        return null;
    }

    public static Buku cariIdBuku(String idBuku) {
        for (Buku bk : daftarBuku) {
            if (bk.getId().equals(idBuku)) {
                return bk;
            }
        }
        return null;
    }

    public static ItemPeminjaman cariIdItemPj(String idItemPj) {
        for (ItemPeminjaman itPj : daftarItemPeminjaman) {
            if (itPj.getId().equals(idItemPj))
                return itPj;
        }
        return null;
    }

    public static void inisialisasiBuku() {
        daftarBuku.add(new Buku(
                "9786020324781", "Laskar Pelangi",
                new ArrayList<>(Arrays.asList("Andrea Hirata")),
                2005, "Novel", 1, 10));

        daftarBuku.add(new Buku(
                "9786022912122", "Negeri 5 Menara",
                new ArrayList<>(Arrays.asList("Ahmad Fuadi")),
                2009, "Novel", 1, 8));

        daftarBuku.add(new Buku(
                "9786020324798", "Bumi",
                new ArrayList<>(Arrays.asList("Tere Liye")),
                2014, "Fantasy", 2, 12));

        daftarBuku.add(new Buku(
                "9786020324804", "Bulán",
                new ArrayList<>(Arrays.asList("Tere Liye")),
                2015, "Fantasy", 2, 12));

        daftarBuku.add(new Buku(
                 "9789793062797", "Tentang Kamu",
                new ArrayList<>(Arrays.asList("Tere Liye")),
                2016, "Drama", 3, 7));

        daftarBuku.add(new Buku(
                "9786020330560", "Mindset",
                new ArrayList<>(Arrays.asList("Carol S. Dweck")),
                2006, "Pengembangan Diri", 4, 5));

        daftarBuku.add(new Buku(
                 "9786020630479", "Atomic Habits",
                new ArrayList<>(Arrays.asList("James Clear")),
                2018, "Pengembangan Diri", 4, 15));

        daftarBuku.add(new Buku(
                 "9786020632756", "Filosofi Teras",
                new ArrayList<>(Arrays.asList("Henry Manampiring")),
                2018, "Filsafat", 5, 9));

        daftarBuku.add(new Buku(
                 "9786230000001", "Sejarah Indonesia Modern",
                new ArrayList<>(Arrays.asList("MC Ricklefs")),
                2008, "Sejarah", 5, 6));

        daftarBuku.add(new Buku(
                 "9786020325061", "Pulang",
                new ArrayList<>(Arrays.asList("Tere Liye")),
                2015, "Drama", 3, 10));

        daftarBuku.add(new Buku(
                 "9786020325078", "Pergi",
                new ArrayList<>(Arrays.asList("Tere Liye")),
                2018, "Drama", 3, 10));

        daftarBuku.add(new Buku(
                 "9786022924569", "The Subtle Art of Not Giving a F*ck",
                new ArrayList<>(Arrays.asList("Mark Manson")),
                2016, "Pengembangan Diri", 4, 20));

        daftarBuku.add(new Buku(
                 "9786020324002", "Rich Dad Poor Dad",
                new ArrayList<>(Arrays.asList("Robert Kiyosaki")),
                1997, "Bisnis", 5, 14));

        daftarBuku.add(new Buku(
                 "9786020635511", "Matematika SMA Kelas 12",
                new ArrayList<>(Arrays.asList("Tim Penyusun Erlangga")),
                2021, "Pendidikan", 4, 25));

        daftarBuku.add(new Buku(
                 "9786020635528", "Pemrograman Java Dasar",
                new ArrayList<>(Arrays.asList("Wahyu Hidayat")),
                2020, "Teknologi", 5, 11));

        daftarBuku.add(new Buku(
                 "9786020323005", "Algoritma & Pemrograman",
                new ArrayList<>(Arrays.asList("Rinaldi Munir")),
                2013, "Teknologi", 5, 10));

        daftarBuku.add(new Buku(
                 "9786230001120", "Sapiens: A Brief History of Humankind",
                new ArrayList<>(Arrays.asList("Yuval Noah Harari")),
                2011, "Sejarah", 5, 7));

        daftarBuku.add(new Buku(
                 "9786020639007", "Psikologi Kepribadian",
                new ArrayList<>(Arrays.asList("Feist", "Feist", "Roberts")),
                2017, "Psikologi", 5, 5));

        daftarBuku.add(new Buku(
                 "9786020327113", "Cantik Itu Luka",
                new ArrayList<>(Arrays.asList("Eka Kurniawan")),
                2002, "Novel", 1, 6));

        daftarBuku.add(new Buku(
                 "9786020325027", "The Power of Habit",
                new ArrayList<>(Arrays.asList("Charles Duhigg")),
                2012, "Pengembangan Diri", 4, 13));

        daftarBuku.add(new Buku(
                 "9786020634501", "Kimia SMA Kelas 12",
                new ArrayList<>(Arrays.asList("Tim Erlangga")),
                2021, "Pendidikan", 4, 20));

        daftarBuku.add(new Buku(
                 "9786020634100", "Fisika SMA Kelas 12",
                new ArrayList<>(Arrays.asList("Tim Erlangga")),
                2021, "Pendidikan", 4, 22));

        daftarBuku.add(new Buku(
                 "9786020634209", "Biologi SMA Kelas 12",
                new ArrayList<>(Arrays.asList("Tim Erlangga")),
                2021, "Pendidikan", 4, 19));

        daftarBuku.add(new Buku(
                 "9786230301121", "Design Thinking",
                new ArrayList<>(Arrays.asList("Tim Brown")),
                2009, "Bisnis", 5, 9));

        daftarBuku.add(new Buku(
                 "9786020413217", "Ayat-Ayat Cinta",
                new ArrayList<>(Arrays.asList("Habiburrahman El Shirazy")),
                2004, "Novel Religi", 2, 12));

        daftarBuku.add(new Buku(
                 "9786022911124", "Hafalan Shalat Delisa",
                new ArrayList<>(Arrays.asList("Tere Liye")),
                2005, "Drama", 3, 10));

        daftarBuku.add(new Buku(
                 "9786028791224", "Pemrograman Web Modern",
                new ArrayList<>(Arrays.asList("Budi Raharja")),
                2019, "Teknologi", 5, 8));

        daftarBuku.add(new Buku(
                 "9786020329992", "Ensiklopedia Sains",
                new ArrayList<>(Arrays.asList("Tim Pustaka")),
                2018, "Referensi", 5, 15));

        daftarBuku.add(new Buku(
                 "9786020328889", "Statistika Dasar",
                new ArrayList<>(Arrays.asList("Sudjana")),
                2017, "Pendidikan", 4, 17));

        daftarBuku.add(new Buku(
                 "9786020335276", "Dasar-Dasar Manajemen",
                new ArrayList<>(Arrays.asList("Handoko")),
                2016, "Bisnis", 5, 14));

    }
}
