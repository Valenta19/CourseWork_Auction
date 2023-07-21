package com.example.coursework_auction.service;

import com.example.coursework_auction.dto.BidDTO;
import com.example.coursework_auction.dto.CreateLotDTO;
import com.example.coursework_auction.dto.FullLotDTO;
import com.example.coursework_auction.model.Bid;
import com.example.coursework_auction.model.Lot;
import com.example.coursework_auction.model.Status;
import com.example.coursework_auction.repository.BidRepository;
import com.example.coursework_auction.repository.LotRepository;
import com.opencsv.CSVWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class LotServiceImpl implements LotService {
    private final LotRepository lotRepository;
    private final BidRepository bidRepository;

    private static final Logger logger = LoggerFactory.getLogger(LotService.class);

    public LotServiceImpl(LotRepository lotRepository, BidRepository bidRepository) {
        this.lotRepository = lotRepository;
        this.bidRepository = bidRepository;
    }

    @Override
    public Optional<BidDTO> getFirstBid(int id) {
        logger.debug("firs bid with id= " + id);
        return Optional.of(
                BidDTO.fromBid(bidRepository.getFirstBid(id)));
    }

    @Override
    public Optional<BidDTO> getMostFrequent(int id) {
        logger.debug("Most Frequent with id= " + id);
        return Optional.of(
                BidDTO.fromBid(bidRepository.getFrequent(id)));
    }

    @Override
    public Optional<FullLotDTO> getLotFullInfoById(int id) {

        FullLotDTO lotFullInfo = new FullLotDTO();
        Optional<Lot> lot = lotRepository.findById(id);
        lotFullInfo.setId(lot.get().getId());
        lotFullInfo.setStatus(lot.get().getStatus());
        lotFullInfo.setTitle(lot.get().getTitle());
        lotFullInfo.setDescription(lot.get().getDescription());
        lotFullInfo.setStartPrice(lot.get().getStartPrice());
        lotFullInfo.setBidPrice(lot.get().getBidPrice());
        lotFullInfo.setCurrentPrice(lot.get().getStartPrice() + (lotFullInfo.getBidPrice() * bidRepository.getNumberOfBids(id)));
        lotFullInfo.setLastBid(BidDTO.fromBid(bidRepository.getLastBid(id)));
        logger.debug(" full info lot with id= " + id);
        return Optional.of(lotFullInfo);
    }

    @Override
    public void startLot(int id) {
        Lot lot = new Lot();
        lot.setId(lotRepository.findById(id).get().getId());
        lot.setTitle(lotRepository.findById(id).get().getTitle());
        lot.setDescription(lotRepository.findById(id).get().getDescription());
        lot.setStartPrice(lotRepository.findById(id).get().getStartPrice());
        lot.setBidPrice(lotRepository.findById(id).get().getBidPrice());
        lot.setStatus(Status.STARTED);
        lotRepository.save(lot);
        logger.debug("start lot with id= " + id);
    }

    @Override
    public void stopBidding(int id) {
        Lot lot = new Lot();
        lot.setId(lotRepository.findById(id).get().getId());
        lot.setTitle(lotRepository.findById(id).get().getTitle());
        lot.setDescription(lotRepository.findById(id).get().getDescription());
        lot.setStartPrice(lotRepository.findById(id).get().getStartPrice());
        lot.setBidPrice(lotRepository.findById(id).get().getBidPrice());
        lot.setStatus(Status.STOPPED);
        lotRepository.save(lot);
        logger.debug("stop bidding with id= " + id);
    }

    @Override
    public void createBid(Bid bid, Integer id) {
        Lot lot = new Lot();
        Optional<Lot> lotWithStatus = lotRepository.findById(id);
        if (!Objects.equals(Status.STARTED, lotWithStatus.get().getStatus())) {
            throw new RuntimeException("Incorrect lot status");
        } else {
            lot.setId(id);
            bid.setLot(lot);
            bidRepository.save(bid);
        }
        logger.debug("create bid" + bid);
    }

    @Override
    public void createLot(Lot lot) {
        lotRepository.save(lot);
        logger.debug("create lot" + lot);
    }


    @Override
    public List<Lot> findLots(int status, int page) {
        PageRequest.of(page, 10);
        logger.debug("find lots where status= " + status + "page= " + page);
        return lotRepository.findByStatus(PageRequest.of(page, 10), status);
    }

    @Override
    public List<Lot> getLotCSV() {
        List<Lot> list;
            list = lotRepository.findAll();
            return list;
    }
}
