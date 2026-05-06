package com.app.controller;



import com.app.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/excel")
public class ExcelController {

    @Autowired
    private ExcelService excelService;


    @PostMapping("/upload")
    public ResponseEntity<String> uploadExcelFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please upload a valid Excel file.");
        }

        try {
            excelService.saveBrandsFromExcel(file);
            return ResponseEntity.ok("Brands uploaded successfully!");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error processing file: " + e.getMessage());
        }
    }
}

