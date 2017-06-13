package javaexercise.corejava.exception11.exceptional;

import java.io.IOException;

public class ExceptionOverride
{

}

class SuperClass{
    public void start() throws IOException{
        throw new IOException("Not able to open file");
    }
}

class SubClass extends SuperClass {
    //Exception is not compatible with throws clause in SuperClass.start()
    /*public void start() throws Exception {
        throw new Exception("Not able to start");
    }*/
    
    public void start() throws IOException {
        throw new IOException("Not able to start");
    }
}
