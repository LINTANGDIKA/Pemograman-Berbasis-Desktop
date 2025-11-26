import java.util.Scanner;

class MenuD {
    String namaMakanan;
    double hargaMakanan;
    String kategoriMakanan; 

    public MenuD( String namaMakanan, String kategoriMakanan, double hargaMakanan){
        this.namaMakanan = namaMakanan;
        this.hargaMakanan = hargaMakanan;
        this.kategoriMakanan = kategoriMakanan;
    }

    public String getNama(){
        return namaMakanan;
    }
    public double getHarga(){
        return hargaMakanan;
    }
    public String getKategori(){
        return kategoriMakanan;
    }
}

public class Restaurant {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int noMkn1;
        int noMkn2 = 0;
        int noMkn3 = 0;
        int noMkn4 = 0;
        int jmlMkn1;
        int jmlMkn2 = 0;
        int jmlMkn3 = 0;
        int jmlMkn4 = 0;
        String pilihan = "";
        double total = 0;
        String free1Minuman = "";

        MenuD[] daftarMenu = {
            new MenuD("Mie Kuah","Makanan",  15000),
            new MenuD("Mie Goreng","Makanan", 15000),
            new MenuD("Nasi Goreng","Makanan", 14000),
            new MenuD("Kwetiaw","Makanan", 16000),
            new MenuD("Es Teh","Minuman",4000),
            new MenuD("Es Jeruk","Minuman", 5000)
        };

        System.out.println("===== Menu Makanan =====");
        System.out.println("1. " + daftarMenu[0].getNama() + "             " + "Rp " +daftarMenu[0].getHarga() + "    " + daftarMenu[0].getKategori());
        System.out.println("2. " + daftarMenu[1].getNama() + "             " + "Rp " +daftarMenu[1].getHarga() + "    " + daftarMenu[1].getKategori());
        System.out.println("3. " + daftarMenu[2].getNama() + "             " + "Rp " +daftarMenu[2].getHarga() + "    " + daftarMenu[2].getKategori());
        System.out.println("4. " + daftarMenu[3].getNama() + "             " + "Rp " +daftarMenu[3].getHarga() + "    " + daftarMenu[3].getKategori());
        System.out.println("===== Menu Minuman =====");
        System.out.println("5. " + daftarMenu[4].getNama() + "             " + "Rp " +daftarMenu[4].getHarga() + "    " + daftarMenu[4].getKategori());
        System.out.println("6. " + daftarMenu[5].getNama() + "             " + "Rp " +daftarMenu[5].getHarga() + "    " + daftarMenu[5].getKategori());

        System.out.println("\n");

        System.out.println("Masukan no menu makanan atau minuman untuk memesan makanan atau minuman");
        System.out.println("\n");
        
        System.out.println("No Makanan atau Minuman : ");
        noMkn1 = input.nextInt();
        System.out.println("Jumlah "+ daftarMenu[noMkn1 - 1].getNama() + " : ");
        jmlMkn1 = input.nextInt();
        System.out.println("Apakah ingin memesan lagi (Y/N) ?");
        pilihan = input.next();
        if(pilihan.equalsIgnoreCase("Y")){
            System.out.println("No Makanan atau Minuman : ");
            noMkn2 = input.nextInt();
            System.out.println("Jumlah "+ daftarMenu[noMkn2 - 1].getNama() + " : ");
            jmlMkn2 = input.nextInt();
            System.out.println("Apakah ingin memesan lagi (Y/N) ?");
            pilihan = input.next();
            if(pilihan.equalsIgnoreCase("Y")){
                System.out.println("No Makanan atau Minuman : ");
                noMkn3 = input.nextInt();
                System.out.println("Jumlah "+ daftarMenu[noMkn3 - 1].getNama() + " : ");
                jmlMkn3 = input.nextInt();
                System.out.println("Apakah ingin memesan lagi (Y/N) ?");
                pilihan = input.next();
                if(pilihan.equalsIgnoreCase("Y")){
                    System.out.println("No Makanan atau Minuman : ");
                    noMkn4 = input.nextInt();
                    System.out.println("Jumlah "+ daftarMenu[noMkn4 - 1].getNama() + " : ");
                    jmlMkn4 = input.nextInt();
                    System.out.println("Anda Sudah mencapai batas pemesanan sebesar 4 kali");
                    System.out.println("\n");
                }
            }
        }
    
        total +=  (daftarMenu[noMkn1-1].getHarga() * jmlMkn1);

        if(noMkn2 != 0) total +=  (daftarMenu[noMkn2-1].getHarga() * jmlMkn2);
        if(noMkn3 != 0) total +=  (daftarMenu[noMkn2-1].getHarga() * jmlMkn3);
        if(noMkn4 != 0) total +=  (daftarMenu[noMkn2-1].getHarga() * jmlMkn4);
        
        if(total > 50000) free1Minuman = "Anda Dapat bonus 1 minuman karena dapat promo beli 1 gratis 1";

        

        System.out.println(" ==== Struk Pesanan ====");
        System.out.println(daftarMenu[noMkn1 -1].getNama() + "         " + "Rp " +daftarMenu[noMkn1-1].getHarga() * jmlMkn1);
        System.out.println(noMkn2 == 0 ? "" : daftarMenu[noMkn2 -1].getNama() + "         " + "Rp " +daftarMenu[noMkn2-1].getHarga() * jmlMkn2);
        System.out.println(noMkn3 == 0 ? "" : daftarMenu[noMkn3 -1].getNama() + "         " + "Rp " +daftarMenu[noMkn3-1].getHarga() * jmlMkn3);
        System.out.println(noMkn4 == 0 ? "" : daftarMenu[noMkn4 -1].getNama() + "         " + "Rp " +daftarMenu[noMkn4-1].getHarga() * jmlMkn4);
        System.out.println("Total" + "         " + total);
        if(total > 100000) total -= (total * 10/100);
        total += 20000;
        total += (total * 10/100);
        System.out.println("Biaya Layanan" + "         " + "Rp 20.000,0");
        System.out.println("Pajak" + "         " + "10%");
        System.out.println("Total" + "         " + total);
        System.out.println("\n");
        System.out.println(free1Minuman);
    }
}
