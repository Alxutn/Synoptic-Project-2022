import java.io.*;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.ArrayList;

public class Main extends Thread{
    static ArrayList<MotionSensor> sensorArrayList = new ArrayList<>(); //List of motion sensors
    static Scanner usrInput = new Scanner(System.in);
    static Thread t1;
    static Thread t2;

    public static void addSensorUser(){ //Adding a sensor using user input
        Scanner scannerObj = new Scanner(System.in);
        System.out.println("Please name this sensor: \n");
        String inputLocation = scannerObj.nextLine();
        System.out.println("Please give the X Coordinate: \n");
        float xCoord = scannerObj.nextFloat();
        System.out.println("Please give the Y Coordinate: \n");
        float yCoord = scannerObj.nextFloat();
        System.out.println("Please provide the ID of the sensor the new sensor will be connected to: \n");
        int previousSensor = scannerObj.nextInt();
        int inputID = sensorArrayList.get(sensorArrayList.size()-1).getSensorID()+1;
        t1.interrupt();
        sensorArrayList.add(new MotionSensor(inputLocation, inputID, xCoord, yCoord, previousSensor)); //No need for last ping input here
        sensorArrayList.get(sensorArrayList.size()-1).setLastPing(LocalDateTime.now());
        try {
            exitProgram();
            System.out.println("Please disregard error text");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    public static void addExistingSensors() { //This function will add all sensors present in the csv
        String inputLocation, line, splitBy = ",";
        int inputID, previousSensor, counter = 0;
        float xCoord, yCoord;
        LocalDateTime lastPing;
        try {
            BufferedReader br = new BufferedReader(new FileReader("sensors.csv"));
            while((line = br.readLine()) != null){
                String[] sensor = line.split(splitBy);
                inputLocation = sensor[0];
                inputID = Integer.parseInt(sensor[1]);
                xCoord = Float.parseFloat(sensor[2]);
                yCoord = Float.parseFloat(sensor[3]);
                previousSensor = Integer.parseInt(sensor[4]);
                lastPing = LocalDateTime.parse(sensor[5]);//Used for Last Ping
                sensorArrayList.add(new MotionSensor(inputLocation, inputID, xCoord, yCoord, previousSensor));
                sensorArrayList.get(counter).setLastPing(lastPing);
                counter++;
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

    public static void displayOptions(){
        System.out.println("""
                1. Check status of all sensors.
                2. Add additional sensor.(This will cause the program to restart)\s
                3. Remove sensor. (This will cause the program to restart)\s
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
                System.out.println("Adding additional sensor...");
                addSensorUser();
            }
            case 3 -> {
                System.out.println("Removing Sensor... ");
                removeSensorInput();
            }
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

    public static void removeSensorInput(){
        System.out.println("Please input the ID of the sensor you would like removed: ");
        int usrOption = usrInput.nextInt();
        removeSensor(usrOption);
    }

    public static void removeSensor(int inputID){
        for(int i = 0; i < sensorArrayList.size(); i++){
            if(sensorArrayList.get(i).getSensorID() == inputID){
                t1.interrupt();
                sensorArrayList.remove(i);
            }
        }
        try {
            exitProgram();
            System.out.println("Please disregard error text");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    public static void beginProgram(){
        t1 = new Thread(() -> {
            for(;;) {
                for (MotionSensor motionSensor : sensorArrayList) {
                    motionSensor.checkSurroundings();
                }
            }
        });

        t2 = new Thread(() -> {
            for (; ; ) {
                displayOptions();
                userOptions();
            }
        });


        t1.start();
        t2.start();
    }



    public static void main(String[] args) {
        addExistingSensors();
        introductionMessage();
        beginProgram();
    }
}
