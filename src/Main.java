public class Main {
    public static void main(String[] args) {
        BacktrackingBST myArr = new BacktrackingBST(new Stack(), new Stack());
        myArr.insert(new BacktrackingBST.Node(1, null));
        myArr.print();
        myArr.insert(new BacktrackingBST.Node(2, null));
        myArr.print();
        myArr.backtrack();
        myArr.print();
        myArr.retrack();
        myArr.print();
        myArr.backtrack();
        myArr.print();
        myArr.retrack();
        myArr.print();
        myArr.retrack();
        myArr.print();
        myArr.backtrack();
        myArr.print();
        myArr.backtrack();
        myArr.print();

    }
}
