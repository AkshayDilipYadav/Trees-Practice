import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

class TreeNode{
    int val;
    TreeNode left, right;

    TreeNode(int x){
        val = x;
        left = right = null;
    }
}

class PostorderTraversal{
    public List<Integer> postorderTraversal(TreeNode root){
        List<Integer> result = new ArrayList<>();
        if(root == null){return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        TreeNode lastVisited = null;
        while(current != null || !stack.isEmpty()){
            if(current != null){
                stack.push(current);
                current = current.left;
            }
            else{
                TreeNode peekNode = stack.peek();
                if(peekNode.right != null && lastVisited != peekNode.right){current = peekNode.right;}
                else{
                    result.add(peekNode.val);
                    lastVisited = stack.pop();
                }
            }
        }
        return result;
    }

    public TreeNode buildTree(Scanner sc){
        System.out.println("Enter value for the root node: ");
        int val = sc.nextInt();
        TreeNode root = new TreeNode(val);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode current = queue.poll();
            System.out.println("Enter left: " + current.val + " enter -1 if none");
            int leftVal = sc.nextInt();
            if(leftVal != -1){
                current.left = new TreeNode(leftVal);
                queue.add(current.left);
            }
            System.out.println("Enter right: " + current.val + " enter -1 if none");
            int rightVal = sc.nextInt();
            if(rightVal != -1){
                current.right = new TreeNode(rightVal);
                queue.add(current.right);
            }
        }
        return root;
    }

    public static void main(String[] args){
        PostorderTraversal obj = new PostorderTraversal();
        Scanner sc = new Scanner(System.in);
        TreeNode root = obj.buildTree(sc);
        List<Integer> result = obj.postorderTraversal(root);
        System.out.println("Postorder Traversal: ");
        for(int val : result){
            System.out.print(val + " ");
        }
    }

}