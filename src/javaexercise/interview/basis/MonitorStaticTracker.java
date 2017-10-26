package javaexercise.interview.basis;

public class MonitorStaticTracker
{
public static void main(String [] args) {
//    System.out.print(Door.class.getClassLoader());
    Door door = new AlarmDoor();
    door.get();
}
}

abstract class Door {
    public static void get() {
        System.out.print("Door");
    }
    public abstract  void getName();
}

class AlarmDoor extends Door {
    public static void get() {
        System.out.print("AlarmDoor");
    }

    @Override
    public synchronized void getName()
    {
       System.out.println("ddd");
    }
    
   
}