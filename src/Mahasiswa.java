import entity.MahasiswaEntity;
import helper.KoneksiMySQL;

import java.sql.*;
import java.util.*;

public class Mahasiswa {
    private static Scanner input = new Scanner(System.in);
    public static Connection connection = KoneksiMySQL.getConnection();
    String sql;

    public int getNpm(String nama){
        int status = -1;
        try {
            sql = "SELECT npm FROM mahasiswa WHERE nama_mahasiswa = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nama);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                status = resultSet.getInt("npm");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return status;
    }

    public MahasiswaEntity typeMahasiswa(){
        System.out.print("MASUKKAN NAMA MAHASISWA : ");
        String nama = input.nextLine();
        System.out.print("MASUKKAN EMAIL MAHASISWA : ");
        String email = input.nextLine();
        System.out.print("MASUKKAN NO TELEPON MAHASISWA : ");
        int noTelpon = input.nextInt();
        if ((nama.isBlank()) || (email.isBlank())){
            System.out.println("INPUTAN TIDAK BOLEH KOSONG");
        }
        else {
            return new MahasiswaEntity(nama, email, noTelpon);
        }
        return null;
    }

    public void inputMahasiswa(){
        MahasiswaEntity mahasiswaEntity = typeMahasiswa();
        if (mahasiswaEntity == null){
            System.out.println("ADA INPUTAN TIDAK TERISI SILAHKAN ULANGI LAGI");
        }
        else {
            try{
                sql = "INSERT INTO mahasiswa(npm, nama_mahasiswa, email_mahasiswa, notelp_mahasiswa)" +
                        "VALUES(?,?,?,?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, mahasiswaEntity.getNpm());
                statement.setString(2, mahasiswaEntity.getNama());
                statement.setString(3, mahasiswaEntity.getEmail());
                statement.setInt(4, mahasiswaEntity.getNoTelpon());
                statement.executeUpdate();

            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public void printMahasiswa(){
        ArrayList<MahasiswaEntity> mahasiswaEntities = getListMahasiswa();
        if (mahasiswaEntities.isEmpty()){
            System.out.println("DATA KOSONG");
        }
        else {
            System.out.println("======================================");
            System.out.println("           DATA MAHASISWA");
            System.out.println("======================================");
            for (MahasiswaEntity mahasiswa: mahasiswaEntities) {
                System.out.println("======================================");
                System.out.println("NPM : " + mahasiswa.getNpm());
                System.out.println("NAMA : " + mahasiswa.getNama());
                System.out.println("EMAIL : " + mahasiswa.getEmail());
                System.out.println("NO TELEPON : " + mahasiswa.getNoTelpon());
                System.out.println("======================================");
            }
        }

    }

    public void updateMahasiswa(){
        System.out.println("MASUKKAN NPM : ");
        int npm = input.nextInt();
        if(cekNpm(npm)){
            int pilihan;
            do {
                System.out.println("1. NAMA\n2. EMAIL \n3. NO TELEPON\n4. KELUAR");
                pilihan = input.nextInt();
                input.nextLine();
                switch (pilihan){
                    case 1 -> {
                        System.out.println("MASUKKAN NAMA BARU :");
                        String nama = input.nextLine();
                        if (nama.isBlank()){
                            System.out.println("INPUTAN TIDAK BOLEH KOSONG");
                        }
                        else {
                            updateSql("nama_mahasiswa", nama, npm);
                            System.out.println("BERHASIL UPDATE DATA");
                        }
                    }
                    case 2 -> {
                        System.out.println("MASUKKAN EMAIL BARU : ");
                        String email = input.nextLine();
                        if (email.isBlank()){
                            System.out.println("INPUTAN TIDAK BOLEH KOSONG");
                        }
                        else {
                            updateSql("email_mahasiswa", email, npm);
                            System.out.println("BERHASIL UPDATE DATA");
                        }
                    }
                    case 3 -> {
                        System.out.println("MASUKKAN NO TELEPON");
                        int noTelpon = input.nextInt();
                        updateSql("notelp_mahasiswa", noTelpon, npm);
                        System.out.println("BERHASIL UPDATE DATA");
                    }
                    case 4 -> System.out.println("KELUAR");
                    default -> System.out.println("PILIHAN TIDAK ADA");
                }
            }while (pilihan != 4);
        }
        else {
            System.out.println("NPM TIDAK DITEMUKAN");
        }
    }

    public void deleteMahasiswa(){
        System.out.println("MASUKKAN NPM : ");
        int npm = input.nextInt();
        if (cekNpm(npm)){
            try{
                sql = "DELETE FROM mahasiswa WHERE npm = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, npm);
                statement.executeUpdate();
                System.out.println("DATA MAHASISWA BERHASIL DIHAPUS");
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        else {
            System.out.println("NPM TIDAK DITEMUKAN");
        }
    }

    public boolean cekNpm(int npm){
        boolean status = false;
        try {
            sql = "SELECT npm FROM mahasiswa WHERE npm = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, npm);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                status = true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return status;
    }

    public void updateSql(String column, String data, int npm){
        try {
            sql = "UPDATE mahasiswa SET " + column + " = ? WHERE npm = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, data);
            statement.setInt(2, npm);
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateSql(String column, int data, int npm){
        try {
            sql = "UPDATE mahasiswa SET " + column + " = ? WHERE npm = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, data);
            statement.setInt(2, npm);
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public ArrayList<MahasiswaEntity> getListMahasiswa(){
        ArrayList<MahasiswaEntity> mahasiswaEntities = new ArrayList<>();
        try {
            sql = "SELECT * FROM mahasiswa";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                MahasiswaEntity mahasiswa = new MahasiswaEntity(
                    resultSet.getInt("npm"),
                    resultSet.getString("nama_mahasiswa"),
                    resultSet.getString("email_mahasiswa"),
                    resultSet.getInt("notelp_mahasiswa")
                );
                mahasiswaEntities.add(mahasiswa);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return mahasiswaEntities;
    }
}
