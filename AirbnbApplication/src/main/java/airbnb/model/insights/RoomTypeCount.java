package airbnb.model.insights;

/**
 * The `RoomTypeCount` class represents the count of listings for each room type.
 * It includes details such as the room type and the corresponding count of listings.
 *
 * @author ambikakabra
 */
public class RoomTypeCount {
	private String roomType;
    private int roomTypeCount;
	
    //constructors
    public RoomTypeCount(String roomType, int roomTypeCount) {
		this.roomType = roomType;
		this.roomTypeCount = roomTypeCount;
	}
    
	public RoomTypeCount() {}

	//getters and setters
	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public int getRoomTypeCount() {
		return roomTypeCount;
	}

	public void setRoomTypeCount(int roomTypeCount) {
		this.roomTypeCount = roomTypeCount;
	}

	/**
     * Returns a string representation of the `RoomTypeCount` object.
     *
     * @return A string representation of the `RoomTypeCount` object.
     */
	@Override
    public String toString() {
        return "RoomTypeCount{" +
               "roomType='" + roomType + '\'' +
               ", roomTypeCount=" + roomTypeCount +
               '}';
    }
}
