import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;


public class Main {
    static ArrayList<MotionSensor> sensorArrayList = new ArrayList<>();
    static Scanner usrInput = new Scanner(System.in);

    public static void addSensorUser(){
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
        String inputLocation;
        int inputID, previousSensor;
        float xCoord, yCoord;
        String line;
        String splitBy = ",";
        try {
            BufferedReader br = new BufferedReader(new FileReader("sensors.csv"));
            while((line = br.readLine()) != null){
                String[] sensor = line.split(splitBy);
                inputLocation = sensor[0];
                inputID = Integer.parseInt(sensor[1]);
                xCoord = Float.parseFloat(sensor[2]);
                yCoord = Float.parseFloat(sensor[3]);
                previousSensor = Integer.parseInt(sensor[4]);
                sensorArrayList.add(new MotionSensor(inputLocation, inputID, xCoord, yCoord, previousSensor));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void introductionMessage(){
        System.out.println("""

                Sensor System 2022 (c) University of East Anglia.\s
                Welcome to the sensor system, sensor notifications are automatic.\s
                Please select an option by typing in a number below:\s""");
    }

    private static void displayOptions(){
        System.out.println("""
                1. Check status of all sensors.
                2. Add additional sensor.\s
                3. Remove sensor(maybe if we have time).\s
                4. Exit and save""");
    }

    private static void displaySensors(){
        for (MotionSensor motionSensor : sensorArrayList) {
            System.out.println(motionSensor);
        }
    }

    private static void exitProgram() throws IOException {
        new FileOutputStream("sensors.csv").close();
        PrintWriter writer = new PrintWriter("sensors.csv");
        for (MotionSensor motionSensor : sensorArrayList) {
            String data = motionSensor.convertToCSV();
            writer.write(data);
        }
        writer.close();
    }

    private static void userOptions(){
        while(!usrInput.hasNextInt()){
            System.out.println("That is not an option, try again");
            usrInput.next();
            displayOptions();
        }
        int usrOption = usrInput.nextInt();
        switch (usrOption) {
            case 1 -> {
                System.out.println("Checking status...\n");
                displaySensors();
                System.out.println("\n");
            }
            case 2 -> {
                System.out.println("Adding additional sensor");
                addSensorUser();
            }
            case 3 -> System.out.println("Removing Sensor");
            case 4 -> {
                System.out.println("Saving and exiting...");
                try {
                    exitProgram();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.exit(0);
            }
            default -> System.out.println("Invalid option");
        }
    }


    public static void main(String[] args) {
        addExistingSensors();
        introductionMessage();
        while(true) {
            displayOptions();
            userOptions();
        }
    }
}
