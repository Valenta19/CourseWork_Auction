package com.example.coursework_auction.controller;

import com.example.coursework_auction.dto.BidDTO;
import com.example.coursework_auction.dto.FullLotDTO;
import com.example.coursework_auction.dto.LotDTO;
import com.example.coursework_auction.model.Lot;
import com.example.coursework_auction.service.LotService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("lot")
public class AuctionController {
private final LotService lotService;

    public AuctionController(LotService lotService) {
        this.lotService = lotService;
    }

    @GetMapping("/{id}/first")
    public Optional<BidDTO> getFirstBid(@PathVariable int id) {
        return lotService.getFirstBid(id);
    }

    @GetMapping("/{id}/frequent")
    public Optional<BidDTO> getMostFrequent(@PathVariable int id) {
        return lotService.getMostFrequent(id);
    }

    @GetMapping("/{id}")
    public Optional<FullLotDTO> getLotFullInfoById(@PathVariable int id) {
        return lotService.getLotFullInfoById(id);
    }

    @PostMapping("/{id}/start")
    public void startLot(@PathVariable int id) {
        lotService.startLot(id);
    }

    @PostMapping("/{id}/bid")
    public void createBid(@RequestBody BidDTO bid,
                          @PathVariable int id) {
        lotService.createBid(bid.fromBidDTO(), id);
    }

    @PostMapping("/{id}/stop")
    public void stopBidding(@PathVariable int id) {
        lotService.stopBidding(id);
    }
    @PostMapping("/lot")
    public void createLot(@RequestBody LotDTO lot) {
        lotService.createLot(lot.fromDTO());
    }
    @GetMapping("/lot")
    public List<Lot> findLots(@RequestParam(required = false,defaultValue = "0") int page,int status) {

        return lotService.findLots(page,status);
    }
    @GetMapping("/export")
    public String getCSVFile() {
        return lotService.getLotCSV();
    }
}
