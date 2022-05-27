
package jam;


public class jam {
    private int id;
    private String merek;
    private String warna;
    private String harga;
    private String tanggal;

    public jam(int id, String merek, String warna, String harga, String tanggal) {
        this.id = id;
        this.merek = merek;
        this.warna = warna;
        this.harga = harga;
        this.tanggal = tanggal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMerek() {
        return merek;
    }

    public void setMerek(String merek) {
        this.merek = merek;
    }

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
    
    
    
}
