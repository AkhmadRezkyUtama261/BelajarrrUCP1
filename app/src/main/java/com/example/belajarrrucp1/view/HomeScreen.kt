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
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.belajarrrucp1.R
import com.example.belajarrrucp1.ui.theme.BelajarrrUCP1Theme
import com.example.belajarrrucp1.viewmodel.MahasiswaViewModel

/**
 * 1. KODE INI DIGUNAKAN UNTUK APA:
 * Halaman utama (Home Screen) aplikasi yang menyapa pengguna, memberikan ringkasan status data,
 * dan menyediakan navigasi cepat ke form input maupun ke daftar mahasiswa.
 *
 * 2. MENGAPA KODE INI DIPERLUKAN:
 * Halaman beranda adalah titik masuk (landing page) agar pengguna memahami fungsi aplikasi,
 * melihat ringkasan singkat, dan dapat memilih aksi yang ingin dilakukan selanjutnya.
 *
 * 3. BAGAIMANA KODE INI BEKERJA:
 * - Menggunakan Scaffold untuk struktur dasar halaman lengkap dengan TopAppBar.
 * - Mengamati jumlah data mahasiswa dari `MahasiswaViewModel` menggunakan `collectAsState()`.
 * - Menampilkan Card informasi dan dua tombol aksi yang memicu callback fungsi navigasi.
 *
 * 4. BAGIAN YANG HARUS DIUBAH JIKA STUDI KASUS UJIAN BERBEDA:
 * - Ganti judul "Student App" dengan judul studi kasusmu (misal: "Inventaris Barang App").
 * - Sesuaikan teks sambutan dan ringkasan pada Card Ringkasan.
 * - Ganti teks tombol jika nama entitas berubah (misal: "Tambah Barang" & "Lihat Daftar Barang").
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: MahasiswaViewModel,
    onNavigateToForm: () -> Unit,
    onNavigateToList: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Mengamati data list mahasiswa secara reaktif dari ViewModel
    val listMahasiswa by viewModel.listMahasiswa.collectAsState()
    val scrollState = rememberScrollState()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            // TopAppBar bagian atas halaman
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
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
        // Column sebagai container vertikal utama dengan dukungan scroll jika layar kecil
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(scrollState)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // 1. Gambar Logo / Ilustrasi
            Image(
                painter = painterResource(id = R.drawable.logo_umy),
                contentDescription = "Logo Utama",
                modifier = Modifier
                    .size(100.dp)
                    .padding(top = 10.dp)
            )

            // 2. Teks Judul dan Sambutan
            Text(
                text = stringResource(id = R.string.app_title),
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = colorResource(id = R.color.text_primary)
            )

            Text(
                text = stringResource(id = R.string.welcome_text),
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                color = colorResource(id = R.color.text_secondary)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // 3. Card Ringkasan (Menampilkan jumlah data tersimpan & tips ujian)
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = colorResource(id = R.color.card_blue)
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Ringkasan Data",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Total Mahasiswa Terdaftar:",
                            fontSize = 15.sp,
                            color = Color.White
                        )
                        Text(
                            text = "${listMahasiswa.size} Orang",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = colorResource(id = R.color.text_yellow)
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Tip Ujian: Gunakan ViewModel agar data tetap tersimpan saat berpindah layar!",
                        fontSize = 12.sp,
                        color = Color.White.copy(alpha = 0.85f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // 4. Tombol Aksi Navigasi: Tambah Mahasiswa
            Button(
                onClick = onNavigateToForm,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.primary_color)
                )
            ) {
                Text(
                    text = stringResource(id = R.string.btn_add_student),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            // 5. Tombol Aksi Navigasi: Lihat Daftar Mahasiswa
            OutlinedButton(
                onClick = onNavigateToList,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.btn_view_students),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = colorResource(id = R.color.primary_color)
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    BelajarrrUCP1Theme {
        HomeScreen(
            viewModel = MahasiswaViewModel(),
            onNavigateToForm = {},
            onNavigateToList = {}
        )
    }
}
