package Model;

public class Wizard extends Hero {

    public Wizard() {
        super("Wizard", 100, 25, 3, 0.1,
                new GameItem[] {new TimeTurner(), new SoulCharm()});
    }
}
