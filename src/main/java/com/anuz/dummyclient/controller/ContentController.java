/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anuz.dummyclient.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.xml.ws.Response;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author anuz
 */
@Controller
@RequestMapping("/contents")
public class ContentController {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
//    @RequestMapping(value = "/unzip",method = RequestMethod.POST,consumes = "application/zip")
////    @RequestParam(value = "zipFile", required = false) MultipartFile zipFile
//    public ResponseEntity<Void> unzipContents(@RequestParam(value = "zipFile", required = false) MultipartFile zipFile,@ModelAttribute ModelMap map){
//            System.out.println(map.get("zipFile"));
//            System.out.println(zipFile);
//        return ResponseEntity.ok().build();
//        
//    }
    
    @RequestMapping(value = "/unzip",method = RequestMethod.POST)
    public ResponseEntity<Void> unzipContents(@RequestBody ModelMap map){
        System.out.println("Test");
//            System.out.println(map.get("files"));
            JSONObject json= new JSONObject(map);
            unZipIt(new ByteArrayInputStream(json.toString().getBytes()),"/home/anuz/javaworkspace/DummyClient/contents/newContents"+(new Date()).getTime());
                      
            
//            System.out.println(zipFile);
        return ResponseEntity.ok().build();
        
    }

    private void unZipIt(InputStream zipFile, String location) {
         byte[] buffer = new byte[1024];

     try{

    	//create output directory is not exists
    	File folder = new File(location);
    	if(!folder.exists()){
    		folder.mkdir();
    	}

    	//get the zip file content
    	ZipInputStream zis =
    		new ZipInputStream(zipFile);
    	//get the zipped file list entry
    	ZipEntry ze = zis.getNextEntry();
        logger.info(ze+"");
    	while(ze!=null){

    	   String fileName = ze.getName();
           File newFile = new File(location + File.separator + fileName);

           System.out.println("file unzip : "+ newFile.getAbsoluteFile());

            //create all non exists folders
            //else you will hit FileNotFoundException for compressed folder
            new File(newFile.getParent()).mkdirs();

            FileOutputStream fos = new FileOutputStream(newFile);

            int len;
            while ((len = zis.read(buffer)) > 0) {
       		fos.write(buffer, 0, len);
            }

            fos.close();
            ze = zis.getNextEntry();
    	}

        zis.closeEntry();
    	zis.close();

    	System.out.println("Done");

    }catch(IOException ex){
        logger.info(ex.getMessage());
    }
    }
    
}
