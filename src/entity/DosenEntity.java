package entity;

public class DosenEntity {
    private int nip;
    private String nama;
    private String email;
    private int noTelpon;

    public DosenEntity() {
    }

    public DosenEntity(int nip, String nama, String email, int noTelpon) {
        this.nip = nip;
        this.nama = nama;
        this.email = email;
        this.noTelpon = noTelpon;
    }

    public DosenEntity(String nama, String email, int noTelpon) {
        this.nama = nama;
        this.email = email;
        this.noTelpon = noTelpon;
    }

    public int getNip() {
        return nip;
    }

    public void setNip(int nip) {
        this.nip = nip;
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
