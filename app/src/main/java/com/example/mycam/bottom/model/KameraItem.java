package com.example.mycam.bottom.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

    public class KameraItem {

        @SerializedName("id_kamera")
        @Expose
        private String idKamera;
        @SerializedName("name_kamera")
        @Expose
        private String nameKamera;
        @SerializedName("merk_kamera")
        @Expose
        private String merkKamera;
        @SerializedName("spek_kamera")
        @Expose
        private String spekKamera;
        @SerializedName("harga_kamera")
        @Expose
        private String hargaKamera;
        @SerializedName("id_file")
        @Expose
        private String idFile;
        @SerializedName("nama_file")
        @Expose
        private String namaFile;

        public String getIdKamera() {
            return idKamera;
        }

        public void setIdKamera(String idKamera) {
            this.idKamera = idKamera;
        }

        public String getNameKamera() {
            return nameKamera;
        }

        public void setNameKamera(String nameKamera) {
            this.nameKamera = nameKamera;
        }

        public String getMerkKamera() {
            return merkKamera;
        }

        public void setMerkKamera(String merkKamera) {
            this.merkKamera = merkKamera;
        }

        public String getSpekKamera() {
            return spekKamera;
        }

        public void setSpekKamera(String spekKamera) {
            this.spekKamera = spekKamera;
        }

        public String getHargaKamera() {
            return hargaKamera;
        }

        public void setHargaKamera(String hargaKamera) {
            this.hargaKamera = hargaKamera;
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

