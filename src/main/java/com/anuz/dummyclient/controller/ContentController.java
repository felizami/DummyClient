/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anuz.dummyclient.controller;

import com.anuz.dummyclient.constants.CONSTANT;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author anuz
 */
@Controller
@RequestMapping("/contents")
public class ContentController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "files/{directory}/{fileName}", method = RequestMethod.GET)
    public void image(@PathVariable("directory") String directory, @PathVariable("fileName") String fileName,@RequestHeader HttpHeaders headers,  HttpServletResponse response) {
        try {
            logger.info(fileName);
            
            
            File file = new File(CONSTANT.CONTENTS + directory + "/" + fileName);
            Files.copy(file.toPath(), response.getOutputStream());
            
        } catch (IOException ex) {
            logger.info("Image not found");
            logger.debug(ex.getMessage());
        }
    }

//    @RequestMapping(value = "/unzip",method = RequestMethod.POST,consumes = "application/zip")
////    @RequestParam(value = "zipFile", required = false) MultipartFile zipFile
//    public ResponseEntity<Void> unzipContents(@RequestParam(value = "zipFile", required = false) MultipartFile zipFile,@ModelAttribute ModelMap map){
//            System.out.println(map.get("zipFile"));
//            System.out.println(zipFile);
//        return ResponseEntity.ok().build();
//        
//    }
//    @RequestMapping(value = "/unzip",method = RequestMethod.POST)
//    public ResponseEntity<Void> unzipContents(@RequestBody ModelMap map){
//        System.out.println("Test");
////            System.out.println(map.get("files"));
//
////            ZipFile zf=new ZipFile()
//            JSONObject json= new JSONObject(map);
//            unZipIt(new ByteArrayInputStream(json.toString().getBytes()),"/home/anuz/javaworkspace/DummyClient/contents/newContents"+(new Date()).getTime());
//                      
//            
////            System.out.println(zipFile);
//        return ResponseEntity.ok().build();
//        
//    }
//    private void unZipIt(InputStream zipFile, String location) {
//         byte[] buffer = new byte[1024];
//
//     try{
//
//    	//create output directory is not exists
//    	File folder = new File(location);
//    	if(!folder.exists()){
//    		folder.mkdir();
//    	}
//
//    	//get the zip file content
//        logger.info("test");
//    	ZipInputStream zis =
//    		new ZipInputStream(zipFile);
//    	//get the zipped file list entry
//    	ZipEntry ze = zis.getNextEntry();
//        logger.info(ze+"");
//    	while(ze!=null){
//
//    	   String fileName = ze.getName();
//           File newFile = new File(location + File.separator + fileName);
//
//           System.out.println("file unzip : "+ newFile.getAbsoluteFile());
//
//            //create all non exists folders
//            //else you will hit FileNotFoundException for compressed folder
//            new File(newFile.getParent()).mkdirs();
//
//            FileOutputStream fos = new FileOutputStream(newFile);
//
//            int len;
//            while ((len = zis.read(buffer)) > 0) {
//       		fos.write(buffer, 0, len);
//            }
//
//            fos.close();
//            ze = zis.getNextEntry();
//    	}
//
//        zis.closeEntry();
//    	zis.close();
//
//    	System.out.println("Done");
//
//    }catch(IOException ex){
//        logger.info(ex.getMessage());
//    }
//    }
}
