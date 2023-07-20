package com.example.coursework_auction.dto;

import com.example.coursework_auction.model.Lot;
import com.example.coursework_auction.model.Status;

public class CreateLotDTO {
    private String title;
    private String description;
    private Integer startPrice;
    private Integer bidPrice;

    public CreateLotDTO() {
    }

    public static CreateLotDTO fromLot(Lot lot) {

        CreateLotDTO createLotDTO = new CreateLotDTO();

        createLotDTO.setTitle(lot.getTitle());
        createLotDTO.setDescription(lot.getDescription());
        createLotDTO.setStartPrice(lot.getStartPrice());
        createLotDTO.setBidPrice(lot.getBidPrice());

        return createLotDTO;
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
