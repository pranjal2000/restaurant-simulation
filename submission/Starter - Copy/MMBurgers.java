// import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;
// import java.util.TreeMap;
import java.util.Vector;

class customerQue{
    public int size = 0;
    // public int counter_no;
    public Deque<customer> q = new LinkedList<customer>();
};
class heap{
    int size_of_heap=0;
    Vector<customerQue> heap = new Vector<customerQue>(size_of_heap+1);

    public void percolateDown(int i, customerQue cq){
        if(2*i > size_of_heap){
            heap.set(i,cq); 
        }
        else if(2*i==size_of_heap){
            if(heap.get(2*i).size < cq.size){
                heap.set(i, heap.get(2*i));
                heap.set(2*i, cq);
            }else{
                heap.set(i,cq);
            }
        }else if(2*i<size_of_heap){
            int j;
            if(heap.get(2*i).size <= heap.get(2*i+1).size){
                j=2*i;
            }else{
                j=2*i+1;
            }
            if(heap.get(j).size < cq.size){
                heap.set(i, heap.get(j));
                percolateDown(j, cq);
            }
            else{
                heap.set(i, cq);
            }
        }
    }
    public void insert(customer c){
        heap.get(1).q.addLast(c);
        heap.get(1).size++;

        //perculate down
        percolateDown(1, heap.get(1));
    }
    


    
};
class customer{
    int id;
    int order;
    int order_completed=0;
    int counter_no=0;
    int time_front=0;
    int waiting_time=0;
    int arrival_time=0;
    int arrival_no=0;
    customer (int id, int order, int arrival_time){
        this.id=id;
        this.order = order;
        this.arrival_time=arrival_time;
    }

};

class onGrill{
    int id;
    int burgers;
    int arrived_at;
    int counter;
};
// class waitingForGrill{
//     int id;
//     int burgers;
//     int arrived_at;

// };

class grill{
    int size;
    Deque<onGrill> que = new LinkedList<onGrill>(); 
    grill(int m){
        this.size = m;
    }
};

class SortByArrival implements Comparator<customer>{
    // Used for sorting in ascending order of
    // name
    public int compare(customer a, customer b)
    {
        return a.arrival_no -b.arrival_no;
    }
}


public class MMBurgers implements MMBurgersInterface {
    // TreeMap<Integer, customer> tree = new TreeMap<>();
    Deque<onGrill> waiting_for_grill = new LinkedList<onGrill>();
    int k=0;
    int m=0;
    int time = 0;
    Deque<onGrill> after_grill = new LinkedList <onGrill>();
    Vector<customerQue> queues = new Vector<customerQue>(this.k); 
    grill grill = new grill(m);
    // heap heap = new heap();
    // heap.size_of_heap = this.k;
    // customerQue cq = new customerQue();
    
    


    //heap of queues or
    //vector of queues
    AVLTree tree = new AVLTree();

    public void simulation(int t){
        

        while(time<=t){

            if(time==0){
                Vector<customer> v = tree.searchTime(time);
                Collections.sort(v, new SortByArrival());
                System.out.println(v.size());
                if(v!=null){
                    for(int i=0;i<v.size();i++){
                        customer c = v.get(i);
                        int min = Integer.MAX_VALUE;
                        int index=0;
                        
                        for(int j=0;j< queues.size();j++){
                            System.out.println("141");
                            if(queues.get(j)==null)
                            {   
                                customerQue cq = new customerQue();
                                cq.size=1;
                                cq.q=new LinkedList<>();
                                c.counter_no=j+1;
                                cq.q.addLast(c);
                                queues.set(j, cq);
                                break;
                            }
                            if(queues.get(j).size<min){
                                min = queues.get(j).size;
                                
                                index = j;
                            }
                        }
                        if(c.counter_no==0){
                            c.counter_no=index+1;
                    
                            queues.get(index).q.addLast(c);  
                        }
                                            
                        
                    }
                }
            
            }

            
            System.out.println("queues size: "+queues.size());
            // 1. Billing specialist prints an order and sends it to the chef; customer leaves the queue.
            Stack<onGrill> to_wait = new Stack<onGrill>();
            System.out.println("133");
            for(int i=0;i<this.k;i++){
                System.out.println("178");
                if(queues.get(i)==null || queues.get(i).q.size()==0){
                    System.out.println("136");
                    continue;
                }
                if(i==1){
                    System.out.println("i abhi 1 hua");
                }
                if(queues.get(i).q.size()!=0){
                    System.out.println("184 q dead nahi h toh print hoga");
                }
                Iterator<customer> it = queues.get(i).q.iterator();
                while(it.hasNext()){
                    System.out.println(it.next().id + " que id");
                }

                if(queues.get(i).q.getFirst().time_front < queues.get(i).q.getFirst().counter_no){
                    queues.get(i).q.getFirst().time_front++;
                    System.out.println("if time "+time+ " id "+ queues.get(i).q.getFirst().id);
                }
                else if( queues.get(i).q.getFirst().time_front == queues.get(i).q.getFirst().counter_no){
                    onGrill order = new onGrill();
                    order.arrived_at=time;
                    order.burgers= queues.get(i).q.getFirst().order;
                    order.counter = queues.get(i).q.getFirst().counter_no;
                    order.id =queues.get(i).q.getFirst().id;
                    System.out.println("else if time "+time+ " id "+ queues.get(i).q.getFirst().id);
                    // waiting_for_grill.addLast(order);
                    to_wait.push(order);

                    queues.get(i).q.getFirst().counter_no= this.k +1 ;

                    queues.get(i).q.removeFirst();

                }
                
            }
            System.out.println("155");
            while(to_wait.isEmpty()==false){
                System.out.println(time + "stack time  "+ to_wait.peek().id);
                waiting_for_grill.addLast(to_wait.peek()); 
                to_wait.pop();
            }   
            
        
            System.out.println("162");
            
            

            // 2. A cooked patty is removed from the griddle.
            int burgers_on_grill=0;
            Iterator<onGrill> it2 = grill.que.iterator();
                // System.out.println(grill.que.size()+  " 252");
                // prints [3, 2, 1]
                while (it2.hasNext()) {
                    burgers_on_grill+=it2.next().burgers;
                    // System.out.println(it.next());
                    // sum += it.next().burgers;
                }
            System.out.println("burgers on grill "+burgers_on_grill + " at time "+ time);
            if(burgers_on_grill == 0){
                System.out.println("175");
                // Deque<onGrill> q = new LinkedList<>();
                int n=this.m;
                System.out.println("n: "+n );
                Iterator<onGrill> it1 =waiting_for_grill.iterator();
                while(it1.hasNext()){
                    System.out.println(it1.next().id+ " 182 "+ time);
                }


                
                    while(n>0 && waiting_for_grill.isEmpty()==false){
                        if(waiting_for_grill.getFirst().burgers<= n){
                            onGrill o = new onGrill();
                            o.burgers = waiting_for_grill.getFirst().burgers;
                            o.id= waiting_for_grill.getFirst().id;
                            n = n - o.burgers;
                            grill.que.addLast(o);
                            o.arrived_at=time;
                            waiting_for_grill.removeFirst();
                            System.out.println("187");
                        }
                        else{
                            onGrill o = new onGrill();
                            o.burgers = n;
                            o.id= waiting_for_grill.getFirst().id;
                            waiting_for_grill.getFirst().burgers-=n;
                            n = n - o.burgers;
                            o.arrived_at=time;
                            grill.que.addLast(o);
                            System.out.println("195");
                        }
                        
                    }
                
                
                Iterator<onGrill> it = grill.que.iterator();
                System.out.println(grill.que.size()+  " 252");
                // prints [3, 2, 1]
                while (it.hasNext()) {
                    System.out.println(it.next().id +" 212");
                    // sum += it.next().burgers;
                }
                System.out.println("201");

            }else if(burgers_on_grill<this.m){
                System.out.println("line 276 arrived at "+grill.que.getFirst().arrived_at);
                int n1= 0;
                while(grill.que.isEmpty()==false && (grill.que.getFirst().arrived_at+10)==time){
                    //put in after_grill
                    System.out.println("209 bajdadadaldwhsnjaldn");
                    onGrill o1 = new onGrill();
                    o1 = grill.que.getFirst();
                    o1.arrived_at = time;
                    after_grill.addLast(o1);
    
                    //check ki kitne remove hue
                    n1 += grill.que.getFirst().burgers;
                    grill.que.removeFirst();
    
                    //put on grill from waiting list
    
                    //jitne remove hue ho utne hi dalna h
                    // 3. The chef puts another patty on the griddle.
                        
                }
                // while(grill.que.isEmpty()==false && (grill.que.getFirst().arrived_at+10)==time){
                //     //put in after_grill
                //     System.out.println("209 bajdadadaldwhsnjaldn");
                //     onGrill o1 = new onGrill();
                //     o1 = grill.que.getFirst();
                //     o1.arrived_at = time;
                //     after_grill.addLast(o1);
    
                //     //check ki kitne remove hue
                //     int n = n1-grill.que.getFirst().burgers;
                //     grill.que.removeFirst();
    
                //     //put on grill from waiting list
    
                //     //jitne remove hue ho utne hi dalna h
                //     // 3. The chef puts another patty on the griddle.
                    
                //         while(n>0 && waiting_for_grill.isEmpty()==false){
                //             System.out.println("225");
                //             if(waiting_for_grill.getFirst().burgers<= n){
                //                 System.out.println("227");
                //                 onGrill o = new onGrill();
                //                 o.id= waiting_for_grill.getFirst().id;
                //                 o.burgers = waiting_for_grill.getFirst().burgers;
                //                 n = n - o.burgers;
                //                 grill.que.addLast(o);
                //                 o.arrived_at=time;
                //                 waiting_for_grill.removeFirst();
                //             }
                //             else{
                //                 onGrill o = new onGrill();
                //                 System.out.println("236");
                //                 o.burgers = n;
                //                 o.id= waiting_for_grill.getFirst().id;
                //                 waiting_for_grill.getFirst().burgers-=n;
                //                 n = n - o.burgers;
                //                 o.arrived_at=time;
                //                 grill.que.addLast(o);
                //             }
                            
                //         }
    
    
                // }
                
                if(burgers_on_grill - n1<m){
                    int n = m-(burgers_on_grill - n1);
                    while(n>0 && waiting_for_grill.isEmpty()==false){
                        System.out.println("282");
                        if(waiting_for_grill.getFirst().burgers<= n){
                            System.out.println("284");
                            onGrill o = new onGrill();
                            o.id= waiting_for_grill.getFirst().id;
                            o.burgers = waiting_for_grill.getFirst().burgers;
                            n = n - o.burgers;
                            o.arrived_at=time;
                            grill.que.addLast(o);
                            
                            waiting_for_grill.removeFirst();
                        }
                        else{
                            onGrill o = new onGrill();
                            System.out.println("295");
                            o.burgers = n;
                            o.id= waiting_for_grill.getFirst().id;
                            waiting_for_grill.getFirst().burgers-=n;
                            n = n - o.burgers;
                            o.arrived_at=time;
                            grill.que.addLast(o);
                        }
                        
                    }
                }
            }
            else if(burgers_on_grill==this.m){
                System.out.println("207");
                // System.out.println("arrived at "+grill.que.getFirst().arrived_at);
                // int n1= grill.que.size();
                // if(n1<m){
                //     int n = m-n1;
                //     while(n>0 && waiting_for_grill.isEmpty()==false){
                //         System.out.println("282");
                //         if(waiting_for_grill.getFirst().burgers<= n){
                //             System.out.println("284");
                //             onGrill o = new onGrill();
                //             o.id= waiting_for_grill.getFirst().id;
                //             o.burgers = waiting_for_grill.getFirst().burgers;
                //             n = n - o.burgers;
                //             grill.que.addLast(o);
                //             o.arrived_at=time;
                //             waiting_for_grill.removeFirst();
                //         }
                //         else{
                //             onGrill o = new onGrill();
                //             System.out.println("295");
                //             o.burgers = n;
                //             o.id= waiting_for_grill.getFirst().id;
                //             waiting_for_grill.getFirst().burgers-=n;
                //             n = n - o.burgers;
                //             o.arrived_at=time;
                //             grill.que.addLast(o);
                //         }
                        
                //     }
                // }
                int n =0;
                while(grill.que.isEmpty()==false && (grill.que.getFirst().arrived_at+10)==time){
                    //put in after_grill
                    System.out.println("209 bajdadadaldwhsnjaldn");
                    onGrill o1 = new onGrill();
                    o1 = grill.que.getFirst();
                    o1.arrived_at = time;
                    after_grill.addLast(o1);
    
                    //check ki kitne remove hue
                    n += grill.que.getFirst().burgers;
                    grill.que.removeFirst();
    
                    //put on grill from waiting list
    
                    //jitne remove hue ho utne hi dalna h
                    // 3. The chef puts another patty on the griddle.
                        
                }
                while(n>0 && waiting_for_grill.isEmpty()==false){
                    System.out.println("225");
                    if(waiting_for_grill.getFirst().burgers<= n){
                        System.out.println("227");
                        onGrill o = new onGrill();
                        o.id= waiting_for_grill.getFirst().id;
                        o.burgers = waiting_for_grill.getFirst().burgers;
                        n = n - o.burgers;
                        o.arrived_at=time;
                        grill.que.addLast(o);
                        
                        waiting_for_grill.removeFirst();
                    }
                    else{
                        onGrill o = new onGrill();
                        System.out.println("236");
                        o.burgers = n;
                        o.id= waiting_for_grill.getFirst().id;
                        waiting_for_grill.getFirst().burgers-=n;
                        n = n - o.burgers;
                        o.arrived_at=time;
                        grill.que.addLast(o);
                    }
                    
                }
            }
            

            
            // 4. A newly arrived customer joins a queue.
           
            if(time>0){
                Vector<customer> v = tree.searchTime(time);
                // System.out.println(v.size());
                if(v!=null){
                    System.out.println(v.size() + " 315");
                    for(int i=0;i<v.size();i++){
                        customer c = v.get(i);
                        int min = Integer.MAX_VALUE;
                        int index=0;
                        
                        for(int j=0;j< queues.size();j++){
                            System.out.println("221");
                            if(queues.get(j)==null)
                            {   
                                customerQue cq = new customerQue();
                                cq.size=1;
                                cq.q=new LinkedList<>();
                                c.counter_no=j+1;
                                cq.q.addLast(c);
                                queues.set(j, cq);
                                break;
                            }
                            if(queues.get(j).size<min){
                                min = queues.get(j).size;
                                
                                index = j;
                            }
                        }
                        if(c.counter_no==0){
                            c.counter_no=index+1;
                    
                            queues.get(index).q.addLast(c);  
                        }
                    }
                }
            }
            // 5. Cooked burgers are delivered to customers.
            Iterator<onGrill> it3 = after_grill.iterator();
            while(it3.hasNext()){
                int t1=time-1;
                System.out.println("burgers in after_grill at time " +time +" "+it3.next().id);
            }
            System.out.println("362  after grill size check at " + time+ " size is "+ after_grill.size());
            if(after_grill.size()==0){
                System.out.println("364 after grill 0 size");
            }
            while(after_grill.isEmpty()==false && after_grill.getFirst().arrived_at==time-1){
                System.out.println("367");
                onGrill o = new onGrill();
                o = after_grill.getFirst();
                after_grill.removeFirst();
                int id1 = o.id;
                customer cust = tree.search(id1);
                tree.search(id1).order_completed+=o.burgers;
                System.out.println("372");
                System.out.println("452 order comp of id" +id1+" "+ tree.search(id1).order_completed);
                if( tree.search(id1).order_completed ==  cust.order){
                    // System.out.println("452 order comp "+ tree.search(id1).order_completed);
                    tree.search(id1).counter_no = this.k + 2;
                    tree.search(id1).waiting_time = time - cust.arrival_time;
                    System.out.println("375 "+tree.search(id1).waiting_time);
                }
            }
            


            this.time++;
        }
    }



    public boolean isEmpty(){
        //your implementation
        //travel whole tree and check if all customers have k+1
        return tree.allCustomerLeft(this.k+2);

	    // throw new java.lang.UnsupportedOperationException("Not implemented yet.");	
    } 
    
    public void setK(int k1) throws IllegalNumberException{
        //your implementation
        if(k1<0){
            throw new IllegalNumberException("k is not a valid integer");
        }
           
        else{
            this.k = k1;
            queues.setSize(k);
            // heap.size_of_heap = k1;
            
        }
        

	    // throw new java.lang.UnsupportedOperationException("Not implemented yet.");	
    }   
    
    public void setM(int m1) throws IllegalNumberException{
        //your implementation
        if(m1<0){
            throw new IllegalNumberException("m is not a valid integer");
        }
           
        else{
            this.m = m1;
            grill.size=m;
        }
	    // throw new java.lang.UnsupportedOperationException("Not implemented yet.");	
    } 

    public void advanceTime(int t) throws IllegalNumberException{
        //your implementation
        simulation(t);
	    // throw new java.lang.UnsupportedOperationException("Not implemented yet.");	
    } 

    public void arriveCustomer(int id1, int t, int numb) throws IllegalNumberException{
        //your implementation
        if(t<time-1){
            throw new IllegalNumberException("t is not valid");
        }
        if(numb<0){
            throw new IllegalNumberException("numb is not valid");
        }
        if(tree.search(id1) != null){
            throw new IllegalNumberException("Id already exists");
        }
        customer c = new customer(id1, numb, t);
        int x =  tree.searchTime(t)==null? 0:tree.searchTime(t).size();
        c.id=id1;
        c.order=numb;
        c.arrival_time=t;
        c.arrival_no=x+1;
        tree.insert(id1, c);

        
	    // throw new java.lang.UnsupportedOperationException("Not implemented yet.");	
    } 

    public int customerState(int id, int t) throws IllegalNumberException{
        //your implementation
        if(t<time-1){
            throw new IllegalNumberException("t is not valid");
        }
        if(tree.search(id) == null){
            throw new IllegalNumberException("Id invalid");
        }
        // System.out.println(queues.size());
        // customer c = tree.search(1);
        // customer c1 = tree.search(2);
        // customer c2 = tree.search(3);
        // System.out.println(c.id + " " + c1.id + " "+ c2.id);
        tree.inorder();
        // search in tree and return counter_no
        // Vector<customer> v = tree.searchTime(0);
        
        simulation(t);
        System.out.println("customerState at time "+ t +" of id "+ id +" is "+ tree.search(id).counter_no);
        return tree.search(id).counter_no;
	    // throw new java.lang.UnsupportedOperationException("Not implemented yet.");	
    } 

    public int griddleState(int t) throws IllegalNumberException{
        //your implementation
        if(t<time-1){
            throw new IllegalNumberException("t is not valid");
        }
        simulation(t);
        //number of burgers in que of grill
        int sum=0;
        Iterator<onGrill> it = grill.que.iterator();
 
        // prints [3, 2, 1]
        while (it.hasNext()) {
            System.out.println(366);
            sum += it.next().burgers;
        }
        System.out.println("griddleState at time " + t +" is "+ sum);
        return sum;
	    // throw new java.lang.UnsupportedOperationException("Not implemented yet.");	
    } 

    public int griddleWait(int t) throws IllegalNumberException{
        //your implementation
        if(t<time-1){
            throw new IllegalNumberException("t is not valid");
        }
        //call simulation
        simulation(t);
        //no of burgers  in waitign_for_grill
        int sum=0;
        Iterator<onGrill> it = waiting_for_grill.iterator();
 
        // prints [3, 2, 1]
        while (it.hasNext()) {
            sum += it.next().burgers;
        }
        System.out.println("griddleWait at time" + t +" is "+ sum);
        return sum;

	    // throw new java.lang.UnsupportedOperationException("Not implemented yet.");	
    } 

    public int customerWaitTime(int id) throws IllegalNumberException{
        //your implementation
        if(tree.search(id) == null){
            throw new IllegalNumberException("Id invalid");
        }
        //search in tree for customer with id , return waiting_time
        int t=time-1;
        System.out.println("customerWaitTime at time "+ t+" of id "+ id +" is "+ tree.search(id).waiting_time);
        return tree.search(id).waiting_time;
	    // throw new java.lang.UnsupportedOperationException("Not implemented yet.");	
    } 

	public float avgWaitTime(){
        //your implementation
        // poore tree m jaakr avg
        Vector<Integer> ans = new Vector<>();

        ans= tree.sumAllTree();
        if(ans.get(1)==0) return 0;
        int t=time-1;
        System.out.println("avgWaitTime at time " + t +" is "+ (float)ans.get(0)/ans.get(1));
        return (float)ans.get(0)/ans.get(1);
	    // throw new java.lang.UnsupportedOperationException("Not implemented yet.");	
    } 

    
}
