package com.example.belajarrrucp1.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.belajarrrucp1.R
import com.example.belajarrrucp1.viewmodel.MahasiswaViewModel

/**
 * 1. KODE INI DIGUNAKAN UNTUK APA:
 * Halaman detail untuk melihat seluruh atribut data mahasiswa yang dipilih oleh pengguna.
 *
 * 2. MENGAPA KODE INI DIPERLUKAN:
 * Memperlihatkan bagaimana cara membaca argumen navigasi (NIM) dan mencari objek data
 * yang bersangkutan dari ViewModel, lalu merendernya dalam format tampilan yang terstruktur.
 *
 * 3. BAGAIMANA KODE INI BEKERJA:
 * - Menerima parameter `nim` yang dikirim dari NavHost.
 * - Meminta data mahasiswa kepada ViewModel via `viewModel.getMahasiswaByNim(nim)`.
 * - Jika data ada, data dipecah menjadi daftar Pair (Label & Nilai) lalu ditampilkan berurutan dengan HorizontalDivider
 *   (mengikuti pola `TampilData.kt` pada repositori referensi Navigasi MVVM).
 * - Jika data tidak ditemukan, menampilkan teks peringatan.
 *
 * 4. BAGIAN YANG HARUS DIUBAH JIKA STUDI KASUS UJIAN BERBEDA:
 * - Ganti item-item yang ditampilkan di `detailItems` sesuai atribut model barumu.
 * - Ubah tampilan card detail jika ada informasi khusus seperti gambar atau status.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailMahasiswaScreen(
    nim: String,
    viewModel: MahasiswaViewModel,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Mencari data mahasiswa di ViewModel berdasarkan parameter NIM
    val mahasiswa = viewModel.getMahasiswaByNim(nim)
    val scrollState = rememberScrollState()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.detail_title),
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
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            if (mahasiswa == null) {
                // Penanganan jika data mahasiswa tidak ditemukan atau telah dihapus
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Data mahasiswa dengan NIM '$nim' tidak ditemukan!",
                        color = Color.Red,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(onClick = onNavigateBack) {
                        Text(text = "Kembali ke Daftar")
                    }
                }
            } else {
                // Tampilan jika data mahasiswa ditemukan
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Header Kartu Profil
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = colorResource(id = R.color.card_blue)
                        ),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.logo_umy),
                                contentDescription = "Logo Kampus",
                                modifier = Modifier.size(64.dp)
                            )

                            Spacer(modifier = Modifier.width(16.dp))

                            Column {
                                Text(
                                    text = mahasiswa.nama,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                                Text(
                                    text = "Status: Mahasiswa Aktif",
                                    fontSize = 13.sp,
                                    color = colorResource(id = R.color.text_yellow)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // Card rincian data (mengikuti pola TampilData.kt pada referensi)
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        ),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            val detailItems = listOf(
                                Pair("NOMOR INDUK MAHASISWA (NIM)", mahasiswa.nim),
                                Pair("NAMA LENGKAP", mahasiswa.nama),
                                Pair("PROGRAM STUDI", mahasiswa.prodi),
                                Pair("SEMESTER", "Semester ${mahasiswa.semester}")
                            )

                            detailItems.forEach { item ->
                                Column {
                                    // Label field
                                    Text(
                                        text = item.first,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = colorResource(id = R.color.text_secondary)
                                    )

                                    Spacer(modifier = Modifier.height(4.dp))

                                    // Nilai field
                                    Text(
                                        text = item.second,
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = colorResource(id = R.color.text_primary)
                                    )
                                }

                                // Garis pembatas horizontal antar field
                                HorizontalDivider(
                                    thickness = 1.dp,
                                    color = Color.LightGray.copy(alpha = 0.5f),
                                    modifier = Modifier.padding(vertical = 4.dp)
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Tombol Kembali ke Daftar Mahasiswa
                Button(
                    onClick = onNavigateBack,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.primary_color)
                    )
                ) {
                    Text(
                        text = "Kembali ke Daftar",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
