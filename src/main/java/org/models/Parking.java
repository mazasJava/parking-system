package org.models;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Projections;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.controllers.DbConnection;
import org.controllers.HistoryController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mongodb.client.model.Aggregates.project;
import static com.mongodb.client.model.Projections.fields;

public class Parking {

    private String adresse;
    private static int numberVec;
    public static List<Car> capacity;

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getNumberVec() {
        return numberVec;
    }

    public void setNumberVec(int numberVec) {
        Parking.numberVec = numberVec;
    }

    public static List<Car> getcapacity() {
        return capacity;
    }

    public static void setcapacity(List<Car> capacity) {
        Parking.capacity = capacity;
    }


    public static Car createCar(ObjectId id, String matricule) throws IOException {
        id = new ObjectId("ds");
        MongoCollection<Car> carMongoCollection = DbConnection.database.getCollection("cars", Car.class);
        Car newCar = new Car().setId(id).setMatricule(matricule);
        try {
            carMongoCollection.insertOne(newCar);
            System.out.println("Successfully inserted Car documents. \n");
            HistoryController.setCarHistorique(newCar.getId());
            return newCar;
        } catch (Exception e) {
            return null;
        }
    }

    public Parking(String adresse, int numberVec) {
        this.adresse = adresse;
        this.numberVec = numberVec;
        capacity = new ArrayList<Car>();
    }

    @Override
    public String toString() {
        return "Parking{" +
                ", adresse='" + adresse + '\'' +
                ", numberVec=" + numberVec +
                '}';
    }
    public static boolean addCar(Car c){
        if(capacity.size() == numberVec){
            return false;
        }
        return capacity.add(c);
    }
    public static boolean removeCar(Car c){
        return capacity.remove(c);
    }
    public static Car removeCar(int index){
        return capacity.remove(index);
    }
    public static int[] getState(){
        int[] state = new int[3];
        state[0] = Parking.numberVec;//Total capacity
        state[1] = Parking.numberVec - capacity.size();//free
        state[2] = Parking.capacity.size();//
        return state;
    }
    public static void main(String[] args) {
    }
}
