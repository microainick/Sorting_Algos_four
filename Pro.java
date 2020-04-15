import java.util.Random;
import java.util.Arrays;
import java.util.*;

public class Pro
{
  public static final Integer UPPERBOUND = 20000;
  public static final Random rando = new Random();
  public static Integer[] num1Array;
  public static Integer[] num2Array;
  public static Integer[] num3Array;
  public static Integer[] num4Array;

  public static void main(String args[])
  {
    demoInsertion();
    demoQuick();
    demoMerge();
    demoHeap();
  } //end main

  public static Integer[] gen(Integer n)
  {
    Random rando = new Random();
    Integer[] myArray = new Integer[n];
    List<Integer> myList = new ArrayList<>();
    Integer int_rando;
    for (Integer i = 0; i < n; i++)
    {
      int_rando = 1 + rando.nextInt(UPPERBOUND);
      while (myList.contains(int_rando))
      {
        int_rando = 1 + rando.nextInt(UPPERBOUND);
      }
      myList.add(int_rando);
      myArray[i] = int_rando;
    }
    return myArray;
  }

  public static void insertionSort(Integer arr[])
  {
    Integer n = arr.length;
    for (Integer i = 1; i < n; ++i)
    {
      Integer tmp = arr[i];
      Integer j = i - 1;
      while (j >= 0 && arr[j] > tmp)
      {
        arr[j + 1] = arr[j];
        j = j - 1;
      }
      arr[j + 1] = tmp;
    }
  } //end method

  public static Integer get_n()
  {
    boolean isValid;
    String input;
    Integer inputNum = 0;
    Scanner scn = new Scanner(System.in);
    do
    {
      isValid = true;
      System.out.println("\nEnter one of the following values for size n");
      System.out.println("n = 100, 500, 1000, 2000, 5000, 8000 or 10000\n");
      System.out.println("Any value from 1 to 10,000, inclusive, will work.");
      System.out.println("HINT:  Start Small!\n\n");
      System.out.print("n = ");
      input = scn.nextLine();
      try
      {
        inputNum = Integer.parseInt(input);
        if (inputNum < 1 || inputNum > 10000)
        {
          isValid = false;
          System.out.println("Invalid. Range of exceptable values is from 1-10,000.");
        }
      } // end Try
      catch (NumberFormatException e)
      {
        isValid = false;
        System.out.println("Invalid. Not a number. Try again.");
      } //close catch
    } while (!isValid);
    //scn.close();
    return inputNum;
  }

  public static Integer parti(Integer arr[], Integer start, Integer fin)
  {
    Integer my_Pivot = arr[fin];
    Integer i = (start - 1);
    for (Integer j = start; j < fin; j++)
    {
      if (arr[j] <= my_Pivot)
      {
        i++;
        Integer swapTemp = arr[i];
        arr[i] = arr[j];
        arr[j] = swapTemp;
      }
    } //end for j
    Integer swapTemp = arr[i + 1];
    arr[i + 1] = arr[fin];
    arr[fin] = swapTemp;
    return i+1;
  }

  public static void quick_sort(Integer arr[], Integer start, Integer fin)
  {
    if (start < fin)
    {
      Integer part_Index = parti(arr, start, fin);
      quick_sort(arr, start, part_Index - 1);
      quick_sort(arr, part_Index + 1, fin);
    }
  } //end method

  public static void merge(Integer[] leftArray,Integer[] rightArray, Integer[] arr, Integer size_L, Integer size_R)
  {
    Integer a = 0;
    Integer b = 0;
    Integer c = 0;
    while(b < size_L && c < size_R)
    {
      if (leftArray[b] < rightArray[c])
      {
        arr[a++] = leftArray[b++];
      }
      else
      {
        arr[a++] = rightArray[c++];
      }
    }
    while(b < size_L)
    {
      arr[a++] = leftArray[b++];
    }
    while(c < size_R)
    {
      arr[a++] = rightArray[c++];
    }
  } //end method

  public static void merge_sort(Integer [] arr, Integer leng)
  {
    if (leng < 2)
    {
      return;
    }
    Integer middle = leng / 2;
    Integer[] left_Array = new Integer[middle];
    Integer[] right_Array = new Integer[leng - middle];
    Integer j = 0;
    for(Integer i = 0; i < leng; ++i)
    {
      if(i < middle)
      {
        left_Array[i] = arr[i];
      }
      else
      {
        right_Array[j] = arr[i];
        j = j + 1;
      }
    } //end for i
    merge_sort(left_Array, middle);
    merge_sort(right_Array, leng-middle);
    merge(left_Array, right_Array, arr, middle, leng - middle);
  } //end method

  public static void heapSort(Integer[] arr)
	{
		Integer count = arr.length;
		makeHeap(arr, count);
		Integer fin = count - 1;
		while (fin > 0)
		{
			Integer temp = arr[fin];
			arr[fin] = arr[0];
			arr[0] = temp;
			slideDown(arr, 0, fin - 1);
			fin--;
		} //end while
	} //end method

	public static void makeHeap(Integer[] arr, Integer count)
	{
		Integer begin = (count - 2) / 2;
		while (begin >= 0)
		{
			slideDown(arr, begin, count - 1);
			begin--;
		}
	} // end method

	public static void slideDown(Integer[] arr, Integer begin, Integer fin)
	{
		Integer base = begin;
		while ((base * 2 + 1) <= fin)
		{
			Integer child = base * 2 + 1;
			if (child + 1 <= fin && arr[child] < arr[child + 1])
			{
				child = child + 1;
			}
			if (arr[base] < arr[child])
			{
				Integer temp = arr[base];
				arr[base] = arr[child];
				arr[child] = temp;
				base = child;
			}
			else
			{
				return;
			}
		} //end while
	} //end method

  public static void demoInsertion()
  {
    Scanner scan = new Scanner(System.in);
    System.out.println("\n\n\n\n\n\n\n\n_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ ");
    System.out.println("Insertion Sort");
    System.out.println("(Best Case)  Time Complexity:  O(n)");
    System.out.println("(Worst Case) Time Complexity:  O(n^2)\n");
    Integer n = get_n();
    num1Array = new Integer[n];
    num1Array = gen(n);
    System.out.println("\nWould you like to view the unsorted array?  Enter \"y\" for yes.");
    System.out.print("Response = ");
    char inputAnswer = scan.next().charAt(0);
    if (inputAnswer == 'y' || inputAnswer == 'Y')
    {
      System.out.println("\n\nUnsorted array with " + n + " elements and no duplicate values:  \n");
      System.out.println(Arrays.toString(num1Array));
    }
    System.out.println("\n\nWould you like to view the Sorted array?  Enter \"y\" for yes.");
    System.out.print("Response = ");
    inputAnswer = scan.next().charAt(0);
    if (inputAnswer == 'y' || inputAnswer == 'Y')
    {
      System.out.println("\n\n\nSorted array (Insertion Sort):  \n");
      insertionSort(num1Array);
      System.out.println(Arrays.toString(num1Array));
    }
    System.out.println("\n_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ \n\n\n ");
  } //end method

  public static void demoQuick()
  {
    Scanner scan = new Scanner(System.in);
    System.out.println("\n\n\n\n\n_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ ");
    System.out.println("Quick Sort");
    System.out.println("(Average and Best Case)  Time Complexity:  O(nlogn)");
    System.out.println("     (Worst Case)        Time Complexity:  O(n^2)\n");
    Integer n = get_n();
    num2Array = new Integer[n];
    num2Array = gen(n);
    System.out.println("\nWould you like to view the unsorted array?  Enter \"y\" for yes.");
    System.out.print("Response = ");
    char inputAnswer = scan.next().charAt(0);
    if (inputAnswer == 'y' || inputAnswer == 'Y')
    {
      System.out.println("\n\nUnsorted array with " + n + " elements and no duplicate values:  \n");
      System.out.println(Arrays.toString(num2Array));
    }
    System.out.println("\n\nWould you like to view the Sorted array?  Enter \"y\" for yes.");
    System.out.print("Response = ");
    inputAnswer = scan.next().charAt(0);
    if (inputAnswer == 'y' || inputAnswer == 'Y')
    {
      System.out.println("\n\n\nSorted array (Quick Sort):  \n");
      quick_sort(num2Array, 0, n-1);
      System.out.println(Arrays.toString(num2Array));
    }
    System.out.println("\n_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ \n\n\n ");
  } // end method

  public static void demoMerge()
  {
    Scanner scan = new Scanner(System.in);
    System.out.println("\n\n\n\n\n_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ ");
    System.out.println("Merge Sort");
    System.out.println("(Average/Best/Worst Case)  Time Complexity:  O(nlogn)");
    Integer n = get_n();
    num3Array = new Integer[n];
    num3Array = gen(n);
    System.out.println("\nWould you like to view the unsorted array?  Enter \"y\" for yes.");
    System.out.print("Response = ");
    char inputAnswer = scan.next().charAt(0);
    if (inputAnswer == 'y' || inputAnswer == 'Y')
    {
      System.out.println("\n\nUnsorted array with " + n + " elements and no duplicate values:  \n");
      System.out.println(Arrays.toString(num3Array));
    }
    System.out.println("\n\nWould you like to view the Sorted array?  Enter \"y\" for yes.");
    System.out.print("Response = ");
    inputAnswer = scan.next().charAt(0);
    if (inputAnswer == 'y' || inputAnswer == 'Y')
    {
      System.out.println("\n\n\nSorted array (Merge Sort):  \n");
      merge_sort(num3Array, num3Array.length);
      System.out.println(Arrays.toString(num3Array));
    }
    System.out.println("\n_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ \n\n\n");
  } //end method

  public static void demoHeap()
  {
    Scanner scan = new Scanner(System.in);
    System.out.println("\n\n\n\n\n_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ ");
    System.out.println("Heap Sort");
    System.out.println("(Average/Best/Worst Case)  Time Complexity:  O(nlogn)");
    Integer n = get_n();
    num4Array = new Integer[n];
    num4Array = gen(n);
    System.out.println("\nWould you like to view the unsorted array?  Enter \"y\" for yes.");
    System.out.print("Response = ");
    char inputAnswer = scan.next().charAt(0);
    if (inputAnswer == 'y' || inputAnswer == 'Y')
    {
      System.out.println("\n\nUnsorted array with " + n + " elements and no duplicate values:  \n");
      System.out.println(Arrays.toString(num4Array));
    }
    System.out.println("\n\nWould you like to view the Sorted array?  Enter \"y\" for yes.");
    System.out.print("Response = ");
    inputAnswer = scan.next().charAt(0);
    if (inputAnswer == 'y' || inputAnswer == 'Y')
    {
      System.out.println("\n\n\nSorted array (Heap Sort):  \n");
      heapSort(num4Array);
      System.out.println(Arrays.toString(num4Array));
    }
    System.out.println("\n_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ \n\n\n");
  } // end method

} // end class
