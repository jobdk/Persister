package com.forschungsprojekt.persister.controller;

import com.forschungsprojekt.persister.data.ImageInformation;
import com.google.gson.Gson;
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

@RestController
@RequestMapping("persist")
public class PersistenceController {

    private static final String RESULT = "result";
    private static final String SEPARATOR = "_";
    private static final String DOT = ".";
    private static final String DATE_PATTERN = "dd-MM-yyyy_HH:mm:ss";
    private static final File DOCKER_PATH = new File("/var/tmp");
    private static final String RESULT_FILE_EXTENSION = ".txt";

    /**
     * is called when a new image is sent for evaluation
     * persists image and result
     */
    @PostMapping("/imageandresult")
    public void persistImage(@RequestBody ImageInformation imageInformation) throws IOException {
        createImageFile(imageInformation);
        createResultFile(imageInformation);
    }

    @PostMapping("/result")
    public void persistResult(@RequestBody ImageInformation imageInformation) throws IOException {
        createResultFile(imageInformation);
    }

    private void createResultFile(ImageInformation imageInformation) throws IOException {
        File resultFile = new File(DOCKER_PATH, imageInformation.name
                .concat(SEPARATOR)
                .concat(convertTime(imageInformation.time))
                .concat(SEPARATOR)
                .concat(RESULT)
                .concat(RESULT_FILE_EXTENSION));

        resultFile.createNewFile();

        FileWriter file = new FileWriter(resultFile);
        file.write(new Gson().toJson(imageInformation.result));
        file.close();
    }

    private void createImageFile(ImageInformation imageInformation) throws IOException {
        File imageFile = new File(DOCKER_PATH, imageInformation.name
                .concat(SEPARATOR)
                .concat(convertTime(imageInformation.time))
                .concat(DOT)
                .concat(imageInformation.extension));

        if (imageFile.exists()) return;
        imageFile.createNewFile();

        byte[] bytes = DatatypeConverter.parseBase64Binary(imageInformation.image);
        BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(bytes));
        ImageIO.write(bufferedImage, imageInformation.extension, imageFile);
    }

    public String convertTime(long time) {
        Date date = new Date(time);
        Format format = new SimpleDateFormat(DATE_PATTERN);
        return format.format(date);
    }
}
