package study.redis;


public class Vehicle {
    private String vehicleId;//车辆编号
    private String vin;//VIN
    private String plateNo;//车牌号

    public Vehicle(String vehicleId, String vin, String plateNo) {
        this.vehicleId = vehicleId;
        this.vin = vin;
        this.plateNo = plateNo;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }
}
