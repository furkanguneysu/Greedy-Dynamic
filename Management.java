import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Management {
	private Sheet sheet;
	private Player[] players;
	
	public Management(int n,int k,int W,File file) throws BiffException, IOException {
		sheet = getSheet(file);
		players = getPlayers(n,k,sheet);
		getPositionStartIndex(sheet);
		getPlayers(n,k,sheet);
		Dynamic_Approach(n,k,W,players);
		Greedy_Approach(n,k,W,players);
	}

	private void Dynamic_Approach(int s, int k, int W, Player[] players) {
		int start = (int) System.currentTimeMillis();
		int n = players.length;
		int[][] V = new int[n+1][W+1];
		for(int i=0;i<=W;i++) {
			V[0][i]=0;
		}
		for(int i=0;i<=n;i++) {
			V[i][0]=0;
		}
		
		for(int i=1;i<=n;i++) {
			for(int w=1;w<=W;w++) {
					int index = calculateIndex(n,k,i);
					if(players[i-1].getPrice()<=w) {
						V[i][w] = Math.max(V[i-1][w],players[i-1].getRating()+V[index-1][w-players[i-1].getPrice()]);
					}else {
						V[i][w] = V[i-1][w];
					}
			}
		}
		System.out.println("---DP Results---");
		int t = W;
		int result = V[n][W];
		int i=n;
		while(t>0&&i>0) {
			if(V[i-1][t]==result) {
				i--;
				continue;
			}
			else {
				System.out.println(players[i-1].toString());
				result = result-players[i-1].getRating();
				t=t-players[i-1].getPrice();
				i=calculateIndex(n,k,i)-1;
			}
		}
		int end = (int) System.currentTimeMillis();
		System.out.println("Total Rating:"+V[n][W]);
		System.out.println("Total Cost:"+(W-t));
		System.out.println("Time elapsed:"+(end-start)+"ms");
		System.out.println("-----------------------------------------");
		
	}

	private int calculateIndex(int n,int k,int i) {
		int count=0;
		if(i==0) {
			return 1;
		}
		int arr[] = getIndex(n,k);
		for(int j=0;j<arr.length;j++) {
			if(arr[j]>i) {
				break;
			}
			count++;
		}
		return arr[count-1];
		
	}

	private int[] getIndex(int n, int k) {
		int arr[] = new int[n];
		arr[0]=1;
		for(int j=1;j<n;j++) {
			arr[j]=arr[j-1]+k;
		}
		return arr;
		
	}

	private void Greedy_Approach(int n, int k,int W,Player[] players) {
		int start = (int) System.currentTimeMillis();
		int a = 0;
		int totalRating = 0;
		int totalMoney = W;
		for(int i=0;i<n;i++) {
			Arrays.sort(players,a,a+k);
			a=a+k;
		}
		System.out.println("--Greedy Approach Result---");
		for(int i=0;i<n*k;i+=k) {
			if(totalMoney>players[i].getPrice()) {
				totalRating+=players[i].getRating();
				System.out.println(players[i].toString());
				totalMoney-=players[i].getPrice();
			}
			else {
				break;
			}
		}
		int end = (int) System.currentTimeMillis();
		System.out.println("Time Elapsed:"+(end-start)+"ms");
		System.out.println("Total Rating:"+totalRating);
		System.out.println("Total Cost:"+(W-totalMoney));
		
	}

	private Player[] getPlayers(int n, int k, Sheet sheet) {
		int[] position_index = getPositionStartIndex(sheet);
		int count=0;
		int totalPlayers=n*k;
		Player[] players = new Player[totalPlayers];
		for(int i=0;i<n;i++) {
			int startIndex = position_index[i];
			for(int j=0;j<k;j++) {
				String name = sheet.getCell(0,startIndex+j).getContents();
				int position = Integer.parseInt(sheet.getCell(1,startIndex+j).getContents());
				int rating = Integer.parseInt(sheet.getCell(2,startIndex+j).getContents());
				int price = Integer.parseInt(sheet.getCell(3,startIndex+j).getContents());
				players[count]=new Player(name,position,rating,price);
				count++;
			}
		}
		
		return players;
		
	}

	private int[] getPositionStartIndex(Sheet sheet) {
		int startPosition=-1;
		int nextPosition=-1;
		int count=0;
		int rowNum = sheet.getRows();
		int totalPositions = Integer.parseInt(sheet.getCell(1,rowNum-1).getContents());
		int[] position_index = new int[totalPositions];
		
		for(int i=1;i<rowNum;i++) {
			if(startPosition==totalPositions) {
				break;
			}
			nextPosition = Integer.parseInt(sheet.getCell(1,i).getContents());
			if(startPosition!=nextPosition) {
				startPosition=Integer.parseInt(sheet.getCell(1,i).getContents());
				position_index[count]=i;
				count++;
			}
		}
		return position_index;
	}

	private Sheet getSheet(File file) throws BiffException, IOException {
		Workbook workbook = null;
		workbook = Workbook.getWorkbook(file);
		Sheet sheet = workbook.getSheet(0);
		return sheet;
	}
	


}
