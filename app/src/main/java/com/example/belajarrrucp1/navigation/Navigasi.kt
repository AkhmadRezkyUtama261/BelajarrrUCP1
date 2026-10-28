package com.example.belajarrrucp1.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.belajarrrucp1.view.DaftarMahasiswaScreen
import com.example.belajarrrucp1.view.DetailMahasiswaScreen
import com.example.belajarrrucp1.view.FormMahasiswaScreen
import com.example.belajarrrucp1.view.HomeScreen
import com.example.belajarrrucp1.viewmodel.MahasiswaViewModel

/**
 * 1. KODE INI DIGUNAKAN UNTUK APA:
 * Mendefinisikan daftar rute/nama halaman yang tersedia di aplikasi menggunakan `enum class`.
 * Pola ini diadopsi langsung dari repositori referensi Navigasi MVVM (`QuestNavigasiMVVM_0261`).
 *
 * 2. MENGAPA KODE INI DIPERLUKAN:
 * Menggunakan enum class mencegah kesalahan pengetikan (typo) saat berpindah halaman
 * dibandingkan menuliskan string rute manual berulang kali.
 *
 * 3. BAGAIMANA KODE INI BEKERJA:
 * Setiap item enum mewakili satu halaman tujuan:
 * - Home: Halaman beranda
 * - Form: Halaman formulir input
 * - List: Halaman daftar mahasiswa
 * - Detail: Halaman rincian mahasiswa
 *
 * 4. BAGIAN YANG HARUS DIUBAH JIKA STUDI KASUS UJIAN BERBEDA:
 * Tambah atau kurangi nama konstanta enum jika jumlah layar pada ujian berbeda
 * (misal: HalamanTransaksi, HalamanKategori).
 */
enum class HalamanNavigasi {
    Home,
    Form,
    List,
    Detail
}

/**
 * 1. KODE INI DIGUNAKAN UNTUK APA:
 * Composable utama untuk mengatur alur navigasi antarhalaman menggunakan Navigation Compose (`NavHost`).
 *
 * 2. MENGAPA KODE INI DIPERLUKAN:
 * Sebagai pengatur lalu lintas (routing) aplikasi sehingga pengguna dapat berpindah layar
 * dengan lancar dan tombol kembali (back button) berfungsi secara otomatis.
 *
 * 3. BAGAIMANA KODE INI BEKERJA:
 * - Menerima atau membuat instance `NavHostController` dengan `rememberNavController()`.
 * - Membuat satu instance bersama `MahasiswaViewModel` menggunakan `viewModel()` agar data list mahasiswa
 *   konsisten dan tersimpan sama di semua layar.
 * - Mendaftarkan setiap rute composable:
 *     * `HalamanNavigasi.Home.name`: Membuka HomeScreen.
 *     * `HalamanNavigasi.Form.name`: Membuka FormMahasiswaScreen.
 *     * `HalamanNavigasi.List.name`: Membuka DaftarMahasiswaScreen.
 *     * `${HalamanNavigasi.Detail.name}/{nim}`: Membuka DetailMahasiswaScreen dengan argumen string `nim`.
 *
 * 4. BAGIAN YANG HARUS DIUBAH JIKA STUDI KASUS UJIAN BERBEDA:
 * Ubah rute `startDestination` jika ingin membuka halaman lain pertama kali,
 * atau ganti parameter `{nim}` menjadi `{idProduk}` jika studi kasusnya produk.
 */
@Composable
fun StudentAppNavHost(
    navController: NavHostController = rememberNavController(),
    mahasiswaViewModel: MahasiswaViewModel = viewModel()
) {
    NavHost(
        navController = navController,
        startDestination = HalamanNavigasi.Home.name
    ) {
        // 1. Rute Halaman Home (Beranda)
        composable(route = HalamanNavigasi.Home.name) {
            HomeScreen(
                viewModel = mahasiswaViewModel,
                onNavigateToForm = {
                    navController.navigate(HalamanNavigasi.Form.name)
                },
                onNavigateToList = {
                    navController.navigate(HalamanNavigasi.List.name)
                }
            )
        }

        // 2. Rute Halaman Form Input Mahasiswa
        composable(route = HalamanNavigasi.Form.name) {
            FormMahasiswaScreen(
                viewModel = mahasiswaViewModel,
                onNavigateBack = {
                    navController.popBackStack()
                },
                onSuccessSave = {
                    // Setelah sukses simpan, langsung arahkan ke halaman Daftar Mahasiswa
                    navController.navigate(HalamanNavigasi.List.name) {
                        // Pop rute form agar ketika di list ditekan back, tidak kembali ke form kosong
                        popUpTo(HalamanNavigasi.Home.name)
                    }
                }
            )
        }

        // 3. Rute Halaman Daftar Mahasiswa
        composable(route = HalamanNavigasi.List.name) {
            DaftarMahasiswaScreen(
                viewModel = mahasiswaViewModel,
                onNavigateToHome = {
                    // Kembali ke Home dengan menghapus tumpukan backstack
                    navController.popBackStack(HalamanNavigasi.Home.name, inclusive = false)
                },
                onNavigateToForm = {
                    navController.navigate(HalamanNavigasi.Form.name)
                },
                onNavigateToDetail = { nim ->
                    // Mengirimkan argumen NIM ke halaman detail
                    navController.navigate("${HalamanNavigasi.Detail.name}/$nim")
                }
            )
        }

        // 4. Rute Halaman Detail Mahasiswa dengan argumen dinamis {nim}
        composable(
            route = "${HalamanNavigasi.Detail.name}/{nim}",
            arguments = listOf(
                navArgument("nim") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            // Membaca argumen NIM dari NavBackStackEntry
            val nim = backStackEntry.arguments?.getString("nim") ?: ""

            DetailMahasiswaScreen(
                nim = nim,
                viewModel = mahasiswaViewModel,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
