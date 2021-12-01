public class Tester2 {
    public static void main(String[] args){
        MMBurgersInterface mm = new MMBurgers();
        System.out.println("\n--Started simulation--");

        // Set number of counters and griddle capacity
        try{
            mm.setK(2);
            mm.setM(8);
            //System.out.println("Complete 1");
        }
        catch(IllegalNumberException e){
            System.out.println(e);
        }

        // t = 0
        try{
            // Customer 1 arrives
            mm.arriveCustomer(1, 0, 5);
            //System.out.println("Complete 2.1");
            // Customer 2 arrives
            mm.arriveCustomer(2, 0, 6);
            //System.out.println("Complete 2.1");
        }
        catch(IllegalNumberException e){
            System.out.println(e);
        }

        // t = 1
        try{
            // Customer 3 arrives
            mm.arriveCustomer(3, 1, 4);
            //System.out.println("Complete 3.1");
            // Customer 4 arrives
            mm.arriveCustomer(4, 1, 5);
            //System.out.println("Complete 3.2");
            // Query customer state
            mm.customerState(3, 1);
            //System.out.println("Complete 3.3");
            // Query customer state
            mm.customerState(4, 1);
            //System.out.println("Complete 3.4");
            // Query griddle state
            mm.griddleState(1);
            //System.out.println("Complete 3.5");
        }
        catch(IllegalNumberException e){
            System.out.println(e);
        }

        // t = 5
        try{
            // Query griddle state
            mm.griddleState(5);
            //System.out.println("Complete 34");
        }
        catch(IllegalNumberException e){
            System.out.println(e);
        }

        // t = 6
        try{
            // Query customer state
            mm.customerState(2, 6);
            //System.out.println("Complete 5.1");
            // Query griddle state
            mm.griddleState(6);
            //System.out.println("Complete 5.2");
        }
        catch(IllegalNumberException e){
            System.out.println(e);
        }

        // t = 12
        try{
            // Query griddle state
            mm.griddleWait(12);
            //System.out.println("Complete 6");
        }
        catch(IllegalNumberException e){
            System.out.println(e);
        }

        // t = 25
        try{
            // Query griddle state
            mm.griddleState(25);
            //System.out.println("Complete 7");
        }
        catch(IllegalNumberException e){
            System.out.println(e);
        }

         // t = 32
         try{
            // Query griddle state
            mm.advanceTime(32);
            //System.out.println("Complete 8.1");
            mm.isEmpty();
            //System.out.println(mm.isEmpty());
            //System.out.println("Complete 8.2");
        }
        catch(IllegalNumberException e){
            System.out.println(e);
        }

        // End of simulation
        System.out.println("\n--End of simulation--");
        mm.isEmpty();
        //System.out.println(mm.isEmpty());
        //System.out.println("Complete 8.3");

        // Query wait times
        try{
            mm.customerWaitTime(1);
            //System.out.println("Complete 9.1");
            mm.customerWaitTime(2);
            //System.out.println("Complete 9.2");
            mm.customerWaitTime(3);
            //System.out.println("Complete 9.3");
            mm.customerWaitTime(4);
            //System.out.println("Complete 9.4");
            mm.avgWaitTime();
            //System.out.println("Complete 9.5");
        }
        catch(IllegalNumberException e){
            System.out.println(e);
        }

    }
}
