public class Minuman extends MenuItem {
    private String jenisMinuman;

    public Minuman(String nama, double harga, String jenisMinuman) {
        super(nama, harga, "Minuman");
        this.jenisMinuman = jenisMinuman;
    }

    public String getJenisMinuman() { return jenisMinuman; }
    public void setJenisMinuman(String jenisMinuman) { this.jenisMinuman = jenisMinuman; }


    @Override
    public void tampilMenu() {
        System.out.printf("%-22s | Rp%-10.0f | Kategori: %-8s | Jenis: %s\n",
                getNama(), getHarga(), getKategori(), jenisMinuman);
    }

    @Override
    public String toFileString() {
        return "Minuman;" + getNama() + ";" + getHarga() + ";" + jenisMinuman;
    }
}