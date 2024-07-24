import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int x) {
        val = x;
        left = right = null;
    }
}

class PreorderTraversal {

    public void preorderTraversal(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        result.add(root.val);
        preorderTraversal(root.left, result);
        preorderTraversal(root.right, result);
    }

    public TreeNode buildTree(Scanner sc) {
        System.out.println("Enter value for root node: ");
        int val = sc.nextInt();
        TreeNode root = new TreeNode(val);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            System.out.println("Enter left child of " + current.val + " (enter -1 if no child): ");
            int leftVal = sc.nextInt();
            if (leftVal != -1) {
                current.left = new TreeNode(leftVal);
                queue.add(current.left);
            }
            System.out.println("Enter right child of " + current.val + " (enter -1 if no child): ");
            int rightVal = sc.nextInt();
            if (rightVal != -1) {
                current.right = new TreeNode(rightVal);
                queue.add(current.right);
            }
        }
        return root;
    }

    public static void main(String[] args) {
        PreorderTraversal obj = new PreorderTraversal();
        Scanner sc = new Scanner(System.in);
        TreeNode root = obj.buildTree(sc);

        List<Integer> result = new ArrayList<>();
        obj.preorderTraversal(root, result);

        System.out.println("Preorder Traversal: ");
        for (int val : result) {
            System.out.print(val + " ");
        }

    }
}
