package com.cs6650.Auctionservice.repository;

import com.cs6650.Auctionservice.model.AuctionItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuctionItemRepository extends JpaRepository<AuctionItem, Long> {
    Optional<AuctionItem> findById(Long id);
}
