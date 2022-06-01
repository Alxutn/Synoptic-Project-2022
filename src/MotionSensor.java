import java.time.LocalDateTime;
import java.time.Month;
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
        //fake data about if an object is detected moving nearby
        boolean detected = false;

        compareMonth(lastPing.getMonth());

        int n = rand.nextInt(100);
        if(n == 1) {
            detected = true;
        }
        if(detected){
            LocalDateTime timeDetectedRaw = LocalDateTime.now();

            //if(lastPing){
                //lastPing = timeDetectedRaw;
            //}else {

                DateTimeFormatter timeDetected = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String formattedDate = timeDetectedRaw.format(timeDetected);
            //}
            return true;
        }else{
            return false;
        }
    }

    public void compareMonth(Month inputMonth){
        if(inputMonth.equals(Month.JUNE)) {
            System.out.println(inputMonth);
        }
    }
    public void compareMinute(int inputMinute){
        System.out.println(inputMinute);
    }
    public void compareYear(int inputYear){
        System.out.println(inputYear);
    }

    public static void main(String[] args) {
        MotionSensor sensor1 = new MotionSensor("RangerStation1", 0, 0, 0);
    }
}

