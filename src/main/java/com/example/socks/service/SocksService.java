package com.example.socks.service;


import com.example.socks.model.SocksModel;

public interface SocksService {
    public void addSocks(SocksModel socks);
    
    public void removeSocks(SocksModel socks);
    
    public Integer getNumberOfSocks(String color, String operation, Integer cottonPart);
}
