package Test5_Thread;

class Conductor{
    //boolean isFree = true;
    public int Five = 1;
    public int Ten = 0;
    public int Twenty = 0;
}

class Tourist extends Thread{
    private int five;
    private int ten;
    private int twenty;
    private int ticket;
    private Conductor conductor;
    public Tourist(String name, int five, int ten, int twenty, int ticket, Conductor conductor){
        setName(name);
        this.five = five;
        this.ten = ten;
        this.twenty = twenty;
        this.ticket = ticket;
        this.conductor = conductor;
    }

    public synchronized void Check(){
        System.out.println("Five: "+conductor.Five);
        System.out.println("Ten: "+conductor.Ten);
        System.out.println("Twenty: "+conductor.Twenty);
    }


    public synchronized boolean Change() {
        if(ticket==1){
            if(five==1){
                conductor.Five++;
                return true;
            }
            if(ten==1){
                if(conductor.Five>=1){
                    conductor.Five--;
                    conductor.Ten++;
                    return true;
                }else{
                    return  false;
                }
            }
            if(twenty==1){
                if(conductor.Five>=1&&conductor.Ten>=1){
                    conductor.Twenty++;
                    conductor.Ten--;
                    conductor.Five--;
                    return true;
                }else{
                    return false;
                }
            }

        }
        if(ticket==2){
            if(ten==1){
                conductor.Ten++;
                return true;
            }
            if(twenty==1){
                if(conductor.Ten>=1){
                    conductor.Ten++;
                    return true;
                }else {
                    return false;
                }
            }
        }
        return false;
    }

    @Override
    public void run() {
        while(!Change()){
            System.out.println(getName()+" comes too early!!!");
            yield();
        }
        System.out.println(getName()+" has bought successfully!!!!");
        //Check();


    }
}

public class ThreadTest3 {
    public static void main(String[] args) {
        Conductor conductor = new Conductor();
        new Tourist("zhao",0,0,1,2,conductor).start();
        new Tourist("qian",0,0,1,1,conductor).start();
        new Tourist("sun",0,1,0,1,conductor).start();
        new Tourist("li",0,1,0,2,conductor).start();
        new Tourist("zhou",1,0,0,1,conductor).start();

    }



}
