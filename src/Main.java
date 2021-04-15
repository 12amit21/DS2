public class Main {
    public static void main(String[] args){
        BacktrackingBST myArr = new BacktrackingBST(new Stack(), new Stack());
        myArr.insert(new BacktrackingBST.Node(10, null));
        myArr.print();
        myArr.insert(new BacktrackingBST.Node(2, null));
        myArr.print();
        myArr.insert(new BacktrackingBST.Node(15, null));
        myArr.print();
        myArr.insert(new BacktrackingBST.Node(1, null));
        myArr.print();
        myArr.insert(new BacktrackingBST.Node(8, null));
        myArr.print();
        myArr.insert(new BacktrackingBST.Node(11, null));
        myArr.print();
        myArr.insert(new BacktrackingBST.Node(17, null));
        myArr.print();
        myArr.insert(new BacktrackingBST.Node(0, null));
        myArr.print();
        myArr.insert(new BacktrackingBST.Node(6, null));
        myArr.print();
        myArr.insert(new BacktrackingBST.Node(9, null));
        myArr.print();
        myArr.insert(new BacktrackingBST.Node(13, null));
        myArr.print();
        myArr.insert(new BacktrackingBST.Node(3, null));
        myArr.print();
        myArr.insert(new BacktrackingBST.Node(7, null));
        myArr.print();
        myArr.insert(new BacktrackingBST.Node(5, null));
        myArr.print();
        myArr.insert(new BacktrackingBST.Node(4, null));
        myArr.print();

        myArr.delete(myArr.search(15));
        myArr.print();
        myArr.delete(myArr.search(10));
        myArr.print();
        myArr.backtrack();
        myArr.print();
        myArr.backtrack();
        //System.out.println(myArr.getRoot().right.getKey());
        myArr.print();
    }
}
