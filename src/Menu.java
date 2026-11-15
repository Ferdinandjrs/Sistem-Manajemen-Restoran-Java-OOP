import java.io.*;
import java.util.ArrayList;

public class Menu {
    private ArrayList<MenuItem> daftarMenu;
    private final String NAMA_FILE = "daftar_menu.txt";

    public Menu() {
        daftarMenu = new ArrayList<>();
        muatDariFile(); // Otomatis memuat file saat program dijalankan
    }

    public void tambahItem(MenuItem item) {
        daftarMenu.add(item);
        simpanKeFile();
    }

    public ArrayList<MenuItem> getDaftarMenu() {
        return daftarMenu;
    }

    public MenuItem getItem(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= daftarMenu.size()) {
            throw new IndexOutOfBoundsException("Nomor menu tidak ditemukan dalam daftar!");
        }
        return daftarMenu.get(index);
    }

    public void tampilkanSemuaMenu() {
        if (daftarMenu.isEmpty()) {
            System.out.println("Daftar menu masih kosong.");
            return;
        }
        System.out.println("\n=========================================================================");
        System.out.println("                           DAFTAR MENU RESTORAN                          ");
        System.out.println("=========================================================================");
        for (int i = 0; i < daftarMenu.size(); i++) {
            System.out.print((i + 1) + ". ");
            daftarMenu.get(i).tampilMenu(); // Polymorphism bekerja di sini!
        }
        System.out.println("=========================================================================");
    }


    public void simpanKeFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NAMA_FILE))) {
            for (MenuItem item : daftarMenu) {
                writer.write(item.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Gagal menyimpan menu ke file: " + e.getMessage());
        }
    }

    public void muatDariFile() {
        File file = new File(NAMA_FILE);
        if (!file.exists()) {
            // Data awal default jika file belum ada sama sekali
            daftarMenu.add(new Makanan("Nasi Goreng Spesial", 25000, "Makanan Berat"));
            daftarMenu.add(new Makanan("Ayam Geprek Sambal Matah", 22000, "Makanan Berat"));
            daftarMenu.add(new Minuman("Es Teh Manis", 5000, "Minuman Dingin"));
            daftarMenu.add(new Minuman("Kopi Susu Gula Aren", 15000, "Minuman Kopi"));
            daftarMenu.add(new Diskon("Promo Hemat Akhir Pekan", 10.0));
            simpanKeFile();
            return;
        }

        daftarMenu.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String baris;
            while ((baris = reader.readLine()) != null) {
                String[] data = baris.split(";");
                if (data.length == 4) {
                    String tipe = data[0];
                    String nama = data[1];
                    double nilai = Double.parseDouble(data[2]);
                    String ekstra = data[3];

                    if (tipe.equals("Makanan")) {
                        daftarMenu.add(new Makanan(nama, nilai, ekstra));
                    } else if (tipe.equals("Minuman")) {
                        daftarMenu.add(new Minuman(nama, nilai, ekstra));
                    } else if (tipe.equals("Diskon")) {
                        daftarMenu.add(new Diskon(nama, nilai));
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Gagal memuat file menu: " + e.getMessage());
        }
    }
}