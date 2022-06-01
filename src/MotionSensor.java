import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;


public class MotionSensor {
    String location;
    float xCoord, yCoord;
    int previousSensor;
    Random rand = new Random();
    LocalDateTime lastPing = 2007-12-03T10:15:30;

    public MotionSensor(String inputLocation, float inputXCoord, float inputYCoord, int inputPreviousSensor){
        location = inputLocation;
        xCoord = inputXCoord;
        yCoord = inputYCoord;
        previousSensor = inputPreviousSensor;
    }

    public boolean checkSurroundings(){
        //fake data about if an object is detected moving nearby
        boolean detected = false;
        int n = rand.nextInt(100);
        if(n == 1) {
            detected = true;
        }
        if(detected){
            LocalDateTime timeDetectedRaw = LocalDateTime.now();
            if(lastPing){
                lastPing = timeDetectedRaw;
            }else {
                DateTimeFormatter timeDetected = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String formattedDate = timeDetectedRaw.format(timeDetected);
            }
            return true;
        }else{
            return false;
        }
    }
}
