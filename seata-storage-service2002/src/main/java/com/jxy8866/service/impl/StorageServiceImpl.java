package com.jxy8866.service.impl;

import com.jxy8866.dao.StorageDao;
import com.jxy8866.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class StorageServiceImpl implements StorageService {

    @Resource
    private StorageDao storageDao;

    @Override
    public int decrease(Long productId, Integer count) {
        return storageDao.decrease(productId, count);
    }
}
