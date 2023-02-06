/**
Program:BattleShip
Language:Java
Programmer:Poon Pak Hin     
class:SE1A
Student ID:190003296
**/

import java.util.Scanner;
public class BattleShip{
	public static Scanner input = new Scanner(System.in);			//scanner
	public static char [][] grid1 = new char[10][10]; 				//create a new 2D table
	public static char [][] grid2 = new char[10][10]; 				//create more new 2D table
	public static void main(String [] args) { 
		for(int i=0;i<=9;i++){ 										//write a for loop to set the grid1_table all is .
			for(int k=0;k<grid1.length;k++){
				grid1 [i][k] ='.';
			}
			for(int k=0;k<grid2.length;k++){ 						//same as grid1
					grid2 [i][k] ='.';
			}
		}
		while(true){   												//checking user must input 0 or 1 orx
			System.out.print("Battleship Player1 (0 - Human, 1 - CPU, x - Exit):"); //remind user input									
			String n = input.nextLine();     						//user input           						
			if(n.equals("0")){	
				HumanMode();										//run method	
				break;												//leave the program
			}
			if(n.equals("1")){
				CPUMode();											//run method
				break;												//leave the program
			}
			if(n.equals("x")){  
				xExit(n);											//a method use to leave program
				break;												//leave the program
			}
			System.out.println("Please input 0 or 1, x - Exit "); 	//wrong statement
		}
	}	
	public static void HumanMode(){ 
		printTable(grid1);	 										//print the table grid1
		System.out.println("Player1: Set the ship: Carrier, ship size: 5"); //print statement
		checkNum(5,"Carrier"); 										//user method and past the value to method
		System.out.println("Player1: Set the ship: Battleship, ship size: 4");//print statement
		checkNum(4,"Battleship"); 									//user method and past the value to method
		System.out.println("Player1: Set the ship: Destroyer, ship size: 3");//print statement
		checkNum(3,"Destroyer"); 									//user method and past the value to method
		System.out.println("Player1: Set the ship: Submarine, ship size: 3");//print statement
		checkNum(3,"Submarine");									//user method and past the value to method
		System.out.println("Player1: Set the ship: Patrol Boat, ship size: 2");//print statement
		checkNum(2,"Patrol Boat"); 									//user method and past the value to method
		enterKey();													//method to tell user press enter key
		clearScreen(); 												//print 100Line statement
		printTable(grid2);											//print the table grid2
		shootShipChecking(); 										//call a method
	}
	public static void CPUMode(){
		cpuCheck(5);												//call a method to let the computer auto set the ship
		cpuCheck(4);												//call a method to let the computer auto set the ship
		cpuCheck(3);												//call a method to let the computer auto set the ship
		cpuCheck(3);												//call a method to let the computer auto set the ship
		cpuCheck(2);												//call a method to let the computer auto set the ship
		clearScreen(); 												// print 100Line statement
		printTable(grid2); 											// print the table grid2
		shootShipChecking(); 										//call a method
	}
	public static void printTable(char[][]map){ 					//This method is use to print the 2D table
		System.out.println();
		System.out.printf("      ");
		for(int i=0;i<10;i++){					
			System.out.printf("%3d",i);		
		}
		System.out.printf(" "+"<--"+"x");
		System.out.println();
		System.out.println("   --+------------------------------");
		for(int i=0;i<10;i++){
			System.out.printf("%4d |",i);
			for(int k=0;k<map.length;k++){
				System.out.print("  "+map[i][k]);
			}
			System.out.println();
		}
		System.out.println("   "+"^");
		System.out.println("   "+"Y");
	}
	public static void checkNum(int shipsize, String n){  			// use to check the user input number
		System.out.print("Orientation (0 - horizontal, 1 - vertical), x - Exit: "); //remind user input
		String b; 													//declared 
		while(true){ 												//use to check true or false
			b = input.nextLine(); 									//user input
			if(b.equals("1")){ 
				break;  											//if correct ,than leave here to run under 
			}
			if(b.equals("0")){ 
				break;												//if correct ,than leave here to run under 
			}
			if(b.equals("x")){ 
				xExit(b);											//use to leave program
				break;	 
			}
			System.out.print("Please input 0 or 1, x - Exit : "); 	//wrong statement
		}
		while(true){												//use to check true or false
			char ch1, ch2,S; 										//declared 
			int n1=0;												//declared 
			int n2=0;												//declared 
			System.out.print("Position of "+n+"[XY], x - Exit :");	//remind user input
			String s = input.nextLine();							//user input
			if(s.equals("x")){
				xExit(s);											//use to leave program
				break;
			}
			if(s.length() == 2){ 									//check user input must 2 digit
				ch1 = s.charAt(0); 									//separate 2 digit
				ch2 = s.charAt(1);									//separate 2 digit
				if(Character.isDigit(ch1) || Character.isDigit(ch2)) {
					n1 = Character.getNumericValue(ch1); 			//past digit
					n2 = Character.getNumericValue(ch2);			//past digit
					char C = shipError(b,n1,n2,shipsize);			//call a method and method will return a letter to C
					if(C == 'b'){ 									//check the method return letter
						System.out.println();
						System.out.println("The ships cannot be over the boundary!");  //print wrong statement
					}
					if(C == 'd'){									//check the method return letter
						System.out.println("The ships cannot overlap!");				//print wrong statement
					}
					if(C == 'e'){									//check the method return letter
						printTable(grid1); 							//print 2D table
						return; 									//leave this method
					}
				}
			}
				System.out.println(); 								// print a null Line
		}
	}
	public static void enterKey(){  								//use to check user enter key
		System.out.println("Press Enter for Player2 to start ..."); //remind user press enter key
		String readString = input.nextLine();
		while(readString!=null){
			System.out.println(readString);
			if(readString.isEmpty()){
				break;
			}
			if(input.hasNextLine()){
				readString = input.nextLine();
			} else {
				readString = null;
			}
		}
	}
	public static void clearScreen(){ 								//use to print 100 null Line
		for(int i=0;i<100;i++)
			 System.out.println();
	}
	public static void shootShipChecking(){ 
		int miss = 0;  								 				//declared miss calculate user miss time
		int hit = 0;	 								 			//declared hit calculate user hit time
		int launched = 0;	 								 		//declared launched to calculate user input time
		while(true){  								 				//use to check true or false
			if(hit == 17){ 											//all shipsize is 17 , when the hit is calculate to 17 should be stop 
				System.out.print("   You have hit all battleships!"); //tell statement to user 
				System.out.println();
				break; 								 				//leave program
			}
			char ch1, ch2,S;  								 		//declared
			int n1=0;												//declared
			int n2=0;												//declared
			System.out.print("Player2: Set your missile [XY], x - Exit :");	//remind user input
			String s = input.nextLine(); 							//check user input
			if(s.equals("x")){
				xExit(s); 											//use to end program
				break;
			}
			if(s.length() == 2){  									//check 2 digit
				ch1 = s.charAt(0); 									//paste digit
				ch2 = s.charAt(1); 									//paste digit
				if(Character.isDigit(ch1) || Character.isDigit(ch2)) {
					n1 = Character.getNumericValue(ch1); 
					n2 = Character.getNumericValue(ch2);
					char L = shootShip(n1,n2);						 //call method , user L to save the method return
					if(L == 'Y'){
						System.out.println("You have already fired this area."); //print wrong statement
					}
					if(L == 'G'){
						hit++;  									//increase hit
						launched++; 								//increase launched
						System.out.println(" It's a hit!!"); 		//remind user statement 
						System.out.println("Launched:"+launched+", Hit:"+hit); //calculate time
						printTable(grid2); 							//print grid2 table
					}
					if(L == 'N'){
						miss++; 									//increase miss
						launched++;  								//increase launched
						System.out.println(" Missed.");  			//remind user statement 
						System.out.println("Launched:"+launched+", Hit:"+hit);//calculate time
						printTable(grid2);							//print grid2 table
					}
					if(L == 'F')
						System.out.println(); 						//null
				}else
					System.out.println("Error in [XY]! Please input again, x - Exit: "); //print wrong statement
			}	
		}
	}	
	private static void xExit(String x){  							//use to end program
		if (x.length() == 1 && x.charAt(0)=='x'){
			System.exit(0);
		}
	}
	public static char shipError(String b,int n1,int n2,int shipsize){ // pasted Orientation , 2 digit ,shipsize to this method 
		char z;  													//declared z is use to return letter
		while(true){ 								 				//use to check true or false
			if(b.equals("0")){
				if((n1+shipsize)>10)								//check user input must input the ship in boundary
					return z = 'b';  								//return the b give the statement
				for(int i=n1;i<shipsize+n1;i++){      
					if(grid1[n2][i] == 'S'){ 						//check there have ,if have return that tell user reinput
						return z = 'd';
					}
				}
				for(int i=n1;i<shipsize+n1;i++){					//if input is not out of boundary ,input the ship
					grid1[n2][i] = 'S';								//place ship
				}	
					return z = 'e';		 							//no any problem return that 
			}
			if(b.equals("1")){
				if((n2+shipsize)>10) 								//check user input must input the ship in boundary
					return z = 'b'; 								//return the b give the statement
				for(int i=n2;i<shipsize+n2;i++){								
					if(grid1[i][n1] == 'S'){						//check there have ,if have return that tell user reinput
						return z = 'd';
					}
				}
				for(int i=n2;i<shipsize+n2;i++){					//if input is not out of boundary ,input the ship
					grid1[i][n1] = 'S';	 							//place ship
				}	
					return z = 'e';		 							//no any problem return that 
			}
		}		
	}				
	public static char shootShip(int n1,int n2){ 					//past 2 digit to check p1table
		while(true){	 											//use to check true or false
			char E; 												//declared
			if(grid2[n2][n1] == '#'|| grid2[n2][n1] == 'o')			//check there is any already fire 
				return E = 'Y';  									//return
			if(grid1[n2][n1] == 'S'){  								//check there is any ship
				grid2[n2][n1] = '#';  								//if there is a ship , use # place that 
				return E = 'G';  									//return 
			}else if(grid1[n2][n1] != 'S'){  						//check there is any ship 
				grid2[n2][n1] = 'o';  								// no ship , so use o to place that
				return E = 'N';  									//return 
			}
				return 'A';		 									//finish return
		}
	}
	public static void cpuCheck(int shipsize){  					//past shipsize
		String b;  								 					//declared
		b = String.valueOf((int)(Math.random()*2)); 				//random 0~1 to comfirm the Orientation
		while(true){	 											//use to check true or false	
		int n1=0;	  								 				//declared
		int n2=0;	 												//declared
			n1 = (int)(Math.random()*10); 							//random 0~9 past to n1
			n2 = (int)(Math.random()*10); 							//random 0~9 past to n2
			if(b.equals("0")){
					char C = shipError(b,n1,n2,shipsize); 			//past the value to method
					if(C == 'e') 									//get return
						return; 									//leave
			}
			if(b.equals("1")){
					char C = shipError(b,n1,n2,shipsize);			//past the value to method
					if(C == 'e') 									//get return
						return;										//leave
			}
			
		}
	}
}   
		