package BusSystem;

import neko.dsa.graph.Node;

import java.util.List;

public class bus {
    private Passenger[] seatList;
    private Node currentNode;
    int indexOfSeatList = 0;
    OrderedArrayMaxPQ<Passenger> priorityQueue;

    public bus(Node currentNode) {
        this.priorityQueue = new OrderedArrayMaxPQ<>(16);
        seatList = priorityQueue.getPq();
        this.currentNode = currentNode;
    }

    private void printPickupNotification(int passengerId) {
        System.out.println("Bus picked up Passenger " + passengerId);
    }

    // Method to print notifications for moving to a different node
    private void printMoveNotification(int nextNode) {
        System.out.println("Bus moved to Node " + nextNode);
    }

    public void addPassenger(Passenger passenger, OrderedArrayMaxPQ<Passenger> priorityQueue) {

        seatList[indexOfSeatList] = priorityQueue.insert(passenger);
    }

    // MỖI PASSENGER CÓ MỘT PATH RIÊNG, VỚI PASSENGER A THÌ PATH NÀY LÀ KHOẢNG CÁCH TỪ CHỖ A ĐỨNG
    // ĐẾN ĐỊA ĐIỂM A MUỐN ĐẾN. HÀM NÀY GIẢ SỬ XE ĐANG ĐỨNG TẠI VỊ TRÍ KHÁCH ĐẦU TIÊN NÊN PHẢI CÓ
    // HÀM ĐỂ XE DI CHUYỂN TỪ ĐỊA ĐIỂM ĐẦU TIÊN CỦA XE ĐẾN VỊ TRÍ KHÁCH ĐẦU TIÊN.
    public void move(List<Passenger> passengerList) {
        for (int i = 0; i < passengerList.size(); i++)
        {
            Node[] path = passengerList.get(i).getPath();
            for (int j = 0; j < path.length; j++) {
                this.currentNode = path[i];
                if (this.currentNode == passengerList.get(i).getLocation()) {
                    this.addPassenger(passengerList.get(i), this.priorityQueue);
                }
            }
            List<Node> anotherPath = this.moveBetweenNonPath(this.currentNode, passengerList.get(i + 1).getLocation());
            for (int k = 0; k < anotherPath.size(); k++) {
                this.currentNode = anotherPath.get(k);
            }
        }
    }

    public List<Node> moveBetweenNonPath(Node start, Node end) {

    }
}
