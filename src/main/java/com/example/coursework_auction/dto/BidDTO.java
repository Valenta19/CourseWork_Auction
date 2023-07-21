package com.example.coursework_auction.dto;


import com.example.coursework_auction.model.Bid;

import java.time.LocalDateTime;

public class BidDTO {
    String bidName;
    LocalDateTime bidData;

    public static BidDTO fromBid(Bid bid) {
        BidDTO bidDTO = new BidDTO();
        bidDTO.setBidName(bid.getBidName());
        bidDTO.setBidData(bid.getBidData());
        return bidDTO;
    }

    public Bid fromBidDTO() {
        Bid bid = new Bid();
        bid.setBidName(this.bidName);
        bid.setBidData(this.bidData);
        return bid;

    }

    public BidDTO() {
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


}

