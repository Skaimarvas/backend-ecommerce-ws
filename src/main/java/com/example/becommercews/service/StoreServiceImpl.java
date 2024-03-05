package com.example.becommercews.service;

import com.example.becommercews.entity.Store;
import com.example.becommercews.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreServiceImpl implements StoreService{

    private final StoreRepository storeRepository;

    @Autowired
    public StoreServiceImpl(StoreRepository storeRepository){
        this.storeRepository = storeRepository;
    }
    @Override
    public Store save(Store store) {
        return storeRepository.save(store);
    }
}
