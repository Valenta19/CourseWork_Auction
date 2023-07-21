package com.example.coursework_auction.service;

import com.example.coursework_auction.dto.BidDTO;
import com.example.coursework_auction.dto.FullLotDTO;
import com.example.coursework_auction.model.Bid;
import com.example.coursework_auction.model.Lot;

import java.util.List;
import java.util.Optional;

public interface LotService {
    Optional<BidDTO> getFirstBid(int id);

    Optional<BidDTO> getMostFrequent(int id);

    void startLot(int id);

    void stopBidding(int id);

    void createBid(Bid bid, Integer id);

    Optional<FullLotDTO> getLotFullInfoById(int id);

    void createLot(Lot lot);

    List<Lot> findLots(int page, int status);

    List<Lot> getLotCSV();
}
