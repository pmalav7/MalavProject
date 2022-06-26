package milestone;

import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Objects;
import java.util.Stack;
import java.util.TreeMap;
import java.util.logging.SocketHandler;

/**
 * This class creates the object of space and implements all the associated methods.
 */

public class SpaceImpl implements SpaceInterface {


  public static Map<String, String> playerRoom;
  public static Map<String, List<String>> playerItem;
  private final List<RoomImpl> rooms;
  private final List<Items> items;
  private Map<String, List<String>> hashMapRoomItems;
  private Map<String, List<String>> hashMapRoomNeighbours;
  private Map<Integer, List<Integer>> hashMapRoomNeighbourIndexed;
  private HumanPlayer hp;


  /**
   * The constructor of the room takes in List of rooms object and list of all items object .
   *
   * @param roomsList The room list contains all the important fields of all the rooms.These fields
   *                  will be used for various functions. These fields are accessed by the getter
   *                  methods of the room class.
   * @param itemsList The items list contains all the important fields of all the items.These fields
   *                  will be used for various functions. These fields are accessed by the getter
   *                  methods of the item class.
   */

  public SpaceImpl(List<RoomImpl> roomsList, List<Items> itemsList) {
    if (roomsList == null || itemsList == null) {
      throw new IllegalArgumentException("Room List or Item List cannot be null");
    }
    rooms = new ArrayList<>(roomsList);
    items = new ArrayList<>(itemsList);
    playerRoom = new HashMap<>();
    playerItem = new HashMap<>();
    hashMapRoomNeighbourIndexed = new HashMap<>();
    checkOverlapNew();
    matchRoomsItems();
    matchRoomNeighbours();
    matchRoomNeighbourIndex();
  }


  //This method returns a list of all the room objects
  @Override
  public List<RoomImpl> getRooms() {
    return new ArrayList<>(rooms);
  }

  @Override
  public List<Items> getItems() {
    return new ArrayList<>(items);
  }

  //This method returns a name of all the rooms inside our world
  @Override
  public String[] getAllRooms() {
    String[] allRooms = new String[rooms.size()];
    for (int i = 0; i < rooms.size(); i++) {
      allRooms[i] = rooms.get(i).getRoomName().trim();
    }
    return allRooms;
  }

  //This method returns a list of all the neighbouring rooms to a specific roomname
  // that is passed as an argument
  @Override
  public List<String> getNeighbours(String roomName) {
    if (roomName == null) {
      throw new IllegalStateException("Room name cannot be null");
    }
    return (hashMapRoomNeighbours.get(roomName));
  }

  //This method prepares a hashmap with roomname as key and its neighbours as values
  //and prints them in form of a string
  private Map<String, List<String>> matchRoomNeighbours() {
    hashMapRoomNeighbours = new HashMap<>();
    for (int i = 0; i < rooms.size(); i++) {
      String roomName = rooms.get(i).getRoomName();
      int leftCoordinate = rooms.get(i).getLeftcoordinate();
      int rightCoordinate = rooms.get(i).getRightcoordinate();
      int bottomCoordinate = rooms.get(i).getBottomcoordinate();
      int topCoordinate = rooms.get(i).getTopcoordinate();
      List<String> it = new ArrayList<>();
      for (int j = 0; j < rooms.size(); j++) {
        if (i == j) {
          continue;
        } else {
          if (rooms.get(j).getBottomcoordinate() == topCoordinate - 1) {
            if (leftCoordinate <= rooms.get(j).getLeftcoordinate()
                    && rooms.get(j).getLeftcoordinate() <= rightCoordinate
                    || leftCoordinate <= rooms.get(j).getRightcoordinate()
                    && rooms.get(j).getRightcoordinate() <= rightCoordinate
                    || leftCoordinate > rooms.get(j).getLeftcoordinate()
                    && rooms.get(j).getRightcoordinate() > rightCoordinate) {
              it.add(rooms.get(j).getRoomName());
            }
          } else if (rooms.get(j).getRightcoordinate() == leftCoordinate - 1) {
            if (topCoordinate <= rooms.get(j).getTopcoordinate()
                    && rooms.get(j).getTopcoordinate() <= bottomCoordinate
                    || topCoordinate <= rooms.get(j).getBottomcoordinate()
                    && rooms.get(j).getTopcoordinate() <= topCoordinate
                    || topCoordinate > rooms.get(j).getTopcoordinate()
                    && rooms.get(i).getBottomcoordinate() > bottomCoordinate) {
              it.add(rooms.get(j).getRoomName());
            }
          } else if (rooms.get(j).getTopcoordinate() == bottomCoordinate + 1) {
            if (leftCoordinate <= rooms.get(j).getLeftcoordinate()
                    && rooms.get(j).getLeftcoordinate() <= rightCoordinate
                    || leftCoordinate <= rooms.get(j).getRightcoordinate()
                    && rooms.get(j).getRightcoordinate() <= rightCoordinate
                    || leftCoordinate > rooms.get(j).getLeftcoordinate()
                    && rooms.get(j).getRightcoordinate() > rightCoordinate) {
              it.add(rooms.get(j).getRoomName());
            }
          } else if (rooms.get(j).getLeftcoordinate() == rightCoordinate + 1) {
            if (topCoordinate <= rooms.get(j).getTopcoordinate()
                    && rooms.get(j).getTopcoordinate() <= bottomCoordinate
                    || topCoordinate <= rooms.get(j).getBottomcoordinate()
                    && rooms.get(j).getTopcoordinate() <= topCoordinate
                    || topCoordinate > rooms.get(j).getTopcoordinate()
                    && rooms.get(j).getBottomcoordinate() > bottomCoordinate) {
              it.add(rooms.get(j).getRoomName());
            }
          }
        }
        hashMapRoomNeighbours.put(roomName, it);
      }
    }
    return hashMapRoomNeighbours;
  }

  private Map<Integer, List<Integer>> matchRoomNeighbourIndex() {
    for (int i = 0; i < rooms.size(); i++) {
      String roomName = rooms.get(i).getRoomName();
      int roomIndex = rooms.get(i).getIndex();
      int leftCoordinate = rooms.get(i).getLeftcoordinate();
      int rightCoordinate = rooms.get(i).getRightcoordinate();
      int bottomCoordinate = rooms.get(i).getBottomcoordinate();
      int topCoordinate = rooms.get(i).getTopcoordinate();
      List<String> it = new ArrayList<>();
      List<Integer> it2 = new ArrayList<>();
      for (int j = 0; j < rooms.size(); j++) {
        if (i == j) {
          continue;
        } else {
          if (rooms.get(j).getBottomcoordinate() == topCoordinate - 1) {
            if (leftCoordinate <= rooms.get(j).getLeftcoordinate()
                    && rooms.get(j).getLeftcoordinate() <= rightCoordinate
                    || leftCoordinate <= rooms.get(j).getRightcoordinate()
                    && rooms.get(j).getRightcoordinate() <= rightCoordinate
                    || leftCoordinate > rooms.get(j).getLeftcoordinate()
                    && rooms.get(j).getRightcoordinate() > rightCoordinate) {
              it.add(rooms.get(j).getRoomName());
              it2.add(rooms.get(j).getIndex());
            }
          } else if (rooms.get(j).getRightcoordinate() == leftCoordinate - 1) {
            if (topCoordinate <= rooms.get(j).getTopcoordinate()
                    && rooms.get(j).getTopcoordinate() <= bottomCoordinate
                    || topCoordinate <= rooms.get(j).getBottomcoordinate()
                    && rooms.get(j).getTopcoordinate() <= topCoordinate
                    || topCoordinate > rooms.get(j).getTopcoordinate()
                    && rooms.get(i).getBottomcoordinate() > bottomCoordinate) {
              it.add(rooms.get(j).getRoomName());
              it2.add(rooms.get(j).getIndex());
            }
          } else if (rooms.get(j).getTopcoordinate() == bottomCoordinate + 1) {
            if (leftCoordinate <= rooms.get(j).getLeftcoordinate()
                    && rooms.get(j).getLeftcoordinate() <= rightCoordinate
                    || leftCoordinate <= rooms.get(j).getRightcoordinate()
                    && rooms.get(j).getRightcoordinate() <= rightCoordinate
                    || leftCoordinate > rooms.get(j).getLeftcoordinate()
                    && rooms.get(j).getRightcoordinate() > rightCoordinate) {
              it.add(rooms.get(j).getRoomName());
              it2.add(rooms.get(j).getIndex());
            }
          } else if (rooms.get(j).getLeftcoordinate() == rightCoordinate + 1) {
            if (topCoordinate <= rooms.get(j).getTopcoordinate()
                    && rooms.get(j).getTopcoordinate() <= bottomCoordinate
                    || topCoordinate <= rooms.get(j).getBottomcoordinate()
                    && rooms.get(j).getTopcoordinate() <= topCoordinate
                    || topCoordinate > rooms.get(j).getTopcoordinate()
                    && rooms.get(j).getBottomcoordinate() > bottomCoordinate) {
              it.add(rooms.get(j).getRoomName());
              it2.add(rooms.get(j).getIndex());
            }
          }
        }
        hashMapRoomNeighbourIndexed.put(roomIndex, it2);
      }
    }
    return hashMapRoomNeighbourIndexed;
  }

  //Returns a copy of hashMapRoomItems
  @Override
  public Map<String, List<String>> clonedRoomItem() {
    return new HashMap<>(hashMapRoomItems);
  }

  //This method prepares a hashmap with roomname as key and its items as values
  //and prints them in form of a string
  private Map<String, List<String>> matchRoomsItems() {
    hashMapRoomItems = new HashMap<>();
    for (RoomImpl room : rooms) {
      List<String> it = new ArrayList<>();
      for (Items item : items) {
        if (room.getIndex() == item.getLocation()) {
          it.add(item.getName());
        }
      }
      hashMapRoomItems.put(room.getRoomName(), it);
    }
    return hashMapRoomItems;
  }

  //This method returns the list of all the items present in a room that has been passed in as a
  // parameter
  @Override
  public List<String> itemsInRoom(String roomName) {
    if (roomName == null) {
      throw new IllegalStateException("Room name cannot be null");
    }

    return (hashMapRoomItems.get(roomName));
  }

  //This method returns the number of items present in a room name that has been passed in as
  // parameter
  @Override
  public int numberOfItemsInRoom(String roomName) {
    if (roomName == null) {
      throw new IllegalStateException("Room name is null");
    }
    List<String> itemsFound = new ArrayList<>();
    int position = 0;
    roomName = roomName.replaceAll("\\s+", "");
    for (RoomImpl room : rooms) {
      if (roomName.equals(room.getRoomName().replaceAll("\\s+", ""))) {
        position = room.getIndex();
      }
    }
    for (Items item : items) {
      if (position == item.getLocation()) {
        itemsFound.add(item.getName());
      }
    }
    return itemsFound.size();
  }

  // This method provide a description about the room name that has been passed in as parameter.
  // It provides information like its neighbours and items present in the room.
  @Override
  public String getRoomDetails(String roomName, HumanPlayer hp, TargetImpl t, PetImpl p) {
    if (roomName == null || !Arrays.asList(getAllRooms()).contains(roomName)
            || t == null || p == null) {
      throw new IllegalStateException("One of the room parameter is null");
    }
    String neighbours = hashMapRoomNeighbours.get(roomName).toString();
    String items = hashMapRoomItems.get(roomName).toString();
    List<String> playerPresent = new ArrayList<>();
    for (String key : playerRoom.keySet()) {
      if (Objects.equals(playerRoom.get(key), roomName)) {
        playerPresent.add(key);
      }
    }
    String playerPresentS = playerPresent.toString();
    int position = t.getLocation();
    int petPosition = p.getLocation();
    for (int i = 0; i < getAllRooms().length - 1; i++) {
      if (roomName.equals(getAllRooms()[i]) && i == position && i != petPosition) {
        StringBuilder sb = new StringBuilder();
        sb.append(" In room named ").append(roomName).append(" there is/are ").append(items)
                .append("present and its neighbour is/are ").append(neighbours)
                .append(" . The players present are ").append(playerPresentS)
                .append("Target ").append(t.getName()).append(" is also here");
        return sb.toString();
      }
    }
    for (int i = 0; i < getAllRooms().length - 1; i++) {
      if (roomName.equals(getAllRooms()[i]) && i == petPosition && i != position) {
        StringBuilder sb = new StringBuilder();
        sb.append(" In room named ").append(roomName).append(" there is/are ").append(items)
                .append("present and its neighbour is/are ").append(neighbours)
                .append(" . The players present are ").append(playerPresentS)
                .append(" . Pet ").append(p.getPetName()).append(" is also here");
        return sb.toString();
      }
    }
    for (int i = 0; i < getAllRooms().length - 1; i++) {
      if (roomName.equals(getAllRooms()[i]) && i == petPosition && i == position) {
        StringBuilder sb = new StringBuilder();
        sb.append(" In room named ").append(roomName).append(" there is/are ").append(items)
                .append("present and its neighbour is/are ").append(neighbours)
                .append(" . The players present are ").append(playerPresentS)
                .append("Target ").append(t.getName()).append(" is also here along with Pet ")
                .append(p.getPetName()).append(" is also here");
        return sb.toString();
      }
    }
    StringBuilder sb = new StringBuilder();
    sb.append(" In room named ").append(roomName).append(" there is/are ").append(items)
            .append("present and its neighbour is/are ").append(neighbours)
            .append(" . The players present are ").append(playerPresentS);
    return sb.toString();
  }

  //This method checks whether any space is overlapping or not. This method is called during
  // the creation of SpaceImpl object.
  private void checkOverlapNew() {
    for (int i = 0; i < rooms.size(); i++) {
      int leftCoordinate = rooms.get(i).getLeftcoordinate();
      int rightCoordinate = rooms.get(i).getRightcoordinate();
      int bottomCoordinate = rooms.get(i).getBottomcoordinate();
      int topCoordinate = rooms.get(i).getTopcoordinate();

      for (int j = 0; j < rooms.size(); j++) {
        if (i == j) {
          continue;
        } else {
          int x = Math.max(leftCoordinate, rooms.get(i).getLeftcoordinate());
          int y = Math.min(rightCoordinate, rooms.get(i).getRightcoordinate());
          int height = y - x;
          int m = Math.max(topCoordinate, rooms.get(i).getTopcoordinate());
          int n = Math.min(bottomCoordinate, rooms.get(i).getBottomcoordinate());
          int width = n - m;
          if (height == 0 || width == 0) {
            System.out.println(x);
            System.out.println(m);
            System.out.println(y);
            System.out.println(n);
            throw new IllegalArgumentException("Spaces are overlapping");
          }
        }
      }
    }
  }


  @Override
  public List<String> dfs(int start) {
    if (start < 0) {
      throw new IllegalStateException("integer can't be less than 0");
    }
    int node = hashMapRoomNeighbourIndexed.size();
    boolean[] visited = new boolean[node];
    ArrayList<ArrayList<Integer>> roadUsed =
            new ArrayList<>();

    for (int i = 0; i < node; i++) {
      visited[i] = false;
    }
    List<Integer> order = new ArrayList<>();

    List<Integer> orderTraversal = dfsUtil(start, node, visited, roadUsed, -1, 0, order);
    List<String> roomMovement = new ArrayList<>();

    for (int j = 0; j < orderTraversal.size(); j++) {
      for (RoomImpl room : getRooms()) {
        if (orderTraversal.get(j) == room.getIndex()) {
          roomMovement.add(room.getRoomName());
        }
      }
    }
    return roomMovement;
  }

  private List<Integer> dfsUtil(int u, int node, boolean[] visited,
                                ArrayList<ArrayList<Integer>> roadUsed,
                                int parent, int it, List<Integer> order) {
    int c = 0;
    for (int i = 0; i < node; i++) {
      if (visited[i]) {
        c++;
      }
    }
    if (c == node) {
      return order;
    }

    visited[u] = true;
    roadUsed.add(new ArrayList<Integer>(Arrays.asList(parent, u)));

    order.add(u);
    for (int x : hashMapRoomNeighbourIndexed.get(u)) {
      if (!visited[x]) {
        dfsUtil(x, node, visited, roadUsed, u, it + 1, order);
      }
    }
    for (int y = 0; y < roadUsed.size(); y++) {
      if (roadUsed.get(y).get(1) == u) {
        dfsUtil(roadUsed.get(y).get(0), node,
                visited, roadUsed, u, it + 1, order);
      }
    }
    return order;
  }
}


