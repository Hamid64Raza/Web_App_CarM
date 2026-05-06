package com.app.service;



import com.app.entity.cars.Brand;
import com.app.repository.BrandRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ExcelService {

    @Autowired
    private BrandRepository brandRepository;

    public void saveBrandsFromExcel(MultipartFile file) {
        try (InputStream inputStream = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            Sheet sheet = workbook.getSheetAt(0); // Read the first sheet
            Iterator<Row> rowIterator = sheet.iterator();

            List<Brand> brands = new ArrayList<>();
            boolean isFirstRow = true;

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                if (isFirstRow) {  // Skip the header row
                    isFirstRow = false;
                    continue;
                }

                String brandName = row.getCell(0).getStringCellValue();
                brands.add(new Brand());
                brands.get(brands.size() - 1).setName(brandName);
            }

            brandRepository.saveAll(brands); // Bulk insert
        } catch (Exception e) {
            throw new RuntimeException("Failed to process Excel file: " + e.getMessage());
        }
    }
}

