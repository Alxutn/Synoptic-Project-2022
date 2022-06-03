import java.time.LocalDateTime;
import java.util.Random;
import java.time.format.DateTimeFormatter;


public class MotionSensor {
    String location;
    float xCoord, yCoord;
    int previousSensor, sensorID;
    LocalDateTime lastPing = LocalDateTime.now();
    //for random data
    Random rand = new Random();

    public MotionSensor(String inputLocation, int inputSensorID, float inputXCoord, float inputYCoord, int inputPreviousSensor){
        location = inputLocation;
        xCoord = inputXCoord;
        yCoord = inputYCoord;
        previousSensor = inputPreviousSensor;
        sensorID = inputSensorID;
    }

    public float getxCoord(){
        return xCoord;
    }
    public float getyCoord(){
        return yCoord;
    }
    public int getPreviousSensor(){
        return previousSensor;
    }
    public String getLocation(){
        return location;
    }
    public int getSensorID(){
        return sensorID;
    }

    public void setxCoord(float value){
        xCoord = value;
    }
    public void setyCoord(float value){
        yCoord = value;
    }
    public void setPreviousSensor(int value){ previousSensor = value; }
    public void setLocation(String value){
        location = value;
    }
    public void setSensorID(int value){
        sensorID = value;
    }

    public String toString(){
        return location +"ID: "+sensorID+" X Coordinate: "+xCoord+" Y Coordinate "+yCoord+" Connected to sensor "+previousSensor;
    }

    public void checkSurroundings(){
        boolean detected = false;

        //fake data about if an object is detected moving nearby
        int n = rand.nextInt(100);
        if(n == 1) {
            detected = true;
        }

        if(detected) {
            LocalDateTime timeDetectedRaw = LocalDateTime.now();
            if(timeComparisons(timeDetectedRaw)){
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                System.out.println("Movement Detected at: "+getLocation()+" at: "+lastPing.format(dateFormat));
                //for next time start looking at CSV.
            }
        }
    }

    public boolean timeComparisons(LocalDateTime inputTime) {
        //testAgainstTime = testAgainstTime.plusMinutes(5);
        LocalDateTime tempLastPing = lastPing.plusMinutes(5);
        System.out.println(tempLastPing);
        System.out.println(lastPing);
        if(inputTime.isAfter(tempLastPing)){
            lastPing = inputTime;
            //print it out somewhere!!
            return true;
        }else{
            return false;
        }
    }



    public static void main(String[] args) {
        MotionSensor sensor1 = new MotionSensor("RangerStation1", 1, 0, 0, 0);
    }
}

