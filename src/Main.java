import java.io.File;
import java.util.Calendar;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        //Initialization block
        Scanner scanner = new Scanner(System.in);
        boolean viewing = true;

        Enclosure cat_enclosure = new Enclosure("Cat",fetchTemp(),"src/cat.txt");
        Enclosure fox_enclosure = new Enclosure("Fox",fetchTemp(),"src/fox.txt");
        Enclosure hippo_enclosure = new Enclosure("Hippo",fetchTemp(),"src/hippo.txt");
        Enclosure kangaroo_enclosure = new Enclosure("Kangaroo",fetchTemp(),"src/kangaroo.txt");

        System.out.println("Welcome to our teleoperated zoo, if you're wondering why all of the enclosures have different temperatures it's because we gave all the animals thermostats");
        //Program loop
        while(viewing) {
            System.out.println("Please enter the number of the habitat that you'd like to view: "+
                    "\n1 - Cat"+
                    "\n2 - Fox"+
                    "\n3 - Hippo"+
                    "\n4 - Kangaroo\n");
            int inp = scanner.nextInt();
            switch(inp) {
                case -1:
                    viewing = false;
                    break;
                case 1:
                    displayEnclosure(cat_enclosure);
                    break;
                case 2:
                    displayEnclosure(fox_enclosure);
                    break;
                case 3:
                    displayEnclosure(hippo_enclosure);
                    break;
                case 4:
                    displayEnclosure(kangaroo_enclosure);
                    break;
                default:
                    System.out.println("Camera not registered");
            }
        System.out.println("Thank you for visiting the teleoperated zoo");
        }
    }

    //Functions
    public static void displayEnclosure(Enclosure enclosure) {
        System.out.println("\nAnimal type: "+enclosure.getAnimal()+
                "\nCurrent time: "+fetchTime()+
                "\nHabitat temperature = "+ enclosure.getTemp());
        fetchAnimalIMG(enclosure.getFilepath());
        System.out.println();
    }

    public static String fetchTime()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.getTime();
        String placeholder = "";
        int minutes = calendar.get(Calendar.MINUTE);
        if (minutes < 10) {
            placeholder = "0"; //Placeholder turns into a 0 to fill the gap so we don't get something like 2:5
        }
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        if (hours > 12) {
            hours = hours - 12; //No military time!
        }
        return(hours+":"+placeholder+minutes);
    }

    public static double fetchTemp()
    {
        Random rand = new Random();
        return rand.nextDouble(100 - 50 + 1) + 50;
    }

    public static void fetchAnimalIMG(String filepath)
    {
        File file = new File(filepath);
        try{
            Scanner fr = new Scanner(file);
            while(fr.hasNextLine()){
                String i = fr.nextLine();
                System.out.println(i);
            }
        } catch (Exception e){ //If it can't find a file at the provided file path
            System.out.println("No file");
        }
    }
}