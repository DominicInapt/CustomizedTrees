public class TreeTest {
    /**
     * @author Dominic_Cartmel
     * This class is used to test the functionality of the display methods and
     * the different tree structures.
     */

    //Some pre-defined static Trees.
    public static Tree<Integer> t;
    public static LayerTree<Integer> lt;
    public static SortedTree<Integer> st;


    public static void main(String args[]){
       // buildTree();
        int[] toAdd= new int[]{5,4,8,6,2,9,7,3,10};
        SortedTree<Integer> h= new SortedTree<>();
        for(int num: toAdd){
            h.add(num);
        }
        TreePrinter.printFancy(h);
        h.remove(8);
        TreePrinter.printFancy(h);
        TreePrinter.printPreOrder(h);




    }

    /**
     * Builds "at" into an AVLTree.
     */
    public static void buildTree(){
        AVLTree<Integer> at= new AVLTree<>();
        int[] toAdd = new int[]{1,2,3,4,5,6,7,8,9,10,12,13};

        for(int num: toAdd){
            at.add(num);
        }
        System.out.println(TreePrinter.height(at));
        TreePrinter.printFancy(at);
    }

    /**
     *  A helper method to add all ints of the given array to the given tree and
     *  then print the tree in many different methods.
     * @param tree
     * @param toAdd
     */
    public static void displayTree(Tree tree, int[] toAdd){
        for(int num: toAdd){
            tree.add(num);
            System.out.println(tree.root());
        }
        System.out.println(tree.empty());
        TreePrinter.printAll(tree);
    }

    /**
     * Initializes "lt" as a LayerTree.
     */
    public static void buildLayerTree(){
        lt=new LayerTree<>();
        int[] toAdd= new int[]{8,5,4,3,2,1,90};

        for(int num: toAdd){
            lt.add(num);
        }
        TreePrinter.printAll(lt);
    }
}

