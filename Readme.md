# Sistem Manajemen Restoran (Java OOP & File I/O)

Aplikasi konsol berbasis Java untuk manajemen restoran yang mengimplementasikan seluruh pilar *Object-Oriented Programming* (OOP), *Exception Handling*, dan operasi baca-tulis berkas teks (*File I/O*).

## Konsep & Fitur Utama
- **Abstraction & Encapsulation:** Menggunakan *abstract class* `MenuItem` dengan atribut `private` serta metode *Getter* dan *Setter*
- **Inheritance & Polymorphism:** Subkelas `Makanan`, `Minuman`, dan `Diskon` mewarisi `MenuItem` dan melakukan *override* pada metode `tampilMenu()`
- **Operasi File (I/O):**
    - Menyimpan dan memuat daftar menu secara otomatis melalui berkas `daftar_menu.txt`
    - Mencatat serta menampilkan riwayat struk transaksi melalui berkas `struk_pesanan.txt`
- **Exception Handling:** Penanganan kesalahan input (`NumberFormatException`, `IllegalArgumentException`) dan akses indeks di luar daftar (`IndexOutOfBoundsException`)

## Struktur Berkas
- `MenuItem.java` — *Abstract class* kerangka dasar item menu
- `Makanan.java` — Subkelas untuk item makanan
- `Minuman.java` — Subkelas untuk item minuman
- `Diskon.java` — Subkelas untuk voucher promo diskon
- `Menu.java` — Pengelola `ArrayList<MenuItem>` dan operasi I/O daftar menu
- `Pesanan.java` — Pengelola keranjang pesanan, kalkulasi struk, dan operasi I/O riwayat struk
- `Main.java` — Kelas utama pengendali navigasi dan interaksi pengguna