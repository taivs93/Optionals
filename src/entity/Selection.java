package entity;

public enum Selection {
    ADD(1,"Thêm user."),
    FINDBYID(2,"Tìm user theo ID."),
    FINDBYCITY(3,"Tìm tất cả người dùng trong thành phố cụ thể."),
    FINDBYEMAIL(4,"Tìm tất cả người dùng theo email domain cụ thể."),
    EXIT(5,"Thoát.");
    private int value;
    private String description;

    Selection(int value, String description){
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public static Selection getSelectionByValue(int value){
        for (Selection selection : Selection.values()){
            if(selection.getValue() == value) return selection;
        }
        throw new IllegalArgumentException("Lựa chọn ko hợp lệ!");
    }
}
