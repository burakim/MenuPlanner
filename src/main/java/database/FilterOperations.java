package database;

import Models.Food;
import org.apache.poi.ss.usermodel.Row;

import java.awt.*;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by burak on 11/03/15.
 */
public class FilterOperations {
    private Map<String,ArrayList<Food>> dataStore;
    private ArrayList<String> bannedFoods;
    public FilterOperations()
    {
        dataStore = new HashMap<String, ArrayList<Food>>();
        bannedFoods = new ArrayList<String>();
        bannedFoods.add("LOUIS RICH");
        try {
            BufferedReader br = new BufferedReader(new FileReader("/Users/burak/Bitirme Odevi/Database Analyzing/src/main/java/BRANDS_FOOD"));

            try {
                StringBuilder sb = new StringBuilder();
                String line;
                while((line = br.readLine()) != null)
                {
                    bannedFoods.add(line);
                }



            } catch (IOException exception) {
                exception.printStackTrace();
            } finally {
                br.close();
            }
        }
        catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
        catch (IOException ex1)
        {
            ex1.printStackTrace();
        }
    }

    public void  getRow(Row row)
    {
        String name = row.getCell(1).getStringCellValue();
        String[] nameArr = name.split(",");

        String key = nameArr[0];
        if(!bannedFoods.contains(key)) {
            Food tempFood = new Food(row);
            if (dataStore.containsKey(key)) {
                dataStore.get(key).add(tempFood);
            } else {
                ArrayList<Food> foodArrayList = new ArrayList<Food>();
                foodArrayList.add(tempFood);
                dataStore.put(key, foodArrayList);
            }
        }
        else {
            System.out.println(key + " is omitted");
        }
        // databank.add(tempFood);


    }
//    public void maintaindataStore()
//    {
//        String[] keyArray =  dataStore.keySet().toArray(new String[dataStore.size()]);
//        for(int i =0; i<keyArray.length;i++)
//        {
//            // if(keyArray[i].equals("CHEESE") ) {
////            ArrayList<Food> tempList =secondNameOptimization(dataStore.get(keyArray[i]));
//            dataStore.remove(keyArray[i]);
//            dataStore.put(keyArray[i],tempList );
//            /// }
//            // standardDeviation((ArrayList<Food>)dataStore.get(keyArray[i]));
//        }
//        int count = 0;
//        for(int i =0; i<keyArray.length;i++)
//        {
//            count += dataStore.get(keyArray[i]).size();
//        }
//        System.out.println("Total db size "+count);
//    }
    class  FoodSize
    {
        private int size;
        FoodSize(){
            this.size =0;
        }
        void increment()
        {
            size++;
        }
        int getSize()
        {
            return size;
        }
    }

//    public ArrayList<Food> secondNameOptimization(ArrayList<Food> secondFielddata)
//    {
//        HashMap<String, ArrayList<Food>> mapDataStore = new HashMap<String, ArrayList<Food>>();
//        HashMap<String, FoodSize> mapDataStoreDivide = new HashMap<String, FoodSize>();
//        ArrayList<Food> returnedVal = new ArrayList<Food>();
//        for(int i=0; i<secondFielddata.size();i++) {
//            String[] tempArray = secondFielddata.get(i).getFoodName().split(",");
//            if (tempArray.length > 1) {
//                if (mapDataStore.containsKey(tempArray[1])) {
//                    int size = mapDataStoreDivide.get(tempArray[1]).getSize();
//
//                    mapDataStore.get(tempArray[1]).get(0).multiplyThemAll(size);
//                    mapDataStoreDivide.get(tempArray[1]).increment();
//                    size = mapDataStoreDivide.get(tempArray[1]).getSize();
//                    mapDataStore.get(tempArray[1]).get(0).AddAverage(secondFielddata.get(i));
//                    mapDataStore.get(tempArray[1]).get(0).divideThemAll(size);
//
////                mapDataStore.get(tempArray[1]).add(secondFielddata.get(i));
////
////                mapDataStore.get(tempArray[1]).get(mapDataStore.get(tempArray[1]).size()-1).setFoodName(tempArray[0]+","+tempArray[1]);
////                mapDataStore.get(tempArray[1]).get(0).getAverage(secondFielddata.get(i),mapDataStore.get(tempArray[1]).size());
////                mapDataStore.get(tempArray[1]).get(0).setFoodName(tempArray[0]+","+tempArray[1]);
//
//
//                    //  mapDataStore.get(tempArray[0]).add(secondFielddata.get(i));
//                } else {
//                    FoodSize foodSize = new FoodSize();
//                    foodSize.increment();
//                    ArrayList<Food> temp = new ArrayList<Food>();
//                    temp.add(secondFielddata.get(i));
//                    mapDataStore.put(tempArray[1], temp);
//                    mapDataStoreDivide.put(tempArray[1], foodSize);
//                }
//
//            }
//        }
//        String[] keys = mapDataStore.keySet().toArray(new String[mapDataStore.keySet().size()]);
//
//
//        for(int i=0;i<keys.length;i++)
//        {
//            for(int j=0; j<mapDataStore.get(keys[i]).size();j++)
//            {
//                returnedVal.add(mapDataStore.get(keys[i]).get(j));
//            }
//        }
//
////        String[] keyArray =  dataStore.keySet().toArray(new String[dataStore.size()]);
////        for(int i =0; i<keyArray.length;i++)
////        {
////            if(keyArray[i].equals("CHEESE") )
////                standardDeviation((ArrayList<Food>)dataStore.get(keyArray[i]));
////        }
//        return returnedVal;
//    }
//    public ArrayList<Food> standardDeviation(ArrayList<Food> data)
//    {
//        // Standard deviation hesaplanacak.
//        int amount = data.size();
//        String tempStr =  data.get(0).getFoodName();
//        String[] array = tempStr.split(",");
//
//        double sum=0;
//        for (int i=0;i <data.size();i++)
//        {
//            sum += data.get(i).getEnergy();
//        }
//
//        System.out.println(array[0] + " - "+  sum/amount);
//        return null;
//
//    }
    public void printFoods()
    {
        Iterator<Food> iterator = dataStore.get("CHEESE").iterator();
        while (iterator.hasNext())
        {
            Food temp =  iterator.next();
            iterator.remove();
            System.out.println(temp.toString());
        }

    }
}

