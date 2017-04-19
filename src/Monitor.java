/**
 * Class Monitor is the specification for any monitor to be used.
 * A monitor can either put a character into its contents at a specified index or it
 * can get a character from its contents at a specified index.
 *
 * @author Nick Marchionda
 */
public class Monitor{
    private Character contents; //the contents of the monitor
    private boolean valuePresent; //if a value is present or not
    private Integer currIndex; //the current index where characters are being put

    /**
     *Monitor constructor, makes new instance of Monitor
     */
    public Monitor(){
        valuePresent = false; //there is no value present initially
        this.currIndex = 0; //the index starts at 0
        contents = '0'; //arbitrary value to avoid null pointer exception
    }
    /**
     * putChar puts a character into the contents at the index specified. Only puts
     * it there if there is nothing there currently
     *
     * @param int i - the index to put the character
     * @param char c - the character to put
     */
    public synchronized void putChar(int i, char c) throws InterruptedException{

        //error check: If a character has already been placed at this index
        //checks to see if the passed in index is less than what the current index is
        //if so, this means that the index already had a character placed there earlier
        if( i < this.currIndex){
            System.err.println("A character has already been placed at this index");
            System.exit(0);
        }
        //Condition: There is no character currently at the index.
        while (valuePresent || (i != this.currIndex)){
            wait();
        }
        this.contents = c; //actually placing the char
        this.valuePresent = true; //there is now a char present
        this.currIndex += 1; //add onto the index
        notifyAll(); //notify waiting threads
    }
    
    /**
     * getChar gets a character from contents at a specified index. Does not return
     * until the character at index i has been put.
     *
     * @param int i - the index to get the character from
     * @return char the character at the index
     */
    public synchronized char getChar(int i) throws InterruptedException{
        Character tempChar; //temporary variable to hold the char we get.

        //Condition: There is a character at the index.
        while ( (!this.valuePresent) || (i == this.currIndex)){
            wait();
        }
        tempChar = this.contents; //actually getting the char
        this.valuePresent = false; //there is no longer a character present
        notifyAll(); //notify waiting threads
        return tempChar; //returning the char we got

    }
}
