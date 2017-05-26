/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anuz.dummyclient.controller.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author anuz
 */
@Service
public class ClientService {
    
    public ModelMap htmtUpdates(String url){
           
        RestTemplate restTemplate =new RestTemplate();
        ModelMap map=restTemplate.getForObject(url, ModelMap.class);
        return map;
    }
    
}
