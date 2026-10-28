package com.example.belajarrrucp1.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.belajarrrucp1.R
import com.example.belajarrrucp1.ui.components.CustomMahasiswaCard
import com.example.belajarrrucp1.ui.theme.BelajarrrUCP1Theme
import com.example.belajarrrucp1.viewmodel.MahasiswaViewModel

/**
 * 1. KODE INI DIGUNAKAN UNTUK APA:
 * Halaman untuk menampilkan seluruh daftar mahasiswa yang tersimpan di memori.
 * Memanfaatkan komponen LazyColumn (seperti RecyclerView di XML) untuk scrolling efisien.
 *
 * 2. MENGAPA KODE INI DIPERLUKAN:
 * Dalam ujian studi kasus, mahasiswa selalu diminta menampilkan kumpulan data dalam bentuk list
 * atau card berulang, serta memberikan aksi klik untuk melihat detail atau menghapus data.
 *
 * 3. BAGAIMANA KODE INI BEKERJA:
 * - Mengamati `viewModel.listMahasiswa` dengan `collectAsState()`.
 * - Jika list kosong, menampilkan teks pemberitahuan "Belum ada data mahasiswa".
 * - Jika list memiliki data, `LazyColumn` dengan `itemsIndexed` merender setiap data menggunakan `CustomMahasiswaCard`.
 * - Tombol "Detail" memicu fungsi `onNavigateToDetail(nim)`, dan tombol "Hapus" memanggil `viewModel.hapusMahasiswa(nim)`.
 *
 * 4. BAGIAN YANG HARUS DIUBAH JIKA STUDI KASUS UJIAN BERBEDA:
 * - Sesuaikan teks judul dan pesan kosong jika studi kasus berbeda (misal: "Daftar Barang").
 * - Sesuaikan parameter yang dikirim ke fungsi detail (misal: `it.idBarang`).
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DaftarMahasiswaScreen(
    viewModel: MahasiswaViewModel,
    onNavigateToHome: () -> Unit,
    onNavigateToForm: () -> Unit,
    onNavigateToDetail: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    // Mengamati data list mahasiswa secara reaktif
    val listMahasiswa by viewModel.listMahasiswa.collectAsState()

    // Palet warna variatif untuk latar belakang Card agar tampilan menarik seperti tugas praktikum
    val cardColors = listOf(
        colorResource(id = R.color.card_blue),
        colorResource(id = R.color.card_purple),
        colorResource(id = R.color.card_green),
        colorResource(id = R.color.card_orange),
        colorResource(id = R.color.teal_700)
    )

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.list_title),
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.primary_color)
                )
            )
        },
        // Floating Action Button di pojok kanan bawah untuk cepat menambah mahasiswa baru
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNavigateToForm,
                containerColor = colorResource(id = R.color.primary_color),
                contentColor = Color.White
            ) {
                // Teks simbol tambah sederhana tanpa perlu library icons eksternal
                Text(
                    text = "+",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Header bar navigasi cepat kembali ke Home
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Total: ${listMahasiswa.size} Mahasiswa",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = colorResource(id = R.color.text_primary)
                )

                OutlinedButton(
                    onClick = onNavigateToHome,
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(text = "Ke Beranda", fontSize = 12.sp)
                }
            }

            // Kondisi jika daftar kosong
            if (listMahasiswa.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(id = R.string.empty_list),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium,
                            color = colorResource(id = R.color.text_secondary),
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        OutlinedButton(onClick = onNavigateToForm) {
                            Text(text = "Tambah Mahasiswa Sekarang")
                        }
                    }
                }
            } else {
                // LazyColumn untuk menampilkan daftar data secara efisien (scrollable)
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    itemsIndexed(
                        items = listMahasiswa,
                        key = { _, item -> item.nim } // Kunci unik untuk performa rendering
                    ) { index, mahasiswa ->
                        // Memilih warna card secara bergantian berdasarkan index
                        val color = cardColors[index % cardColors.size]

                        CustomMahasiswaCard(
                            mahasiswa = mahasiswa,
                            cardBackgroundColor = color,
                            onDetailClick = {
                                // Navigasi ke halaman detail dengan membawa parameter NIM
                                onNavigateToDetail(mahasiswa.nim)
                            },
                            onDeleteClick = {
                                // Hapus item langsung dari ViewModel
                                viewModel.hapusMahasiswa(mahasiswa.nim)
                            }
                        )
                    }

                    // Spacer di bagian paling bawah agar item terakhir tidak tertutup FAB
                    item {
                        Spacer(modifier = Modifier.height(72.dp))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DaftarMahasiswaScreenPreview() {
    BelajarrrUCP1Theme {
        DaftarMahasiswaScreen(
            viewModel = MahasiswaViewModel(),
            onNavigateToHome = {},
            onNavigateToForm = {},
            onNavigateToDetail = {}
        )
    }
}
