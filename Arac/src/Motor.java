public class Motor {
    int motorHacmi;
    DisliSeti ds;
    public  Motor(){
        this.motorHacmi=1400;
        ds=new DisliSeti();
        ds.calistir();
    }
    void calistir(){
        System.out.println("disli seti calisti");
    }
    class DisliSeti{
        void calistir(){}
    }
}
