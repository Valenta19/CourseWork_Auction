package com.example.coursework_auction.repository;

import com.example.coursework_auction.model.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BidRepository extends JpaRepository<Bid, Integer> {
    @Query(value = "SELECT * FROM bid WHERE lot_id = :id " +
            "LIMIT 1",
            nativeQuery = true)
    Bid getFirstBid(int id);

    @Query(value = "SELECT * FROM bid WHERE lot_id = :id " +
            "GROUP BY bid.id " +
            "ORDER BY count(bidName),bidName desc " +
            "LIMIT 1",
            nativeQuery = true)
    Bid getFrequent(int id);

    @Query(value = "select * from bid WHERE lot_id = :id " +
            "order by bidData desc " +
            "limit 1 ",
            nativeQuery = true)
    Bid getLastBid(int id);

    @Query(value = "select count(lot_id) from bid where lot_id = :id",
            nativeQuery = true)
    Integer getNumberOfBids(int id);
}

