import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class Main {
    static ArrayList<MotionSensor> sensorArrayList = new ArrayList<>();

    public void addSensorUser(){
        Scanner scannerObj = new Scanner(System.in);
        System.out.println("Please name this sensor: \n");
        String inputLocation = scannerObj.nextLine();
        System.out.println("Please give the X Coordinate: \n");
        float xCoord = scannerObj.nextFloat();
        System.out.println("Please give the Y Coordinate: \n");
        float yCoord = scannerObj.nextFloat();
        System.out.println("Please provide the ID of the sensor the new sensor will be connected to: \n");
        int previousSensor = scannerObj.nextInt();
        int inputID = sensorArrayList.size();
        sensorArrayList.add(new MotionSensor(inputLocation, inputID, xCoord, yCoord, previousSensor));
    }

    public static void addExistingSensors() {
        String inputLocation = null, tempString;
        int inputID = 0, previousSensor = 0;
        float xCoord = 0, yCoord = 0;
        try {
            Scanner sc = new Scanner(new File("sensors.csv"));
            sc.useDelimiter(",");
            while (sc.hasNextLine()) {
                for(int i = 0; i < 5; i++) {
                    if (i == 0) {
                        tempString = sc.next();
                        System.out.println(tempString);

                    } else if (i == 1) {
                        tempString = sc.next();
                        System.out.println(tempString);
                        // inputID = Integer.parseInt(tempString);
                        // inputID = sc.nextInt();
                    } else if (i == 2) {
                        tempString = sc.next();
                        System.out.println(tempString);

                        //xCoord = Float.parseFloat(tempString);
                        //xCoord = sc.nextFloat();
                    } else if (i == 3) {
                        tempString = sc.next();
                        System.out.println(tempString);

                        //yCoord = Float.parseFloat(tempString);
                        //yCoord = sc.nextFloat();
                    } else if (i == 4) {
                        tempString = sc.next();
                        System.out.println(tempString);
                        //previousSensor = Integer.parseInt(tempString);
                        //previousSensor = sc.nextInt();
                        System.out.println("end of loop");
                    }
                }
                sensorArrayList.add(new MotionSensor(inputLocation, inputID, xCoord, yCoord, previousSensor));
            }
            sc.close();
        }catch (FileNotFoundException e) {
            FileNotFoundException();
        }
    }

    private static void FileNotFoundException(){
        System.out.println("the sensors file has not been found");
        System.out.println("Please attempt to find the file restart the program");
    }


    public static void main(String[] args) {
       //MotionSensor sensor1 = new MotionSensor("RangerStation1",1, 0, 0, 0);
        addExistingSensors();
        /*
        for(int i = 0; i < sensorArrayList.size(); i++){
            System.out.println(sensorArrayList.get(i));
        }
         */
    }
}
