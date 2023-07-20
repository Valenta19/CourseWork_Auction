package com.example.coursework_auction.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "bid")
public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "bid_name")
    private String bidName;
    @Column(name = "bid_data")
    private LocalDateTime bidData;
    @ManyToOne
    private Lot lot;

    public Bid(String bidName, LocalDateTime bidData, Lot lot) {
        this.bidName = bidName;
        this.bidData = bidData;
        this.lot = lot;
    }

    public Bid() {

    }

    public Lot getLot() {
        return lot;
    }

    public void setLot(Lot lot) {
        this.lot = lot;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBidName() {
        return bidName;
    }

    public void setBidName(String bidName) {
        this.bidName = bidName;
    }

    public LocalDateTime getBidData() {
        return bidData;
    }

    public void setBidData(LocalDateTime bidData) {
        this.bidData = bidData;
    }

    @Override
    public String toString() {
        return "Bid{" +
                "id=" + id +
                ", bidName='" + bidName + '\'' +
                ", bidData=" + bidData +
                '}';
    }
}