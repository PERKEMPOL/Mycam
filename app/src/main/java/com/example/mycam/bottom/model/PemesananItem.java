package com.example.mycam.bottom.model;


import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class PemesananItem {
    @SerializedName("id_pemesanan")
    @Expose
    private String idPemesanan;
    @SerializedName("nama_pemesan")
    @Expose
    private String namaPemesan;
    @SerializedName("id_kamera")
    @Expose
    private String idKamera;
    @SerializedName("nomor_pemesan")
    @Expose
    private String nomorPemesan;
    @SerializedName("alamat_pemesan")
    @Expose
    private String alamatPemesan;
    @SerializedName("email_pemesan")
    @Expose
    private String emailPemesan;
    @SerializedName("status_pemesanan")
    @Expose
    private String statusPemesanan;
    @SerializedName("name_kamera")
    @Expose
    private String nameKamera;
    @SerializedName("harga_kamera")
    @Expose
    private String hargaKamera;

    public String getIdPemesanan() {
        return idPemesanan;
    }

    public void setIdPemesanan(String idPemesanan) {
        this.idPemesanan = idPemesanan;
    }

    public String getNamaPemesan() {
        return namaPemesan;
    }

    public void setNamaPemesan(String namaPemesan) {
        this.namaPemesan = namaPemesan;
    }

    public String getIdKamera() {
        return idKamera;
    }

    public void setIdKamera(String idKamera) {
        this.idKamera = idKamera;
    }

    public String getNomorPemesan() {
        return nomorPemesan;
    }

    public void setNomorPemesan(String nomorPemesan) {
        this.nomorPemesan = nomorPemesan;
    }

    public String getAlamatPemesan() {
        return alamatPemesan;
    }

    public void setAlamatPemesan(String alamatPemesan) {
        this.alamatPemesan = alamatPemesan;
    }

    public String getEmailPemesan() {
        return emailPemesan;
    }

    public void setEmailPemesan(String emailPemesan) {
        this.emailPemesan = emailPemesan;
    }

    public String getStatusPemesanan() {
        return statusPemesanan;
    }

    public void setStatusPemesanan(String statusPemesanan) {
        this.statusPemesanan = statusPemesanan;
    }

    public String getNameKamera() {
        return nameKamera;
    }

    public void setNameKamera(String nameKamera) {
        this.nameKamera = nameKamera;
    }

    public String getHargaKamera() {
        return hargaKamera;
    }

    public void setHargaKamera(String hargaKamera) {
        this.hargaKamera = hargaKamera;
    }

}