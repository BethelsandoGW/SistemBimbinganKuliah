import entity.BimbinganEntity;
import helper.KoneksiMySQL;

import java.sql.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Date;


public class Bimbingan {
    private static Scanner input = new Scanner(System.in);
    public static Connection connection = KoneksiMySQL.getConnection();
    String sql;
    public void printBimbingan(){
        ArrayList<BimbinganEntity> bimbinganEntities = getListBimbingan();
        if (bimbinganEntities.isEmpty()){
            System.out.println("DATA KOSONG");
        }
        else {
            System.out.println("======================================");
            System.out.println("            DATA BIMBINGAN");
            System.out.println("======================================");
            for (BimbinganEntity bimbingan : bimbinganEntities) {
                System.out.println("======================================");
                System.out.println("ID BIMBINGAN : " + bimbingan.getId_bimbingan());
                System.out.println("NAMA DOSEN : " + bimbingan.getNama_dosen());
                System.out.println("NAMA MAHASISWA : " + bimbingan.getNama_mahasiswa());
                System.out.println("WAKTU BIMBINGAN : " + bimbingan.getWaktu_bimbingan());
            }
        }

    }
    public LocalDateTime stringToDateTime(String dateTimeString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);
        return dateTime;
    }
    public Date convertLocalDateTimeToDateUsingInstant(LocalDateTime dateToConvert) {
        return java.util.Date.from(dateToConvert.atZone(ZoneId.systemDefault()).toInstant());
    }
    public LocalDateTime convertToLocalDateTimeViaMilisecond(Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
    public ArrayList<BimbinganEntity> getListBimbingan(){
        ArrayList<BimbinganEntity> bimbinganEntities = new ArrayList<>();
        try {
            sql = "SELECT id_bimbingan, nama_mahasiswa, nama_dosen, judul_bimbingan, waktu_bimbingan FROM bimbingan JOIN mahasiswa ON bimbingan.npm = mahasiswa.npm JOIN dosen_pembimbing ON bimbingan.nip = dosen_pembimbing.nip";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                BimbinganEntity bimbingan = new BimbinganEntity(
                        resultSet.getInt("id_bimbingan"),
                        resultSet.getString("nama_mahasiswa"),
                        resultSet.getString("nama_dosen"),
                        resultSet.getString("judul_bimbingan"),
                        convertToLocalDateTimeViaMilisecond(resultSet.getDate("waktu_bimbingan"))
                );
                bimbinganEntities.add(bimbingan);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return bimbinganEntities;
    }
    public void insertBimbingan(){
        Mahasiswa mahasiswa = new Mahasiswa();
        Dosen dosen = new Dosen();
        BimbinganEntity bimbingan = typeBimbingan();
        if (bimbingan == null){
            System.out.println("ADA INPUTAN TIDAK TERISI SILAHKAN ULANGI LAGI");
        }
        else {
            try {
                PreparedStatement statement = connection.prepareStatement(sql);
                sql = "INSERT INTO bimbingan(npm, nip, judul_bimbingan, waktu_bimbingan) VALUES (?, ?, ?, ?)";
                if (mahasiswa.getNpm(bimbingan.getNama_mahasiswa()) == -1 || dosen.getNip(bimbingan.getNama_dosen()) == -1){
                    System.out.println("MAHASISWA ATAU DOSEN TIDAK TERDAFTAR");
                }
                else {
                    statement.setInt(1, mahasiswa.getNpm(bimbingan.getNama_mahasiswa()));
                    statement.setInt(2, dosen.getNip(bimbingan.getNama_dosen()));
                    statement.setString(3, bimbingan.getJudul());
                    statement.setDate(4, (java.sql.Date) convertLocalDateTimeToDateUsingInstant(bimbingan.getWaktu_bimbingan()));
                    statement.executeUpdate();
                    System.out.println("BIMBINGAN BERHASIL DITAMBAHKAN");
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
    public BimbinganEntity typeBimbingan(){
        System.out.println("MASUKKAN NAMA DOSEN : ");
        String dosen = input.nextLine();
        System.out.println("MASUKKAN NAMA MAHASISWA : ");
        String mahasiswa = input.nextLine();
        System.out.println("MASUKKAN JUDUL BIMBINGAN : ");
        String bimbingan = input.nextLine();
        System.out.println("MASUKKAN TANGGAL BIMBINGAN (yyyy-mm-dd): ");
        String tanggal = input.nextLine();
        System.out.println("MASUKKAN WAKTU BIMBINGAN (hh:mm): ");
        String waktu = input.nextLine();
        String tanggalWaktu = tanggal + " " + waktu;
        if ((dosen.isBlank())||(mahasiswa.isBlank())||(bimbingan.isBlank())||(tanggal.isBlank())||(waktu.isBlank())){
            System.out.println("INPUTAN TIDAK BOLEH KOSONG");
        }
        else {
            return new BimbinganEntity(dosen, mahasiswa, bimbingan, stringToDateTime(tanggalWaktu));
        }
        return null;
    }
    public boolean cekId(int id){
        try {
            sql ="SELECT id_bimbingan FROM bimbingan";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                return true;
            }
            else {
                return false;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public void updateWaktuBimbingan(){
        System.out.println("MASUKKAN ID BIMBINGAN : ");
        int id = input.nextInt();
        if (cekId(id)){
            System.out.println("MASUKKAN TANGGAL BIMBINGAN (yyyy-mm-dd): ");
            String tanggal = input.nextLine();
            System.out.println("MASUKKAN WAKTU BIMBINGAN (hh:mm): ");
            String waktu = input.nextLine();
            if ((tanggal.isBlank()) || (waktu.isBlank())){
                System.out.println("INPUTAN TIDAK BOLEH KOSONG");
            }
            else {
                String tanggalWaktu = tanggal + " " + waktu;
                try {
                    sql = "UPDATE bimbingan SET waktu bimbingan = ? WHERE id_bimbingan = ?";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setDate(1, (java.sql.Date) convertLocalDateTimeToDateUsingInstant(stringToDateTime(tanggalWaktu)));
                    statement.setInt(2, id);
                    statement.executeUpdate();
                    System.out.println("WAKTU BIMBINGAN BERHASIL DIUBAH");
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
        else {
            System.out.println("ID BIMBINGAN TIDAK DITEMUKAN");
        }
    }
    public void deleteBimbingan(){
        System.out.println("MASUKKAN ID BIMBINGAN : ");
        int id = input.nextInt();
        if (cekId(id)){
            try{
                sql = "DELETE FROM bimbingan WHERE id_bimbingan = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, id);
                statement.executeUpdate();
                System.out.println("DATA BIMBINGAN BERHASIL DIHAPUS");
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        else {
            System.out.println("ID BIMBINGAN TIDAK DITEMUKAN");
        }
    }
}
