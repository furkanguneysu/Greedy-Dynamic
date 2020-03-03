

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import jxl.read.biff.BiffException;


public class test {
	public static void main(String[] args) throws BiffException, IOException{
		int n,k,W;
		File file = new File("C:\\Users\\SONY\\Desktop\\inputexcel.xls");
		Scanner scan = new Scanner(System.in);
		System.out.println("---Assignment2---");
		System.out.print("Please enter the amount to spend:");
		W=scan.nextInt();
		System.out.print("Please enter the position number:");
		n=scan.nextInt();
		System.out.print("Please enter the number of available player for each position:");
		k=scan.nextInt();
		scan.close();
		System.out.println("Processing...");
		@SuppressWarnings("unused")
		Management mg = new Management(n,k,W,file);
	}
}
