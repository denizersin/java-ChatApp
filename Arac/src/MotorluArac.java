public abstract class MotorluArac {
    int beygirGucu;
    Benzin b;
    public  Motor mm;

    public  MotorluArac(){

        this.arizaKontrol();
        this.b=new Benzin();
        this.mm=new Motor();


    }

    abstract void motorCalistir(int a);
    boolean arizaKontrol(){
        System.out.println("ariza yok");
        return  true;
    }

}
