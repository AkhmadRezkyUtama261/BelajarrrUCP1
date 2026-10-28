package com.example.belajarrrucp1.model

/**
 * 1. KODE INI DIGUNAKAN UNTUK APA:
 * Data class ini berfungsi sebagai "Model" (huruf 'M' dalam pola arsitektur MVVM).
 * Model ini merepresentasikan struktur data entitas Mahasiswa di dalam aplikasi.
 *
 * 2. MENGAPA KODE INI DIPERLUKAN:
 * Dalam pemrograman berorientasi objek dan Compose, data class memudahkan kita
 * membungkus beberapa data terkait (NIM, Nama, Prodi, Semester) menjadi satu kesatuan objek.
 *
 * 3. BAGAIMANA KODE INI BEKERJA:
 * Kotlin secara otomatis menyediakan fungsi bawaan seperti copy(), equals(),
 * hashCode(), dan toString() untuk data class ini.
 *
 * 4. BAGIAN YANG HARUS DIUBAH JIKA STUDI KASUS UJIAN BERBEDA:
 * Jika soal ujian meminta entitas lain (misalnya "Barang" atau "Buku"), ubah nama kelas
 * dan atributnya. Contoh:
 * data class Produk(val idProduk: String, val namaProduk: String, val harga: String, val stok: String)
 */
data class Mahasiswa(
    // Properti NIM sebagai identitas unik mahasiswa
    val nim: String = "",

    // Properti nama lengkap mahasiswa
    val nama: String = "",

    // Properti program studi / jurusan mahasiswa
    val prodi: String = "",

    // Properti semester mahasiswa yang sedang ditempuh
    val semester: String = ""
)
