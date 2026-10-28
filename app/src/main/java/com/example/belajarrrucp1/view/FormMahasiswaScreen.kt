package com.example.belajarrrucp1.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.belajarrrucp1.R
import com.example.belajarrrucp1.model.Mahasiswa
import com.example.belajarrrucp1.viewmodel.MahasiswaViewModel
import kotlinx.coroutines.launch

/**
 * 1. KODE INI DIGUNAKAN UNTUK APA:
 * Halaman formulir input data mahasiswa baru.
 * Pengguna dapat mengetikkan NIM, Nama, Program Studi, dan Semester, lalu menyimpannya ke ViewModel.
 *
 * 2. MENGAPA KODE INI DIPERLUKAN:
 * Mengajarkan konsep penanganan User Input pada Jetpack Compose menggunakan state lokal
 * (`remember` dan `mutableStateOf`), validasi isian form, serta umpan balik (feedback) visual lewat Snackbar.
 *
 * 3. BAGAIMANA KODE INI BEKERJA:
 * - Setiap field input diikat ke variabel state (misal `nim`, `nama`, dll) via parameter `value` dan `onValueChange`.
 * - Saat tombol "Simpan" ditekan, fungsi validasi mengecek apakah ada field yang kosong atau NIM sudah terdaftar.
 * - Jika valid, objek `Mahasiswa` dibuat dan dikirim ke `viewModel.tambahMahasiswa()`, lalu Snackbar ditampilkan
 *   dan form di-reset atau langsung berpindah ke halaman daftar mahasiswa.
 *
 * 4. BAGIAN YANG HARUS DIUBAH JIKA STUDI KASUS UJIAN BERBEDA:
 * - Sesuaikan jumlah dan nama variabel state (misal: `namaBarang`, `kategori`, `harga`, `stok`).
 * - Sesuaikan `OutlinedTextField` (label, placeholder, keyboardType) sesuai atribut studi kasus.
 * - Sesuaikan objek yang dibuat saat menyimpan data.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormMahasiswaScreen(
    viewModel: MahasiswaViewModel,
    onNavigateBack: () -> Unit,
    onSuccessSave: () -> Unit,
    modifier: Modifier = Modifier
) {
    // State lokal untuk menyimpan teks yang diketik pengguna pada masing-masing input field
    var nimInput by remember { mutableStateOf("") }
    var namaInput by remember { mutableStateOf("") }
    var prodiInput by remember { mutableStateOf("") }
    var semesterInput by remember { mutableStateOf("") }

    // State untuk pesan error validasi
    var errorMessage by remember { mutableStateOf<String?>(null) }

    // SnackbarHostState dan CoroutineScope untuk menampilkan notifikasi snackbar
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val scrollState = rememberScrollState()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.form_title),
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.primary_color)
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(scrollState)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Isi Lengkap Data Mahasiswa",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.text_primary),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
            )

            // Pesan Error jika validasi gagal
            if (errorMessage != null) {
                Text(
                    text = errorMessage ?: "",
                    color = Color.Red,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                )
            }

            // 1. Input Field: NIM (Nomor Induk Mahasiswa)
            OutlinedTextField(
                value = nimInput,
                onValueChange = {
                    nimInput = it
                    errorMessage = null // Bersihkan error saat pengguna mulai mengetik kembali
                },
                label = { Text("NIM (Nomor Induk Mahasiswa)") },
                placeholder = { Text("Contoh: 20220140261") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(14.dp))

            // 2. Input Field: Nama Lengkap
            OutlinedTextField(
                value = namaInput,
                onValueChange = {
                    namaInput = it
                    errorMessage = null
                },
                label = { Text("Nama Lengkap") },
                placeholder = { Text("Contoh: Akhmad Rezky Utama") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(14.dp))

            // 3. Input Field: Program Studi
            OutlinedTextField(
                value = prodiInput,
                onValueChange = {
                    prodiInput = it
                    errorMessage = null
                },
                label = { Text("Program Studi") },
                placeholder = { Text("Contoh: Teknologi Informasi") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(14.dp))

            // 4. Input Field: Semester
            OutlinedTextField(
                value = semesterInput,
                onValueChange = {
                    semesterInput = it
                    errorMessage = null
                },
                label = { Text("Semester") },
                placeholder = { Text("Contoh: 5") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Baris Tombol Aksi: Simpan dan Kembali
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Tombol Kembali
                OutlinedButton(
                    onClick = onNavigateBack,
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.btn_back),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                // Tombol Simpan dengan Validasi
                Button(
                    onClick = {
                        // VALIDASI INPUT: Memastikan semua field telah diisi
                        when {
                            nimInput.isBlank() -> {
                                errorMessage = "NIM wajib diisi!"
                            }
                            namaInput.isBlank() -> {
                                errorMessage = "Nama lengkap wajib diisi!"
                            }
                            prodiInput.isBlank() -> {
                                errorMessage = "Program studi wajib diisi!"
                            }
                            semesterInput.isBlank() -> {
                                errorMessage = "Semester wajib diisi!"
                            }
                            // Memeriksa apakah NIM sudah terdaftar sebelumnya
                            viewModel.getMahasiswaByNim(nimInput.trim()) != null -> {
                                errorMessage = "Mahasiswa dengan NIM tersebut sudah terdaftar!"
                            }
                            else -> {
                                // Buat objek Mahasiswa dari input pengguna
                                val mahasiswaBaru = Mahasiswa(
                                    nim = nimInput.trim(),
                                    nama = namaInput.trim(),
                                    prodi = prodiInput.trim(),
                                    semester = semesterInput.trim()
                                )

                                // Simpan ke ViewModel
                                viewModel.tambahMahasiswa(mahasiswaBaru)

                                // Bersihkan form
                                nimInput = ""
                                namaInput = ""
                                prodiInput = ""
                                semesterInput = ""
                                errorMessage = null

                                // Tampilkan notifikasi snackbar dan lanjutkan ke halaman daftar
                                coroutineScope.launch {
                                    snackbarHostState.showSnackbar("Data mahasiswa berhasil disimpan!")
                                }
                                onSuccessSave()
                            }
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.primary_color)
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.btn_save),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
