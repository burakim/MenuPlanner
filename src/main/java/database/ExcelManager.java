package database;

/**
 * Created by burak on 07/04/15.
 */
import Models.Food;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Created by burak on 08/03/15.
 */

public class ExcelManager {

    private Queue<String> databank;
    private Map<String, List<Food>> foodMap;

    XSSFWorkbook workbook;
    FilterOperations filterOperations;
    //Get first/desired sheet from the workbook
    XSSFSheet sheet;

    public ExcelManager(FileInputStream inputStream)
    {
        try {
            workbook = new XSSFWorkbook(inputStream);
            filterOperations = new FilterOperations();
        }
        catch (IOException ex) {
            System.out.println("Input File Not Found");
        }
    }

    public Queue<String> GroupDataGetQueue(int[] sheets)
    {
        try{
            if(workbook!= null) {
                //Get first/desired sheet from the workbook
                for (int i = 0; i < sheets.length; i++) {
                    XSSFSheet sheet = workbook.getSheetAt(i);

                    //Iterate through each rows one by one
                    Iterator<Row> rowIterator = sheet.iterator();
                    Row row1 = rowIterator.next();

                    while (rowIterator.hasNext()) {
                        Row row = rowIterator.next();
                        filterOperations.getRow(row);

                        //For each row, iterate through all the columns
                        // buryaa eklersin.
                    }
                    filterOperations.maintaindataStore();
                }
            }
            filterOperations.printFoods();
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
            e.printStackTrace();
        }

        return null;

    }

    public boolean AddQueue(String str)
    {


        return databank.add(str);
    }



}
