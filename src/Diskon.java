public class Diskon extends MenuItem {
    private double diskon;

    public Diskon(String nama, double diskon) {
        super(nama, 0, "Diskon");
        this.diskon = diskon;
    }

    public double getDiskon() { return diskon; }
    public void setDiskon(double diskon) { this.diskon = diskon; }

    @Override
    public void tampilMenu() {
        System.out.printf("%-22s | Potongan %-5.0f%% | Kategori: %-8s | Promo Spesial\n",
                getNama(), diskon, getKategori());
    }

    @Override
    public String toFileString() {
        return "Diskon;" + getNama() + ";" + diskon + ";Promo";
    }
}