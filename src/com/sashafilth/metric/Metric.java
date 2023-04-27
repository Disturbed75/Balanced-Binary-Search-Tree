package com.sashafilth.metric;

public class Metric implements Comparable<Metric> {

    private Integer data;
    private int length;
    private long insert;
    private long delete;
    private long find;

    public Metric() {
    }

    public Metric(int length, Integer data) {
        this.length = length;
        this.data = data;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public long getInsert() {
        return insert;
    }

    public void setInsert(long insert) {
        this.insert = insert;
    }

    public long getDelete() {
        return delete;
    }

    public void setDelete(long delete) {
        this.delete = delete;
    }

    public long getFind() {
        return find;
    }

    public void setFind(long find) {
        this.find = find;
    }

    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Metric{" +
                "length=" + length +
                ", insert=" + insert +
                ", delete=" + delete +
                ", find=" + find +
                '}';
    }

    @Override
    public int compareTo(Metric metric) {
        return this.length - metric.length;
    }
}
