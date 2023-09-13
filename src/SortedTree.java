import java.util.ArrayList;
import java.util.Random;

public class SortedTree<E extends Comparable<E>> extends Tree<E>{
    /**
     * @author Dominic Cartmel
     * A tree that enforces that the left child must always be smaller than its parent,
     * and the right child must always be larger than its parent.
     * This tree is not balanced, so degenerate tree's are not prevented.
     * For a balanced tree, see AVLTree
     */
    public SortedTree(){
        super();
    }

    public SortedTree(E item){
        super(item);

    }

    public SortedTree(TreeNode<E> root){
        super(root);
    }



    public void add(E item){
        if(empty()){
            System.out.println("Added at root.");
            root=new TreeNode(item);
        } else if(!contains(item)){
            doAdd(root,item);
        }else{
            System.out.println("That's already here.");
        }
    }
    public void doAdd(TreeNode<E> node, E item){

        if(node.getItem().compareTo(item)>0){
            if(node.getLeft()==null) {
                node.setLeft(new TreeNode(item));
            }else{
                doAdd(node.getLeft(),item);
            }
        } else{
            if(node.getRight()==null){
                node.setRight(new TreeNode(item));
            }else{
                doAdd(node.getRight(),item);
            }
        }
    }

    public static <T extends Comparable<T>> boolean remove(TreeNode<T> top, T item){
        if(top==null){
            return false;
        }
        int compare = top.getItem().compareTo(item);

        if(compare==0){
            boolean hasLeft= top.getLeft()!=null;
            boolean hasRight=top.getRight()!=null;
            if(!hasLeft && !hasRight){
                return true; //Sends a signal that root should be set to null.
            } else{

                if(hasLeft && hasRight){
                    T replace=getLargest(top.getLeft()).getItem();
                    remove(top,replace);
                    top.setItem(replace);
                }else {
                    TreeNode<T> replace = hasLeft ? top.getLeft() : top.getRight();
                    top.setLeft(replace.getLeft());
                    top.setRight(replace.getRight());
                    top.setItem(replace.getItem());
                    replace.clear();
                }
            }

        } else if(compare>0){
           if (remove(top.getLeft(),item)){
               top.setLeft(null);
           }

        } else{
           if (remove(top.getRight(),item)){
               top.setRight(null);
           }
        }
        return false;
    }

    public static <T extends Comparable<T>> TreeNode<T> getLargest(TreeNode<T> top){
        if(top.getRight()==null){
            return top;
        }
        return getLargest(top.getRight());
    }

    public void remove(E item){
        sortedRemove(this,item);
    }
    public static<T extends Comparable<T>,V extends SortedTree> void sortedRemove(V tree,T rem){

        if(!tree.empty()){
            if(remove(tree.root(),rem)){
                tree.empty();
            }
        }
    }


}
