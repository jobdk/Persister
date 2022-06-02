package com.forschungsprojekt.persister.controller;

import com.forschungsprojekt.persister.data.ImageInformation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
public class PersistenceController {

    @PostMapping("/image")
    public void addUser(@RequestBody ImageInformation imageInformation) throws IOException {
        byte[] bytes = DatatypeConverter.parseBase64Binary(imageInformation.imageAsBase64);
        BufferedImage image = ImageIO.read(new ByteArrayInputStream(bytes));
        // todo save image
    }
}
