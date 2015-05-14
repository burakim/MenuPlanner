package database;

import Models.Food;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by burak on 23/04/15.
 */
public class MongoManager {

    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;
    private ArrayList<Food>  foodList;

    public MongoManager()
    {
        mongoClient  = new MongoClient();
        database = mongoClient.getDatabase("fooddatabank");
        collection = database.getCollection("foodbank");
        foodList = new ArrayList<Food>();

    }
    public ArrayList<Food> getAllElements()
    {
        List<Document> foundDocument = collection.find().into(new ArrayList<Document>());
        for(Document document: foundDocument)
        {
            Food tempfood = new Food();
            tempfood.setFoodName(document.getString("foodName"));
            tempfood.setWater(document.getDouble("water"));
            tempfood.setVitaminA_IU(document.getDouble("vitaminA_IU"));
            tempfood.setVitaminC(document.getDouble("vitaminC"));
            tempfood.setVitaminB6(document.getDouble("vitaminB6"));
            tempfood.setVitaminB12(document.getDouble("vitaminB12"));
            tempfood.setThiamin(document.getDouble("thiamin"));
            tempfood.setAphaCarot(document.getDouble("aphaCarot"));
            tempfood.setBetaCarot(document.getDouble("betaCarot"));
            tempfood.setAsh(document.getDouble("ash"));
            tempfood.setIron(document.getDouble("iron"));
            tempfood.setCalcium(document.getDouble("calcium"));
            tempfood.setBetaCrypt(document.getDouble("betaCrypt"));
            tempfood.setCarbonhydrt(document.getDouble("carbonhydrt"));
            tempfood.setEnergy(document.getDouble("energy"));
            tempfood.setCopper(document.getDouble("copper"));
            tempfood.setCholestrl(document.getDouble("cholestrl"));
            tempfood.setCholineTot(document.getDouble("cholineTot"));
            tempfood.setFaMono(document.getDouble("faMono"));
            tempfood.setFaSat(document.getDouble("faSat"));
            tempfood.setFiber(document.getDouble("fiber"));
            tempfood.setSugar(document.getDouble("sugar"));
            tempfood.setFaPoly(document.getDouble("faPoly"));
            tempfood.setFloateDFE(document.getDouble("floateDFE"));
            tempfood.setGmWt_1(document.getDouble("gmWt_1"));
            tempfood.setGmWt_2(document.getDouble("gmWt_2"));
            tempfood.setGmWt_Desc1(document.getString("gmWt_Desc1"));
            tempfood.setGmWt_Desc2(document.getString("gmWt_Desc2"));
            tempfood.setLipid(document.getDouble("lipid"));
            //tempfood.setLutZea(document.getDouble(""));
            tempfood.setMagnesium(document.getDouble("magnesium"));
            tempfood.setManganese(document.getDouble("manganese"));
            tempfood.setVitaminK(document.getDouble("vitaminK"));
            tempfood.setFood_folate(document.getDouble("food_folate"));
            tempfood.setRetinol(document.getDouble("retinol"));
            tempfood.setProtein(document.getDouble("protein"));
            tempfood.setRefusePct(document.getDouble("refusePct"));
            tempfood.setNiacin(document.getDouble("niacin"));
            tempfood.setRiboflavin(document.getDouble("riboflavin"));
            tempfood.setPantoAcid(document.getDouble("pantoAcid"));
            tempfood.setZinc(document.getDouble("zinc"));
            tempfood.setVitaminA_RAE(document.getDouble("vitaminA_RAE"));
            tempfood.setPhosphorus(document.getDouble("phosphorus"));
            tempfood.setVitaminD_IU(document.getDouble("vitaminD_IU"));
            tempfood.setVitaminD(document.getDouble("vitaminD"));
            tempfood.setVitaminE(document.getDouble("vitaminE"));
           // tempfood.setVitaminC(document.getDouble("vitaminC"));
            tempfood.setSelenium(document.getDouble("selenium"));
            tempfood.setFolateTot(document.getDouble("folateTot"));
            tempfood.setPotassium(document.getDouble("potassium"));
            tempfood.setLipid(document.getDouble("lipid"));
            tempfood.setFolicAcid(document.getDouble("folicAcid"));
            tempfood.setLycopene(document.getDouble("lycopene"));
            tempfood.setSodium(document.getDouble("sodium"));
            tempfood.setRate(document.getDouble("rate"));
            tempfood.setCost(document.getDouble("cost"));


            foodList.add(tempfood);





        }
        return foodList;
    }

}
