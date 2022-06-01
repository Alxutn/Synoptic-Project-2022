import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;


public class MotionSensor {
    String location;
    float xCoord, yCoord;
    int previousSensor;
    Random rand = new Random();
    LocalDateTime lastPing = LocalDateTime.now();

    public MotionSensor(String inputLocation, float inputXCoord, float inputYCoord, int inputPreviousSensor){
        location = inputLocation;
        xCoord = inputXCoord;
        yCoord = inputYCoord;
        previousSensor = inputPreviousSensor;
        checkSurroundings();
    }

    public boolean checkSurroundings(){
        boolean detected = false;
        //fake data about if an object is detected moving nearby
        int n = rand.nextInt(100);
        if(n == 1) {
            detected = true;
        }
        if(detected){
            LocalDateTime timeDetectedRaw = LocalDateTime.now();
            timeComparisons(timeDetectedRaw);
            return true;
        }else{
            return false;
        }
    }

    public boolean timeComparisons(LocalDateTime inputTime) {
        //testAgainstTime = testAgainstTime.plusMinutes(5);
        if(inputTime.isAfter(lastPing)){
            lastPing = inputTime;
            //print it out somewhere!!
            return true;
        }else{
            return false;
        }
    }



    public static void main(String[] args) {
        MotionSensor sensor1 = new MotionSensor("RangerStation1", 0, 0, 0);
    }
}

