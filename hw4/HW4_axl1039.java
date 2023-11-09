public class HW4_axl1039{
    //set up HashTable 
    public static class HashTable {
        private class Entry {
            //set up vars
            //since we don't need all the operations of a HashTable we don't need all the vars 
            //since this is for only problem 1 and 2, i can say the key is an integer 
            int key;

            //initialize Entry 
            private Entry(int key) {
                this.key = key;
            }
        }

        private Entry[] table;
        private int tableSize;

        //initialize HashTable 
        public HashTable(int size) {
            table = new Entry[size];
            tableSize = size;
        }

        //will create a hash with linear probing
        public String linearProbing(int[] arr){
            String steps = "";
            for (int i = 0; i < arr.length; i++){ //loop through helper for each value 
                steps += '\n' + linearProbingHelper(arr[i]); //add to steps
            }
            return steps;
        }

        //adds input where it belongs according to linear probing rules  
        public String linearProbingHelper(int input){
            String text = "";
            int mod = input % tableSize; //get mod of input 

            text += input + ": " + input + " % " + tableSize + " = " + mod; //shows mod operation  

            if (table[mod] == null) { //if space is empty, assign new value there 
                table[mod] = new Entry(input);

            } else { //is space isn't empty, keep going down the table until there is an empty spot 
                while (table[mod] != null){ //repeat until index is null
                    mod = (mod + 1) % tableSize; //mod it so it won't go over the table size 
                    text += "(collision) -> " + mod; //print out each collision/loop
                }

                table[mod] = new Entry(input); //create new entry 
                
            }

            return text += "(open)"; //at end of everything, the index should be open 
        }

        //print out table based on increasing index 
        public void print(){
            for (int i = 0; i < tableSize; i++){
                System.out.print(table[i].key + " ");
            }
        }

        //double hash 
        //given h1(k) = k % tableSize;
        //given h2(k) = 7 - (k % 7)
        public String doubleHash(int[] arr){
            String steps = "";
            for (int i = 0; i < arr.length; i++){ //loop through helper for each value 
                steps += '\n' + doubleHashHelper(arr[i]); //add to steps 
            }
            return steps;
        }

        public String doubleHashHelper(int input){
            String text = "";
            int h1mod = input % tableSize; //get mod of input 

            text += input + ": " + input + " % " + tableSize + " = " + h1mod; //shows mod operation  

            if (table[h1mod] == null) { //if space is empty, assign new value there 
                table[h1mod] = new Entry(input);

            } else { //is space isn't empty, do second operation
                int h2mod = h1mod;
                for (int i = 1; table[h2mod] != null; i++){ //repeat until mod isn't null, increment i in the meanwhile 
                    h2mod = (h1mod + i*(7 - (input % 7))) % tableSize;
                    if (i > 1) { //stylistic choice to indent if repeats
                        text += "\n                ";   
                    }
                    text += " -> (" + h1mod + " + " + i + "*(7 - " + input + " % 7))" + " % " + tableSize + " = " + h2mod; 
                    //print out each collision operation 
                }

                table[h2mod] = new Entry(input); //create new entry at null entry 
            } 

            return text += "(open)"; //last index should be open 

        }

    }


    public static void main(String[] args) {
        
        System.out.println("---------------------------PART 1: Linear Probing---------------------------"); 
        System.out.println("Array: [14,17,18,3,8,1,18,11,13,20]");

        int[] arr1 = {14,17,18,3,8,1,18,11,13,20};
        HashTable table1 = new HashTable(arr1.length);
        String steps1 = table1.linearProbing(arr1);
        System.out.println(steps1);
        System.out.print("\nFinal table: ");
        table1.print();

        System.out.println();
        System.out.println("\n---------------------------PART 2: Double Hashing---------------------------"); 
        System.out.println("Array: [2,12,22,32,42,52,62,72,82,92,14,17,18,3,8,1,18,11,13,20]");
        
        int[] arr2 = {2,12,22,32,42,52,62,72,82,92,14,17,18,3,8,1,18,11,13,20};
        HashTable table2 = new HashTable(arr2.length);
        String steps2 = table2.doubleHash(arr2);
        System.out.println(steps2);
        System.out.print("\nFinal table: ");
        table2.print();

    }
}