public class Main {


    public static void main(String[] args){
        BacktrackingBST myArr = new BacktrackingBST(new Stack(), new Stack());
        myArr.print();
        myArr.insert(new BacktrackingBST.Node(6, null));
        myArr.print();
        myArr.insert(new BacktrackingBST.Node(4, null));
        myArr.print();
        myArr.insert(new BacktrackingBST.Node(10, null));
        myArr.print();
        myArr.insert(new BacktrackingBST.Node(2, null));
        myArr.print();
        myArr.insert(new BacktrackingBST.Node(5, null));
        myArr.print();
        myArr.insert(new BacktrackingBST.Node(8, null));
        myArr.print();
        myArr.insert(new BacktrackingBST.Node(11, null));
        myArr.print();
        myArr.insert(new BacktrackingBST.Node(0, null));
        myArr.print();
        myArr.insert(new BacktrackingBST.Node(1, null));
        myArr.print();
        myArr.insert(new BacktrackingBST.Node(9, null));
        myArr.print();
        myArr.delete(myArr.search(7));
        myArr.print();


//        System.out.println(myArr.minimum().getKey());
//        System.out.println(myArr.maximum().getKey());
//        System.out.println(myArr.search(7).getKey());
    }
}
