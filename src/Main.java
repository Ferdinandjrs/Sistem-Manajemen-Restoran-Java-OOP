import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);
    static Menu manajemenMenu = new Menu();

    public static void main(String[] args) {
        boolean berjalan = true;
        do {
            System.out.println("\n==========================================");
            System.out.println("   SISTEM MANAJEMEN RESTORAN (OOP & I/O)  ");
            System.out.println("==========================================");
            System.out.println("1. Tambah Item Baru ke Menu");
            System.out.println("2. Tampilkan Menu Restoran");
            System.out.println("3. Terima Pesanan Pelanggan");
            System.out.println("4. Lihat Riwayat Struk dari File");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu (1-5): ");

            String pilihan = input.nextLine();

            switch (pilihan) {
                case "1":
                    prosesTambahMenu();
                    break;
                case "2":
                    manajemenMenu.tampilkanSemuaMenu();
                    break;
                case "3":
                    prosesPesananPelanggan();
                    break;
                case "4":
                    Pesanan riwayat = new Pesanan();
                    riwayat.tampilkanRiwayatStrukDariFile();
                    break;
                case "5":
                    berjalan = false;
                    System.out.println("Program selesai. Data menu tersimpan aman di file.");
                    break;
                default:
                    System.out.println("ERROR: Pilihan tidak valid! Masukkan angka 1-5.");
            }
        } while (berjalan);
    }

    static void prosesTambahMenu() {
        System.out.println("\n--- TAMBAH ITEM MENU BARU ---");
        System.out.println("Pilih Jenis Item:");
        System.out.println("1. Makanan");
        System.out.println("2. Minuman");
        System.out.println("3. Diskon / Promo");
        System.out.print("Pilihan (1-3): ");
        String jenis = input.nextLine();

        try {
            if (jenis.equals("1")) {
                System.out.print("Nama Makanan  : ");
                String nama = input.nextLine();
                System.out.print("Harga (Rp)    : ");
                double harga = Double.parseDouble(input.nextLine());
                if (harga <= 0) throw new IllegalArgumentException("Harga harus lebih dari 0!");
                System.out.print("Jenis Makanan (Berat/Ringan/dll): ");
                String jenisMakanan = input.nextLine();

                manajemenMenu.tambahItem(new Makanan(nama, harga, jenisMakanan));
                System.out.println("-> Makanan berhasil ditambahkan dan disimpan ke file!");

            } else if (jenis.equals("2")) {
                System.out.print("Nama Minuman  : ");
                String nama = input.nextLine();
                System.out.print("Harga (Rp)    : ");
                double harga = Double.parseDouble(input.nextLine());
                if (harga <= 0) throw new IllegalArgumentException("Harga harus lebih dari 0!");
                System.out.print("Jenis Minuman (Dingin/Panas/dll): ");
                String jenisMinuman = input.nextLine();

                manajemenMenu.tambahItem(new Minuman(nama, harga, jenisMinuman));
                System.out.println("-> Minuman berhasil ditambahkan dan disimpan ke file!");

            } else if (jenis.equals("3")) {
                System.out.print("Nama Promo Diskon : ");
                String nama = input.nextLine();
                System.out.print("Persentase Diskon (%): ");
                double diskon = Double.parseDouble(input.nextLine());
                if (diskon <= 0 || diskon > 100) {
                    throw new IllegalArgumentException("Diskon harus di antara 1 - 100%!");
                }

                manajemenMenu.tambahItem(new Diskon(nama, diskon));
                System.out.println("-> Voucher Diskon berhasil ditambahkan dan disimpan ke file!");

            } else {
                System.out.println("ERROR: Jenis item tidak valid.");
            }
        } catch (NumberFormatException e) {
            System.out.println("ERROR: Input angka tidak valid! Gagal menambah menu.");
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    static void prosesPesananPelanggan() {
        Pesanan pesananBaru = new Pesanan();

        while (true) {
            manajemenMenu.tampilkanSemuaMenu();
            System.out.println("Ketik 'selesai' untuk mengakhiri pesanan dan mencetak struk.");
            System.out.print("Pilih nomor menu: ");
            String inputMenu = input.nextLine();

            if (inputMenu.equalsIgnoreCase("selesai")) {
                if (pesananBaru.isKosong()) {
                    System.out.println("Pesanan dibatalkan (tidak ada item yang dipilih).");
                    return;
                }
                break;
            }

            try {
                int nomorMenu = Integer.parseInt(inputMenu);
                MenuItem itemDipilih = manajemenMenu.getItem(nomorMenu - 1);

                int jumlah = 1;
                if (!(itemDipilih instanceof Diskon)) {
                    System.out.print("Jumlah porsi untuk " + itemDipilih.getNama() + ": ");
                    jumlah = Integer.parseInt(input.nextLine());
                    if (jumlah <= 0) {
                        System.out.println("ERROR: Jumlah pesanan harus minimal 1!");
                        continue;
                    }
                }

                pesananBaru.tambahPesanan(itemDipilih, jumlah);
                System.out.println("-> Ditambahkan: " + itemDipilih.getNama() + " (x" + jumlah + ")");

            } catch (NumberFormatException e) {
                System.out.println("ERROR: Masukkan nomor menu berupa angka atau ketik 'selesai'!");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("ERROR EXCEPTION: " + e.getMessage());
            }
        }

        pesananBaru.prosesDanCetakStruk();
    }
}