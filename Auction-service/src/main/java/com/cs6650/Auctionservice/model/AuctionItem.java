package com.cs6650.Auctionservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "t_items")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuctionItem {
    @Id
    private String id;
    private String name;
    private BigDecimal price;
}
