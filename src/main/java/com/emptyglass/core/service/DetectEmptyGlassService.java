package com.emptyglass.core.service;

import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifierResult;

import java.io.File;
import java.io.FileNotFoundException;

public interface DetectEmptyGlassService {
    ClassifierResult isGlassEmpty(File image) throws FileNotFoundException;
}
