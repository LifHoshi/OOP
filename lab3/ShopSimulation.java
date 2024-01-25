import java.util.Scanner;

 
class ShopSimulation extends Simulation {

  private Counter[] counters;

  private Event[] initEvents;

  public ShopSimulation(Scanner sc) {
    initEvents = new Event[sc.nextInt()];
    int numOfCounters = sc.nextInt();
    int p = sc.nextInt();
    int q = sc.nextInt();


    Shop shop = new Shop(numOfCounters, p, q);


    int id = 0;
    while (sc.hasNextDouble()) {
      double arrivalTime = sc.nextDouble();
      double serviceTime = sc.nextDouble();
      Customer CustomerID = new Customer(id,serviceTime);
      initEvents[id] = new ShopArrival( 
          arrivalTime, CustomerID, shop);
      id += 1;
    }
  }

  /**
   * Retrieve an array of events to populate the 
   * simulator with.
   *
   * @return An array of events for the simulator.
   */
  @Override
  public Event[] getInitialEvents() {
    return initEvents;
  }
}
