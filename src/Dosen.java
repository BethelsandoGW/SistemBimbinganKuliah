import entity.DosenEntity;
import helper.KoneksiMySQL;

import java.sql.*;
import java.util.*;

public class Dosen {
    private static Scanner input = new Scanner(System.in);
    public static Connection connection = KoneksiMySQL.getConnection();
    public ArrayList<DosenEntity> dosenEntities = new ArrayList<>();
    String sql;

    public int getNip(String nama){
        int status = -1;
        try {
            sql = "SELECT nip FROM dosen_pembimbing WHERE nama_dosen = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nama);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                status = resultSet.getInt("nip");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return status;
    }

    public DosenEntity typeDosen(){
        System.out.print("MASUKKAN NAMA DOSEN : ");
        String nama = input.nextLine();
        System.out.print("MASUKKAN EMAIL DOSEN : ");
        String email = input.nextLine();
        System.out.print("MASUKKAN NO TELEPON DOSEN : ");
        int noTelpon = input.nextInt();
        if ((nama.isBlank()) || (email.isBlank())){
            System.out.println("INPUTAN TIDAK BOLEH KOSONG");
        }
        else {
            return new DosenEntity(nama, email, noTelpon);
        }
        return null;
    }

    public void inputDosen(){
        DosenEntity dosenEntity = typeDosen();
        if (dosenEntity == null){
            System.out.println("ADA INPUTAN TIDAK TERISI SILAHKAN ULANGI LAGI");
        }
        else {
            try{
                sql = "INSERT INTO dosen_pembimbing(nip, nama_dosen, email_dosen, notelp_dosen)" +
                        "VALUES(?,?,?,?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, dosenEntity.getNip());
                statement.setString(2, dosenEntity.getNama());
                statement.setString(3, dosenEntity.getEmail());
                statement.setInt(4, dosenEntity.getNoTelpon());
                statement.executeUpdate();

            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public void printDosen(){
        ArrayList<DosenEntity> dosenEntities = getListDosen();
        if (dosenEntities.isEmpty()){
            System.out.println("DATA KOSONG");
        }
        else {
            System.out.println("======================================");
            System.out.println("               DATA DOSEN");
            System.out.println("======================================");
            for (DosenEntity dosen: dosenEntities) {
                System.out.println("======================================");
                System.out.println("NIP : " + dosen.getNip());
                System.out.println("NAMA : " + dosen.getNama());
                System.out.println("EMAIL : " + dosen.getEmail());
                System.out.println("NO TELEPON : " + dosen.getNoTelpon());
                System.out.println("======================================");
            }
        }

    }

    public void updateDosen(){
        System.out.println("MASUKKAN NIP : ");
        int nip = input.nextInt();
        if(cekNip(nip)){
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
                            updateSql("nama_dosen", nama, nip);
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
                            updateSql("email_dosen", email, nip);
                            System.out.println("BERHASIL UPDATE DATA");
                        }
                    }
                    case 3 -> {
                        System.out.println("MASUKKAN NO TELEPON");
                        int noTelpon = input.nextInt();
                        updateSql("notelp_dosen ", noTelpon, nip);
                        System.out.println("BERHASIL UPDATE DATA");
                    }
                    case 4 -> System.out.println("KELUAR");
                    default -> System.out.println("PILIHAN TIDAK ADA");
                }
            }while (pilihan != 4);
        }
        else {
            System.out.println("NIP TIDAK DITEMUKAN");
        }
    }

    public void deleteDosen(){
        System.out.println("MASUKKAN NIP : ");
        int nip = input.nextInt();
        if (cekNip(nip)){
            try{
                sql = "DELETE FROM dosen_pembimbing WHERE nip = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, nip);
                statement.executeUpdate();
                System.out.println("DATA DOSEN BERHASIL DIHAPUS");
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        else {
            System.out.println("NIP TIDAK DITEMUKAN");
        }
    }

    public boolean cekNip(int nip){
        boolean status = false;
        try {
            sql = "SELECT nip FROM dosen_pembimbing WHERE nip = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, nip);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                status = true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return status;
    }

    public void updateSql(String column, String data, int nip){
        try {
            sql = "UPDATE dosen_pembimbing SET " + column + " = ? WHERE nip = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, data);
            statement.setInt(2, nip);
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateSql(String column, int data, int nip){
        try {
            sql = "UPDATE dosen_pembimbing SET " + column + " = ? WHERE nip = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, data);
            statement.setInt(2, nip);
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public ArrayList<DosenEntity> getListDosen(){
        ArrayList<DosenEntity> dosenEntities = new ArrayList<>();
        try {
            sql = "SELECT * FROM dosen_pembimbing";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                DosenEntity dosen = new DosenEntity(
                        resultSet.getInt("nip"),
                        resultSet.getString("nama_dosen"),
                        resultSet.getString("email_dosen"),
                        resultSet.getInt("notelp_dosen")
                );
                dosenEntities.add(dosen);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return dosenEntities;
    }
}
