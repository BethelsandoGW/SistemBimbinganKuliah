import java.util.Scanner;

public class App {
    private static Scanner input = new Scanner(System.in);
    public void run(){
        menu();
    }
    public void menu(){
        int pilihan = 0;
        do {
            System.out.println("1. DOSEN \n2. MAHASISWA \n3. BIMBINGAN \n4. KELUAR");
            System.out.print("MASUKKAN PILIHAN : ");
            pilihan = input.nextInt();
            input.nextLine();
            switch (pilihan){
                case 1 -> {
                    menuDosen();
                }
                case 2 -> {
                    menuMahasiswa();
                }
                case 3 -> {
                    menuBimbingan();
                }
                case 4 -> {
                    System.out.println("KELUAR");
                }
                default -> {
                    System.out.println("PILIHAN TIDAK DITEMUKAN");
                }
            }
        }while (pilihan != 5);
    }
    public void menuDosen(){
        Dosen dosen = new Dosen();
        int pilihan = 0;
        do {
            System.out.println("1. TAMBAH DOSEN \n2. HAPUS DOSEN \n3. UPDATE DOSEN \n4. LIHAT DOSEN \n5. KELUAR");
            System.out.print("MASUKKAN PILIHAN : ");
            pilihan = input.nextInt();
            input.nextLine();
            switch (pilihan){
                case 1 -> {
                    dosen.inputDosen();
                }
                case 2 -> {
                    dosen.deleteDosen();
                }
                case 3 -> {
                    dosen.updateDosen();
                }
                case 4 -> {
                    dosen.printDosen();
                }
                case 5 -> {
                    System.out.println("KELUAR");
                }
                default -> {
                    System.out.println("PILIHAN TIDAK DITEMUKAN");
                }
            }
        }while (pilihan != 5);
    }
    public void menuMahasiswa(){
        Mahasiswa mahasiswa = new Mahasiswa();
        int pilihan = 0;
        do {
            System.out.println("1. TAMBAH MAHASISWA \n2. HAPUS MAHASISWA \n3. UPDATE MAHASISWA \n4. LIHAT MAHASISWA \n5. KELUAR");
            System.out.print("MASUKKAN PILIHAN : ");
            pilihan = input.nextInt();
            input.nextLine();
            switch (pilihan){
                case 1 -> {
                    mahasiswa.inputMahasiswa();
                }
                case 2 -> {
                    mahasiswa.deleteMahasiswa();
                }
                case 3 -> {
                    mahasiswa.updateMahasiswa();
                }
                case 4 -> {
                    mahasiswa.printMahasiswa();
                }
                case 5 -> {
                    System.out.println("KELUAR");
                }
                default -> {
                    System.out.println("PILIHAN TIDAK DITEMUKAN");
                }
            }
        }while (pilihan != 5);
    }
    public void menuBimbingan(){
        Bimbingan bimbingan = new Bimbingan();
        int pilihan = 0;
        do {
            System.out.println("1. TAMBAH BIMBINGAN \n2. HAPUS BIMBINGAN \n3. UPDATE BIMBINGAN \n4. LIHAT BIMBINGAN \n5. KELUAR");
            System.out.print("MASUKKAN PILIHAN : ");
            pilihan = input.nextInt();
            input.nextLine();
            switch (pilihan){
                case 1 -> {
                    bimbingan.insertBimbingan();
                }
                case 2 -> {
                    bimbingan.deleteBimbingan();
                }
                case 3 -> {
                    bimbingan.updateWaktuBimbingan();
                }
                case 4 -> {
                    bimbingan.printBimbingan();
                }
                case 5 -> {
                    System.out.println("KELUAR");
                }
                default -> {
                    System.out.println("PILIHAN TIDAK DITEMUKAN");
                }
            }
        }while (pilihan != 5);
    }
}
