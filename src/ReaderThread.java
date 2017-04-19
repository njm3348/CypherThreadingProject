import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * Class ReaderThread is the thread that reads the file and puts the characters into
 * the ciphertext monitor.
 *Implements the Runnable interface, run is its only method. Uses a FileInputStream 
 *and a InputStreamReader to read the file
 */
public class ReaderThread implements Runnable{
    private Monitor cipherMon; //the ciphertext Monitor
    private String[] args; //the filename
    
    /**
     * ReaderThread constructor, makes a new instance of ReaderThread
     *
     * @param Monitor cipherMon - the ciphertext monitor to put chars into.
     * @param String[] args - the arguments passed in on command line.
     */
    public ReaderThread(Monitor cipherMon, String[] args){
        this.cipherMon = cipherMon;
        this.args = args;
    }
    
    /**
     * run is Runnable's required method. Called when the thread is started. Reads
     * the file character by character and puts them into the ciphertext monitor.
     */
    public void run(){
        //Checking the number of arguments, should be one, if not exit program.
        if(this.args.length != 1){
            System.out.println("Usage: java Decrypt <file> (Only one file at a time)");
            System.exit(0);
        }

        File file = new File(args[0]); //making a file object for the filename
        
        //tries to open the file given by args[0]
        //throws I/O error if there is a problem.
        try (InputStream in = new FileInputStream(file)){
            Reader reader = new InputStreamReader(in); //the reader
            int counter = 0; //the file index
            int r; // the holder for the char read in.

            //Reads until the end of file.
            while ((r = reader.read()) != -1){
                char ch = (char) r; //must cast the thing we read to a character.
                this.cipherMon.putChar(counter,ch); //putting into monitor
                counter++; //incrementing the counter
            }
            //placing the sentinel value into the monitor to indicate termination.
            this.cipherMon.putChar(counter, '\u0000');
            this.cipherMon.putChar(counter + 1, '\u0000');
            return; //ending thread
        }
        //throws if there is a problem with the file.
        catch(IOException e){
            System.err.println("I/OError: " + e.getMessage());
            System.exit(0);
        }
        //required
        catch(InterruptedException e){
        }
    }
}
