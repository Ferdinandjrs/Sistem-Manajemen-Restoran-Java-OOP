import java.io.*;
import java.util.ArrayList;

public class Pesanan {
    private ArrayList<MenuItem> itemDipesan;
    private ArrayList<Integer> jumlahPesanan;
    private final String FILE_STRUK = "struk_pesanan.txt";

    public Pesanan() {
        itemDipesan = new ArrayList<>();
        jumlahPesanan = new ArrayList<>();
    }

    public void tambahPesanan(MenuItem item, int jumlah) {
        itemDipesan.add(item);
        jumlahPesanan.add(jumlah);
    }

    public boolean isKosong() {
        return itemDipesan.isEmpty();
    }

    public void prosesDanCetakStruk() {
        double subtotal = 0;
        double persenDiskon = 0;
        String namaDiskon = "";

        StringBuilder struk = new StringBuilder();
        struk.append("\n==============================================\n");
        struk.append("            STRUK PEMBAYARAN RESTORAN         \n");
        struk.append("==============================================\n");


        for (int i = 0; i < itemDipesan.size(); i++) {
            MenuItem item = itemDipesan.get(i);
            int qty = jumlahPesanan.get(i);

            if (item instanceof Diskon) {
                Diskon d = (Diskon) item;
                persenDiskon += d.getDiskon();
                namaDiskon = d.getNama();
                struk.append(String.format("[VOUCHER] %s (Diskon %.0f%%)\n", d.getNama(), d.getDiskon()));
            } else {
                double totalItem = item.getHarga() * qty;
                subtotal += totalItem;
                struk.append(String.format("%-22s x%-2d : Rp%.0f\n", item.getNama(), qty, totalItem));
            }
        }

        if (persenDiskon > 100) persenDiskon = 100;

        double nominalDiskon = subtotal * (persenDiskon / 100.0);
        double totalSetelahDiskon = subtotal - nominalDiskon;
        double pajak = totalSetelahDiskon * 0.10;
        double biayaPelayanan = 20000;
        double totalAkhir = totalSetelahDiskon + pajak + biayaPelayanan;

        struk.append("----------------------------------------------\n");
        struk.append(String.format("Subtotal                    : Rp%.0f\n", subtotal));
        if (nominalDiskon > 0) {
            struk.append(String.format("Diskon (%s %.0f%%) : -Rp%.0f\n", namaDiskon, persenDiskon, nominalDiskon));
        }
        struk.append(String.format("Pajak (10%%)                 : Rp%.0f\n", pajak));
        struk.append(String.format("Biaya Pelayanan             : Rp%.0f\n", biayaPelayanan));
        struk.append("----------------------------------------------\n");
        struk.append(String.format("TOTAL BAYAR                 : Rp%.0f\n", totalAkhir));
        struk.append("==============================================\n");


        String hasilStruk = struk.toString();
        System.out.println(hasilStruk);

        simpanStrukKeFile(hasilStruk);
    }

    private void simpanStrukKeFile(String teksStruk) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_STRUK, true))) {
            writer.write(teksStruk);
            writer.newLine();
            System.out.println("-> Struk berhasil disimpan ke file '" + FILE_STRUK + "'");
        } catch (IOException e) {
            System.out.println("Gagal menyimpan struk: " + e.getMessage());
        }
    }

    public void tampilkanRiwayatStrukDariFile() {
        File file = new File(FILE_STRUK);
        if (!file.exists()) {
            System.out.println("\nBelum ada riwayat struk pesanan yang tersimpan.");
            return;
        }

        System.out.println("\n>>> MEMUAT RIWAYAT STRUK DARI FILE <<<");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String baris;
            while ((baris = reader.readLine()) != null) {
                System.out.println(baris);
            }
        } catch (IOException e) {
            System.out.println("Gagal membaca file struk: " + e.getMessage());
        }
    }
}