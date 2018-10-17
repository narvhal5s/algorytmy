package rbtree;

public class RBTree<K extends Comparable<K>, V> implements MapInterface<K, V> {

    private static final boolean BLACK = true;
    private static final boolean RED = false;

    private Node root;
    private int size;

    public RBTree() {
        this.root = null;
        size = 0;
    }

    private class Node {

        K key;
        V value;
        Node left, right, parrent;
        boolean color;
        boolean isleft;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = right = parrent = null;
            color = RED;
            isleft = false;
        }

    }

    @Override
    public void setValue(K key, V value) {
        Node node = new Node(key, value);
        if (root == null) {
            root = node;
            root.color = BLACK;
            size++;
        } else {
            Node parrent = root;
            Node child = null;
            while (true) {
                K p_key = parrent.key;
                if (p_key.compareTo(key) < 0) {
                    child = parrent.right;
                    if (child == null) {
                        parrent.right = node;
                        node.parrent = parrent;
                        size++;
                        checkColor(node);
                        return;
                    }
                } else if (p_key.compareTo(key) > 0) {
                    child = parrent.left;
                    if (child == null) {
                        parrent.left = node;
                        node.isleft = true;
                        node.parrent = parrent;
                        size++;
                        checkColor(node);
                        return;
                    }
                }else{
                    parrent.value = value ;
                    return;
                }
                parrent = child;
            }
        }
    }

    @Override
    public V getValue(K key) {
        V result = null;
        Node current = root;
        while (current != null) {
            K c_key = current.key;
            if (c_key.compareTo(key) == 0) {
                result = current.value;
                break;
            } else if (c_key.compareTo(key) > 0) {
                current = current.left;
            } else if (c_key.compareTo(key) < 0) {
                current = current.right;
            }
        }

        return result;
    }

    private void checkColor(Node node) {
        if (node == root) {
            return;
        }
        if (node.color == RED && node.parrent.color == RED) {
            checkAunt(node);
            checkColor(node.parrent);
        }

    }

    private void checkAunt(Node node) {
        if (node.parrent.isleft) {
            if (node.parrent.parrent.right == null || node.parrent.parrent.right.color == BLACK) {
                rotate(node);
                return;
            }
            if (node.parrent.parrent.right != null) {
                node.parrent.parrent.right.color = BLACK;
            }
            node.parrent.parrent.color = RED;
            node.parrent.color = BLACK;
            return;
        }
        if (!node.parrent.isleft) {
            if (node.parrent.parrent.left == null || node.parrent.parrent.left.color == BLACK) {
                rotate(node);
                return;
            }
            if (node.parrent.parrent.left != null) {
                node.parrent.parrent.left.color = BLACK;
            }
            node.parrent.parrent.color = RED;
            node.parrent.color = BLACK;
        }

    }

    private void rotate(Node node) {
        if (node.isleft) {
            if (node.parrent.isleft) {
                rotateRight(node.parrent.parrent);
                node.color = RED;
                node.parrent.color = BLACK;
                if (node.parrent.right != null) {
                    node.parrent.right.color = RED;
                }
                return;
            }
            rotateRightLeft(node.parrent.parrent);
            node.color = BLACK;
            node.right.color = RED;
            node.left.color = RED;
            return;
        }
        if (!node.isleft) {
            if (!node.parrent.isleft) {
                rotateLeft(node.parrent.parrent);
                node.color = RED;
                node.parrent.color = BLACK;
                if (node.parrent.right != null) {
                    node.parrent.right.color = RED;
                }
                return;
            }
            rotateLeftRight(node.parrent.parrent);
            node.color = BLACK;
            node.right.color = RED;
            node.left.color = RED;
        }
    }

    private void rotateLeft(Node node) {
        Node tmp = node.right;
        node.right = tmp.left;
        if (node.right != null) {
            node.right.parrent = node;
            node.right.isleft = false;
        }
        if (node.parrent == null) {
            // We are root 
            root = tmp;
            tmp.parrent = null;

        } else {
            tmp.parrent = node.parrent;
            if (node.isleft) {
                tmp.isleft = true;
                tmp.parrent.left = tmp;
            } else {
                tmp.isleft = false;
                tmp.parrent.right = tmp;
            }
        }
        tmp.left = node;
        node.isleft = true;
        node.parrent = tmp;
    }

    private void rotateRight(Node node) {
        Node tmp = node.left;
        node.left = tmp.right;
        if (node.left != null) {
            node.left.parrent = node;
            node.left.isleft = true;
        }
        if (node.parrent == null) {
            // We are root 
            root = tmp;
            tmp.parrent = null;
        } else {
            tmp.parrent = node.parrent;
            if (node.isleft) {
                tmp.isleft = true;
                tmp.parrent.left = tmp;
            } else {
                tmp.isleft = false;
                tmp.parrent.right = tmp;
            }
        }
        tmp.right = node;
        node.isleft = false;
        node.parrent = tmp;
    }

    private void rotateLeftRight(Node node) {
        rotateLeft(node.left);
        rotateRight(node);
    }

    private void rotateRightLeft(Node node) {
        rotateRight(node.right);
        rotateLeft(node);
    }

    public int getSize() {
        return size;
    }
    
    

    public static void main(String[] args) {
        RBTree<Integer, String> test = new RBTree<>();
        test.setValue(1, "Jeden");
        test.setValue(0, "Zero");
        test.setValue(2, "Dwa");
        test.setValue(-1, "ujemna");
        test.setValue(3, "Trzy");
        test.setValue(4, "Cztery");
    }

}
