//https://www.codewars.com/kata/the-wrong-way-cow
//
//Task
//Given a field of cows find which one is the Wrong-Way Cow and return her position.
//
//Notes:
//
//There are always at least 3 cows in a herd
//There is only 1 Wrong-Way Cow!
//Fields are rectangular
//The cow position is zero-based [x,y] of her head (i.e. the letter c)
//Examples
//Ex1
//
//cow.cow.cow.cow.cow
//cow.cow.cow.cow.cow
//cow.woc.cow.cow.cow
//cow.cow.cow.cow.cow
//Answer: [6,2]
//
//Ex2
//
//c..........
//o...c......
//w...o.c....
//....w.o....
//......w.cow
//Answer: [8,4]
//
//Notes
//The test cases will NOT test any situations where there are "imaginary" cows, so your solution does not need to worry about such things!
//
//To explain - Yes, I recognize that there are certain configurations where an "imaginary" cow may appear that in fact is just made of three other "real" cows.
//In the following field you can see there are 4 real cows (3 are facing south and 1 is facing north). There are also 2 imaginary cows (facing east and west).
//
//...w...
//..cow..
//.woco..
//.ow.c..
//.c.....

package extras.the_wrong_way_cow;

public class TheWrongWayCow {

    public static int[] findWrongWayCow(final char[][] field) {
        // Fill in the code to return the x,y coordinate position of the
        // head (letter 'c') of the wrong way cow!
    	int[] Downend = new int[2];
    	int[] Rightend = new int[2];
    	int[] Upend = new int[2];
    	int[] Leftend = new int[2];
    	int CowUp = 0;
    	int CowDown = 0;
    	int CowRight = 0;
    	int CowLeft = 0;
    	
        for(int i = 0; i<field.length;i++) {
        	for(int j = 0; j<field[0].length;j++) {
        		if(j<field[0].length-2&&field[i][j] == 'c'&&field[i][j+1] == 'o'&&field[i][j+2] == 'w') {
        			CowRight++;
        			Rightend[0] = j;
        			Rightend[1] = i;
        		
        		}
        		else if(j>1&&field[i][j] == 'c'&&field[i][j-1] == 'o'&&field[i][j-2] == 'w') {
        			CowLeft++;
        			Leftend[0] = j;
        			Leftend[1] = i;
        			
        		}
        		else if(i>1&&field[i][j] == 'c'&&field[i-1][j] == 'o'&&field[i-2][j] == 'w') {
        			CowUp++;
        			Upend[0] = j;
        			Upend[1] = i;
        			
        		}
        		else if(i<field.length-2&&field[i][j] == 'c'&&field[i+1][j] == 'o'&&field[i+2][j] == 'w') {
        			CowDown++;
        			Downend[0] = j;
        			Downend[1] = i;
        			
        		}
        	}
        }
        System.out.println(CowUp);
        System.out.println(CowDown);
        System.out.println(CowLeft);
        System.out.println(CowRight);
        if(CowDown==1) {
        	System.out.println(Downend[0]+", "+Downend[1]);
        	return Downend;
        }
        else if(CowUp==1) {
        	System.out.println(Upend[0]+", "+Upend[1]);
        	return Upend;
        }
        else if(CowLeft==1) {
        	System.out.println(Leftend[0]+", "+Leftend[1]);
        	return Leftend;
        }
        else if(CowRight==1) {
        	System.out.println(Rightend[0]+", "+Rightend[1]);
        	return Rightend;
        }
        return null;
    }
}
