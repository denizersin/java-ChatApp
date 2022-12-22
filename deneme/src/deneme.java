public class deneme {

    public static void main(String[] args) {
        System.out.println("asd");
        A[] dizi=new A[3];
        dizi[0]=new B();
        dizi[1]=new B();
        dizi[2]=new B();

        for (A item:dizi){
            item.hello();
        }
    }

}
