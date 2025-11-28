import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


abstract class MenuItem {
    
    private String nama;
    private double harga;
    private String kategori;

    public MenuItem(String nama, double harga, String kategori) {
        this.nama = nama;
        this.harga = harga;
        this.kategori = kategori;
    }


    public String getNama() { return nama; }
    public double getHarga() { return harga; }
    public String getKategori() { return kategori; }

   
    public abstract void tampilMenu();
}


class Makanan extends MenuItem {
    private String jenisMakanan; 

    public Makanan(String nama, double harga, String kategori, String jenisMakanan) {
        super(nama, harga, kategori);
        this.jenisMakanan = jenisMakanan;
    }

    public String getJenisMakanan() { return jenisMakanan; }

    @Override
    public void tampilMenu() {
        System.out.println("Makanan : " + getNama() + " | Rp" + getHarga() + " | " + getKategori() + " | Info: " + jenisMakanan);
    }
}

class Minuman extends MenuItem {
    private String jenisMinuman; 

    public Minuman(String nama, double harga, String kategori, String jenisMinuman) {
        super(nama, harga, kategori);
        this.jenisMinuman = jenisMinuman;
    }

    public String getJenisMinuman() { return jenisMinuman; }

    @Override
    public void tampilMenu() {
        System.out.println("Minuman : " + getNama() + " | Rp" + getHarga() + " | " + getKategori() + " | Info: " + jenisMinuman);
    }
}

class Diskon extends MenuItem {
    private double besarDiskon;

    public Diskon(String nama, double besarDiskon) {
        
        super(nama, 0, "Diskon");
        this.besarDiskon = besarDiskon;
    }

    public double getBesarDiskon() { return besarDiskon; }

    @Override
    public void tampilMenu() {
        System.out.println("Promo   : " + getNama() + " | Potongan: Rp" + besarDiskon);
    }
}


class Menu {
    private ArrayList<MenuItem> daftarMenu = new ArrayList<>();

    public void tambahItem(MenuItem item) {
        daftarMenu.add(item);
    }

    public void tampilkanSemuaMenu() {
        System.out.println("\n=== DAFTAR MENU RESTORAN ===");
        if (daftarMenu.isEmpty()) {
            System.out.println("Menu belum tersedia.");
        } else {
            for (int i = 0; i < daftarMenu.size(); i++) {
                System.out.print((i + 1) + ". ");
               
                daftarMenu.get(i).tampilMenu(); 
            }
        }
    }

    public MenuItem cariMenu(String nama) {
        for (MenuItem item : daftarMenu) {
            if (item.getNama().equalsIgnoreCase(nama)) {
                return item;
            }
        }
        return null;
    }

   
    public void simpanMenuKeFile(String namaFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(namaFile))) {
            for (MenuItem item : daftarMenu) {
                if (item instanceof Makanan) {
                    writer.write("Makanan;" + item.getNama() + ";" + item.getHarga() + ";" + item.getKategori() + ";" + ((Makanan) item).getJenisMakanan());
                } else if (item instanceof Minuman) {
                    writer.write("Minuman;" + item.getNama() + ";" + item.getHarga() + ";" + item.getKategori() + ";" + ((Minuman) item).getJenisMinuman());
                } else if (item instanceof Diskon) {
                    writer.write("Diskon;" + item.getNama() + ";" + ((Diskon) item).getBesarDiskon());
                }
                writer.newLine();
            }
            System.out.println("Data menu berhasil disimpan ke file.");
        } catch (IOException e) {
            System.out.println("Gagal menyimpan file: " + e.getMessage());
        }
    }

    public void muatMenuDariFile(String namaFile) {
        daftarMenu.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(namaFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts[0].equals("Makanan")) {
                    daftarMenu.add(new Makanan(parts[1], Double.parseDouble(parts[2]), parts[3], parts[4]));
                } else if (parts[0].equals("Minuman")) {
                    daftarMenu.add(new Minuman(parts[1], Double.parseDouble(parts[2]), parts[3], parts[4]));
                } else if (parts[0].equals("Diskon")) {
                    daftarMenu.add(new Diskon(parts[1], Double.parseDouble(parts[2])));
                }
            }
            System.out.println("Data menu berhasil dimuat dari file.");
        } catch (IOException e) {
            System.out.println("File menu tidak ditemukan, memulai dengan menu kosong.");
        }
    }
}

class Pesanan {
    private ArrayList<MenuItem> daftarPesanan = new ArrayList<>();

    public void tambahPesanan(MenuItem item) {
        daftarPesanan.add(item);
        System.out.println(item.getNama() + " ditambahkan ke pesanan.");
    }

    public double hitungTotal() {
        double total = 0;
        double totalDiskon = 0;

        for (MenuItem item : daftarPesanan) {
            if (item instanceof Diskon) {
                totalDiskon += ((Diskon) item).getBesarDiskon();
            } else {
                total += item.getHarga();
            }
        }
        return (total - totalDiskon) < 0 ? 0 : (total - totalDiskon);
    }

    public void cetakStruk() {
        System.out.println("\n=== STRUK PESANAN ===");
        double totalBayar = 0;

        ArrayList<MenuItem> tempPesanan = new ArrayList<>(daftarPesanan);

        while (!tempPesanan.isEmpty()) {
            MenuItem currentItem = tempPesanan.get(0);
            int count = 0;

            for (int i = 0; i < tempPesanan.size(); i++) {
                if (tempPesanan.get(i).getNama().equals(currentItem.getNama())) {
                    count++;
                }
            }

            double hargaSatuan = (currentItem instanceof Diskon) ? -((Diskon)currentItem).getBesarDiskon() : currentItem.getHarga();
            double subTotal = hargaSatuan * count;
            
            System.out.printf("%-15s x %d : Rp%.2f\n", currentItem.getNama(), count, subTotal);
            
            totalBayar += subTotal;
            String namaHapus = currentItem.getNama();
            tempPesanan.removeIf(item -> item.getNama().equals(namaHapus));
        }
        
        System.out.println("--------------------------------");
        System.out.printf("Total Bayar       : Rp%.2f\n", (totalBayar < 0 ? 0 : totalBayar));
    }


    public void simpanStrukKeFile(String namaFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(namaFile))) {
            writer.write("=== STRUK PESANAN ===\n");
            for (MenuItem item : daftarPesanan) {
                double hargaTampil = (item instanceof Diskon ? -((Diskon)item).getBesarDiskon() : item.getHarga());
                writer.write(item.getNama() + " : Rp" + hargaTampil + "\n");
            }
            writer.write("--------------------------------\n");
            writer.write("Total Bayar : Rp" + hitungTotal() + "\n");
            System.out.println("Struk berhasil disimpan ke " + namaFile);
        } catch (IOException e) {
            System.out.println("Gagal menyimpan struk.");
        }
    }
}

// --- MAIN CLASS ---
public class ManajemenRestoran {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Menu menuRestoran = new Menu();
        Pesanan pesananPelanggan = new Pesanan();
        
        
        menuRestoran.muatMenuDariFile("menu.txt");

        boolean berjalan = true;
        while (berjalan) {
            try {
                System.out.println("\n=== APLIKASI MANAJEMEN RESTORAN ===");
                System.out.println("1. Tambah Item Menu Baru");
                System.out.println("2. Tampilkan Menu");
                System.out.println("3. Buat Pesanan");
                System.out.println("4. Cetak & Simpan Struk");
                System.out.println("5. Keluar & Simpan Data Menu");
                System.out.print("Pilih opsi: ");
                
                int pilihan = scanner.nextInt();
                scanner.nextLine(); 

                switch (pilihan) {
                    case 1:
                        System.out.println("Jenis Item (1. Makanan, 2. Minuman, 3. Diskon): ");
                        int jenis = scanner.nextInt();
                        scanner.nextLine();
                        
                        System.out.print("Nama: ");
                        String nama = scanner.nextLine();
                        
                        if (jenis == 3) {
                            System.out.print("Besar Potongan (Rp): ");
                            double disc = scanner.nextDouble();
                            menuRestoran.tambahItem(new Diskon(nama, disc));
                        } else {
                            System.out.print("Harga: ");
                            double harga = scanner.nextDouble();
                            scanner.nextLine();
                            System.out.print("Kategori: ");
                            String kat = scanner.nextLine();
                            
                            if (jenis == 1) {
                                System.out.print("Jenis Makanan (e.g. Pedas): ");
                                String jm = scanner.nextLine();
                                menuRestoran.tambahItem(new Makanan(nama, harga, kat, jm));
                            } else if (jenis == 2) {
                                System.out.print("Jenis Minuman (e.g. Dingin): ");
                                String jmin = scanner.nextLine();
                                menuRestoran.tambahItem(new Minuman(nama, harga, kat, jmin));
                            }
                        }
                        System.out.println("Item berhasil ditambahkan!");
                        break;

                    case 2:
                        menuRestoran.tampilkanSemuaMenu();
                        break;

                    case 3:
                        menuRestoran.tampilkanSemuaMenu();
                        System.out.print("Masukkan nama menu yang dipesan (Ketik 'Selesai' untuk berhenti): ");
                        while (true) {
                            String namaPesanan = scanner.nextLine();
                            if (namaPesanan.equalsIgnoreCase("Selesai")) break;
                            
                            MenuItem item = menuRestoran.cariMenu(namaPesanan);
                           
                            if (item != null) {
                                System.out.print("Masukkan Jumlah: ");
                                int qty = 0;
                                try {
                                    qty = scanner.nextInt();
                                    scanner.nextLine(); 
                                } catch (Exception e) {
                                    System.out.println("Input jumlah salah, dianggap 1.");
                                    qty = 1;
                                    scanner.nextLine();
                                }

                                if (qty <= 0) qty = 1; 

                                for (int i = 0; i < qty; i++) {
                                    pesananPelanggan.tambahPesanan(item);
                                }
                                System.out.println(qty + " x " + item.getNama() + " berhasil ditambahkan.");
                            } else {
                                System.out.println("Menu tidak ditemukan! Silakan input lagi atau ketik 'Selesai'.");
                            }
                        }
                        break;

                    case 4:
                        pesananPelanggan.cetakStruk();
                        pesananPelanggan.simpanStrukKeFile("struk_pelanggan.txt");
                        break;

                    case 5:
                        menuRestoran.simpanMenuKeFile("menu.txt");
                        System.out.println("Terima kasih telah menggunakan aplikasi.");
                        berjalan = false;
                        break;

                    default:
                        System.out.println("Pilihan tidak valid.");
                }
            } catch (Exception e) {
                
                System.out.println("Terjadi kesalahan input: " + e.getMessage());
                scanner.nextLine(); 
            }
        }
        scanner.close();
    }
}