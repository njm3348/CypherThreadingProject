import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *Class Decrypt is the main part of the program.
 *Decrypt has the main method which creates all objects required and starts all the
 *threads using a thread pool.
 */
public class Decrypt{
    
    /**
     * The main method of the program, only starts threads into execution using a 
     * thread pool.
     * @param String[] args - the name of the file to be opened. There can only be 
     * one file argument passed.
     */
    public static void main(String[] args){
        ExecutorService pool = Executors.newCachedThreadPool(); //initializing pool
        //Constructing the monitor for the plaintext.
        Monitor plainMonitor = new Monitor();
        //Constructing the monitor for the ciphertext.
        Monitor cipherMonitor = new Monitor();
        //Constructing and starting the thread that prints the plaintext chars
        pool.execute(new PrintThread(plainMonitor));
        //Constructing and starting the thread that Decrypts even index characters
        //the 0 passed in tells it to start at 0 which makes it the even thread
        pool.execute(new DecrypterThread(cipherMonitor, plainMonitor, 0));
        //Constructing and starting the thread that Decrypts odd index characters
        //the 1 passed in tells it to start at 1 which makes it the odd thread
        pool.execute(new DecrypterThread(cipherMonitor, plainMonitor, 1));
        //Constructing and starting the thread that reads the character file
        pool.execute(new ReaderThread(cipherMonitor, args));
        pool.shutdown(); //closing the pool to any further threads.
    }



}
