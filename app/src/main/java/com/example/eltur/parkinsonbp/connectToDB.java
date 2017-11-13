package com.example.eltur.parkinsonbp;

import com.example.eltur.parkinsonbp.HttpClient.HttpClient;
import com.example.eltur.parkinsonbp.ServerClass.ActivityUpdate;
import com.example.eltur.parkinsonbp.ServerClass.HabitUpdate;
import com.example.eltur.parkinsonbp.ServerClass.Medicine;
import com.example.eltur.parkinsonbp.ServerClass.MedicineUpdate;
import com.example.eltur.parkinsonbp.ServerClass.MoodCondition;
import com.example.eltur.parkinsonbp.ServerClass.PatientRecord;
import com.example.eltur.parkinsonbp.ServerClass.SleepCondition;
import com.example.eltur.parkinsonbp.ServerClass.SleepConditionAndDisorder;
import com.example.eltur.parkinsonbp.ServerClass.SleepDisorder;
import com.example.eltur.parkinsonbp.ServerClass.SubMenu;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;


/**
 * Created by Eltur on 28/05/2017.
 */

public class connectToDB {


    private static HttpClient httpClient = HttpClient.getClient();
    public static ArrayList<String> getActivitiesArray() {
        return ActivitiesArray;
    }

    public static void setActivitiesArray(ArrayList<String> activitiesArray) {
        ActivitiesArray = activitiesArray;
    }

    public static List<List<SubMenu>> getSubMenuList() {
        return subMenuList;
    }

    public static void setSubMenuList(List<List<SubMenu>> subMenuList) {
        connectToDB.subMenuList = subMenuList;
    }

    private static List<List<SubMenu>> subMenuList = new ArrayList<List<SubMenu>>();

    public static void setMedicineSerialNumbers(ArrayList<String> medicineSerialNumbers) {
        MedicineSerialNumbers = medicineSerialNumbers;
    }

    private static ArrayList<String> MedicineSerialNumbers = new ArrayList<String>();

    private static Collection<SleepDisorder> SleepOrdercollection;
    private static ArrayList<String> ActivitiesArray = new ArrayList<>();

    public static ArrayList<String> getMedicineArray() {
        return MedicineArray;
    }

    public static void setMedicineArray(ArrayList<String> medicineArray) {
        MedicineArray = medicineArray;
    }

    public static ArrayList<String> getSleepDisorderArray() {
        return SleepDisorderArray;
    }

    public static void setSleepDisorderArray(ArrayList<String> sleepDisorderArray) {
        SleepDisorderArray = sleepDisorderArray;
    }

    private static ArrayList<String> MedicineArray = new ArrayList<>();
    private static ArrayList<String> SleepDisorderArray = new ArrayList<>();

    public static ArrayList<String> getSleepConditionArray() {
        return SleepConditionArray;
    }

    public static void setSleepConditionArray(ArrayList<String> sleepConditionArray) {
        SleepConditionArray = sleepConditionArray;
    }

    private static ArrayList<String> SleepConditionArray = new ArrayList<>();
    public static ArrayList<String> getHergelimArray() {
        return HergelimArray;
    }

    public static void setHergelimArray(ArrayList<String> hergelimArray) {
        HergelimArray = hergelimArray;
    }

    private static ArrayList<String> HergelimArray = new ArrayList<>();


    public static ArrayList<String> getMoodArray() {
        return MoodArray;
    }

    public static void setMoodArray(ArrayList<String> moodArray) {
        MoodArray = moodArray;
    }

    private static ArrayList<String> MoodArray = new ArrayList<>();

    public static ArrayList<String> getLinksArray() {
        return LinksArray;
    }

    public static void setLinksArray(ArrayList<String> linksArray) {
        LinksArray = linksArray;
    }

    private static ArrayList<String> LinksArray = new ArrayList<>();

    public static String getUserDetails(String PatientID) throws MalformedURLException {

        List<String> PatientReportlist = new ArrayList<>();
        String userName="";

        PatientReportlist = httpClient.GetUserName("http://10.0.2.2:8080/BEAT-PD/Admin/GET/ReportBYPatientID?value="+PatientID);

        userName = PatientReportlist.toString();
        return userName;
    }

    public static String AddDataToDB(String patientID, ActivityUpdate[] userActivity, HabitUpdate[] userHargelim, MedicineUpdate[] userMedicine, ArrayList<String> userMoodcondition, SleepConditionAndDisorder sleepConditionAndDisorder){
        boolean serverResponde = false;
        PatientRecord content = new PatientRecord();
        Date date = new Date();
       String str = new SimpleDateFormat("yyyy-MM-dd").format(date);

        content.setPatientLastUpdate(str);
        content.setPatientID(patientID);

        if(userMedicine != null)
        {
            Collection<MedicineUpdate> collection;
            ArrayList jsonMedicine =  new ArrayList<>();
            //  Activity[] Activitiesjson = HttpClient.getJson();
            int i =0;
            for ( i=0 ; i < userMedicine.length; i++) {
                //  if (!userActivity[i].toString().isEmpty()) {

                // Activitiesjson[i].setActivityName(userActivity[i].toString());
                // Activitiesjson[i].setActivityDescription("דוגמא");
                jsonMedicine.add(userMedicine[i]);
                // }
            }
            collection = jsonMedicine;
            content.setListOfMedicineUpdate(collection);
           /* Collection<Medicine> collection;
            ArrayList jsonAddMedicine =  new ArrayList<>();
            Medicine[] Medicinejson = HttpClient.getJsonMedicine();
            int i =0;
            for ( i=0 ; i < userMedicine.size(); i++) {
                if (!userMedicine.get(i).toString().isEmpty()) {
                    for (int t = 0; t < Medicinejson.length; t++) {
                        String UsermediciineName = userMedicine.get(i).toString();
                        String JsonmediciineName = Medicinejson[t].getMedicineName().toString();
                        if (UsermediciineName.contains(JsonmediciineName)) {
                            Medicinejson[t].setMedicineSerialNumber(Medicinejson[t].getMedicineSerialNumber());
                            Medicinejson[t].setMedicineName(userMedicine.get(i).toString());
                            Medicinejson[t].setMedicineLimitation(Medicinejson[t].getMedicineLimitation());
                            Medicinejson[t].setInfo(Medicinejson[t].getInfo());
                            jsonAddMedicine.add(Medicinejson[t]);
                        }
                    }
                }
            }

            collection=jsonAddMedicine;*/


           // content.setListOfMedicineUpdate(collection);
        }

        if(userActivity != null)
        {
            Collection<ActivityUpdate> collection;
            ArrayList json2 =  new ArrayList<>();
          //  Activity[] Activitiesjson = HttpClient.getJson();
            int i =0;
            for ( i=0 ; i < userActivity.length; i++) {
              //  if (!userActivity[i].toString().isEmpty()) {

                   // Activitiesjson[i].setActivityName(userActivity[i].toString());
                   // Activitiesjson[i].setActivityDescription("דוגמא");
                    json2.add(userActivity[i]);
               // }
            }
            collection = json2;
            content.setListOfActivityUpdate(collection);
        }

        if(userHargelim != null) {
           // Collection<HabitUpdate> collection;
            ArrayList json3 =  new ArrayList<>();
            int i =0;
            for ( i=0 ; i < userHargelim.length; i++) {
                    json3.add( userHargelim[i]);
            }
           // collection = json3;
            content.setListOfHabitUpdate(json3);
        }

        if(userMoodcondition != null) {

            Collection<MoodCondition> collection;
            ArrayList json3 =  new ArrayList<>();

            MoodCondition[] MoodConditionjson = HttpClient.getJsonMood();
            int i =0;
            for ( i=0 ; i < userMoodcondition.size(); i++) {
                if (!userMoodcondition.get(i).toString().isEmpty()) {

                    MoodConditionjson[i].setMoodConditionName(userMoodcondition.get(i).toString());
                    json3.add( MoodConditionjson[i]);

                }
            }
            collection = json3;
            content.setListOfMoodCondition(collection);
        }
        if(sleepConditionAndDisorder != null ) {
            content.setSleepConditionAndDisorder(sleepConditionAndDisorder);
        }

        try {

            serverResponde =  HttpClient.getClient().SendPatientRecordToServer("http://10.0.2.2:8080/BEAT-PD/User/Update/PatientRecord", content);
        } catch (MalformedURLException | JsonProcessingException ex) {
            System.out.println(String.format("Error:%s", ex.getMessage()));
        }

        if (serverResponde) {
            return "Success";
        } else {
            return "Faild";
        }
    }


    public static ArrayList<String> getAllActivies() {
        try {
            ActivitiesArray.clear();
            ActivitiesArray = httpClient.GetAllActiviesFromServer("http://10.0.2.2:8080/BEAT-PD/User/GET/AllActivities/");
        } catch (MalformedURLException ex) {
            System.out.println(String.format("Error:%s", ex.getMessage()));
        }

        return ActivitiesArray;
    }

    public static ArrayList<String> getAllHergelim() {
        try {
           HergelimArray.clear();
            HergelimArray = httpClient.GetAllHergelimFromServer("http://10.0.2.2:8080/BEAT-PD/User/GET/AllHabits/");
        } catch (MalformedURLException ex) {
            System.out.println(String.format("Error:%s", ex.getMessage()));
        }

        return HergelimArray;
    }


    public static ArrayList<String> getAllMoods() {
            try {
                 MoodArray.clear();
                MoodArray = httpClient.GetAllMoodsFromServer("http://10.0.2.2:8080/BEAT-PD/User/GET/AllMoodConditions/");
            } catch (MalformedURLException ex) {
                System.out.println(String.format("Error:%s", ex.getMessage()));

        }


        return MoodArray;
    }

    public static ArrayList<String> GetAllLinks() {
        try {
            LinksArray.clear();
            LinksArray = httpClient.GetAllLinks("http://10.0.2.2:8080/BEAT-PD/User/GET/AllLinks");
        } catch (MalformedURLException ex) {
            System.out.println(String.format("Error:%s", ex.getMessage()));

        }
        return LinksArray;
    }

    public static ArrayList<String> getAllMedicines() {
        try {
            MedicineArray.clear();
            MedicineArray = httpClient.GetAllMedicineFromServer("http://10.0.2.2:8080/BEAT-PD/User/GET/AllMedicines/");
        } catch (MalformedURLException ex) {
            System.out.println(String.format("Error:%s", ex.getMessage()));
        }

        return MedicineArray;
    }

    public static ArrayList<String> getAllSleepDisorder() {
        try {
            SleepDisorderArray.clear();
            SleepDisorderArray = httpClient.GetAllSleepDisorderFromServer("http://10.0.2.2:8080/BEAT-PD/User/GET/AllSleepDisorders/");
        } catch (MalformedURLException ex) {
            System.out.println(String.format("Error:%s", ex.getMessage()));
        }

        return SleepDisorderArray;
    }

    public static ArrayList<String> getAllSleepQualitySubMenu() {


        try {
            SleepConditionArray.clear();
            SleepConditionArray = httpClient.GetAllSleepQualitySubMenu("http://10.0.2.2:8080/BEAT-PD/User/GET/AllSleepQuality");
        } catch (MalformedURLException ex) {
            //  System.out.println(String.format("Error:%s", ex.getMessage()));
        }
        return SleepConditionArray;
    }

    public static List<List<SubMenu>> getSubMenuListHabit() {

            subMenuList = httpClient.getSubMenuListHabit();
        return subMenuList;
    }

    public static ArrayList<String> getMedicineSerialNumbers() {
        // subMenuList.clear();
        MedicineSerialNumbers = httpClient.getMedicineSerialNumbers();
        return MedicineSerialNumbers;
    }

}




