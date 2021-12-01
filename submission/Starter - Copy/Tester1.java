public class Tester1 {
    public static void main(String[] args){
        MMBurgersInterface mm = new MMBurgers();
        System.out.println("\n--Started simulation--");

        // Set number of counters and griddle capacity
        try{
            mm.setK(3);
            mm.setM(6);

            System.out.println("Complete 1");
        }
        catch(IllegalNumberException e){
            System.out.println(e);
        }

        // t = 0
        try{
            // Customer 1 arrives
            mm.arriveCustomer(1, 0, 3);
            // Customer 2 arrives
            mm.arriveCustomer(2, 0, 4);
            // Customer 3 arrives
            mm.arriveCustomer(3, 0, 5);
            System.out.println("Complete 2");
        }
        catch(IllegalNumberException e){
            System.out.println(e);
        }

        // t = 1
        try{
            // Query customer state
            mm.customerState(2, 1);
            System.out.println("Complete 3.1");
            // Query griddle state
            mm.griddleState(1);
            System.out.println("Complete 3.2");
            mm.griddleWait(1);
            System.out.println("Complete 3.3");
        }
        catch(IllegalNumberException e){
            System.out.println(e);
        }

        // t = 2
        try{
            // Query griddle state
            mm.griddleState(2);
            System.out.println("Complete 4" );
        }
        catch(IllegalNumberException e){
            System.out.println(e);
        }

        // t = 3
        try{
            // Query customer state
            mm.customerState(1, 3);
            System.out.println("Complete 5");
        }
        catch(IllegalNumberException e){
            System.out.println(e);
        }

        // t = 7
        try{
            // Query customer state
            mm.customerState(2, 7);
            System.out.println("Complete 6");
        }
        catch(IllegalNumberException e){
            System.out.println(e);
        }

        // t = 10
        try{
            // Query griddle wait time
            mm.griddleWait(10);
            System.out.println("Complete 7");
        }
        catch(IllegalNumberException e){
            System.out.println(e);
        }

        // t = 14
        try{
            // Query griddle state
            mm.griddleState(14);
            System.out.println("Complete 8");
        }
        catch(IllegalNumberException e){
            System.out.println(e);
        }

        // t = 21
        try{
            // Query griddle state
            mm.griddleState(21);
            System.out.println(mm.isEmpty());
            System.out.println("Complete 9");
        }
        catch(IllegalNumberException e){
            System.out.println(e);
        }

        // t = 23
        try{
            // Advance time
            mm.advanceTime(23);
            // mm.customerState(2, 25);
            
            System.out.println(mm.isEmpty());
            System.out.println("Complete 10");
        }
        catch(IllegalNumberException e){
            System.out.println(e);
        }

        // End of simulation
        System.out.println("\n--End of simulation--");
        

        // Query wait times
        try{
            mm.customerWaitTime(1);
            mm.customerWaitTime(2);
            mm.customerWaitTime(3);
            mm.avgWaitTime();
            System.out.println("Complete 11");
        }
        catch(IllegalNumberException e){
            System.out.println(e);
        }

    }
}
