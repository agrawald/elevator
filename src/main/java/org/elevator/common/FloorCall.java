package org.elevator.common;

/**
 * Created by e7006722 on 27/04/2015.
 */
public class FloorCall {
    private int floorNo;
    private boolean up;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FloorCall floorCall = (FloorCall) o;

        if (floorNo != floorCall.floorNo) return false;
        return up == floorCall.up;

    }

    @Override
    public int hashCode() {
        int result = floorNo;
        result = 31 * result + (up ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FloorCall{" +
                "floorNo=" + floorNo +
                ", up=" + up +
                '}';
    }

    public int getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(int floorNo) {
        this.floorNo = floorNo;
    }

    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return !up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public FloorCall(int floorNo, boolean up) {
        this.floorNo = floorNo;
        this.up = up;
    }
}
