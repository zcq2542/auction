package com.cs6650.Auctionservice.service;

import com.cs6650.Auctionservice.dto.AuctionBidRequest;
import com.cs6650.Auctionservice.dto.AuctionItemRequest;
import com.cs6650.Auctionservice.model.AuctionItem;
import com.cs6650.Auctionservice.repository.AuctionItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuctionService {
    private final AuctionItemRepository auctionItemRepository;

    @Autowired
    private RedisTemplate<String, AuctionBidRequest> redisTemplate;


    @Cacheable(value = "myCache", key = "#auctionBidRequest.id")
    public Boolean bid(AuctionBidRequest auctionBidRequest) {
        Optional<AuctionItem> auctionItem = auctionItemRepository.findById(auctionBidRequest.getId());
        if(auctionItem.isPresent()) {
            AuctionItem auctionItem1 = auctionItem.get();
            BigDecimal bidPrice = auctionBidRequest.getBidPrice();
            if(bidPrice.compareTo(auctionItem1.getPrice()) > 0) {
                auctionItem1.setPrice(bidPrice);
                auctionItemRepository.save(auctionItem1);
                return true;
            }
        }
        return false;
    }

    @CachePut(value = "myCache", key = "#auctionItemRequest.id")
    public void startAuction(AuctionItemRequest auctionItemRequest){
        AuctionItem auctionItem = AuctionItem.builder()
                .id(auctionItemRequest.getId())
                .name(auctionItemRequest.getName())
                .price(auctionItemRequest.getStartPrice())
                .build();
        auctionItemRepository.save(auctionItem);
    }
}
