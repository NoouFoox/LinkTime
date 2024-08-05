package fun.cyclesn.linktime.info;

public class MoveInfo {
    private Double x;
    private Double y;
    private Double z;
    public MoveInfo(Double x, Double y, Double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public boolean equals(MoveInfo moveInfo){
        if(moveInfo==null){
            return false;
        }
        return this.x.equals(moveInfo.getX()) && this.y.equals(moveInfo.getY()) && this.z.equals(moveInfo.getZ());
    }
    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getZ() {
        return z;
    }

    public void setZ(Double z) {
        this.z = z;
    }
}
