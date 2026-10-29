# Student App — Android Coding Exam Cheat Sheet 📱🎓

Selamat datang di repositori **Student App — Android Coding Exam Cheat Sheet**! Proyek ini dirancang khusus sebagai referensi belajar, template kerja, dan lembar contekan (*cheat sheet*) pemrograman Android berbasis **Jetpack Compose** untuk persiapan ujian praktik mata kuliah Pengembangan Aplikasi Mobile (PAM / UCP).

Proyek ini menggabungkan materi esensial dari 4 praktikum/tugas:
1. **Navigasi & MVVM** (`QuestNavigasiMVVM_0261`): Penggunaan `NavHost`, `composable`, rute berparameter, dan `ViewModel`.
2. **User Input** (`QuestTugasUserInput_0261`): Penanganan `OutlinedTextField`, state `remember` & `mutableStateOf`, validasi form, dan `Snackbar`.
3. **Layout** (`QuestTugasLayout_0261`): Desain kartu kustom (`CustomCard`), penataan layout vertikal/horizontal, serta pemanfaatan warna dan bentuk (`RoundedCornerShape`).
4. **Basic Composable** (`ActBasicComposable_0261`): Pondasi `@Composable`, `Text`, `Button`, `Column`, `Row`, `Spacer`, dan `Modifier`.

---

## 📂 Peta File dan Arsitektur Proyek

Struktur folder dibuat modular, bersih, dan memisahkan tanggung jawab sesuai pola arsitektur **MVVM (Model-View-ViewModel)**:

```
com.example.belajarrrucp1/
│
├── MainActivity.kt                      # Titik masuk utama aplikasi (Activity) & pemanggil NavHost
│
├── model/
│   └── Mahasiswa.kt                     # Data Class (Model) penyimpan atribut mahasiswa
│
├── viewmodel/
│   └── MahasiswaViewModel.kt            # Pengelola state data (tambah, hapus, cari) di memori
│
├── navigation/
│   └── Navigasi.kt                      # Pengelola rute (NavHost, enum HalamanNavigasi, argument)
│
├── ui/
│   ├── components/
│   │   └── CustomMahasiswaCard.kt       # Komponen Card item kustom (Reusable)
│   └── theme/
│       ├── Color.kt                     # Definisi palet warna
│       ├── Theme.kt                     # Konfigurasi tema Material 3
│       └── Type.kt                      # Tipografi teks
│
└── view/
    ├── HomeScreen.kt                    # Halaman 1: Beranda & ringkasan data
    ├── FormMahasiswaScreen.kt           # Halaman 2: Formulir input data & validasi
    ├── DaftarMahasiswaScreen.kt         # Halaman 3: List data menggunakan LazyColumn & Card
    └── DetailMahasiswaScreen.kt         # Halaman 4: Rincian lengkap data mahasiswa terpilih
```

---

## 🚀 Panduan Menjalankan Proyek di Android Studio

1. **Buka Android Studio**:
   - Pilih **File > Open**, lalu arahkan ke folder proyek ini (`BelajarrrUCP1`).
2. **Sync Gradle**:
   - Tunggu proses Gradle Sync selesai secara otomatis. Jika tidak otomatis, klik tombol **Sync Project with Gradle Files** (ikon gajah berputar di pojok kanan atas).
3. **Pastikan Emulator / Device Terhubung**:
   - Pilih AVD (Android Virtual Device, misal: *Pixel 9 API 37*) atau sambungkan ponsel fisik dengan USB Debugging aktif.
4. **Jalankan Aplikasi**:
   - Klik tombol **Run** (segitiga hijau ▶️) atau tekan shortcut `Shift + F10`.
5. **Mode Preview (Tanpa Emulator)**:
   - Buka file screen manapun di folder `view/`, lalu klik tombol **Split** atau **Design** di pojok kanan atas editor kode untuk melihat tampilan visual secara instan.

---

## 📋 Tabel Cheat Sheet Komponen Compose

| Kebutuhan Ujian | Komponen / Kode | Fungsi Utama | Letak di Proyek |
| :--- | :--- | :--- | :--- |
| **Membuat Teks** | `Text(text = "...", fontSize = 16.sp, fontWeight = FontWeight.Bold)` | Menampilkan tulisan di layar | Semua file Screen |
| **Membuat Tombol** | `Button(onClick = { ... }) { Text("Simpan") }` | Menerima aksi klik pengguna | `HomeScreen.kt`, `FormMahasiswaScreen.kt` |
| **Tombol Outline** | `OutlinedButton(onClick = { ... }) { Text("Batal") }` | Tombol sekunder berbingkai tipis | `FormMahasiswaScreen.kt`, `DaftarMahasiswaScreen.kt` |
| **Input Teks** | `OutlinedTextField(value = text, onValueChange = { text = it }, label = { Text(...) })` | Menerima ketikan teks dari pengguna | `FormMahasiswaScreen.kt` |
| **Layout Vertikal** | `Column(modifier = Modifier.fillMaxWidth()) { ... }` | Menyusun komponen dari atas ke bawah | Semua file Screen |
| **Layout Horizontal** | `Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) { ... }` | Menyusun komponen dari kiri ke kanan | `CustomMahasiswaCard.kt`, `HomeScreen.kt` |
| **Memberi Jarak** | `Spacer(modifier = Modifier.height(16.dp))` atau `Modifier.width(8.dp)` | Memberikan ruang kosong antar komponen | Semua file |
| **Mengatur Margin/Padding**| `Modifier.padding(16.dp)` / `Modifier.fillMaxSize()` | Mengatur jarak dalam dan ukuran composable | Semua file |
| **Membuat Card / Kartu**| `Card(shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(...))` | Membuat wadah berbentuk kartu melengkung | `CustomMahasiswaCard.kt`, `HomeScreen.kt` |
| **Daftar Scrollable**| `LazyColumn { items(list) { item -> ... } }` | Menampilkan banyak data yang bisa di-scroll secara efisien | `DaftarMahasiswaScreen.kt` |
| **State Input Lokal** | `var input by remember { mutableStateOf("") }` | Menyimpan nilai form sementara di Composable | `FormMahasiswaScreen.kt` |
| **Validasi Form** | `if (input.isBlank()) { error = "Wajib diisi" }` | Memeriksa kelayakan input sebelum disimpan | `FormMahasiswaScreen.kt` |
| **Menyimpan ke ViewModel**| `viewModel.tambahMahasiswa(objekBaru)` | Menyimpan data ke memori ViewModel | `FormMahasiswaScreen.kt` |
| **Menghapus dari ViewModel**| `viewModel.hapusMahasiswa(id)` | Menghapus item dari daftar di ViewModel | `DaftarMahasiswaScreen.kt` |
| **Berpindah Halaman** | `navController.navigate("rute_tujuan")` | Membuka halaman berikutnya | `Navigasi.kt`, `HomeScreen.kt` |
| **Kembali ke Halaman Lalu**| `navController.popBackStack()` | Menutup layar saat ini dan kembali | `Navigasi.kt`, `DetailMahasiswaScreen.kt` |
| **Kirim Argumen Navigasi**| `navController.navigate("detail/$id")` & `navArgument("id")` | Mengirim data spesifik antarhalaman | `Navigasi.kt`, `DaftarMahasiswaScreen.kt` |
| **Mengamati StateFlow** | `val list by viewModel.listMahasiswa.collectAsState()` | Mengupdate UI otomatis saat data ViewModel berubah | `DaftarMahasiswaScreen.kt`, `HomeScreen.kt` |
| **Menampilkan Notifikasi**| `snackbarHostState.showSnackbar("Pesan...")` | Memberikan pop-up bar singkat di bawah layar | `FormMahasiswaScreen.kt` |

---

## 🛠️ Panduan Cepat Modifikasi Saat Ujian (Ubah Studi Kasus)

Jika saat ujian kamu mendapatkan studi kasus yang berbeda (misal: **Data Barang / Produk / Buku / Rental Mobil**), ikuti 4 langkah sederhana ini:

### 1. Ubah Data Class (`model/`)
Ganti nama class dan atributnya sesuai soal:
```kotlin
// Contoh: Dari Mahasiswa menjadi Produk
data class Produk(
    val idProduk: String = "",
    val namaProduk: String = "",
    val kategori: String = "",
    val harga: String = ""
)
```

### 2. Sesuaikan Fungsi ViewModel (`viewmodel/`)
Ganti tipe data yang dikelola:
```kotlin
class ProdukViewModel : ViewModel() {
    private val _listProduk = MutableStateFlow<List<Produk>>(emptyList())
    val listProduk: StateFlow<List<Produk>> = _listProduk.asStateFlow()

    fun tambahProduk(produk: Produk) {
        _listProduk.update { it + produk }
    }

    fun hapusProduk(idProduk: String) {
        _listProduk.update { it.filterNot { item -> item.idProduk == idProduk } }
    }

    fun getProdukById(idProduk: String): Produk? {
        return _listProduk.value.firstOrNull { it.idProduk == idProduk }
    }
}
```

### 3. Sesuaikan Form Input (`view/`)
Ubah label `OutlinedTextField` sesuai atribut:
```kotlin
// Ganti NIM -> ID Produk
OutlinedTextField(
    value = idInput,
    onValueChange = { idInput = it },
    label = { Text("ID Produk") }
)
```

### 4. Sesuaikan Tampilan Card & Detail
Tampilkan atribut data baru:
```kotlin
Text(text = produk.namaProduk, fontWeight = FontWeight.Bold)
Text(text = "Harga: Rp${produk.harga}")
```

> **📌 Catatan:** Seluruh alur **Navigasi**, **LazyColumn**, **Scaffold**, dan **StateFlow** tetap persis sama tanpa perlu diubah arsitekturnya!

---

## 💡 Cuplikan Kode Kunci yang Sering Dipakai

### 1. State Input Form & Validasi Sederhana
```kotlin
var namaInput by remember { mutableStateOf("") }
var errorPesan by remember { mutableStateOf<String?>(null) }

OutlinedTextField(
    value = namaInput,
    onValueChange = { 
        namaInput = it 
        errorPesan = null // Reset pesan error saat user mengetik kembali
    },
    label = { Text("Nama Lengkap") }
)

Button(onClick = {
    if (namaInput.isBlank()) {
        errorPesan = "Nama tidak boleh kosong!"
    } else {
        // Panggil fungsi simpan ke ViewModel
    }
}) {
    Text("Simpan")
}
```

### 2. Navigasi Berargumen Dinamis (Kirim ID / NIM)
```kotlin
// 1. Mendaftarkan rute berargumen di NavHost:
composable(
    route = "detail/{id}",
    arguments = listOf(navArgument("id") { type = NavType.StringType })
) { backStackEntry ->
    val id = backStackEntry.arguments?.getString("id") ?: ""
    DetailScreen(id = id, onBack = { navController.popBackStack() })
}

// 2. Cara memanggil rute dari halaman list/card:
navController.navigate("detail/${item.id}")
```

### 3. LazyColumn (List Scrollable dengan Card)
```kotlin
LazyColumn(modifier = Modifier.fillMaxSize()) {
    itemsIndexed(items = listData, key = { _, item -> item.id }) { index, data ->
        Card(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = data.nama, fontWeight = FontWeight.Bold)
                Button(onClick = { navController.navigate("detail/${data.id}") }) {
                    Text("Lihat Detail")
                }
            }
        }
    }
}
```

---

## 🚨 Troubleshooting Error Umum Saat Ujian

1. **Error: `Unresolved reference 'icons'`**
   - **Penyebab:** Dependency Material Icons Extended belum terpasang.
   - **Solusi:** Jangan pakai `Icons.Default.Add`. Cukup pakai teks simbol bawaan Compose seperti `Text("+", fontSize = 28.sp, color = Color.White)` di dalam FloatingActionButton.
2. **Error: `Aar Dependency compatibility issues / compileSdk`**
   - **Penyebab:** Dependency terbaru butuh SDK compile yang lebih tinggi.
   - **Solusi:** Di `app/build.gradle.kts`, ubah `compileSdk = 37`, lalu klik **Sync Now**.
3. **Error: Composable Preview Merah / Tidak Muncul**
   - **Solusi:** Klik tulisan biru **Build & Refresh... (Ctrl+Shift+F5)** di panel preview setelah melakukan sync gradle yang sukses.
