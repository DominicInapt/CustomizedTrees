import java.util.ArrayList;
import java.util.Random;

public class LayerTree<E extends Comparable<E>> extends Tree<E> {

    /**
     * An overcomplicated tree that fills up each layer from the left.
     * It is not sorted, it just fills elements up from the left.
     */

    public LayerTree() {
        super();
    }

    public LayerTree(E item) {
        super(item);
    }

    public LayerTree(TreeNode<E> root) {
        super(root);
    }

    @Override
    public void add(E item) {
        //Assuming that a tree created by this only ever used layerAdd.
        //System.out.println("HEyyyyy"+item);
        if (empty()) {
            //System.out.println("Helllo");
            root = new TreeNode<E>(item);
        } else {
            //Cake time.
            ArrayList<TreeNode> layer = new ArrayList<>();
            ArrayList<TreeNode> newLayer;
            layer.add(root);
            int row = 1;
            while (layer.size() > 0) {
                newLayer = new ArrayList<>();
                for (TreeNode node : layer) {
                    boolean isLeaf = node.isLeaf();
                    boolean rightNull = node.getRight() == null;

                    if (isLeaf) {
                        node.setLeft(new TreeNode(item));
                        return;
                    } else if (rightNull) {
                        node.setRight(new TreeNode(item));
                        return;
                    } else {
                        newLayer.add(node.getLeft());
                        newLayer.add(node.getRight());
                    }
                }
                layer = newLayer;
                row++;
            } //End while
        } //End if;
    }
}


