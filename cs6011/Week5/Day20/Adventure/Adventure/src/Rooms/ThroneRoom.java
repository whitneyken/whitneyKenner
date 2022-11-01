package Rooms;

import Game.Adventure;
import Items.Item;


//This is Whitney Kenner's code, I worked with Felix Ye to create our interactive rooms
public class ThroneRoom extends Room {
    static boolean hasSeenPainting = false;

    public ThroneRoom() {
        super("Throne room", "A formal room where a monarch presides over official ceremonies");
        Item torch = new Item("Torch", "A brightly burning torch lights up the throne room");
        items_.add(torch);
        Item oldChest = new Item("Chest", "A centuries old chest with ornate inscriptions");
        items_.add(oldChest);
    }

    @Override
    public Item getItem(String name) {
        if (name.equals("Chest") && !hasSeenPainting) {
            System.out.println("The chest is too large to lift and you do not know how to open it.");
            return null;
        } else if (name.equals("Chest")) {
            System.out.println("The chest is still too large to lift, maybe you can try something else....");
            return null;
        } else {
            for (Item item : items_) {
                if (item.getName().equals(name)) {
                    items_.remove(item);
                    return item;
                }
            }
        }
        return null;
    }
    @Override
    public boolean handleCommand( String[] subcommands ) {

        if( subcommands.length <= 1 ) {
            return false;
        }
        String cmd  = subcommands[0];
        String attr = subcommands[1];

        // unlock, use
        if( cmd.equals( "open" ) && attr.equals( "Chest") ) {

            for( Item item : Adventure.inventory ) {
                if( item.getName().equals( "instructions" ) ) {
                    hasSeenPainting = true;
                    break;
                }
            }
            if( hasSeenPainting ) {
                System.out.println( "You follow the directions from the painting and rotate 2 metal hooks 3 " +
                        "times in opposite directions until you hear a click and the lid of the chest opens.\n");
                System.out.println("Inside the opened chest is a large pile of gold");
                Item goldPile = new Item("pile of gold", "A shining pile of gold");
                items_.add(goldPile);
            }
            else {
                System.out.println( "The chest is too large to lift and you do not know how to open it." );
            }
            return true;
        }
        return false;
}


}
