package Model;

public class Elf extends AbstractCharacter {
    Bag myBag;
   public Elf() {
       super("Elf", 100,25, 3, 0.1);
       myBag = new Bag();
   }
}
