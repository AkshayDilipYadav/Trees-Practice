import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int x){val =x;}
}

class Solution {
    public List<List<Integer>> verticalOrder(TreeNode root){
        List<List<Integer>> result = new ArrayList<>();
        if(root == null){return result;}
        Map<Integer, List<Integer>> columnTable = new HashMap<>();
        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();

        int column = 0;
        queue.offer(new Pair<>(root, column));
        int minColumn = 0, maxColumn = 0;

        while(!queue.isEmpty()){
            Pair<TreeNode, Integer> p = queue.poll();
            root = p.getKey();
            column = p.getValue();
            if(root != null){
                if(!columnTable.containsKey(column)){
                    columnTable.put(column, new ArrayList<>());
                }
                columnTable.get(column).add(root.val);
                minColumn = Math.min(minColumn, column);
                maxColumn = Math.max(maxColumn, column);
                queue.offer(new Pair<>(root.left, column -1));
                queue.offer(new Pair<>(root.right, column + 1));
            }
        }
        for(int i = minColumn; i <= maxColumn; i++){
            result.add(columnTable.get(i));
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
        List<List<Integer>> result = solution.verticalOrder(root);

        System.out.println("Vertical order traversal: ");
        for(List<Integer> level : result){
            for(int val : level){
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
}

class Pair<K, V>{
    private K key;
    private V value;
    public Pair(K key, V value){
        this.key = key;
        this.value = value;
    }
    public K getKey(){
        return key;
    }

    public V getValue(){
        return value;
    }

}