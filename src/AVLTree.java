public class AVLTree<E extends Comparable<E>> extends SortedTree<E> {

    /**
     * @author Dominic_Cartmel
     * Creates a balanced sorted tree. This tree maintains that the left child
     * is always smaller than its parent and the right child is always larger.
     * This tree is designed to make sure that no two branches of the tree have a height that differs more than 1.
     */
    public AVLTree(){
        super();
    }
    public AVLTree(E item){
        super(item);
    }



    public void add(E item){
        super.add(item);
        checkWeight();

    }

    public void remove(E item){
        super.remove(item);
        checkWeight();
    }


    /**
     * Checks the heights of the various branches and refactors them if they are unbalanced.
     * These operations preserve the sorted nature of the tree.
     */
    public void checkWeight(){
        //YEAH;
        int length=doCheckWeight(root);
    }

    private int doCheckWeight(TreeNode<E> current){
        int left=0;
        int right=0;
        if(current==null){
            return -1;
        }
        if(current.getLeft() != null){
            left=doCheckWeight(current.getLeft());
        }
        if(current.getRight() != null){
            right=doCheckWeight(current.getRight());
        }

        if(left-right >1){ //problem on left.
            TreeNode<E> problemChild=current.getLeft();
            if(doCheckWeight(problemChild.getLeft())>doCheckWeight(problemChild.getRight())){
                rotateNodes(current,true,true);
                //rotateNodes(current,true,true,true);//LL
            } else{
                rotateNodes(current,true,false);
                //rotateNodes(current,true,true,false); // LR
            }
            return (left+right)/2; //Is balanced now??
        } else if (right-left>1){ //problem on right.
            TreeNode<E> problemChild=current.getRight();
            if(doCheckWeight(problemChild.getLeft())>doCheckWeight(problemChild.getRight())){
                rotateNodes(current,false,true);
                //rotateNodes(current,false,false,true); //RL
            } else{
                rotateNodes(current,false,false);
                //rotateNodes(current,false,false,false); // RR
            }

            return (left+right)/2;
        }else{
            //Bigger value is passed up as height.
            return left > right? left+1 : right+1;
        }

    }

    /**
     * There are 4 operations that should be done if a tree is unbalanced.
     * This method determines which methods are run.
     * @param problem The node who's children are unbalanced.
     * @param isLeft Is the longer branch the left child?
     * @param isChildLeft Is the higher child's left node higher?
     */
    public static void rotateNodes(TreeNode problem,boolean isLeft, boolean isChildLeft){

        //true,true. Parent right.
        //true,false. Child left, then parent right
        //false,true. Child right, then parent left
        //false,false. Parent left.

        if(isLeft!=isChildLeft){
            if(isChildLeft){
                rotateRight(isLeft ? problem.getLeft() : problem.getRight());
            }else{
                rotateLeft(isLeft ? problem.getLeft() : problem.getRight());
            }
        }

        if(isLeft){
            rotateRight(problem);
        }else{
            rotateLeft(problem);
        }

    }

    public static void rotateRight(TreeNode zenith){
        //We can only proceed down.
        //Prevent a reference -_-.
        TreeNode newRight=new TreeNode(zenith.getItem(),null,zenith.getRight());
        TreeNode newTop=zenith.getLeft(); // Node that will replace zenith.

        //zenith remains in zenith position but imitates zenith.left();
        zenith.setLeft(newTop.getLeft());
        zenith.setItem(newTop.getItem());

        //Hopefully this works.
        zenith.setRight(newRight);//Puts zenith on zenith's right....
        newRight.setLeft(newTop.getRight());
    }

    public static void rotateLeft(TreeNode zenith){


        TreeNode newLeft=new TreeNode(zenith.getItem(),zenith.getLeft(),null); // Peak of the rotation.
        TreeNode newTop=zenith.getRight(); // Node that will replace zenith.

        //zenith remains in zenith position but imitates zenith.left();
        zenith.setRight(newTop.getRight());
        zenith.setItem(newTop.getItem());

        //Hopefully this works.
        zenith.setLeft(newLeft);//Puts zenith on zenith's right....
        newLeft.setRight(newTop.getLeft());


    }
}
