package com.example.socks.repository;


import com.example.socks.entity.SocksEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SocksRepository extends CrudRepository<SocksEntity, Long> {

    SocksEntity findAllByColorAndCottonPart(String color, Integer cottonPart);
    
    @Query("SELECT SUM(socks.quantity) FROM SocksEntity socks WHERE socks.color = :color AND socks.cottonPart > :cottonPart")
    Integer sumOfSocksByColorAndCottonPartMoreThan(String color, Integer cottonPart);
    
    @Query("SELECT SUM(socks.quantity) FROM SocksEntity socks WHERE socks.color = :color AND socks.cottonPart < :cottonPart")
    Integer sumOfSocksByColorAndCottonPartLessThan(String color, Integer cottonPart);
    
    @Query("SELECT socks.quantity FROM SocksEntity socks WHERE socks.color = :color AND socks.cottonPart = :cottonPart")
    Integer sumOfSocksByColorAndCottonPartEqual(String color, Integer cottonPart);
}
