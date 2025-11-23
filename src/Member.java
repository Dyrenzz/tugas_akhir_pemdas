public class Member {
    private String id;
    private String nama;
    private String alamat;
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
    public String getNama() {
        return nama;
    }
    public String getAlamat() {
        return alamat;
    }
    public String getNoTelepon() {
        return noTelepon;
    }

    // public void tambahPinjaman(Peminjaman invoice) {
    //     daftarPeminjaman.add(invoice);
    // }

    // public void tampilkanDaftarPeminjaman() {
    //     if (daftarPeminjaman.size() == 0) {
    //         System.out.println("Tidak ada peminjaman!");
    //     } 
    //     else {
    //         System.out.printf("%-5s | %-36s | %-12s | %-12s | %-13s | %-24s | %-24s %n", "Id", "Judul buku", "Tgl Pinjam", "Tg; Kembali", "Status", "Nama Member", "Nama Petugas");
    //         for (Peminjaman invoice: daftarPeminjaman) {
    //             if(!invoice.getStatus()) {
    //                 System.out.println(invoice);
    //             }
    //         }
    //     }
    // }
}
