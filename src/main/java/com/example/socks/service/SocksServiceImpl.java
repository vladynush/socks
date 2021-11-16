package com.example.socks.service;

import com.example.socks.entity.SocksEntity;
import com.example.socks.exception.InvalidDataException;
import com.example.socks.exception.NotEnoughSocksException;
import com.example.socks.exception.SocksNotFoundException;
import com.example.socks.model.SocksModel;
import com.example.socks.repository.SocksRepository;
import com.example.socks.util.SocksValidatorUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
public class SocksServiceImpl implements SocksService {

    private final SocksRepository socksRepository;

    @Autowired
    public SocksServiceImpl(SocksRepository socksRepository) {
        this.socksRepository = socksRepository;
    }


    @Override
    @Transactional
    public void addSocks(SocksModel socks) {
        if (!SocksValidatorUtil.validateSocksModel(socks)) {
            throw new InvalidDataException();
        }

        SocksEntity socksInWarehouse = socksRepository.findAllByColorAndCottonPart(socks.getColor(), socks.getCottonPart());
        if (socksInWarehouse != null) {
            socksInWarehouse.setQuantity(socksInWarehouse.getQuantity() + socks.getQuantity());
            socksRepository.save(socksInWarehouse);
        } else {
            socksRepository.save(new SocksEntity(socks));
        }
    }

    @Override
    @Transactional
    public void removeSocks(SocksModel socks) {
        if (!SocksValidatorUtil.validateSocksModel(socks)) {
            throw new InvalidDataException();
        }

        SocksEntity socksInWarehouse = socksRepository.findAllByColorAndCottonPart(socks.getColor(), socks.getCottonPart());
        if (socksInWarehouse == null) {
            throw new SocksNotFoundException();
        }

        if (socksInWarehouse.getQuantity() < socks.getQuantity()) {
            throw new NotEnoughSocksException();
        }

        socksInWarehouse.setQuantity(socksInWarehouse.getQuantity() - socks.getQuantity());
        if (socksInWarehouse.getQuantity() == 0) {
            socksRepository.delete(socksInWarehouse);
        } else {
            socksRepository.save(socksInWarehouse);
        }
    }

    @Override
    public Integer getNumberOfSocks(String color, String operation, Integer cottonPart) {
        if (!SocksValidatorUtil.validateColor(color) && !SocksValidatorUtil.validateCottonPart(cottonPart)) {
            throw new InvalidDataException();
        }
        Integer result;

        if ("moreThan".equals(operation)) {
            result = socksRepository.sumOfSocksByColorAndCottonPartMoreThan(color, cottonPart);
        } else if ("lessThan".equals(operation)) {
            result = socksRepository.sumOfSocksByColorAndCottonPartLessThan(color, cottonPart);

        } else if ("equal".equals(operation)) {
            result = socksRepository.sumOfSocksByColorAndCottonPartEqual(color, cottonPart);
        } else {
            throw new InvalidDataException();
        }
        return Objects.isNull(result) ? 0 : result;

    }
}
