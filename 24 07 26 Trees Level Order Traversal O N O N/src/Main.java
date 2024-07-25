import java.util.*;

class TreeNode{
    int val;
    TreeNode left, right;

    TreeNode(int x){
        val = x;
    }
}

class Solution{
    public List<List<Integer>> levelOrder(TreeNode root){
        List<List<Integer>> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()){
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();

            for(int i =0; i< levelSize; i++){
                TreeNode currentNode = queue.poll();
                currentLevel.add(currentNode.val);
                if(currentNode.left != null){
                    queue.add(currentNode.left);
                }
                if(currentNode.right != null){
                    queue.add(currentNode.right);
                }
            }
            result.add(currentLevel);
        }
        return result;

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
        List<List<Integer>> result = solution.levelOrder(root);
        System.out.println("Level Order traversal: ");
        for(List<Integer> level : result){
            for(int val : level){
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

}