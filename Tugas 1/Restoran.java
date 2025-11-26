import java.util.Scanner;
class MenuMakanan {
    String nama;
    String kategori;
    double harga;

    public MenuMakanan(String nama, String kategori, double harga){
        this.nama = nama;
        this.kategori = kategori;
        this.harga = harga;
    }
    public String getNama(){
        return nama;
    }
    public String getKategori(){
        return kategori;
    }
    public double getHarga(){
        return harga;
    }
}
 public class Restoran {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int noMkn1;
        int noMkn2 = 0;
        int noMkn3 = 0;
        int noMkn4 = 0;
        int jmlMkn1 = 0;
        int jmlMkn2 = 0;
        int jmlMkn3 = 0;
        int jmlMkn4 = 0;
        int jumlahPesananMenu = 0;
        int maxPesanan = 4;
        double total = 0;

        MenuMakanan[] daftarMenu = {
            new MenuMakanan("Mie Kuah", "Makanan", 15000),
            new MenuMakanan("Mie Goreng", "Makanan",15000),
            new MenuMakanan("Nasi Goreng","Makanan", 14000),
            new MenuMakanan("Kwetiaw","Makanan", 16000),
            new MenuMakanan("Es Teh","Minuman",4000),
            new MenuMakanan("Es Jeruk", "Minuman",5000)
        };

        System.out.println("===== MENU MAKANAN =====");
        System.out.println("1. " + daftarMenu[0].getNama() + "   Rp " + daftarMenu[0].getHarga());
        System.out.println("2. " + daftarMenu[1].getNama() + "   Rp " + daftarMenu[1].getHarga());
        System.out.println("3. " + daftarMenu[2].getNama() + "   Rp " + daftarMenu[2].getHarga());
        System.out.println("4. " + daftarMenu[3].getNama() + "   Rp " + daftarMenu[3].getHarga());
        System.out.println("===== MENU MINUMAN =====");
        System.out.println("5. " + daftarMenu[4].getNama() + "   Rp " + daftarMenu[4].getHarga());
        System.out.println("6. " + daftarMenu[5].getNama() + "   Rp " + daftarMenu[5].getHarga());

        System.out.println("Masukkan no makanan yang ada di Menu Makanan dan Minuman untuk memesan");
        System.out.println("No Makanan/Minuman : ");
        noMkn1 = input.nextInt();
        System.out.println("Jumlah " + daftarMenu[noMkn1-1].getNama() + " : ");
        jmlMkn1 = input.nextInt();

        if (noMkn1 < 7 && noMkn1 > 0) {
            System.out.println("Apakah ingin memesan lagi (Y/N) = ");
            String pilihan = input.next();

            if (pilihan.equalsIgnoreCase("Y")) {
                System.out.println("No Makanan/Minuman : ");
                noMkn2 = input.nextInt();
                System.out.println("Jumlah " + daftarMenu[noMkn2-1].getNama() + " : ");
                jmlMkn2 = input.nextInt();

                System.out.println("Apakah ingin memesan lagi (Y/N) = ");
                pilihan = input.next();
                if (pilihan.equalsIgnoreCase("Y")) {
                    System.out.println("No Makanan/Minuman : ");
                    noMkn3 = input.nextInt();
                    System.out.println("Jumlah " + daftarMenu[noMkn3-1].getNama() + " : ");
                    jmlMkn3 = input.nextInt();

                    System.out.println("Apakah ingin memesan lagi (Y/N) = ");
                    pilihan = input.next();
                    if (pilihan.equalsIgnoreCase("Y")) {
                        System.out.println("No Makanan/Minuman : ");
                        noMkn4 = input.nextInt();
                        System.out.println("Jumlah " + daftarMenu[noMkn4-1].getNama() + " : ");
                        jmlMkn4 = input.nextInt();
                        System.out.println("Anda sudah mencapai batas pemesanan 4 menu ");
                    }
                }
            }
        }
        else {
            System.out.println("Menu Tidak Ada !");
            System.exit(0);
        }
        // System.out.println(noMkn3);

        total = (daftarMenu[noMkn1 - 1].harga *jmlMkn1);
                
        if(noMkn2 != 0) {total += (daftarMenu[noMkn2 - 1].harga*jmlMkn2); };
                
        if(noMkn3 != 0) {total += (daftarMenu[noMkn3 - 1].harga *jmlMkn3); };
                
        if(noMkn4 != 0) {total += (daftarMenu[noMkn4 - 1].harga *jmlMkn4); }; 
                
        if(total > 100000){total -= (total * 10/100);}

        System.out.println(" ===== Total yang dipesan ===== ");

        System.out.println(daftarMenu[noMkn1 - 1].nama + " " + jmlMkn1 + "       "+ "Rp "+ (daftarMenu[noMkn1 - 1].harga *jmlMkn1));
        System.out.println(noMkn2 == 0 ? "" : daftarMenu[noMkn2 - 1].nama + " " + jmlMkn2 + "       "+ "Rp "+ (daftarMenu[noMkn2 - 1].harga *jmlMkn2));
        System.out.println(noMkn3 == 0 ? "" : daftarMenu[noMkn3 - 1].nama + " " + jmlMkn3 + "       "+ "Rp "+ (daftarMenu[noMkn3 - 1].harga *jmlMkn3));
        System.out.println(noMkn4 == 0 ? "" : daftarMenu[noMkn4 - 1].nama + " " + jmlMkn4 + "       "+ "Rp "+ (daftarMenu[noMkn4 - 1].harga *jmlMkn4));
        System.out.println("Total Pesanan              " + "Rp "+ total);
        if(total > 50000) System.out.println("Anda dapat bonus minuman beli 1 gratis 1 ");
        System.out.println("Pajak              " + "10%");
        System.out.println("Total               " + "Rp "+ (total + (total * 10 / 100)));

    }
}
