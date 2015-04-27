package org.elevator.common;

/**
 * Created by Dheeraj Agrawal on 26/04/15.
 */
public class FloorCall implements Comparable{
    private Integer floor;
    private Direction direction;

    public FloorCall(Integer floor, Direction direction) {
        this.floor = floor;
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "FloorCall{" +
                "direction=" + direction +
                ", floor=" + floor +
                '}';
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FloorCall floorCall = (FloorCall) o;

        if (floor != null ? !floor.equals(floorCall.floor) : floorCall.floor != null) return false;
        return direction == floorCall.direction;

    }

    @Override
    public int hashCode() {
        int result = floor != null ? floor.hashCode() : 0;
        result = 31 * result + (direction != null ? direction.hashCode() : 0);
        return result;
    }

    public Direction getDirection() {
        return direction;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof FloorCall){
            return floor.compareTo(((FloorCall)o).getFloor());
        }
        return 0;
    }
}