import java.util.Hashtable;

public class Hash {
    public static void main(String[] args){
        int[] nums = new int[5];
        for(int i = 0; i < nums.length; i++){
            nums[i] = i;
        }
        findSumPair(3,nums);
        findSumPair(4, nums);
        int[]dnums = new int[4];
        dnums[0] = -1;
        dnums[1] = 0;
        dnums[2] = -1;
        dnums[3] = 4;
        System.out.println(DistinctValues(dnums));
    }

    public static void findSumPair(int target, int[] array) {
        Hashtable<Integer,Integer> htable = new Hashtable<>(); //Initializes new hashtable of int x int
        for(int i = 0; i < array.length; i++){
            htable.put(array[i], array[i]); //Fills hashtable with values of array
        }
        int match; //Initializes match variable
        for(int i = 0; i < array.length; i++) { //Iterate through table/array
            match = target-array[i]; //Assign match to find the matching value for the current element in terms of the target
            if(htable.containsKey(match)){ //If the table contains a key that is a match
               System.out.println("Pair " + htable.get(match) + ", " + array[i]); //Print the match
            }
        }

    }

    public static int DistinctValues(int[] array) {
        int count = 0; //Initializes count variable
        Hashtable<Integer, Boolean> htablecheck = new Hashtable<>(); //Initializes int x boolean hashtable
        for (int i = 0; i < array.length; i++) { //Iterate through array
            if (!htablecheck.containsKey(Math.abs(array[i]))){ //Check if element at i has been stored in the hashtable (Checks if already seen)
                htablecheck.put(Math.abs(array[i]), true); //If it has not, put the element as a key with the value true
                count++; //Add to count
                }
            }
        return count; //Return count;
    }
}
