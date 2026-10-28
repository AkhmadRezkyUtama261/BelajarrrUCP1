package com.example.belajarrrucp1.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.belajarrrucp1.R
import com.example.belajarrrucp1.model.Mahasiswa

/**
 * 1. KODE INI DIGUNAKAN UNTUK APA:
 * Komponen Card kustom untuk menampilkan satu item data mahasiswa di dalam daftar (LazyColumn).
 * Didesain dengan gaya visual rapi terinspirasi dari praktikum tugas layout dan user input.
 *
 * 2. MENGAPA KODE INI DIPERLUKAN:
 * Memisahkan tampilan item ke dalam composable tersendiri membuat kode halaman daftar (List)
 * menjadi jauh lebih bersih, rapi, dan mudah dirawat.
 *
 * 3. BAGAIMANA KODE INI BEKERJA:
 * - Menerima objek data Mahasiswa.
 * - Menggunakan Card dengan sudut membulat (RoundedCornerShape 16.dp).
 * - Mengatur baris (Row) untuk gambar logo dan informasi mahasiswa.
 * - Menyediakan dua tombol aksi: "Detail" dan "Hapus" yang memicu callback event.
 *
 * 4. BAGIAN YANG HARUS DIUBAH JIKA STUDI KASUS UJIAN BERBEDA:
 * - Ganti `mahasiswa: Mahasiswa` dengan data model studi kasusmu (misal: `barang: Barang`).
 * - Ganti teks yang ditampilkan (misal: `mahasiswa.nama` menjadi `barang.namaBarang`).
 * - Sesuaikan warna latar card jika ingin warna yang berbeda.
 */
@Composable
fun CustomMahasiswaCard(
    mahasiswa: Mahasiswa,
    cardBackgroundColor: Color = colorResource(id = R.color.card_blue),
    onDetailClick: () -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = cardBackgroundColor
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Baris atas: Logo + Informasi Mahasiswa
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Gambar Icon / Logo kampus/profil
                Image(
                    painter = painterResource(id = R.drawable.logo_umy),
                    contentDescription = "Logo",
                    modifier = Modifier.size(54.dp)
                )

                Spacer(modifier = Modifier.width(14.dp))

                // Kolom informasi teks: Nama, NIM, dan Program Studi
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = mahasiswa.nama,
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    Text(
                        text = "NIM: ${mahasiswa.nim}",
                        color = colorResource(id = R.color.text_yellow),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )

                    Text(
                        text = mahasiswa.prodi,
                        color = Color.White.copy(alpha = 0.9f),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Normal
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Baris tombol aksi: Tombol Detail dan Tombol Hapus
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Tombol Hapus (Outlined / Putih)
                OutlinedButton(
                    onClick = onDeleteClick,
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color.White
                    ),
                    border = ButtonDefaults.outlinedButtonBorder.copy(
                        brush = androidx.compose.ui.graphics.SolidColor(Color.White)
                    ),
                    modifier = Modifier.height(36.dp)
                ) {
                    Text(text = "Hapus", fontSize = 12.sp)
                }

                Spacer(modifier = Modifier.width(8.dp))

                // Tombol Detail (Solid White Button dengan teks warna card)
                Button(
                    onClick = onDetailClick,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black
                    ),
                    modifier = Modifier.height(36.dp)
                ) {
                    Text(text = "Detail", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
