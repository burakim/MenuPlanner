package database;

import Models.ConstraintModel;
import Models.Food;
import Models.Nutrition;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.List;


/**
 * Created by burak on 23/04/15.
 */
public class MongoManager {

    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;
    private ArrayList<Food>  foodList;
    private HashMap<Nutrition,ConstraintModel> constraintModelHashMap;
    public MongoManager()
    {
        mongoClient  = new MongoClient();
        database = mongoClient.getDatabase("fooddatabank");
        collection = database.getCollection("foodbank");
        foodList = new ArrayList<Food>();
        constraintModelHashMap = new HashMap<Nutrition, ConstraintModel>();

    }
    public HashMap<Nutrition,ConstraintModel> getAllConstraints()
    {
        ConstraintModel calcium = new ConstraintModel(2500,0,1);
        ConstraintModel calorie = new ConstraintModel(2400,3000,1);
        ConstraintModel carbohydrate = new ConstraintModel(-2,100,1);
        ConstraintModel protein = new ConstraintModel(40,55,1);
        ConstraintModel vitaminC  = new ConstraintModel(2000,75,1);
        ConstraintModel vitaminE = new ConstraintModel(1000,12,1);
        ConstraintModel thiamine  = new ConstraintModel(-2,1.1,1);
        ConstraintModel niacin = new ConstraintModel(30,12,1);
        ConstraintModel vitaminB6 = new ConstraintModel(-2,1.1,1);
        ConstraintModel folicAcid = new ConstraintModel(800,320,1);
        ConstraintModel vitaminB12 = new ConstraintModel(-2,2.0,1);
        ConstraintModel copper = new ConstraintModel(10000,700,1);
        ConstraintModel iodine = new ConstraintModel(1100,95,1);
        ConstraintModel iron = new ConstraintModel(45,6,1);
        ConstraintModel magnesium = new ConstraintModel(360,340,1);
        ConstraintModel molybdenum =new ConstraintModel(2000,34,1);
        ConstraintModel phosphorus = new ConstraintModel(4000,580,1);
        ConstraintModel selenium =new ConstraintModel(400,45,1);
        ConstraintModel zinc= new ConstraintModel(45,9.4,1);
        ConstraintModel riboflavin = new ConstraintModel(-2,1.1,1);
        constraintModelHashMap.put(Nutrition.Calcium,calcium);
        constraintModelHashMap.put(Nutrition.Calorie,calorie);
        constraintModelHashMap.put(Nutrition.Carbohydrate,carbohydrate);
        constraintModelHashMap.put(Nutrition.Protein,protein);
        constraintModelHashMap.put(Nutrition.VitaminC,vitaminC);
        constraintModelHashMap.put(Nutrition.VitaminE,vitaminE);
        constraintModelHashMap.put(Nutrition.Thiamine,thiamine);
        constraintModelHashMap.put(Nutrition.Niacin,niacin);
        constraintModelHashMap.put(Nutrition.VitaminB6,vitaminB6);
        constraintModelHashMap.put(Nutrition.FolicAcid,folicAcid);
        constraintModelHashMap.put(Nutrition.VitaminB12,vitaminB12);
        constraintModelHashMap.put(Nutrition.Copper,copper);
        //constraintModelHashMap.put(Nutrition.Iodine,iodine);
        constraintModelHashMap.put(Nutrition.Iron,iron);
        constraintModelHashMap.put(Nutrition.Magnesium,magnesium);
       // constraintModelHashMap.put(Nutrition.Molybdenum,molybdenum);
        constraintModelHashMap.put(Nutrition.Phosphorus,phosphorus);
        constraintModelHashMap.put(Nutrition.Selenium,selenium);
        constraintModelHashMap.put(Nutrition.Zinc,zinc);
        constraintModelHashMap.put(Nutrition.Riboflavin,riboflavin);


//        constraintModelHashMap.put(Nutrition.Calcium,getConstraintModel("calcium"));
//        constraintModelHashMap.put(Nutrition.Calorie,getConstraintModel("calorie"));
//        constraintModelHashMap.put(Nutrition.Carbohydrate,getConstraintModel("carbohydrate"));
//        constraintModelHashMap.put(Nutrition.Protein,getConstraintModel("protein"));
//        constraintModelHashMap.put(Nutrition.VitaminC,getConstraintModel("vitaminC"));
//        constraintModelHashMap.put(Nutrition.VitaminE,getConstraintModel("vitaminE"));
//        constraintModelHashMap.put(Nutrition.Thiamine,getConstraintModel("thiamine"));
//        constraintModelHashMap.put(Nutrition.Riboflavin,getConstraintModel("riboflavin"));
//        constraintModelHashMap.put(Nutrition.Niacin,getConstraintModel("niacin"));
//        constraintModelHashMap.put(Nutrition.VitaminB6,getConstraintModel("vitaminb6"));
//        constraintModelHashMap.put(Nutrition.FolicAcid,getConstraintModel("folicacid"));
//        constraintModelHashMap.put(Nutrition.VitaminB12,getConstraintModel("vitaminb12"));
//        constraintModelHashMap.put(Nutrition.Copper,getConstraintModel("copper"));
//        constraintModelHashMap.put(Nutrition.Iodine,getConstraintModel("iodine"));
//        constraintModelHashMap.put(Nutrition.Iron,getConstraintModel("iron"));
//        constraintModelHashMap.put(Nutrition.Magnesium,getConstraintModel("magnesium"));
//        constraintModelHashMap.put(Nutrition.Molybdenum,getConstraintModel("molybdenum"));
//        constraintModelHashMap.put(Nutrition.Phosphorus,getConstraintModel("phosphorus"));
//        constraintModelHashMap.put(Nutrition.Selenium,getConstraintModel("selenium"));
//        constraintModelHashMap.put(Nutrition.Zinc,getConstraintModel("zinc"));

        return constraintModelHashMap;
    }

    private ConstraintModel getConstraintModel(String constraintName )
    {
         MongoCollection<Document> collection = database.getCollection(constraintName);
        List<Document> foundDocument = collection.find().into(new ArrayList<Document>());
        double upperLimit = foundDocument.get(0).getDouble("upperlimit");
        if(upperLimit == -2)
            upperLimit = Double.MAX_VALUE;
        double lowerLimit = foundDocument.get(0).getDouble("lowerlimit");
        double signifiancence = foundDocument.get(0).getDouble("signifiancence");

        ConstraintModel returnedVal = new ConstraintModel(upperLimit,lowerLimit,signifiancence);
        return returnedVal;
    }
    public ArrayList<Food> getAllElements()
    {

        List<Document> foundDocument = collection.find().into(new ArrayList<Document>());
        for(Document document: foundDocument)
        {
            HashMap<Nutrition,Double> nutritionMap = new HashMap<Nutrition, Double>();
            HashMap<Nutrition,String> nutritionDescMap = new HashMap<Nutrition, String>();
            Food tempfood = new Food();
            tempfood.setFoodName(document.getString("foodName"));

            nutritionMap.put(Nutrition.Water, document.getDouble("water"));
            nutritionMap.put(Nutrition.VitaminA_IU, document.getDouble("vitaminA_IU"));
            nutritionMap.put(Nutrition.VitaminC, document.getDouble("vitaminC"));
            nutritionMap.put(Nutrition.VitaminB6, document.getDouble("vitaminB6"));
            nutritionMap.put(Nutrition.VitaminB12, document.getDouble("vitaminB12"));
            nutritionMap.put(Nutrition.Thiamine, document.getDouble("thiamin"));
            nutritionMap.put(Nutrition.AlphaCarot, document.getDouble("aphaCarot"));
            nutritionMap.put(Nutrition.BetaCarot, document.getDouble("betaCarot"));
            nutritionMap.put(Nutrition.Ash, document.getDouble("ash"));
            nutritionMap.put(Nutrition.Iron, document.getDouble("iron"));
            nutritionMap.put(Nutrition.Calcium, document.getDouble("calcium"));
            nutritionMap.put(Nutrition.BetaCrypt, document.getDouble("betaCrypt"));
            nutritionMap.put(Nutrition.Carbohydrate, document.getDouble("carbonhydrt"));
            nutritionMap.put(Nutrition.Calorie, document.getDouble("energy"));
            nutritionMap.put(Nutrition.Copper, document.getDouble("copper"));
            nutritionMap.put(Nutrition.Cholesterol, document.getDouble("cholestrl"));
            nutritionMap.put(Nutrition.CholineTot, document.getDouble("cholineTot"));
            nutritionMap.put(Nutrition.FatMono, document.getDouble("faMono"));
            nutritionMap.put(Nutrition.FatSaturated, document.getDouble("faSat"));
            nutritionMap.put(Nutrition.Fiber, document.getDouble("fiber"));
            nutritionMap.put(Nutrition.Sugar, document.getDouble("sugar"));
            nutritionMap.put(Nutrition.FatPoly, document.getDouble("faPoly"));
            nutritionMap.put(Nutrition.FloateDFE, document.getDouble("floateDFE"));
            nutritionMap.put(Nutrition.GmWt_1, document.getDouble("gmWt_1"));
            nutritionMap.put(Nutrition.GmWt_2, document.getDouble("gmWt_2"));

            nutritionDescMap.put(Nutrition.GmWt_1_Desc1, document.getString("gmWt_Desc1"));
            nutritionDescMap.put(Nutrition.GmWt_2_Desc2, document.getString("gmWt_Desc2"));

            nutritionMap.put(Nutrition.Lipid, document.getDouble("lipid"));
            nutritionMap.put(Nutrition.Magnesium, document.getDouble("magnesium"));
            nutritionMap.put(Nutrition.Manganese, document.getDouble("manganese"));
            nutritionMap.put(Nutrition.VitaminK, document.getDouble("vitaminK"));
            nutritionMap.put(Nutrition.FoodFolate, document.getDouble("food_folate"));
            nutritionMap.put(Nutrition.Retinol, document.getDouble("retinol"));
            nutritionMap.put(Nutrition.Protein, document.getDouble("protein"));
            nutritionMap.put(Nutrition.RefusePct, document.getDouble("refusePct"));
            nutritionMap.put(Nutrition.Niacin, document.getDouble("niacin"));
            nutritionMap.put(Nutrition.Riboflavin, document.getDouble("riboflavin"));
            nutritionMap.put(Nutrition.PantoAcid, document.getDouble("pantoAcid"));
            nutritionMap.put(Nutrition.Zinc, document.getDouble("zinc"));
            nutritionMap.put(Nutrition.VitaminA_RAE, document.getDouble("vitaminA_RAE"));
            nutritionMap.put(Nutrition.Phosphorus, document.getDouble("phosphorus"));
            nutritionMap.put(Nutrition.VitaminD_IU, document.getDouble("vitaminD_IU"));
            nutritionMap.put(Nutrition.VitaminD, document.getDouble("vitaminD"));
            nutritionMap.put(Nutrition.VitaminE, document.getDouble("vitaminE"));
            nutritionMap.put(Nutrition.Selenium, document.getDouble("selenium"));
            nutritionMap.put(Nutrition.FolateTot, document.getDouble("folateTot"));

            nutritionMap.put(Nutrition.Potassium, document.getDouble("potassium"));
            nutritionMap.put(Nutrition.Lipid, document.getDouble("lipid"));
            nutritionMap.put(Nutrition.FolicAcid, document.getDouble("folicAcid"));
            nutritionMap.put(Nutrition.Sodium, document.getDouble("sodium"));
            nutritionMap.put(Nutrition.Lycopene, document.getDouble("lycopene"));
            tempfood.setRate(document.getDouble("rate"));
            tempfood.setCost(document.getDouble("cost"));
            tempfood.setIngredient(nutritionMap);
            tempfood.setIngredient_description(nutritionDescMap);
            foodList.add(tempfood);
        }
        return foodList;
    }

}
