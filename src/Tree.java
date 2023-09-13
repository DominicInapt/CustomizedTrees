public class Tree<E extends Comparable<E>> implements BinaryTree<E>{
    TreeNode<E> root;

    public Tree() {
        root = null;
    }

    public Tree(E item) {
        root = new TreeNode<E>(item);
    }

    public Tree(TreeNode<E> root) {
        this.root = root;
    }

    public void add(E item) {
        System.out.println("You shouldn't be here.");
        throw new UnsupportedOperationException();
    }


    public boolean contains(E item){
        if(empty()){
            return false;
        }
        return doContains(root(),item);
    }
    protected boolean doContains(TreeNode<E> node, E item){
        if(node==null){
            return false;
        }
        if(node.getItem().compareTo(item)==0){
            return true;
        }

        return doContains(node.getLeft(),item) || doContains(node.getRight(),item);

    }

    public boolean empty() {
        return root==null;
    }

    public TreeNode root() {
        return root;
    }
}
