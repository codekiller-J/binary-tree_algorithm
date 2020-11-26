

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class Node {

    char val;
    Node left;
    Node right;

    Node(char val) {
        this.val = val;
    }
}


public class binary-tree {
    public static Node build() {
        //手动构造一棵树

        Node a = new Node('A');
        Node b = new Node('B');
        Node c = new Node('C');
        Node d = new Node('D');
        Node e = new Node('E');
        Node f = new Node('F');
        Node g = new Node('G');
        Node h = new Node('H');

        a.left = b;
        a.right = c;
        b.left = d;
        b.right = e;
        e.left = g;
        g.right = h;
        c.right = f;
        return a;


    }


    public static void preOrder(Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val + " ");
        preOrder(root.left);
        preOrder(root.right);

    }

    public static void inOrder(Node root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        System.out.print(root.val + " ");
        inOrder(root.right);
    }

    public static void postOrder(Node root) {
        if (root == null) {
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.val + " ");

    }


    public static int size(Node root) {
        //求二叉树中节点的个数
        //使用先序遍历的方式
        if (root == null) {
            return 0;

        }
        //体会递归"拆分问题的过程
        //整个树节点个数=根节点的个数(1)+左子树的个数+右子树的个数
        return 1 + size(root.left) + size(root.right);


    }

    public static int leafSize(Node root) {

        //求二叉树叶子节点的个数
        //还是要遍历树,并且拆分问题
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            //此时的root没有子树,root自己就是叶子节点.
            return 1;
        }
        return leafSize(root.left) + leafSize(root.right);
    }

    public static int kLevelSize(Node root, int k) {
        //求二叉树第K层的节点个数
        //如果k<1 那么只能是空树,直接返回0;
        //如果k==1 求根节点的个数.直接返回1
        //k层节点的个数=根节点左子树的k-1层节点个数+根节点右子树的k-1层节点个数
        if (k < 1) {
            return 0;
        }
        if (k == 1) {
            return 1;
        }
        return kLevelSize(root.left, k - 1) + kLevelSize(root.right, k - 1);

    }

    Node find(Node root, char toFind) {
        //在二叉树中查找指定元素
        //如果存在就返回该节点的引用,如果不存在,就返回null
        //核心思路还是遍历
        if (root == null) {
            return null;
        }

        if (root.val == toFind) {
            return root;
        }
        //分别递归的去查找左右子树
        Node result = find(root.left, toFind);

        if (result != null) {
            return result;
        }
        return find(root.right, toFind);
    }

    public static void levelOrder(Node root) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.print(cur.val + " ");
            if (cur.left != null) {
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
            }
        }
    }

    public static boolean wanquantree(Node root) {   //判断一棵二叉树是否是完全二叉树
        if (root == null) {
            return true;
        }
        boolean isSecondStep = false;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (!isSecondStep) {
                //这是第一阶段
                if (cur.left != null && cur.right != null) {
                    //直接把两个子树插入队列
                    queue.offer(cur.left);
                    queue.offer(cur.right);
                } else if (cur.left == null && cur.right != null) {
                    //当第一阶段中某个节点具有右子树，没有左子树。那么这个树一定不是完全二叉树
                    return false;
                } else if (cur.left != null && cur.right == null) {
                    queue.offer(cur.left);
                    isSecondStep = true;
                } else {
                    isSecondStep = true;
                }
            } else {
                //这是第二阶段
                //要求第二阶段中任何一个 节点都必须没有子树
                //只要找到摸个节点带子树，就说明找到了反例
                if (cur.left != null || cur.right != null) {
                    return false;
                }
            }
        }
        //整个树遍历完了，也没有找到反例，最终就是 return true，认为就是完全二叉树
        return true;
    }

    public Node Convert(Node pRootOfTree) {    //二叉搜索树构建双向链表

        if (pRootOfTree == null) {
            return null;
        }
        if (pRootOfTree.left == null && pRootOfTree.right == null) {
            return pRootOfTree;
        }

        Node left = Convert(pRootOfTree.left);
        Node lefttail = left;
        while (lefttail != null && lefttail.right != null) {
            lefttail = lefttail.right;
        }

        if (left != null) {
            lefttail.right = pRootOfTree;
            pRootOfTree.left = lefttail;
        }
        Node right = Convert(pRootOfTree.right);
        if (right != null) {
            right.left = pRootOfTree;
            pRootOfTree.right = right;
        }
        return left == null ? pRootOfTree : left;
    }

    public static void fdgPreOder(Node root) {    //非递归实现前序遍历
        if (root == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();

        stack.add(root);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.print(cur.val + " ");

            if (cur.right != null) {
                stack.add(cur.right);
            }
            if (cur.left != null) {
                stack.add(cur.left);
            }
        }
    }


    public static void fdginorder(Node root) {  //非递归中序遍历
        if (root == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        Node cur = root;
        while (true) {

            while (cur != null) {
                stack.add(cur);
                cur = cur.left;
            }
            if (stack.isEmpty()) {
                break;
            }
            Node top = stack.pop();
            System.out.print(top.val + " ");

            cur = top.right;
        }
    }


    public static void fdgPostOrder(Node root) {        //非递归后序遍历
        if (root == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();

        Node cur = root;
        Node pre = null;

        while (true) {
            while (cur != null) {
                stack.add(cur);
                cur = cur.left;
            }

            if (stack.isEmpty()) {
                break;
            }

            Node top = stack.peek();
            if (top.right == null || top.right == pre) {
                System.out.print(top.val + " ");
                pre=top;
                stack.pop();
            } else {
                cur = top.right;
            }
        }
    }


    public static void main(String[] args) {
        Node root = build();

//        System.out.println("前序遍历");
//        preOrder(root);
//        System.out.println();
//        System.out.println("中序遍历");
//        inOrder(root);
//        System.out.println();
//        System.out.println("后序遍历");
//        postOrder(root);
//        System.out.println();
//        System.out.println("层序遍历");
//        levelOrder(root);
//        System.out.println(wanquantree(root));
//        fdgPreOder(root);
//        fdginorder(root);
//        fdgPostOrder(root);

    }

}

