package com.example.socks.entity;

import com.example.socks.model.SocksModel;
import lombok.Data;

import lombok.RequiredArgsConstructor;


import javax.persistence.*;

@Entity
@Data
@RequiredArgsConstructor

@Table(name = "socks")
public class SocksEntity {
    public SocksEntity(SocksModel socks) {
        this.color = socks.getColor();
        this.cottonPart = socks.getCottonPart();
        this.quantity = socks.getQuantity();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "color", nullable = false)
    private String color;

    @Column(name = "cotton_part", nullable = false)
    private Integer cottonPart;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;
}
