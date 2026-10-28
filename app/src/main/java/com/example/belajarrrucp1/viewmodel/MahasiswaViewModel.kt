package com.example.belajarrrucp1.viewmodel

import androidx.lifecycle.ViewModel
import com.example.belajarrrucp1.model.Mahasiswa
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * 1. KODE INI DIGUNAKAN UNTUK APA:
 * Kelas ViewModel ini berfungsi sebagai penghubung antara UI (View) dan Data (Model).
 * ViewModel bertugas mengelola logika bisnis aplikasi serta menyimpan data sementara
 * di memori agar tidak hilang saat terjadi perubahan konfigurasi (seperti rotasi layar).
 *
 * 2. MENGAPA KODE INI DIPERLUKAN:
 * Untuk menerapkan pola MVVM (Model-View-ViewModel) yang bersih. Dengan ViewModel,
 * komponen UI hanya fokus menampilkan data dan menangkap input pengguna,
 * sedangkan manipulasi data (tambah, hapus, cari) dilakukan di sini.
 *
 * 3. BAGAIMANA KODE INI BEKERJA:
 * ViewModel memanfaatkan StateFlow. Saat data dalam MutableStateFlow berubah,
 * Jetpack Compose yang mengamati StateFlow tersebut akan secara otomatis
 * melakukan recomposition (memperbarui tampilan layar secara instan).
 *
 * 4. BAGIAN YANG HARUS DIUBAH JIKA STUDI KASUS UJIAN BERBEDA:
 * - Ganti tipe data `Mahasiswa` dengan model studi kasusmu (misal: `Produk`, `Buku`).
 * - Sesuaikan logika pencarian `getMahasiswaByNim` dengan primary key studi kasusmu (misal: `idProduk`).
 * - Sesuaikan data dummy awal pada `dummyData`.
 */
class MahasiswaViewModel : ViewModel() {

    // Data dummy awal agar saat pertama kali aplikasi dibuka sudah ada data contoh yang tampil
    private val dummyData = listOf(
        Mahasiswa(
            nim = "20220140261",
            nama = "Akhmad Rezky Utama",
            prodi = "Teknologi Informasi",
            semester = "5"
        ),
        Mahasiswa(
            nim = "20220140001",
            nama = "Budi Santoso",
            prodi = "Teknik Elektro",
            semester = "3"
        )
    )

    // _listMahasiswa bersifat private agar data hanya bisa diubah melalui fungsi di dalam ViewModel ini (Enkapsulasi)
    private val _listMahasiswa = MutableStateFlow<List<Mahasiswa>>(dummyData)

    // listMahasiswa diekspos sebagai StateFlow publik yang hanya bisa dibaca (read-only) oleh UI Composable
    val listMahasiswa: StateFlow<List<Mahasiswa>> = _listMahasiswa.asStateFlow()

    /**
     * Fungsi untuk menambahkan mahasiswa baru ke dalam daftar.
     * Logika: Mengambil daftar saat ini, lalu menambahkan objek mahasiswa baru ke list tersebut.
     * Ubah bagian ini jika ingin menambahkan validasi tambahan sebelum data dimasukkan.
     */
    fun tambahMahasiswa(mahasiswa: Mahasiswa) {
        _listMahasiswa.update { currentList ->
            currentList + mahasiswa
        }
    }

    /**
     * Fungsi untuk menghapus mahasiswa berdasarkan NIM.
     * Logika: Menyaring (filter) daftar mahasiswa agar hanya menyisakan mahasiswa yang NIM-nya tidak sama dengan NIM yang dipilih.
     * Ubah bagian ini jika primary key yang digunakan berbeda (misal: `idBarang`, `kodeBuku`).
     */
    fun hapusMahasiswa(nim: String) {
        _listMahasiswa.update { currentList ->
            currentList.filterNot { it.nim == nim }
        }
    }

    /**
     * Fungsi untuk mencari detail mahasiswa berdasarkan NIM.
     * Logika: Menggunakan fungsi firstOrNull untuk mencari data mahasiswa yang memiliki NIM sesuai.
     * Return: Objek Mahasiswa jika ditemukan, atau null jika tidak ditemukan.
     */
    fun getMahasiswaByNim(nim: String): Mahasiswa? {
        return _listMahasiswa.value.firstOrNull { it.nim == nim }
    }
}
