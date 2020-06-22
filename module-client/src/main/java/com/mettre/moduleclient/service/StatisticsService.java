package com.mettre.moduleclient.service;

import com.mettre.moduleclient.mapper.StatisticsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticsService {

    @Autowired
    public StatisticsMapper statisticsMapper;


}
