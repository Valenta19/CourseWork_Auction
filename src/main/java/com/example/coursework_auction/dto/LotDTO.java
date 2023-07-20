package com.example.coursework_auction.dto;

import com.example.coursework_auction.model.Lot;
import com.example.coursework_auction.model.Status;

public class LotDTO {
    private Integer id;
    private Status status;
    private String title;
    private String description;
    private Integer startPrice;
    private Integer bidPrice;

    public LotDTO() {
    }
    public static LotDTO fromLot(Lot lot){
        LotDTO lotDTO = new LotDTO();
        lotDTO.setId(lot.getId());
        lotDTO.setStatus(lot.getStatus());
        lotDTO.setTitle(lot.getTitle());
        lotDTO.setDescription(lot.getDescription());
        lotDTO.setStartPrice(lot.getStartPrice());
        lotDTO.setBidPrice(lot.getBidPrice());
        return lotDTO;
    }
     public Lot fromDTO() {
         Lot lot = new Lot();
         lot.setTitle(this.getTitle());
         lot.setDescription(this.getDescription());
         lot.setStartPrice(this.getStartPrice());
         lot.setBidPrice(this.getBidPrice());
         lot.setStatus(Status.CREATED);

         return lot;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(Integer startPrice) {
        this.startPrice = startPrice;
    }

    public Integer getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(Integer bidPrice) {
        this.bidPrice = bidPrice;
    }


}
