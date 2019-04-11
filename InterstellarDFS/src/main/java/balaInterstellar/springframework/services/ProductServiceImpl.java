package balaInterstellar.springframework.services;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import balaInterstellar.springframework.commands.ProductForm;
import balaInterstellar.springframework.converters.ProductFormToProduct;
import balaInterstellar.springframework.domain.Product;
import balaInterstellar.springframework.repositories.ProductRepository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bala Bhargha on 4/4/19.
 */
@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private ProductFormToProduct productFormToProduct;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProductFormToProduct productFormToProduct) {
        this.productRepository = productRepository;
        this.productFormToProduct = productFormToProduct;
    }

    public static final String SAMPLE_XLSX_FILE_PATH = "D:\\DumbStuff\\excel.xlsx";

    @Override
    public List<Product> listAll() throws EncryptedDocumentException, InvalidFormatException, IOException {
        List<Product> entries = new ArrayList<>();
        // Creating a Workbook from an Excel file (.xls or .xlsx)
        Workbook workbook = WorkbookFactory.create(new File(SAMPLE_XLSX_FILE_PATH));

        // Retrieving the number of sheets in the Workbook
        System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");

        /*
           =============================================================
           Iterating over all the sheets in the workboo
           =============================================================
        */

       


        System.out.println("Retrieving Sheets using for-each loop");
        for(Sheet sheet: workbook) {
            System.out.println("=> " + sheet.getSheetName());
        }

      

       
        // Getting the Sheet at index 
        Sheet sheet = workbook.getSheetAt(1);
    int numberOfRows = sheet.getPhysicalNumberOfRows();
    System.out.println("Number of Rows in Sheet "+numberOfRows);
        // Create a DataFormatter to format and get each cell's value as String

        // 2. Or you can use a for-each loop to iterate over the rows and columns
        System.out.println("\n\nIterating over Rows and Columns using for-each loop\n");
        for(int i=sheet.getFirstRowNum()+1;i<=sheet.getLastRowNum();i++){//Rows
        	Product route=new Product();
        	Row ro=sheet.getRow(i);
            for(int j=0;j<=ro.getPhysicalNumberOfCells();j++){
            	Cell ce = ro.getCell(j);
                if(j==0){ 
                    
                	route.setId((long) ce.getNumericCellValue());
                }
                if(j==1){
                	route.setPlanetOrign((ce.getStringCellValue()));
                }
                if(j==2){
                	route.setPlanetDestination((ce.getStringCellValue()));
                }
                if(j==3){
                	route.setDistance(Double.valueOf(ce.getNumericCellValue()));
                	//route.setPrice(new BigDecimal(()));
                } 
                
            }
            entries.add(route);
        	
        }
        for(Product route: entries){	        	
        	productRepository.save(route);
        	
        	
        }
        	
        
      
        
        //productRepository.findAll().forEach(entries::add); //fun with Java 8
        return entries;
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findOne(id);
    }

    @Override
    public Product saveOrUpdate(Product product) {
        productRepository.save(product);
        return product;
    }

    @Override
    public void delete(Long id) {
        productRepository.delete(id);

    }

    @Override
    public Product saveOrUpdateProductForm(ProductForm productForm) {
        Product savedProduct = saveOrUpdate(productFormToProduct.convert(productForm));

        System.out.println("Saved Product Id: " + savedProduct.getId());
        return savedProduct;
    }

	@Override
	public List<Product> getAll() {
		List<Product> routeList = new ArrayList<Product>();
		// TODO Auto-generated method stub
		Iterable<Product> rotues =productRepository.findAll();
		for (Product product : rotues) {
			routeList.add(product);
		}
		return routeList;
	}
}
