public class MotionSensor {
    String location;
    float xCoord, yCoord;
    int previousSensor;

    public MotionSensor(String inputLocation, float inputXCoord, float inputYCoord, int inputPreviousSensor){
        location = inputLocation;
        xCoord = inputXCoord;
        yCoord = inputYCoord;
        previousSensor = inputPreviousSensor;
    }
}
