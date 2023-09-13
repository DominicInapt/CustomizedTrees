public class TreeNode<E extends Comparable<E>> implements Comparable<TreeNode>  {

    private E item;
    private TreeNode<E> left;
    private TreeNode<E> right;

    public TreeNode(E item){
        this(item,null,null);
    }
    public TreeNode(E item, TreeNode l, TreeNode r){
        this.item=item;
        left=l;
        right=l;

    }

    public TreeNode<E> getLeft(){
        return left;
    }

    public TreeNode<E> getRight(){
        return right;
    }

    public E getItem(){
        return item;
    }

    public void setRight(TreeNode<E> node){
        right=node;
    }

    public void setLeft(TreeNode<E> node){
        left=node;
    }
    public void setItem(E item){
        this.item=item;
    }

    public boolean isLeaf(){
        return right ==null && left ==null;
    }

    public void removeLeft(){
        left=null;
    }

    public void removeRight(){
        right=null;
    }

    public void clear(){
        right=null;
        left=null;
        item=null;
    }

    @Override
    public int compareTo(TreeNode o) {
        return item.compareTo(item);
    }
}
