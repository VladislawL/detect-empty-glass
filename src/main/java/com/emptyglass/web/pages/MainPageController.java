package com.emptyglass.web.pages;

import com.emptyglass.core.service.DetectEmptyGlassService;
import com.emptyglass.core.utils.FileProcessingUtils;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifierResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.FileNotFoundException;

@Controller
@RequestMapping(value = "/detectEmptyGlass")
public class MainPageController {

    @Autowired
    DetectEmptyGlassService detectEmptyGlassService;

    @GetMapping
    public String getMainPage(@ModelAttribute("result") ClassifierResult classifierResult,
                              @ModelAttribute("fileWasNotSelected") String fileWasNotSelected,
                              Model model) {
        if (classifierResult.getClasses() != null) {
            boolean isEmpty = classifierResult.getClasses().stream()
                    .noneMatch(classResult -> "full_glass".equals(classResult.getClassName()));
            model.addAttribute("isEmpty", isEmpty);
        }
        return "detectEmptyGlass";
    }

    @PostMapping
    public String detectEmptyGlass(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) throws FileNotFoundException {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("fileWasNotSelected", "Please select a file to upload");
            return "redirect:detectEmptyGlass";
        }

        File temp = FileProcessingUtils.saveTempFile(file);

        ClassifierResult result = detectEmptyGlassService.isGlassEmpty(temp);
        redirectAttributes.addFlashAttribute("result", result);

        return "redirect:detectEmptyGlass";
    }

}
