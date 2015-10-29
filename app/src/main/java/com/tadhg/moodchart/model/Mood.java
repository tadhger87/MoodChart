package com.tadhg.moodchart.model;

/**
 * Created by Tadhg on 06/10/2015.
 */
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;



import java.text.SimpleDateFormat;
import java.util.Locale;

public class Mood implements Parcelable, Comparable<Mood> {

    private static final SimpleDateFormat formatter = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);


    private int id;
    private String date;
    private int great;
    private int good;
    private int average;
    private int bad;
    private int terrible;



    public Mood() {
        super();
    }

    public Mood(Parcel in) {
        super();
        this.id = in.readInt();
        this.date = in.readString();
        this.great = in.readInt();//new Date(in.readLong());
        this.good = in.readInt();
        this.average = in.readInt();
        this.bad = in.readInt();
        this.terrible = in.readInt();


    }

    public Mood(String development) {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;

    }

    public int getGreat() {
        return great;
    }

    public void setGreat(int great) {
        this.great = great;
    }

    public int getGood() {
        return good;
    }

    public void setGood(int good) {
        this.good = good;
    }

    public int getAverage() {
        return average;
    }

    public void setAverage(int average) {
        this.average = average;
    }

    public int getBad() {
        return bad;
    }

    public void setBad(int bad) {
        this.bad = bad;
    }

    public int getTerrible() {
        return terrible;
    }

    public void setTerrible(int terrible) {
        this.terrible = terrible;
    }


    @Override
    public String toString() {
        return "Mood [id=" + id + ", date=" + date + ", great="
                + great + ", good=" + good + ", average=" + average + ", bad="
                + bad + ", terrible="
                + terrible +"]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Mood other = (Mood) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public int describeContents() {
        return 1;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(getId());
        parcel.writeString(getDate());
        parcel.writeInt(getGreat());
        parcel.writeInt(getGood());
        parcel.writeInt(getAverage());
        parcel.writeInt(getBad());
        parcel.writeInt(getTerrible());
       // parcel.writeParcelable(getLatlngitem(), flags);
    }

    public static final Parcelable.Creator<Mood> CREATOR = new Parcelable.Creator<Mood>() {
        public Mood createFromParcel(Parcel in) {
            return new Mood(in);
        }

        public Mood[] newArray(int size) {
            return new Mood[size];
        }
    };

    @Override
    public int compareTo(@NonNull Mood another) {
        return getDate().compareTo(another.getDate());
    }
}
