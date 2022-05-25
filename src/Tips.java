import java.util.Random;

public class Tips {
    private final String[] tip = new String[]{
            "Try Out Different Characters",
            "Learn the Map",
            "Try to Learn One Position Really Well",
            "Learn the Hotkeys",
            "Stay Behind Minions"
    };

    public String rollRandomTip() {
        Random rng = new Random();
        int number = rng.nextInt(5);
        return tip[number];
    }

}
