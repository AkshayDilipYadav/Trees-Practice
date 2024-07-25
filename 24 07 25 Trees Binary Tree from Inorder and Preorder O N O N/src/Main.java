import java.util.*;

class TreeNode{
    int val;
    TreeNode left, right;
    TreeNode(int x){
        val = x;
    }
}

class Solution {
    private Map<Integer, Integer> inorderIndexMap;
    private int preorderIndex;

    public TreeNode buildTree(int[] preorder, int[] inorder){
        inorderIndexMap = new HashMap<>();
        preorderIndex = 0;

        for(int i = 0; i< inorder.length; i++){
            inorderIndexMap.put(inorder[i], i);
        }
        return buildTreeHelper(preorder, 0, inorder.length -1);
    }

    private TreeNode buildTreeHelper(int[] preorder, int left, int right) {
        if(left > right){
            return null;
        }
        int rootVal = preorder[preorderIndex++];
        TreeNode root = new TreeNode(rootVal);
        int inorderIndex = inorderIndexMap.get(rootVal);
        root.left = buildTreeHelper(preorder, left, inorderIndex -1);
        root.right = buildTreeHelper(preorder, inorderIndex + 1, right);
        return root;
    }
    public void printTree(TreeNode root){
        if(root == null){
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()){
            int levelSize = queue.size();
            for(int i =0; i< levelSize; i++){
                TreeNode node = queue.poll();
                System.out.print(node.val + " ");
                if(node.left != null){
                    queue.add(node.left);
                }
                if(node.right != null){queue.add(node.right);}
            }
            System.out.println();
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of nodes: ");
        int n = sc.nextInt();

        int[] preorder = new int[n];
        System.out.print("Enter thr preorder traversal: ");
        for(int i = 0; i< n; i++){
            preorder[i] = sc.nextInt();
        }

        int[] inorder = new int[n];
        System.out.print("Enter the inorder traversal : " );
        for(int i = 0; i< n; i++){
            inorder[i] = sc.nextInt();
        }

        Solution solution = new Solution();
        TreeNode root = solution.buildTree(preorder, inorder);
        System.out.println("Level order traversal: ");
        solution.printTree(root);

    }

}