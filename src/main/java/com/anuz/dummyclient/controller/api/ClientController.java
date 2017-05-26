/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anuz.dummyclient.controller.api;

import com.anuz.dummyclient.controller.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author anuz
 */

@RestController
@RequestMapping(value = "/client")
public class ClientController {
    
    @Autowired
    ClientService clientService;
    
    @RequestMapping(value = "/html_update/{clientId}",method = RequestMethod.GET)
    public ResponseEntity getHTMLUpdates(@PathVariable("clientId") int clientId){
        System.out.println("Check");
        String url="http://192.168.0.112:8080/DummyAPI/api/v1/users/"+clientId+"/html/latest";
        ModelMap map=new ModelMap();
        map.addAttribute("updates", clientService.htmtUpdates(url));
        return new ResponseEntity(map, HttpStatus.OK);
        
    }
}
