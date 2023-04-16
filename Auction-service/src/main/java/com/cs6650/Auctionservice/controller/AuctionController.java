package com.cs6650.Auctionservice.controller;

import com.cs6650.Auctionservice.dto.AuctionBidRequest;
import com.cs6650.Auctionservice.dto.AuctionItemRequest;
import com.cs6650.Auctionservice.service.AuctionService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auction")
@AllArgsConstructor
public class AuctionController {

    private final AuctionService auctionService;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Boolean bid(@RequestBody AuctionBidRequest auctionBidRequest) {
        return auctionService.bid(auctionBidRequest);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void startAuction(@RequestBody AuctionItemRequest auctionItemRequest) {
        auctionService.startAuction(auctionItemRequest);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void startAuctionById(@RequestBody AuctionItemRequest auctionItemRequest) {
        auctionService.startAuction(auctionItemRequest);
    }
}
