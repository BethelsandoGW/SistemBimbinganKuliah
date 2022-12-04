package entity;

public class MahasiswaEntity {
    private int npm;
    private String nama;
    private String email;
    private int noTelpon;

    public MahasiswaEntity() {
    }

    public MahasiswaEntity(int npm, String nama, String email, int noTelpon) {
        this.npm = npm;
        this.nama = nama;
        this.email = email;
        this.noTelpon = noTelpon;
    }

    public MahasiswaEntity(String nama, String email, int noTelpon) {
        this.nama = nama;
        this.email = email;
        this.noTelpon = noTelpon;
    }

    public int getNpm() {
        return npm;
    }

    public void setNpm(int npm) {
        this.npm = npm;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNoTelpon() {
        return noTelpon;
    }

    public void setNoTelpon(int noTelpon) {
        this.noTelpon = noTelpon;
    }
}
