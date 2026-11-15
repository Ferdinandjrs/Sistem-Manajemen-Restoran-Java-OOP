public class Makanan extends MenuItem {
    private String jenisMakanan;

    public Makanan(String nama, double harga, String jenisMakanan) {
        super(nama, harga, "Makanan");
        this.jenisMakanan = jenisMakanan;
    }

    public String getJenisMakanan() { return jenisMakanan; }
    public void setJenisMakanan(String jenisMakanan) { this.jenisMakanan = jenisMakanan; }


    @Override
    public void tampilMenu() {
        System.out.printf("%-22s | Rp%-10.0f | Kategori: %-8s | Jenis: %s\n",
                getNama(), getHarga(), getKategori(), jenisMakanan);
    }

    @Override
    public String toFileString() {
        return "Makanan;" + getNama() + ";" + getHarga() + ";" + jenisMakanan;
    }
}