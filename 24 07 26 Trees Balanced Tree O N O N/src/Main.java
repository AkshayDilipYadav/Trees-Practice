import java.util.*;

class TreeNode{
    int val;
    TreeNode left, right;

    TreeNode(int x){
        val = x;
    }
}

class Solution{
    public boolean isBalanced(TreeNode root){
        return checkHeightAndBalance(root).isBalanced;
    }

    private TreeInfo checkHeightAndBalance(TreeNode node){
        if(node == null){return new TreeInfo(0, true);}
        TreeInfo left = checkHeightAndBalance(node.left);
        TreeInfo right = checkHeightAndBalance(node.right);

        boolean isBalanced = left.isBalanced && right.isBalanced && Math.abs(left.height - right.height) <= 1;
        int height = Math.max(left.height, right.height)+ 1;
        return new TreeInfo(height, isBalanced);
    }
    private static class TreeInfo{
        int height;
        boolean isBalanced;
        TreeInfo(int height, boolean isBalanced){
            this.height = height;
            this.isBalanced = isBalanced;
        }
    }

    public static TreeNode createTree(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of nodes: ");
        int n = sc.nextInt();
        if(n == 0){return null;}
        System.out.println("Enter the value of the root node: ");
        TreeNode root = new TreeNode(sc.nextInt());
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        for(int i = 1; i < n; i++){
            TreeNode current = queue.poll();
            System.out.println("Enter left of : " + current.val + " enter -1 if none");
            int leftVal = sc.nextInt();
            if(leftVal != -1){
                current.left = new TreeNode(leftVal);
                queue.add(current.left);
            }

            System.out.println("Enter right of : " + current.val + " enter -1 if none");
            int rightVal = sc.nextInt();
            if(rightVal != -1){
                current.right = new TreeNode(rightVal);
                queue.add(current.right);
            }
        }
        return root;
    }
    public static void main(String[] args){
        Solution solution = new Solution();
        TreeNode root = createTree();
        boolean result = solution.isBalanced(root);
        System.out.println("The tree is " + (result ? "height-balanced" : "not height-balanced"));
    }
}