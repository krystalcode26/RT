module com.yuhsuan.day1 {
  requires javafx.controls;
  requires javafx.fxml;


  opens com.yuhsuan.day1 to javafx.fxml;
  exports com.yuhsuan.day1;
}