package objects;

public class Transection {
    private String SenderId;
    private String ReceiverId;
    private String TransectionId;
    private Integer SecurityCode;

    public Transection(String TransectionId, String SenderId, String ReceiverId, Integer SecurityCode){
        this.TransectionId = TransectionId;
        this.SenderId = SenderId;
        this.ReceiverId = ReceiverId;
        this.SecurityCode = SecurityCode;
    }

    public String getTransectionId(){
        return TransectionId;
    }

    public String getSenderId(){
        return SenderId;
    }

    public String getReceiverId(){
        return ReceiverId;
    }
}
