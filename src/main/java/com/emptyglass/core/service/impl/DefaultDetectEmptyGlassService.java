package com.emptyglass.core.service.impl;

import com.emptyglass.core.service.DetectEmptyGlassService;
import com.ibm.watson.developer_cloud.service.security.IamOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifiedImages;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifierResult;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;

@Service
public class DefaultDetectEmptyGlassService implements DetectEmptyGlassService {

    private static final Logger logger =
            LoggerFactory.getLogger(DefaultDetectEmptyGlassService.class);

    @Value("${apikey}")
    private String apikey;

    @Value("${version}")
    private String version;

    @Value("${url}")
    private String url;

    @Override
    public ClassifierResult isGlassEmpty(File image) throws FileNotFoundException {
        String imageName = image.getName();
        FileInputStream in = new FileInputStream(image);

        VisualRecognition visualRecognition = new VisualRecognition(version);
        visualRecognition.setEndPoint(url);

        IamOptions options = new IamOptions.Builder()
                .apiKey(apikey)
                .build();
        visualRecognition.setIamCredentials(options);

        ClassifyOptions classifyOptions = new ClassifyOptions.Builder()
                .imagesFile(in)
                .imagesFilename(imageName)
                .threshold((float) 0.6)
                .classifierIds(Arrays.asList("DefaultCustomModel_798922664"))
                .build();
        ClassifiedImages result = visualRecognition.classify(classifyOptions).execute();

        return result.getImages().get(0).getClassifiers().get(0);
    }
}
