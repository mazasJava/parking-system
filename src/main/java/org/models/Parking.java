package org.models;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.types.ObjectId;
import org.controllers.DbConnection;
import org.controllers.HistoryController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Parking {

    private String adress;
    private static int capacity;
    public static List<Car> listCars;

    public String getadress() {
        return adress;
    }

    public void setadress(String adress) {
        this.adress = adress;
    }

    public int getcapacity() {
        return capacity;
    }

    public void setcapacity(int capacity) {
        Parking.capacity = capacity;
    }

    public static List<Car> getlistCars() {
        return listCars;
    }

    public static void setlistCars(List<Car> listCars) {
        Parking.listCars = listCars;
    }


    public static Car createCar(ObjectId id, String matricule) throws IOException {
        id = new ObjectId();
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

    public static boolean releaseCarFromDataBase(ObjectId id) {
        try {
            Date date = new Date(System.currentTimeMillis());
            MongoCollection<History> collection = DbConnection.database.getCollection("historys", History.class);
            collection.updateOne(Filters.eq("_id", id), Updates.set("dateRelease", date));
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public static boolean carOut(ObjectId id) {
        return releaseCarFromDataBase(id);
    }

    public static void carIn(String matricule) throws IOException {
        addCar(createCar(new ObjectId(), matricule));
    }

    public Parking(String adress, int capacity) {
        this.adress = adress;
        this.capacity = capacity;
        listCars = new ArrayList<Car>();
    }


    @Override
    public String toString() {
        return "Parking{" +
                ", adress='" + adress + '\'' +
                ", capacity=" + capacity +
                '}';
    }

    public static boolean addCar(Car c) throws IOException {
        if (listCars.size() == capacity) {
            return false;
        }
        return listCars.add(c);
    }

    public static boolean removeCar(Car c) {
        return listCars.remove(c);
    }

    public static Car removeCar(int index) {
        return listCars.remove(index);
    }

    public static int[] getState() {
        int[] state = new int[3];
        state[0] = Parking.capacity;//Total listCars
        state[1] = Parking.capacity - listCars.size();//free
        state[2] = Parking.listCars.size();//full
        return state;
    }

    public static void main(String[] args) throws IOException {

    }
}