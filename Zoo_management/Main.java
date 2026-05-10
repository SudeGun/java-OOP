import java.io.*;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        /**
         *
         * @param args Command-line arguments containing file paths for:
         *             args[0] - animals file
         *             args[1] - persons file
         *             args[2] - foods file
         *             args[3] - commands file
         *             args[4] - output file
         */
        String animals = args[0];
        String persons = args[1];
        String foods = args[2];
        String commands = args[3];
        String output = args[4];
        Map<String,Animals> allAnimals = readAnimals(animals,output);
        Map<String,People> allPersons = readPersons(persons,output);
        Map<String, Double> allFoods = readFoods(foods,output);
        readCommands(commands,output,allAnimals,allPersons,allFoods);

    }
    /**
     * Reads animal data from the specified file and logs initialization output.
     *
     * @param animals Path to the animal input file.
     * @param output  Path to the output file to log initialization details of animals.
     * @return Map of animal name to Animals object.
     */
    public static Map<String,Animals> readAnimals(String animals, String output) {
        Map<String, Animals> allAnimals = new LinkedHashMap<>();  //creating a map to store animal names and Animals objects
        try (BufferedReader br = new BufferedReader(new FileReader(animals))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[1];
                String type = parts[0];
                Animals animal = null;
                switch (type) {
                    case "Lion":
                         animal = new Lion(name, Integer.parseInt(parts[2])); //creating a lion object
                        break;
                    case "Elephant":
                         animal = new Elephant(name, Integer.parseInt(parts[2])); //creating a elephant object
                        break;
                    case "Penguin":
                         animal = new Penguin(name, Integer.parseInt(parts[2])); //creating a penguin object
                        break;
                    case "Chimpanzee":
                         animal = new Chimpanzee(name, Integer.parseInt(parts[2])); //creating a chimpanzee object
                        break;
                }
                allAnimals.put(name,animal); // animal name to animal object
            }

        }catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(output, true))){
            writer.write("***********************************\n");
            writer.write("***Initializing Animal information***\n");
            for(Animals value : allAnimals.values()){
                writer.write("Added new "+ value.getType()+" with name "+value.getName()+" aged "+value.getAge()+".\n");

            }
        }catch (IOException e) {
            e.printStackTrace();
        } return allAnimals;
    }
    /**
     * Reads person data from the specified file and logs initialization output.
     *
     * @param persons Path to the person input file.
     * @param output  Path to the output file to log initialization details of people.
     * @return Map of person ID to People object.
     */
    public static Map<String,People> readPersons(String persons, String output){
        Map<String, People> allPersons = new LinkedHashMap<>(); //creating a map to store person IDs and People objects
        try (BufferedReader br = new BufferedReader(new FileReader(persons))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String type = parts[0];
                String id = parts[2];
                People person = null;
                if (type.equals("Visitor")) {
                    person = new Visitor(parts[1], id); //creating visitor object
                } else if (type.equals("Personnel")) {
                    person = new Personnel(parts[1], id); //creating personnel object
                }
                allPersons.put(id,person);  //person id to people object
            }

        }catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(output, true))){
            writer.write("***********************************\n");
            writer.write("***Initializing Visitor and Personnel information***\n");
            for(People value : allPersons.values()){
                writer.write("Added new "+value.getType()+" with id "+ value.getId()+" and name "+ value.getName()+".\n");

            }
        }catch (IOException e) {
            e.printStackTrace();
        }return allPersons;
    }
    /**
     * Reads food stock data and logs initialization output.
     *
     * @param foods  Path to the food input file.
     * @param output Path to the output file to log initialization details of food stack.
     * @return Map of food type to amount.
     */
    public static  Map<String, Double> readFoods(String foods, String output){
        Map<String, Double> allFoods = new HashMap<>();  //creating a map to store food types and food stocks
        try (BufferedReader br = new BufferedReader(new FileReader(foods))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                double amount = Double.parseDouble(parts[1]);
                allFoods.put(parts[0],amount);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(output, true))){
            writer.write("***********************************\n");
            writer.write("***Initializing Food Stock***\n");
            writer.write("There are " + String.format(Locale.US,"%.3f", allFoods.get("Meat")) + " kg of Meat in stock\n");
            writer.write("There are " + String.format(Locale.US,"%.3f", allFoods.get("Fish")) + " kg of Fish in stock\n");
            writer.write("There are " + String.format(Locale.US,"%.3f", allFoods.get("Plant")) + " kg of Plant in stock\n");
        }catch (IOException e) {
            e.printStackTrace();
        }return allFoods;
    }
    /**
     * Processes a series of zoo management commands such as feeding animals, visiting and listing food stock.
     *
     * @param commands   Path to the command input file.
     * @param output     Path to the output file to log actions and errors.
     * @param allAnimals Map of animal names to Animals objects.
     * @param allPersons Map of person IDs to People objects.
     * @param allFoods   Map of food type to quantity.
     */
    public static void readCommands(String commands,String output, Map<String,Animals> allAnimals, Map<String,People> allPersons, Map<String, Double> allFoods){
        try (BufferedReader br = new BufferedReader(new FileReader(commands))) {
            String line;
            while ((line = br.readLine()) != null) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(output, true))){
                    writer.write("***********************************\n");
                    writer.write("***Processing new Command***\n");
                }
                String[] parts = line.split(",");
                if(parts[0].equals("List Food Stock")){  //first command type
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(output, true))){
                        writer.write("Listing available Food Stock:\n");
                        writer.write("Plant: "+String.format(Locale.US,"%.3f", allFoods.get("Plant"))+" kgs\n");
                        writer.write("Fish: "+String.format(Locale.US,"%.3f", allFoods.get("Fish"))+" kgs\n");
                        writer.write("Meat: "+String.format(Locale.US,"%.3f", allFoods.get("Meat"))+" kgs\n");
                    }
                } else if (parts[0].equals("Animal Visitation")) {  //second command type
                    String id = parts[1];
                    String nameOfAnimal = parts[2];
                    try{
                        People person = allPersons.get(id);
                        if (person == null) { //checking if the id exist
                            throw new AllExceptions.NonexistingInputException("Error: There are no visitors or personnel with the id " + id);  //throwing a custom exception if that person doesn't exist
                        }else {
                            try{
                                Animals animal = allAnimals.get(nameOfAnimal);
                                String message1 = person.attemptToVisit(nameOfAnimal); //writing the attempting message to file
                                try (BufferedWriter writer = new BufferedWriter(new FileWriter(output, true))) {
                                    writer.write(person.getName()+message1+ "\n");
                                } catch (IOException ioException) {
                                    ioException.printStackTrace();
                                }
                                if(animal == null){ // checking if the name exist
                                    throw new AllExceptions.NonexistingInputException("Error: There are no animals with the name "+ nameOfAnimal+"."); //throwing a custom exception if that animal doesn't exist
                                }else{
                                    String message2 = person.visit(animal); //writing the visiting message to file
                                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(output, true))) {
                                        writer.write(person.getName()+message2+ "\n");
                                    } catch (IOException ioException) {
                                        ioException.printStackTrace();
                                    }
                                }
                            }catch (AllExceptions.NonexistingInputException e){
                                try (BufferedWriter writer = new BufferedWriter(new FileWriter(output, true))) {
                                    writer.write(e.getMessage()+ "\n");
                                } catch (IOException ioException) {
                                    ioException.printStackTrace();
                                }
                            }
                        }
                    }catch (AllExceptions.NonexistingInputException e){
                        try (BufferedWriter writer = new BufferedWriter(new FileWriter(output, true))) {
                            writer.write(e.getMessage()+ "\n");
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                } else if (parts[0].equals("Feed Animal")) {
                    String id = parts[1];
                    String nameOfAnimal = parts[2];
                    try {
                        int numberOfMeals = Integer.parseInt(parts[3]);  //checking the type in case it isn't integer
                    }catch (NumberFormatException e){
                        try (BufferedWriter writer = new BufferedWriter(new FileWriter(output, true))) {
                            writer.write("Error processing command: "+line+ "\n");
                            writer.write("Error:For input string: \""+parts[3]+"\"\n");
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }continue;
                    }
                    try{
                        People person = allPersons.get(id);
                        if (person == null) {  //checking if the id exist
                            throw new AllExceptions.NonexistingInputException("Error: There are no visitors or personnel with the id " + id); //throwing a custom exception if that person doesn't exist
                        }else{
                            try{
                                Animals animal = allAnimals.get(nameOfAnimal);
                                String message = person.attemptToFeed(nameOfAnimal); //writing the attempting message to file
                                try (BufferedWriter writer = new BufferedWriter(new FileWriter(output, true))) {
                                    writer.write(person.getName()+message+ "\n");
                                } catch (IOException ioException) {
                                    ioException.printStackTrace();
                                }if(animal == null){ // checking if the name exist
                                    throw new AllExceptions.NonexistingInputException("Error: There are no animals with the name "+ nameOfAnimal+"."); //throwing a custom exception if that animal doesn't exist
                                }else{
                                    double amount = animal.getAmount(animal,Integer.parseInt(parts[3]));  //calculating the amount of food to feed the animal
                                    try{
                                        String message3 = animal.checkingAmount(allFoods,amount);  //checking if we have enough food stock
                                        String foodType = animal.getFoodType();
                                        switch (foodType){  //updating the food stocks
                                            case "meat":
                                                allFoods.put("Meat", allFoods.get("Meat") - amount);
                                                break;
                                            case "plant":
                                                allFoods.put("Plant", allFoods.get("Plant") - amount);
                                                break;
                                            case "fish":
                                                allFoods.put("Fish", allFoods.get("Fish") - amount);
                                                break;
                                            case "meat and plant":
                                                allFoods.put("Meat", allFoods.get("Meat") - amount);
                                                allFoods.put("Plant", allFoods.get("Plant") - amount);
                                                break;
                                        }
                                        try (BufferedWriter writer = new BufferedWriter(new FileWriter(output, true))) {
                                            writer.write(animal.getName()+message3+ "\n");
                                        } catch (IOException ioException) {
                                            ioException.printStackTrace();
                                        }
                                    }catch (AllExceptions.NotEnoughFoodException e){
                                        try (BufferedWriter writer = new BufferedWriter(new FileWriter(output, true))) {
                                            writer.write(e.getMessage()+ "\n");
                                        } catch (IOException ioException) {
                                            ioException.printStackTrace();
                                        }
                                    }
                                }
                            }catch(AllExceptions.VisitorFeedException e){
                                try (BufferedWriter writer = new BufferedWriter(new FileWriter(output, true))) {
                                    writer.write(person.getName()+e.getMessage()+ "\n");
                                } catch (IOException ioException) {
                                    ioException.printStackTrace();
                                }
                            }
                        }
                    }catch (AllExceptions.NonexistingInputException e){
                        try (BufferedWriter writer = new BufferedWriter(new FileWriter(output, true))) {
                            writer.write(e.getMessage()+ "\n");
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                }
            }
        } catch (IOException e) {
                e.printStackTrace();
        }
    }
}