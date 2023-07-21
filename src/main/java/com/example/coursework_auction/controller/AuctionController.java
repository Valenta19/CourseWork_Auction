package com.example.coursework_auction.controller;

import com.example.coursework_auction.dto.BidDTO;
import com.example.coursework_auction.dto.FullLotDTO;
import com.example.coursework_auction.dto.LotDTO;
import com.example.coursework_auction.model.Lot;
import com.example.coursework_auction.service.LotService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
    public ResponseEntity<Resource> getCSVFile() throws IOException {

        String name = "lots.csv";

        List<Lot> list = lotService.getLotCSV();

        BufferedWriter writer = Files.newBufferedWriter(Paths.get(name));
        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                .withHeader("id", "title", "status", "description", "startPrice", "bidPrice"));

        list.forEach(lot -> {
            try {
                csvPrinter.printRecord(lot.getId(), lot.getTitle(), lot.getStatus(), lot.getDescription(), lot.getStartPrice(), lot.getBidPrice());
                csvPrinter.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });

        Resource resource = new PathResource(name);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + name + "\"")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(resource);
    }
}
