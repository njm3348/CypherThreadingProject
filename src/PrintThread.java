/**
 *Class PrintThread is the thread that prints the characters from the plaintext
 *monitor.
 *PrintThread implements the Runnable interface and because of that its only 
 *non-constructer method is the run method. The only thing passed into PrintThread's
 *constructor is the plaintext Monitor from which PrintThread gets the characters to 
 *print. The run method only gets characters from the Monitor and prints them. 
 *The thread terminates when the sentinel value '\u0000' is gotten from the monitor.
 */
public class PrintThread implements Runnable{
    private Monitor plainMon; //the plaintext monitor
    
    /**
     * PrintThread constructor, makes new instance of PrintThread
     *
     * @param Monitor plainMon - the plainText monitor to get chars from
     */
    public PrintThread(Monitor plainMon){
        this.plainMon = plainMon;
    }
    /**
     *run is Runnable's required method. Called when the thread is started. Gets
     *characters from the plaintext monitor and prints them. 
     */
    public void run(){
        char c;
        int counter = 0; //the index from which to get the character.
        try{
            while(true){
                c = plainMon.getChar(counter);
                //checking for sentinel value.
                if(c == '\u0000'){
                    return;
                }
                //Printing the character
                System.out.print(c);
                counter++; //incrementing index
            }
        }
        //required
        catch(InterruptedException e){
        }
        }

}
