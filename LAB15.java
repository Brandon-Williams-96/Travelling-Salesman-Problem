import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

public class LAB15 {
	
	public static void main(String[] args) {
		double[][] D = TSP.ReadArrayFile("c:\\temp\\CS2004 TSP Data (2017-2018)\\TSP_105.txt"," ");
		ArrayList<Integer> T = TSP.ReadIntegerFile("c:\\temp\\CS2004 TSP Data (2017-2018)\\TSP_105_OPT.txt");
		int n= D[0].length;
		int iter=1000000;
		double rmhc,SHC,rrhc,sa,a_rmhc=0,a_SHC=0,a_rrhc=0,a_sa=0,min_rmhc = 0,min_shc = 0,min_rrhc = 0,min_sa = 0,max_rmhc = 0,max_shc = 0,max_rrhc = 0,max_sa = 0;
		//System.out.println(Fitness(D,T,n));
		//System.out.println(RandPerm(n));
		//System.out.println(SmallChange(RandPerm(n),n));
	
		final long startTimeRMHC = System.currentTimeMillis();
		rmhc=RMHC(n,D,iter);
		long endTimeRMHC   = System.currentTimeMillis();
		long totalTimeRMHC = endTimeRMHC - startTimeRMHC;
		
		final long startTimeSHC = System.currentTimeMillis();
		SHC=SHC(n,D,iter);
		long endTimeSHC   = System.currentTimeMillis();
		long totalTimeSHC = endTimeSHC - startTimeSHC;
			
		final long startTimeRRHC = System.currentTimeMillis();
		rrhc=RRHC(n,D,iter);
		long endTimeRRHC   = System.currentTimeMillis();
		long totalTimeRRHC = endTimeRRHC - startTimeRRHC;
			
		final long startTimeSA = System.currentTimeMillis();
		sa=SA(n,D,iter);
		long endTimeSA   = System.currentTimeMillis();
		long totalTimeSA = endTimeSA - startTimeSA;		
		for (int i=0; i<25; i++) {
			
			rmhc=RMHC(n,D,iter);
			if (i==0){
				max_rmhc=rmhc;
				min_rmhc=rmhc;
			}
			if (max_rmhc <rmhc)
			{
			max_rmhc=rmhc;
			}
			if (min_rmhc > rmhc)
			{
			min_rmhc=rmhc;
			}
			
			a_rmhc=a_rmhc+rmhc;
			
	 
			SHC=SHC(n,D,iter);
			if (i==0){
				max_shc=SHC;
				min_shc=SHC;
			}
			if (max_shc <SHC)
			{
			max_shc=SHC;
			}
			if (min_shc > SHC)
			{
			min_shc=SHC;
			}
			
			a_SHC=a_SHC+SHC;
			
			
			rrhc=RRHC(n,D,iter);
			if (i==0){
				max_rrhc=rrhc;
				min_rrhc=rrhc;
			}
			if (max_rrhc <rrhc)
			{
			max_rrhc=rrhc;
			}
			if (min_rrhc > rrhc)
			{
			min_rrhc=rrhc;
			}
			
			a_rrhc=a_rrhc+rrhc;
			
			
			sa=SA(n,D,iter);
			if (i==0){
				max_sa=sa;
				min_sa=sa;
			}
			if (max_sa <sa)
			{
			max_sa=sa;
			}
			if (min_sa > sa)
			{
			min_sa=sa;
			}
			
			a_sa=a_sa+sa;
		}
		a_rmhc=a_rmhc/25.0;
		System.out.println("Average of the RMHC : " +a_rmhc);
		System.out.println("Best of the RMHC " + min_rmhc);
		System.out.println("Worst of the RMCH " + max_rmhc);
		System.out.println("AVERAGE OPT of the RMHC : " +OPT(D,T,n,a_rmhc));
		System.out.println("AVERAGE MST of the RMHC : " +MST(D,n,a_rmhc));
		System.out.println("BEST OPT of the RMHC : " +OPT(D,T,n,min_rmhc));
		System.out.println("BEST MST of the RMHC : " +MST(D,n,min_rmhc));
		System.out.println(totalTimeRMHC);
		System.out.println();
		
		a_SHC=(a_SHC/25.0);
		System.out.println("Average of the SHC : " +a_SHC);
		System.out.println("Best of the SHC : " +min_shc);
		System.out.println("Worst of the SHC : " +max_shc);
		System.out.println("AVERAGE OPT of the SHC : " + OPT(D,T,n,a_SHC));
		System.out.println("AVERAGE MST of the SHC : " + MST(D,n,a_SHC));
		System.out.println("BEST OPT of the SHC : " +OPT(D,T,n,min_shc));
		System.out.println("BEST MST of the SHC : " +MST(D,n,min_shc));
		System.out.println(totalTimeSHC);
		System.out.println();
		
		a_rrhc=(a_rrhc/25.0);
		System.out.println("Average of the RRHC : " +a_rrhc);
		System.out.println("BEST of the RMHC : " + min_rrhc);
		System.out.println("Worst of the RMHC : " +max_rrhc);
		System.out.println("AVERAGE OPT of the RRHC : " + OPT(D,T,n,a_rrhc));
		System.out.println("AVERAGE MST of the RRHC : " + MST(D,n,a_rrhc));
		System.out.println("BEST OPT of the RRHC : " +OPT(D,T,n,min_rrhc));
		System.out.println("BEST MST of the RRHC : " +MST(D,n,min_rrhc));
		System.out.println(totalTimeRRHC);
		System.out.println();
		
		a_sa=(a_sa/25.0);
		System.out.println("Average of the SA : " +a_sa);
		System.out.println("BEST of the SA : " +min_sa);
		System.out.println("Worst of the SA : " +max_sa);
		System.out.println("AVERAGE OPT of the SA : " + OPT(D,T,n,a_sa));
		System.out.println("AVERAGE MST of the SA : " + MST(D,n,a_sa));
		System.out.println("BEST OPT of the SA : " +OPT(D,T,n,min_sa));
		System.out.println("BEST MST of the SA : " +MST(D,n,min_sa));
		System.out.println(totalTimeSA);
	}
	
	static public double UR(double a,double b)
	{
		Random rand;
		rand = new Random();	
		return((b-a)*rand.nextDouble()+a);
	}
	
	public static double Fitness(double[][] D, ArrayList<Integer> T, int n) {
		int end,start;
		double s=0;
		int a,b;		
		for (int i=0; i<n-1; i++)
		{
			a=T.get(i);
			b=T.get(i+1);
			s=s+D[a][b];
		}
		end=T.get(n-1);
		start=T.get(0);
		s=s+D[end][start];
		
		return s;
	}
	
	public static ArrayList<Integer> RandPerm(int n)
	{
		ArrayList<Integer> UI = new ArrayList<Integer>(n);
		for (int j=0; j<n; j++)
		{
			UI.add(j);
		}
		ArrayList<Integer> Rperm = new ArrayList<Integer>(); //create arraylist to add new data to
		Random r = new Random(); //random function for swap feature
		for (int i=0; i<n; i++) //for loop to include all of the cities 
		{
			 int ran = r.nextInt(n-i); //int stored for the random number
			 Rperm.add(UI.get(ran)); //add stored number in the randomly placed position
			 UI.remove(ran); //removed the number stored in the new arraylist
		}
		return Rperm;
	}
	
	public static double OPT(double[][] D, ArrayList<Integer> T, int n, double method)
	{
		double OPT;
		OPT=(Fitness(D,T,n)/method)*100.0;
		return OPT;
	}
	
	public static double MST(double[][] D, int n, double method) {
		double mst_A[][] = MST.PrimsMST(D);
		double MST,Total=0;
		for (int i = 0; i < n; i++)
		{
			for (int j=0; j< n; j++)
			{
				Total = Total + mst_A[i][j];
			}
		}	
		Total=Total/2;
		MST=(Total/method)*100.0;
		return MST;
	}
	
	public static ArrayList<Integer> SmallChange(ArrayList<Integer> Rperm, int n) {
		int i=0,j=0,temp,z=0,x=0;
		Random r= new Random();
		ArrayList<Integer> Rperm1= (ArrayList<Integer>) Rperm.clone();
		//System.out.println(Rperm);
		while (z==x)
		{
				z=r.nextInt(n);
				x=r.nextInt(n);
			
				i=Rperm1.get(z);
				j=Rperm1.get(x);
		}
		temp=i;
		i=j;
		j=temp;
		Rperm1.set(z, i);
		Rperm1.set(x, temp);
		return Rperm1;
	}
	
	public static double RMHC(int n,double d[][],int Iter){
		ArrayList<Integer> T = (ArrayList<Integer>) RandPerm(n);
	    ArrayList<Integer> NewT = new ArrayList<Integer>();
	    double OldFitness = Fitness(d, T, n);
	    double NewFitness;
	    for(int i=1; i<Iter; i++){
	        NewT = (ArrayList<Integer>)SmallChange(T,n).clone();
	        NewFitness = Fitness(d, NewT, n);
	        if(NewFitness<OldFitness){
	        	T = (ArrayList<Integer>)NewT.clone();
	        	OldFitness = NewFitness;
	        } 
	    }
	 return OldFitness;    
	}
	
	public static double SHC(int n, double d[][], int Iter) {
		int x=25;
		ArrayList<Integer> T = (ArrayList<Integer>) RandPerm(n);
	    ArrayList<Integer> NewT = new ArrayList<Integer>();
	    double OldFitness = Fitness(d, T, n);
	    double NewFitness;
	    for(int i=1; i<Iter; i++){
	        NewT = (ArrayList<Integer>)SmallChange(T,n).clone();
	        NewFitness = Fitness(d, NewT, n);
	        if(NewFitness<OldFitness){
	        	T = (ArrayList<Integer>)NewT.clone();
	        	OldFitness = NewFitness;
	        }
	        else if (UR(0,1)>1.0/1.0+Math.exp((NewFitness-OldFitness)/x))
	        {
	        	T = (ArrayList<Integer>)NewT.clone();
	        	OldFitness = NewFitness;
	        } 
	    }
	 return OldFitness;    
	}
		
	public static double RRHC(int n,double d[][],int Iter){
		double FinalFitness=0;
		for (int i=0; i<100; i++)
		{
			
			double z=RMHC(n,d,Iter/100);
			if ((i==0) || (FinalFitness>z))
			{
				FinalFitness=z;
			}
			
		}
		return FinalFitness;
	}
	
	public static double SA(int n,double d[][],int Iter) {
		double f=0.0,New_f,p,temp=100.0,C_Rate;
		C_Rate=(Math.pow(0.0001/temp, 1.0/Iter));
		ArrayList<Integer> NewT = new ArrayList<Integer>();
		ArrayList<Integer> T = (ArrayList<Integer>) RandPerm(n);
		for (int i=0; (i<(Iter-1)); i++)
		{
			f=Fitness(d,T,n);
			NewT = (ArrayList<Integer>)SmallChange(T,n).clone();
			New_f=Fitness(d,NewT,n);
			if (New_f > f)
			{
				p=Math.exp(-Math.abs(f-New_f)/temp);
				if (p < UR(0,1))
				{
		
				}
				else
				{
					T=(ArrayList<Integer>)NewT.clone();
					f=New_f;
				}
			}
			else
			{
				T=(ArrayList<Integer>)NewT.clone();
				f=New_f;
			}
			temp=temp*C_Rate;	
		}
		return f;		
	}
	
}


