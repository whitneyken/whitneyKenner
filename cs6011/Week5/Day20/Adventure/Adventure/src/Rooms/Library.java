package Rooms;

import Game.Adventure;
import Items.Item;

public class Library extends Room {

    private boolean fireplaceLit_ = false;

    private boolean hasTorch = false;
    private boolean seenInstruction = false;


    public Library() {
        super("Library", "Large room surrounded by ceiling height shelves filled with books and a fireplace at the back wall");
        Item firePlace = new Item("Fireplace", "A regal fireplace placed at the back wall with logs already inside");
        items_.add(firePlace);
        Item painting = new Item("Painting", "A giant painting above the fire place, you can't quite make out the content due to lack of lighting");
        items_.add(painting);
    }

    @Override
    public Item getItem(String name) {
        if (name.equals("fireplace") && !fireplaceLit_) {
            System.out.println("there's no point taking the logs from the fireplace");
            return null;
        }
        if (name.equals("fireplace")) {
            System.out.println("The fire is burning, don't burn yourself!");
            return null;
        }
        if (name.equals("painting")) {
            System.out.println("you cannot reach the painting");
            return null;
        }
        return null;
    }



    @Override
    public boolean handleCommand(String[] subcommands) {

        if (subcommands.length <= 1) {
            return false;
        }
        String cmd = subcommands[0];
        String attr = subcommands[1];

        if (cmd.equals("light") && attr.equals("fireplace")) {
            for (Item item : Adventure.inventory) {
                if (item.getName().equals("Torch")) {
                    hasTorch = true;
                    break;
                }
            }
            if (hasTorch) {
                System.out.println("You lit the fireplace with the torch in your hand");
                System.out.println("The painting above the fire place is finally visible, it shows the instruction on how to open up the chest in the throne room");
                fireplaceLit_ = true;
                ThroneRoom.hasSeenPainting = true;
            } else {
                System.out.println("You have nothing to light up the fireplace with");
            }
            return true;
        }
        return false;
    }
}