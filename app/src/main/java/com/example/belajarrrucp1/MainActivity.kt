package com.example.belajarrrucp1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.belajarrrucp1.navigation.StudentAppNavHost
import com.example.belajarrrucp1.ui.theme.BelajarrrUCP1Theme

/**
 * 1. KODE INI DIGUNAKAN UNTUK APA:
 * Titik masuk utama (Main Entry Point) dari aplikasi Android saat pertama kali dijalankan oleh sistem operasi.
 *
 * 2. MENGAPA KODE INI DIPERLUKAN:
 * Diperlukan sebagai Activity yang didaftarkan di `AndroidManifest.xml` dengan intent-filter MAIN dan LAUNCHER.
 * Activity ini menjadi wadah tempat seluruh UI Jetpack Compose dirender.
 *
 * 3. BAGAIMANA KODE INI BEKERJA:
 * - `onCreate()` dipanggil saat activity dibuat.
 * - `enableEdgeToEdge()` mengaktifkan tampilan modern layar penuh tanpa batasan status bar kaku.
 * - `setContent { ... }` menginisialisasi tema aplikasi `BelajarrrUCP1Theme` dan memuat router utama `StudentAppNavHost()`.
 *
 * 4. BAGIAN YANG HARUS DIUBAH JIKA STUDI KASUS UJIAN BERBEDA:
 * Hampir tidak perlu diubah, kecuali jika kamu ingin mengubah Composable awal yang dipanggil
 * atau mengganti nama tema aplikasi.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BelajarrrUCP1Theme {
                // Surface membungkus seluruh layar dengan warna latar belakang default tema
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Memanggil controller navigasi utama aplikasi
                    StudentAppNavHost()
                }
            }
        }
    }
}
