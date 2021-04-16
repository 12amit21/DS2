public class Main {
    public static void main(String[] args){
        BacktrackingBST myArr = new BacktrackingBST(new Stack(), new Stack());
        myArr.insert(new BacktrackingBST.Node(12, null));
        myArr.print();
        myArr.insert(new BacktrackingBST.Node(6, null));
        myArr.print();
        myArr.insert(new BacktrackingBST.Node(29, null));
        myArr.print();
        myArr.insert(new BacktrackingBST.Node(11, null));
        myArr.print();
        myArr.insert(new BacktrackingBST.Node(13, null));
        myArr.print();
        myArr.insert(new BacktrackingBST.Node(-9, null));
        myArr.print();
        myArr.backtrack();
        myArr.print();
        System.out.println(myArr.minimum().getKey());
        myArr.backtrack();
        myArr.print();
        myArr.backtrack();
        myArr.print();
        myArr.retrack();
        myArr.print();
        System.out.println(myArr.maximum().getKey());
        myArr.retrack();
        myArr.print();
        myArr.insert(new BacktrackingBST.Node(35, null));
        myArr.print();
        myArr.retrack();
        myArr.print();
//        System.out.println(myArr.search(29).getParent().getKey());
//        System.out.println(myArr.search(29).left.getKey());
//        System.out.println(myArr.search(29).right.getKey());
//        System.out.println(myArr.search(30).getParent().getKey());
//        System.out.println(myArr.search(30).left);
//        System.out.println(myArr.search(30).right);
//        System.out.println(myArr.search(33).getParent().getKey());
//        System.out.println(myArr.search(33).left.getKey());
//        System.out.println(myArr.search(33).right.getKey());
//        System.out.println(myArr.search(12).getParent());
//        System.out.println(myArr.search(12).left.getKey());
//        System.out.println(myArr.search(12).right.getKey());
    }
}
