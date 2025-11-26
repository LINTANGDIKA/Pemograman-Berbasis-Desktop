import java.util.Scanner;

class Menu {
    private String nama;
    private double harga;
    private String kategori;

    public Menu(String nama, double harga, String kategori) {
        this.nama = nama;
        this.harga = harga;
        this.kategori = kategori;
    }

    public String getNama() { return nama; }
    public double getHarga() { return harga; }
    public void setHarga(double harga) { this.harga = harga; }
    public String getKategori() { return kategori; }
}

class Pesanan {
    Menu menu;
    int jumlah;

    public Pesanan(Menu menu, int jumlah) {
        this.menu = menu;
        this.jumlah = jumlah;
    }
}

public class AplikasiRestoran {
    static Menu[] daftarMenu = new Menu[100]; 
    static int jumlahMenu = 0; 
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        inisialisasiDataAwal();

        while (true) {
            System.out.println("\n==================================");
            System.out.println("     SELAMAT DATANG DI RESTORAN   ");
            System.out.println("==================================");
            System.out.println("1. Menu Pelanggan (Pemesanan)");
            System.out.println("2. Menu Pemilik (Manajemen Menu)");
            System.out.println("3. Keluar Aplikasi");
            System.out.print("Pilih menu (1-3): ");
            
            String pilihan = input.nextLine();

            switch (pilihan) {
                case "1":
                    menuPemesanan();
                    break;
                case "2":
                    menuAdmin();
                    break;
                case "3":
                    System.out.println("Terima kasih telah menggunakan aplikasi ini.");
                    return;
                default:
                    System.out.println("Input tidak valid! Silakan pilih 1, 2, atau 3.");
            }
        }
    }

    static void inisialisasiDataAwal() {
        tambahMenuKeArray(new Menu("Nasi Goreng", 25000, "Makanan"));
        tambahMenuKeArray(new Menu("Ayam Bakar", 30000, "Makanan"));
        tambahMenuKeArray(new Menu("Mie Goreng", 22000, "Makanan"));
        tambahMenuKeArray(new Menu("Sate Ayam", 35000, "Makanan"));
        
        tambahMenuKeArray(new Menu("Es Teh Manis", 5000, "Minuman"));
        tambahMenuKeArray(new Menu("Jus Jeruk", 12000, "Minuman"));
        tambahMenuKeArray(new Menu("Kopi Hitam", 8000, "Minuman"));
        tambahMenuKeArray(new Menu("Air Mineral", 4000, "Minuman"));
    }

    static void tambahMenuKeArray(Menu menuBaru) {
        if (jumlahMenu < daftarMenu.length) {
            daftarMenu[jumlahMenu] = menuBaru;
            jumlahMenu++;
        } else {
            System.out.println("Kapasitas menu penuh!");
        }
    }

    static void tampilkanDaftarMenu() {
        System.out.println("\n------- DAFTAR MENU RESTORAN -------");
        
        System.out.println("\n[ KATEGORI MAKANAN ]");
        int nomor = 1;
        for (int i = 0; i < jumlahMenu; i++) {
            if (daftarMenu[i].getKategori().equalsIgnoreCase("Makanan")) {
                System.out.println(nomor + ". " + daftarMenu[i].getNama() + " \t- Rp " + (int)daftarMenu[i].getHarga());
            }
            nomor++; 
        }

        System.out.println("\n[ KATEGORI MINUMAN ]");
        
        for (int i = 0; i < jumlahMenu; i++) {
            if (daftarMenu[i].getKategori().equalsIgnoreCase("Minuman")) {
                System.out.println(daftarMenu[i].getNama() + " \t- Rp " + (int)daftarMenu[i].getHarga());
            }
        }
        System.out.println("------------------------------------");
    }

    static void tampilkanMenuAdmin() {
        System.out.println("\n--- LIST DATA MENU ---");
        for (int i = 0; i < jumlahMenu; i++) {
            System.out.println((i + 1) + ". " + daftarMenu[i].getNama() + " (" + daftarMenu[i].getKategori() + ") - Rp " + (int)daftarMenu[i].getHarga());
        }
    }

    static void menuPemesanan() {
        Pesanan[] keranjang = new Pesanan[50]; 
        int jumlahItemKeranjang = 0;

        while (true) {
            tampilkanDaftarMenu();
            System.out.println("Ketik nama menu untuk memesan, atau ketik 'selesai' untuk ke pembayaran.");
            System.out.print("Pesanan Anda: ");
            String namaMenu = input.nextLine();

            if (namaMenu.equalsIgnoreCase("selesai")) {
                if (jumlahItemKeranjang > 0) {
                    cetakStruk(keranjang, jumlahItemKeranjang);
                } else {
                    System.out.println("Anda belum memesan apapun.");
                }
                break;
            }

            int indexDitemukan = -1;
            for (int i = 0; i < jumlahMenu; i++) {
                if (daftarMenu[i].getNama().equalsIgnoreCase(namaMenu)) {
                    indexDitemukan = i;
                    break;
                }
            }

            if (indexDitemukan == -1) {
                System.out.println(">>> Menu tidak ditemukan! Silakan input nama menu dengan benar.");
                continue; 
            }

            int qty = 0;
            while (true) {
                try {
                    System.out.print("Masukkan jumlah: ");
                    qty = Integer.parseInt(input.nextLine());
                    if (qty > 0) break;
                    System.out.println("Jumlah harus lebih dari 0.");
                } catch (NumberFormatException e) {
                    System.out.println("Input harus angka!");
                }
            }

            keranjang[jumlahItemKeranjang] = new Pesanan(daftarMenu[indexDitemukan], qty);
            jumlahItemKeranjang++;
            System.out.println(">>> Berhasil ditambahkan ke pesanan.");
        }
    }

    static void cetakStruk(Pesanan[] keranjang, int jumlahItem) {
        double subTotal = 0;
        boolean adaMinuman = false;
        

        double hargaSatuMinuman = 0; 

        System.out.println("\n\n##################################");
        System.out.println("          STRUK PESANAN           ");
        System.out.println("##################################");

        for (int i = 0; i < jumlahItem; i++) {
            Pesanan p = keranjang[i];
            double totalItem = p.menu.getHarga() * p.jumlah;
            subTotal += totalItem;

            System.out.printf("%-15s x %d \t= Rp %s%n", p.menu.getNama(), p.jumlah, (int)totalItem);
        }

        System.out.println("----------------------------------");
        System.out.println("Total Biaya Pesanan : Rp " + (int)subTotal);

        double pajak = subTotal * 0.10;
        double layanan = 20000;
        double diskon = 0;

        if (subTotal > 100000) {
            diskon = subTotal * 0.10;
        }

        double grandTotal = subTotal + pajak + layanan - diskon;

        System.out.println("Pajak (10%)         : Rp " + (int)pajak);
        System.out.println("Biaya Layanan       : Rp " + (int)layanan);
        System.out.println("----------------------------------");
        
        if (diskon > 0) {
            System.out.println("DISKON (Total > 100k): -Rp " + (int)diskon);
        }
        if (subTotal > 50000) {
            System.out.println("Anda Mendapatkan Promo beli 1 gratis 1 menu kategori minuman");
        }

        System.out.println("==================================");
        System.out.println("TOTAL PEMBAYARAN    : Rp " + (int)grandTotal);
        System.out.println("##################################\n");
    }

    static void menuAdmin() {
        while (true) {
            System.out.println("\n===== MANAJEMEN MENU =====");
            System.out.println("1. Tambah Menu Baru");
            System.out.println("2. Ubah Harga Menu");
            System.out.println("3. Hapus Menu");
            System.out.println("4. Kembali ke Menu Utama");
            System.out.print("Pilih: ");
            String pilih = input.nextLine();

            if (pilih.equals("1")) {
                adminTambahMenu();
            } else if (pilih.equals("2")) {
                adminUbahHarga();
            } else if (pilih.equals("3")) {
                adminHapusMenu();
            } else if (pilih.equals("4")) {
                break;
            } else {
                System.out.println("Pilihan tidak valid.");
            }
        }
    }

    static void adminTambahMenu() {
        System.out.println("\n--- Tambah Menu Baru ---");
        System.out.print("Masukkan Nama Menu: ");
        String nama = input.nextLine();
        
        System.out.print("Masukkan Kategori (Makanan/Minuman): ");
        String kategori = input.nextLine();

        double harga = 0;
        while(true) {
            try {
                System.out.print("Masukkan Harga: ");
                harga = Double.parseDouble(input.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Harga harus angka!");
            }
        }

        tambahMenuKeArray(new Menu(nama, harga, kategori));
        System.out.println(">> Menu berhasil ditambahkan!");
    }

    static void adminUbahHarga() {
        tampilkanMenuAdmin();
        System.out.print("Masukkan NOMOR menu yang ingin diubah harganya: ");
        
        int idx = -1;
        try {
            idx = Integer.parseInt(input.nextLine()) - 1; 
        } catch (Exception e) {
            System.out.println("Input harus angka!");
            return;
        }

        if (idx < 0 || idx >= jumlahMenu) {
            System.out.println("Nomor menu tidak ditemukan!");
            return;
        }

        System.out.println("Menu terpilih: " + daftarMenu[idx].getNama() + " (Harga saat ini: " + daftarMenu[idx].getHarga() + ")");
        System.out.print("Apakah Anda yakin ingin mengubah harga? (Ya/Tidak): ");
        String konfirmasi = input.nextLine();

        if (konfirmasi.equalsIgnoreCase("Ya")) {
            System.out.print("Masukkan harga baru: ");
            try {
                double hargaBaru = Double.parseDouble(input.nextLine());
                daftarMenu[idx].setHarga(hargaBaru);
                System.out.println(">> Harga berhasil diubah!");
            } catch (Exception e) {
                System.out.println("Harga tidak valid. Batal mengubah.");
            }
        } else {
            System.out.println(">> Batal mengubah harga.");
        }
    }

    static void adminHapusMenu() {
        tampilkanMenuAdmin();
        System.out.print("Masukkan NOMOR menu yang ingin dihapus: ");
        
        int idx = -1;
        try {
            idx = Integer.parseInt(input.nextLine()) - 1; 
        } catch (Exception e) {
            System.out.println("Input harus angka!");
            return;
        }

        if (idx < 0 || idx >= jumlahMenu) {
            System.out.println("Nomor menu tidak ditemukan!");
            return;
        }

        System.out.println("Menu terpilih: " + daftarMenu[idx].getNama());
        System.out.print("Apakah Anda yakin ingin MENGHAPUS menu ini? (Ya/Tidak): ");
        String konfirmasi = input.nextLine();

        if (konfirmasi.equalsIgnoreCase("Ya")) {
            for (int i = idx; i < jumlahMenu - 1; i++) {
                daftarMenu[i] = daftarMenu[i + 1];
            }
            daftarMenu[jumlahMenu - 1] = null;
            jumlahMenu--; 
            System.out.println(">> Menu berhasil dihapus!");
        } else {
            System.out.println(">> Batal menghapus menu.");
        }
    }
}