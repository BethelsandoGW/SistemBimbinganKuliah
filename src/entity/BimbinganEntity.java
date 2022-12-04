package entity;

import java.time.LocalDateTime;

public class BimbinganEntity {
    private int id_bimbingan;
    private String nama_dosen;
    private String nama_mahasiswa;
    private String judul;
    LocalDateTime waktu_bimbingan;

    public BimbinganEntity(int id_bimbingan, String nama_dosen, String nama_mahasiswa, String judul, LocalDateTime waktu_bimbingan) {
        this.id_bimbingan = id_bimbingan;
        this.nama_dosen = nama_dosen;
        this.nama_mahasiswa = nama_mahasiswa;
        this.judul = judul;
        this.waktu_bimbingan = waktu_bimbingan;
    }

    public BimbinganEntity(String nama_dosen, String nama_mahasiswa, String judul, LocalDateTime waktu_bimbingan) {
        this.nama_dosen = nama_dosen;
        this.nama_mahasiswa = nama_mahasiswa;
        this.judul = judul;
        this.waktu_bimbingan = waktu_bimbingan;
    }

    public BimbinganEntity() {
    }

    public int getId_bimbingan() {
        return id_bimbingan;
    }

    public void setId_bimbingan(int id_bimbingan) {
        this.id_bimbingan = id_bimbingan;
    }

    public String getNama_dosen() {
        return nama_dosen;
    }

    public void setNama_dosen(String nama_dosen) {
        this.nama_dosen = nama_dosen;
    }

    public String getNama_mahasiswa() {
        return nama_mahasiswa;
    }

    public void setNama_mahasiswa(String nama_mahasiswa) {
        this.nama_mahasiswa = nama_mahasiswa;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public LocalDateTime getWaktu_bimbingan() {
        return waktu_bimbingan;
    }

    public void setWaktu_bimbingan(LocalDateTime waktu_bimbingan) {
        this.waktu_bimbingan = waktu_bimbingan;
    }
}
