package com.gusman.uas.biodata.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.gusman.uas.biodata.utilities.Constants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BioData implements Parcelable {
    private int id;
    private String nama, alamat, nomorHp, email, tanggalLahir, jenisKelamin;

    public BioData() {
        this.nama = "";
        this.alamat = "";
        this.nomorHp = "";
        this.email = "";
        this.tanggalLahir = "";
        this.jenisKelamin = Constants.GENDER_L;
    }

    public BioData(int id, String nama, String alamat, String nomorHp, String email, String tanggalLahir, String jenisKelamin) {
        this.id = id;
        this.nama = nama;
        this.alamat = alamat;
        this.nomorHp = nomorHp;
        this.email = email;
        this.tanggalLahir = tanggalLahir;
        this.jenisKelamin = jenisKelamin;
    }

    public BioData(String nama, String alamat, String nomorHp, String email, String tanggalLahir, String jenisKelamin) {
        this.nama = nama;
        this.alamat = alamat;
        this.nomorHp = nomorHp;
        this.email = email;
        this.tanggalLahir = tanggalLahir;
        this.jenisKelamin = jenisKelamin;
    }

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getNomorHp() {
        return nomorHp;
    }

    public String getEmail() {
        return email;
    }

    public String getTanggalLahir() {
        return tanggalLahir;
    }

    public Date getTanggalLahirAsDate() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd MMMM yyyy", Constants.LOCALE_ID);
        try {
            date = format.parse(this.tanggalLahir);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public void setNomorHp(String nomorHp) {
        this.nomorHp = nomorHp;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTanggalLahir(String tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.nama);
        dest.writeString(this.alamat);
        dest.writeString(this.nomorHp);
        dest.writeString(this.email);
        dest.writeString(this.tanggalLahir);
        dest.writeString(this.jenisKelamin);
    }

    public void readFromParcel(Parcel source) {
        this.id = source.readInt();
        this.nama = source.readString();
        this.alamat = source.readString();
        this.nomorHp = source.readString();
        this.email = source.readString();
        this.tanggalLahir = source.readString();
        this.jenisKelamin = source.readString();
    }

    protected BioData(Parcel in) {
        this.id = in.readInt();
        this.nama = in.readString();
        this.alamat = in.readString();
        this.nomorHp = in.readString();
        this.email = in.readString();
        this.tanggalLahir = in.readString();
        this.jenisKelamin = in.readString();
    }

    public static final Parcelable.Creator<BioData> CREATOR = new Parcelable.Creator<BioData>() {
        @Override
        public BioData createFromParcel(Parcel source) {
            return new BioData(source);
        }

        @Override
        public BioData[] newArray(int size) {
            return new BioData[size];
        }
    };
}
