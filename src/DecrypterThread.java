/**
 *Class DecrypterThread is the specification for a thread that decrypts the ciphertext
 *into plaintext. These threads can either be Even or Odd threads depending on what is
 *passed into the constructor. A 0 passed in means it is an Even thread since the loop
 *will start at 0 while a 1 means it is an Odd thread for the same reason. The only 
 *non-constructor method is run because DecrypterThread implements Runnable. run takes
 *characters from the ciphertext monitor, decrypts them, and puts them into the 
 *plaintext Monitor. This thread terminates when the sentinel value '\u0000' is gotten
 *from the ciphertext Monitor.
 */
public class DecrypterThread implements Runnable{
    private Monitor cipherMon; //the cipherMonitor to get characters from
    private Monitor plainMon; //the plainMonitor to put characters in
    private int counter; //counter for the loop that puts and gets chars
    
    /**
     *DecrypterThread constructor, makes new instance of DecrypterThread
     *
     * @param Monitor cipherMon - the cipherMonitor to get chars from
     * @param Monitor plainMon - the plaintext Monitor to put characters
     * @param Integer start - specifies which index to start at (even or odd), 0 
     * means its an even-index thread, 1 means its an odd-index thread.
     */
    public DecrypterThread(Monitor cipherMon, Monitor plainMon, Integer start){
        this.cipherMon = cipherMon;
        this.plainMon = plainMon;
        this.counter = start;
    }
    
    /**
     *run is Runnable's required method. Called when this thread is started. Gets 
     *chars from the ciphertext Monitor, decrypts those characters, and then puts
     *those characters into the plaintext Monitor.
     */
    public void run(){
        char cipherChar; //temporary variable to hold undecrypted chars
        char plainChar; //temporary variable to hold decrypted chars
        try{
        while(true){

            //getting the ciphertext character from the ciphertext Monitor.
            cipherChar = cipherMon.getChar(this.counter);
            //checking for the sentinel value.
            if(cipherChar == '\u0000'){
                //put the sentinel in the plaintext monitor to show termination.
                plainMon.putChar(this.counter, '\u0000'); 
                return;
            }
            //Checking to see if the character is a decryptable letter.
            if(Character.isLetter(cipherChar)){
                //making sure the letter is uppercase
                plainChar = Character.toUpperCase(cipherChar);
                //Decrypting the ciphertext character using our substitution table.
                plainChar = SubTable.key.get(plainChar);
            }
            //If not decryptable, the plaintext is the same as the ciphertext
            else{
                plainChar = cipherChar;
            }
            
            //putting the decrypted character into the plaintext Monitor
            plainMon.putChar(this.counter, plainChar);

            //incrementing by 2 to go to the next even or odd index depending on what 
            //was passed into the constructor
            this.counter += 2;

        }
        //required
        }
        catch(InterruptedException e){
        }
    }
}
