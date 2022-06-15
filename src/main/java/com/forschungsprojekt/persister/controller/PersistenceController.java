package com.forschungsprojekt.persister.controller;

import com.forschungsprojekt.persister.data.ImageInformation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
* todo
*  - festen Ablageort
* - docker
* */
@RestController
@RequestMapping("persist")
public class PersistenceController {

    private static final String RESULT = "result";
    private static final String SEPERATOR = "_";
    private static final String DOT = ".";
    private static final String DATE_PATTERN = "dd-MM-yyyy_HH:mm:ss";

    /**
     * is called when a new image is sent for evaluation
     * persists image and result
     */
    @PostMapping("/imageandresult")
    public void persistImage(@RequestBody ImageInformation imageInformation) throws IOException {
        createImageFile(imageInformation);
        createResultFile(imageInformation);
    }

    private void createResultFile(ImageInformation imageInformation) throws IOException {
        FileWriter file = new FileWriter(imageInformation.name
                .concat(SEPERATOR)
                .concat(convertTime(imageInformation.time))
                .concat(SEPERATOR)
                .concat(RESULT));
        file.write(imageInformation.result.toString());
        file.close();
    }

    private void createImageFile(ImageInformation imageInformation) throws IOException {
        byte[] bytes = DatatypeConverter.parseBase64Binary(imageInformation.image);
        BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(bytes));
        File outputfile = new File(imageInformation.name
                .concat(SEPERATOR)
                .concat(convertTime(imageInformation.time))
                .concat(DOT)
                .concat(imageInformation.extension));
        ImageIO.write(bufferedImage, imageInformation.extension, outputfile);
    }

    public String convertTime(long time) {
        Date date = new Date(time);
        Format format = new SimpleDateFormat(DATE_PATTERN);
        return format.format(date);
    }

    @PostMapping("/result")
    public void persistResult(@RequestBody ImageInformation imageInformation) throws IOException {
        createResultFile(imageInformation);
    }
}
