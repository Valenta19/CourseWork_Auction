package com.example.coursework_auction.repository;

import com.example.coursework_auction.model.Lot;
import com.example.coursework_auction.model.Status;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LotRepository extends JpaRepository<Lot,Integer> {


    @Query(value = "select * from lot where status = :status",
            nativeQuery = true)
    List<Lot> findByStatus(PageRequest of, int status);
}
