package com.ionx.ionx.service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.transaction.Transactional;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ionx.ionx.domain.Prospect;
import com.ionx.ionx.repositories.ReadFileRepository;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

@Service
@Transactional
public class ReadFileServiceImpl implements ReadFileService{

	@Autowired private ReadFileRepository readFileRepository;

	@Override
	public List<Prospect> findAll() {
		return (List<Prospect>) readFileRepository.findAll();
	}

	@Override
	public boolean saveDataFromUploadfile(MultipartFile file) {
		boolean isFlag = false;
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		if(extension.equalsIgnoreCase("json")) {
			isFlag = readDataFromJson(file);
		}else if(extension.equalsIgnoreCase("csv")) {
			isFlag = readDataFromCsv(file);
		}else if(extension.equalsIgnoreCase("xls") || extension.equalsIgnoreCase("xlsx")) {
			isFlag = readDataFromExcel(file);
		}
		
		
		return isFlag;
	}

	private boolean readDataFromExcel(MultipartFile file) {
		Workbook workbook = getWorkBook(file);
		Sheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rows = sheet.iterator();
		rows.next();
		 DataFormatter formatter = new DataFormatter(Locale.US);
		while(rows.hasNext()) {
			Row row = rows.next();
			
			Prospect prospect = new Prospect();
			if(row.getCell(0).getCellType() == Cell.CELL_TYPE_STRING) {
				prospect.setNome(formatter.formatCellValue(row.getCell(0)));
			}
			if(row.getCell(1).getCellType() == Cell.CELL_TYPE_STRING) {
				prospect.setEmpresa(formatter.formatCellValue(row.getCell(1)));
			}
			if(row.getCell(2).getCellType() == Cell.CELL_TYPE_STRING) {
				prospect.setCargo(formatter.formatCellValue(row.getCell(2)));
			}
			if(row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {
				prospect.setTelefone(formatter.formatCellValue(row.getCell(3)));
			}else if(row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
				prospect.setTelefone(formatter.formatCellValue(row.getCell(3)));
			}
			if(row.getCell(4).getCellType() == Cell.CELL_TYPE_STRING) {
				prospect.setEmail(formatter.formatCellValue(row.getCell(4)));
			}
			if(row.getCell(5).getCellType() == Cell.CELL_TYPE_STRING) {
				prospect.setEstado(formatter.formatCellValue(row.getCell(5)));
			}
			if(row.getCell(6).getCellType() == Cell.CELL_TYPE_STRING) {
				prospect.setCidade(formatter.formatCellValue(row.getCell(6)));
			}
			
			if(row.getCell(7).getCellType() == Cell.CELL_TYPE_NUMERIC) {
				prospect.setCep(formatter.formatCellValue(row.getCell(7)));
			}else if(row.getCell(7).getCellType() == Cell.CELL_TYPE_STRING) {
				prospect.setCep(formatter.formatCellValue(row.getCell(7)));
			}
			if(row.getCell(8).getCellType() == Cell.CELL_TYPE_STRING) {
				prospect.setBairro(formatter.formatCellValue(row.getCell(8)));
			}
			if(row.getCell(9).getCellType() == Cell.CELL_TYPE_STRING) {
				prospect.setEndereco(formatter.formatCellValue(row.getCell(9)));
			}
			if(row.getCell(10).getCellType() == Cell.CELL_TYPE_NUMERIC) {
				prospect.setNumeroCasa(formatter.formatCellValue(row.getCell(10)));
			}else if(row.getCell(10).getCellType() == Cell.CELL_TYPE_STRING) {
				prospect.setNumeroCasa(formatter.formatCellValue(row.getCell(7)));
			}
			if(row.getCell(11).getCellType() == Cell.CELL_TYPE_NUMERIC) {
				prospect.setCnpj(formatter.formatCellValue(row.getCell(11)));
			}else if(row.getCell(11).getCellType() == Cell.CELL_TYPE_STRING) {
				prospect.setCnpj(formatter.formatCellValue(row.getCell(11)));
			}
			if(row.getCell(12).getCellType() == Cell.CELL_TYPE_STRING) {
				prospect.setProdutEscolhido(formatter.formatCellValue(row.getCell(12)));
			}
			if(row.getCell(13).getCellType() == Cell.CELL_TYPE_STRING) {
				prospect.setLevel(formatter.formatCellValue(row.getCell(13)));
			}
			 prospect.setFileType(FilenameUtils.getExtension(file.getOriginalFilename()));
			 readFileRepository.save(prospect);
			}
		
		
		
		return true;
	}

	private Workbook getWorkBook(MultipartFile file) {
		Workbook workbook = null;
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		try {
			if(extension.equalsIgnoreCase("xlsx")) {
				workbook = new XSSFWorkbook(file.getInputStream());
			}else if(extension.equalsIgnoreCase("xls")) {
				workbook = new HSSFWorkbook(file.getInputStream());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return workbook;
	}

	private boolean readDataFromCsv(MultipartFile file) {
		try {
			InputStreamReader reader = new InputStreamReader (file.getInputStream());
			CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
			List<String[]> rows = csvReader.readAll();
			for(String[] row:rows) {
				readFileRepository.save(new Prospect(row[0], row[1], row[2], row[3], row[4], row[5], row[6], row[7], row[8], row[9], row[10], row[11], row[12], row[13], FilenameUtils.getExtension(file.getOriginalFilename())));
			}
			
			return true;
			
		}catch(Exception e) {
			return false;
		}

	}

	private boolean readDataFromJson(MultipartFile file) {
		try {
			InputStream inputStream = file.getInputStream();
			ObjectMapper mapper = new ObjectMapper();
			List<Prospect> prospects = Arrays.asList(mapper.readValue(inputStream, Prospect[].class));
			if(prospects!= null && prospects.size()>0) {
				for (Prospect prospect : prospects) {
					prospect.setFileType(FilenameUtils.getExtension(file.getOriginalFilename()));
					readFileRepository.save(prospect);
				}
			}
			
			return true;
		}catch (Exception e) {
			return false;
		}
		
	}
}
