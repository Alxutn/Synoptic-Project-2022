import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class MotionSensor {
    String location;
    float xCoord, yCoord;
    int previousSensor, sensorID;
    LocalDateTime lastPing;
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    //for random data
    SecureRandom newRandom = new SecureRandom();

    public MotionSensor(String inputLocation, int inputSensorID, float inputXCoord, float inputYCoord, int inputPreviousSensor){
        location = inputLocation;
        xCoord = inputXCoord;
        yCoord = inputYCoord;
        previousSensor = inputPreviousSensor;
        sensorID = inputSensorID;
    }

    //getters and setters
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
    public void setLastPing(LocalDateTime value){ lastPing = value; }

    public String toString(){
        return location +", ID: "+sensorID+", X Coordinate: "+xCoord+
                ", Y Coordinate "+yCoord+", Connected to sensor "+previousSensor+
                ", Last ping: "+lastPing.format(dateFormat);
    }



    public void checkSurroundings(){ //This is constantly running
        boolean detected = false;

        //fake data about if an object is detected moving nearby
        int n = newRandom.nextInt(30000000);
        if(n == 1) {
            detected = true;
        }

        if(detected) {//if the fake data is detected
            LocalDateTime timeDetectedRaw = LocalDateTime.now(); //grab a copy of the current time
            if(timeComparisons(timeDetectedRaw)){ //our own comparison for the times
                System.out.println("Movement Detected at: "+getLocation()+" at: "+lastPing.format(dateFormat));
                //Main.displayOptions();
            }
        }
    }

    public boolean timeComparisons(LocalDateTime inputTime) {          //comparing lastPing and the current time at detection
        //These are used for testing --> the top line: (plusMinutes(5)) is what should be used.
        //LocalDateTime tempLastPing = lastPing.plusMinutes(5);
        //LocalDateTime tempLastPing = lastPing.plusSeconds(3);
        LocalDateTime tempLastPing = lastPing.plusMinutes(1);
        if(inputTime.isAfter(tempLastPing)){             //Comparison done here
            lastPing = inputTime;                        //updating LastPing to have the new value
            return true;
        }else{
            return false;
        }
    }

    public String convertToCSV(){
        return location+','+sensorID+','+xCoord+','+yCoord+','+previousSensor+','+lastPing+'\n';
    }

    public static void main(String[] args) {

    }
}

