package com.forschungsprojekt.persister.data;

import java.io.Serializable;
import java.util.List;

public class ImageInformation implements Serializable {
    public String name;
    public long time;
    public String extension;
    public String image;
    public List<PileEvaluationResult> result;
}
