import java.util.ArrayList;
import java.util.Iterator;

public class TreePrinter {
    /**
     *
     * @author Dominic_Cartmel
     * This class provides many different methods for displaying items
     * that implement the "Tree" interface.
     */

    /**
     *
     * Performs a pre-order traversal of the given tree.
     * @param tree
     */
    public static void printPreOrder(Tree tree){
        //Vist on the left, starting at root.
        if(! tree.empty()) {
            System.out.println("Pre-Order traversal.");
            doPrintPreOrder(tree.root());
        }
    }
    private static  void doPrintPreOrder(TreeNode curr){
        if(curr==null){
            return;
        }

        System.out.println(curr.getItem());
        doPrintPreOrder(curr.getLeft());
        doPrintPreOrder(curr.getRight());
    }

    /**
     * Performs an in-order traversal of the given tree.
     * @param tree
     */
    public static void printInOrder(Tree tree){
        //Visit on the bottom.
        if(! tree.empty()){
            System.out.println("In-Order traversal.");
            doPrintInOrder(tree.root());
        }
    }
    private static void doPrintInOrder(TreeNode node){
        if(node ==null){
            return;
        }
        doPrintInOrder(node.getLeft());
        System.out.println(node.getItem());
        doPrintInOrder(node.getRight());
    }

    /**
     * Performs a post-order traversal of the given tree.
     * @param tree
     */
    public static void printPostOrder(Tree tree){
        //vist on the right, from bottom left.
        if(!tree.empty()){
            System.out.println("Post-Order traversal.");
            doPrintPostOrder(tree.root());
        }
    }
    private static void doPrintPostOrder(TreeNode node){
        if(node ==null){
            return;
        }
        doPrintPostOrder(node.getLeft());
        doPrintPostOrder(node.getRight());
        System.out.println(node.getItem());
    }

    /**
     * A custom printing order of a tree.
     * It prints items in the form.
     * root              Row 1
     * rootchild1 rootchild2              Row 2
     * etc.
     * @param tree
     */
    public static void printCakeOrder(Tree tree){
        ArrayList<TreeNode> layer=new ArrayList<>();
        if(tree.empty()){
            return;
        }
        System.out.println("Performing cake traversal.");
        layer.add(tree.root());
        int row=1;
        while(layer.size()>0){
            ArrayList<TreeNode> newLayer = new ArrayList<>();
            for(TreeNode node: layer){
                System.out.print(node.getItem()+" ");

                if(node.getLeft()!=null){
                    newLayer.add(node.getLeft());
                }
                if(node.getRight()!=null){
                    newLayer.add(node.getRight());
                }
            }
            System.out.println("       Row "+row);
            layer=newLayer;
            row++;

        }
    }

    /**
     *  An alternate form of printFancy, made for use on a Heap Tree.
     *  This prints all elements from top to bottom with "stilts" (/  or \) pointing
     *  from each parent to their children. Like its brother method, it only works on Heap<Integer>.
     * @param h The given heap to display.
     */
    public static void printFancy(Heap<Integer> h){

        int rows=h.height();
        StringBuilder EarthBoundGreaterLineWalker;
        //Calculate the buffer between numbers first.

        int midXbuffer= twoSum(rows) -1;
        int startBuffer=twoSum(rows-1);

        for(int row=1; row<=rows;row++){
            //Get current row, start building the line.
            EarthBoundGreaterLineWalker= new StringBuilder();

            //Get the start buffer, which will be preserved for the stilts.
            addSpace(EarthBoundGreaterLineWalker,startBuffer);

            int bigNum = (int) Math.pow(2,row-1);
            int length= h.size();
            for(int i= bigNum-1; i< 2*bigNum-1 && i<length;i++){
                int num= h.get(i);

                EarthBoundGreaterLineWalker.append(getString(num));
                if(i!=2*bigNum-2) {
                    addSpace(EarthBoundGreaterLineWalker, midXbuffer);
                }

            }
            //Print the line.
            System.out.println(EarthBoundGreaterLineWalker);
            //Mid buffer  (from bottom) 1,5, 13, 29

            if(row==rows)break;
            int midBuffer=1;

            for(int stiltLine=1; stiltLine<=Math.pow(2,rows-row); stiltLine++){
                EarthBoundGreaterLineWalker= new StringBuilder();

                // Determines how many stilt cycles per line.
                addSpace(EarthBoundGreaterLineWalker, startBuffer);

                for(int stilt=bigNum-1;stilt<2*bigNum-1;stilt++) {

                    if(h.getChild(stilt,true)==null){
                        EarthBoundGreaterLineWalker.append(' ');
                    } else {
                        EarthBoundGreaterLineWalker.append('/');
                    }
                    addSpace(EarthBoundGreaterLineWalker, midBuffer);

                    if(h.getChild(stilt,false)==null){
                        EarthBoundGreaterLineWalker.append(' ');
                    } else{
                        EarthBoundGreaterLineWalker.append('\\');
                    }

                    if(stilt!=2*bigNum-2) {
                        addSpace(EarthBoundGreaterLineWalker, midXbuffer);
                    }

                }
                System.out.println(EarthBoundGreaterLineWalker);
                midXbuffer-=2; //Bring values closer together.
                startBuffer--; // Shift lines closer to the left.
                midBuffer+=2; //Shift stilts /  \ further apart.
            }

        }
    }

    /**
     * Utilizes a recursive method to find the height of the given tree.
     * @param tree The given tree to find the height of.
     * @return The height of the tree.
     */
    public static int height(Tree tree){
        return doGetHeight(tree.root())+1;
    }
    private static int doGetHeight(TreeNode tn){
        if(tn==null || tn.isLeaf()){
            return 0;
        }
        int leftHeight= doGetHeight(tn.getLeft());
        int rightHeight= doGetHeight(tn.getRight());
        if(leftHeight > rightHeight ){
            return leftHeight+1;
        } else{
            return rightHeight+1;
        }
    }

    /**
     * Prints all the elements in a tree fancily. Each element in the tree has a
     * stilt made of slashes (/ or \) that point to its parents and children if any.
     * @param tree The tree to be printed fancily. Must store Integers.
     */
    public static void printFancy( Tree tree){
        Integer check= 1;
        if(tree.root().getItem().getClass() != check.getClass()){
            System.out.println("This only works on Integers, sorry.");
            return;
        }
        StringBuilder EarthBoundGreaterLineWalker;
        //current row.
        ArrayList<TreeNode<Integer>> crow= new ArrayList<>();

        //Number of rows (counting from 1) that need to be printed.
        int rows=height(tree);
        //Start out the rows with row 1.
        crow.add(tree.root());
        //Calculate the buffer between numbers first.


        int midXbuffer= twoSum(rows) -1;
        int startBuffer=twoSum(rows-1);

        for(int row=1; row<=rows;row++){
            //Get current row, start building the line.
            EarthBoundGreaterLineWalker= new StringBuilder();

            //Get the start buffer, which will be preserved for the stilts.
            addSpace(EarthBoundGreaterLineWalker,startBuffer);

            //Add all of the values across.
            Iterator<TreeNode<Integer>> itr = crow.iterator();
            ArrayList<TreeNode<Integer>> newRow= new ArrayList<>();
            while(itr.hasNext()){
                TreeNode<Integer> num=itr.next();
                int val;
                //This if statement starts compling the children of the next line.
                if(num==null){ //Deals with blank nodes.
                    val=-813;
                    newRow.add(null);
                    newRow.add(null);
                }else{
                    val=num.getItem();
                    newRow.add(num.getLeft());
                    newRow.add(num.getRight());
                }
                EarthBoundGreaterLineWalker.append(getString(val));

                //Adds post-whitespace if there is another element to be displayed.
                if(itr.hasNext()) {
                    addSpace(EarthBoundGreaterLineWalker, midXbuffer);
                }

            }
            crow=newRow;
            //Print  StartBuffer+XXX+midXbuffer+...XXX
            //Currently.
            System.out.println(EarthBoundGreaterLineWalker);

            // The last row of numbers doesn't need stilts.
            if(row==rows)break;
            //Space between stilts. /_\
            int stiltBuffer=1;

            //Determines the number of stiltLines.
            for(int stiltLine=1; stiltLine<=Math.pow(2,rows-row); stiltLine++){
                EarthBoundGreaterLineWalker= new StringBuilder();

                //Prints a single line of stilts.
                addSpace(EarthBoundGreaterLineWalker, startBuffer);
                for(int stiltUnits=0; stiltUnits<Math.pow(2,row-1); stiltUnits++) {

                    //No child, no stilts.
                    if(crow.get(2*stiltUnits)==null){
                        EarthBoundGreaterLineWalker.append(' ');
                    } else {
                        EarthBoundGreaterLineWalker.append('/');
                    }

                    //Add between-stilts-spacing.
                    addSpace(EarthBoundGreaterLineWalker, stiltBuffer);

                    //No child no stilts.
                    if(crow.get(2*stiltUnits+1)==null){
                        EarthBoundGreaterLineWalker.append(' ');
                    } else{
                        EarthBoundGreaterLineWalker.append('\\');
                    }

                    //Add the mid stilt buffer / \XXXX/ \ XXXX / \
                    //Unless it is the last set.
                    if(stiltUnits != Math.pow(2,row-1)-1) {
                        //Strangely, midXbuffer works here.
                        addSpace(EarthBoundGreaterLineWalker, midXbuffer);
                    }

                }
                //Prints stilts.
                System.out.println(EarthBoundGreaterLineWalker);
                //Numbers are sliding closer together.
                midXbuffer-=2;
                //The whole tree is approaching the left border.
                startBuffer--;
                //Stilts are growing larger and larger.
                stiltBuffer+=2;
            }

        }

    }

    /**
     * A helper method for printFancy.
     * It transforms a given number into a corresponding String.
     * If the number is too big, it will return an empty String.
     * @param num The number to be transformed into a String.
     * @return The number as a string or whitespace.
     */
    public static String getString(int num){
        double power;
        //Determines the number's size.
        if(num<-99) {
            power=10000;
        } else {
            power = num >= 0 ? Math.log10(num) : Math.log10(num * -1) + 1;
        }
        //Adds whitespace accordingly.
        String number;
        if(power<1){
            number=" "+num+" ";
        } else if(power<2){
            number=num+" ";
        }else if(power<3){
            number=Integer.toString(num);
        }else{
            number="   ";
        }
        return number;
    }

    /**
     * Adds a given number of spaces ' ' onto the given StringBuilder.
     *
     * @param sb The given stringbuilder that is going to be added to.
     * @param spaces The number of spaces to add to the stringbuilder.
     */
    private static void addSpace(StringBuilder sb, int spaces){
        for(int i=0;i<spaces;i++){
            sb.append(' ');
        }
    }

    /**
     * Calculates the sum of the sum of 0+2^1+2^3+...2^levels
     *
     * @param levels The number of times to sum the powers of 2.
     * @return
     */
    private static int twoSum(int levels){
        //           1  2  3  4
        int sum=0; //2, 6, 14 30

        for(int i=1;i<=levels;i++ ){
            sum+= Math.pow(2,i);
        }
        return sum;
    }

    /**
     * Calls most of the display methods on the given tree.
     * PrintFancy is not called because it only works on Tree<Integer> .
     * @param t The given tree to display.
     */
    public static void printAll(Tree t){
        printPreOrder(t);
        printInOrder(t);
        printPostOrder(t);
        printCakeOrder(t);
    }
}
