package com.example.mycam.bottom.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class CategoriItem {
    @SerializedName("id_merk")
    @Expose
    private String idMerk;
    @SerializedName("name_merk")
    @Expose
    private String nameMerk;
    @SerializedName("id_file")
    @Expose
    private String idFile;
    @SerializedName("nama_file")
    @Expose
    private String namaFile;

    public String getIdMerk() {
        return idMerk;
    }

    public void setIdMerk(String idMerk) {
        this.idMerk = idMerk;
    }

    public String getNameMerk() {
        return nameMerk;
    }

    public void setNameMerk(String nameMerk) {
        this.nameMerk = nameMerk;
    }

    public String getIdFile() {
        return idFile;
    }

    public void setIdFile(String idFile) {
        this.idFile = idFile;
    }

    public String getNamaFile() {
        return namaFile;
    }

    public void setNamaFile(String namaFile) {
        this.namaFile = namaFile;
    }
}
