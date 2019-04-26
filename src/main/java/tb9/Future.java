package tb9;

public class Future {

    private Product product;

    private boolean done;

    public synchronized void setProduct(Product product){
        if(done){
            return;
        }

        this.product = product;
        this.done = true;
        notifyAll();
    }


    public synchronized Product get(){
        while(!done){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return product;
    }

}
