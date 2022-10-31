package Rooms;

import Items.Item;

import java.util.ArrayList;

public class Room {

    private String name_;
    private String description_;

    protected ArrayList<Item> items_ = new ArrayList<>();
    ArrayList<Room> doors_ = new ArrayList<>();

    public Room( String name, String description ) {
        name_ = name;
        description_ = description;
    }

    public void print() {
        System.out.println( "You are in the " + name_ + ", " + description_ + "\n" );
        System.out.println( "You see the following doors:" );

        int num = 1;
        for( Room r : doors_ ) {
            System.out.println( "   " + num + " - " + r.name_ );
            num++;
        }
        if( items_.size() > 0 ) {
            System.out.println( "You see the following items: " );
            for( Item item : items_ ) {
                System.out.println( "   " + item + ": "  + item.getDescription() );
            }
        }
    }

    public void addConnection( Room room ) {
        if( doors_.contains( room ) ) {
            System.out.println( "WARNING: tried to add " + room.name_ + " to " + name_ + 
                                " but it already is connected." );
        }
        else {
            doors_.add( room );
        }

        if( !room.doors_.contains( this ) ) {
            room.doors_.add( this );
        }
    }

    public Room goThroughDoor( int doorNum ) {
        // doorNum comes in 1-based
        // doors_ is 0-based
        doorNum = doorNum - 1;

        if( doorNum > doors_.size() || doorNum < 0 ) {
            System.out.println( "There is no such door." );
            return null;
        }
        else {
            Room newRoom = doors_.get( doorNum );
            System.out.println( "You enter the " + newRoom.name_ );
            return newRoom;
        }
    }

    public void addItem( Item item ) {
        items_.add( item );
    }

    public String getName() { return name_; }

    // Override this method if you want your room to be notified each time a second passes.
    public void heartbeat() {}

    // For most rooms, nothing happens when a player leaves...
    public void playerLeft() {}

    // For most rooms, nothing happens when a player enters...
    public void playerEntered() {
    }

    public Item getItem( String name ) {
        for( Item item : items_ ) {
            if( item.getName().equals( name ) ) {
                items_.remove( item );
                return item;
            }
        }
        return null;
    }
    public boolean handleCommand(String[] subcommands) {
        return false;
    }
}
